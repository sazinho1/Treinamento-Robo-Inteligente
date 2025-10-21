package simulacoes;

import ambiente.*;
import excecoes.*;
import io.*;
import java.util.ArrayList;
import java.util.Iterator;
import obstaculos.*;
import robos.*; // Importante para remover as bombas

public class SimulacaoComObstaculo {
    private final Robo roboNormal;
    private final Robo roboInteligente;
    private AreaDeJogo area;
    private final ArrayList<Obstaculo> obstaculos;
    private final ConsoleReceptor receptor;
    private final ConsoleVisualizador visualizador;

    public SimulacaoComObstaculo(Robo r1, Robo r2, ConsoleReceptor rec, ConsoleVisualizador vis) {
        this.roboNormal = r1;
        this.roboInteligente = r2;
        this.receptor = rec;
        this.visualizador = vis;
        this.obstaculos = new ArrayList<>(); // A lista começa vazia
        this.area = null; // A área ainda não foi definida
    }

    /*  Método público para configurar o ambiente do jogo (Obstáculos e Alimento).
        Retorna true se o setup foi OK, ou false se o usuário deu coordenada inválida para o alimento.*/

    public boolean prepararSimulacao() {
        visualizador.imprimirEscolherObstaculos();
        int numObstaculos = receptor.determinarNumeroObstaculos();
        
        prepararObstaculos(numObstaculos);

        visualizador.determinarPosicaoXDoAlimento();
        int xAlimento = receptor.determinarPosicaoX(); 
    
        visualizador.determinarPosicaoYDoAlimento();
        int yAlimento = receptor.determinarPosicaoY(); 

        try {
            this.area = new AreaDeJogo(xAlimento, yAlimento);
            return true; // Sucesso
        } catch (CoordenadaInvalidaException e) {
            ConsoleVisualizador.imprimirErroCoordenadaInvalida(e);
            return false; // Falha
        }
    }


    private void prepararObstaculos(int numObstaculos) {
        for (int i = 0; i < numObstaculos; i++) {
            try {
                ConsoleVisualizador.ImprimirBombaOuRocha(i + 1);
                String tipo = receptor.receberTipoObstaculo();

                visualizador.ImprimirPosicaoObstaculo("x");
                int x = receptor.determinarPosicaoX();

                visualizador.ImprimirPosicaoObstaculo("y");
                int y = receptor.determinarPosicaoY();

                if (x < 0 || x >= AreaDeJogo.getTAMANHO_AREA() || y < 0 || y >= AreaDeJogo.getTAMANHO_AREA()) {
                    throw new CoordenadaInvalidaException(x, y);
                }

                if (x == area.getxAlimento() && y == area.getyAlimento()) {
                throw new PosicaoOcupadaException("o alimento");
                }
                if (x == 0 && y == 0) {
                    throw new PosicaoOcupadaException("a posição inicial (0,0)");
                }
                for (Obstaculo obsExistente : this.obstaculos) {
                    if (obsExistente.getX() == x && obsExistente.getY() == y) {
                        throw new PosicaoOcupadaException("outro obstáculo");
                    }
                }

                if (tipo.equalsIgnoreCase("B")) {
                    this.obstaculos.add(new Bomba(x, y));
                    visualizador.ImprimirBombaAdicionada(x, y);
                } else if (tipo.equalsIgnoreCase("R")) {
                    this.obstaculos.add(new Rocha(x, y));
                    visualizador.ImprimirRochaAdicionada(x, y);
                } else {
                    visualizador.ImprimirTipoOBstaculoInvalido();
                    i--;
                }

            } catch (CoordenadaInvalidaException e) {
                ConsoleVisualizador.imprimirErroCoordenadaInvalida(e);
                visualizador.imprimirCoordenadaInvalidaObj(i);
                i--; // Repete a iteração
            }

             catch (PosicaoOcupadaException e) {
            ConsoleVisualizador.imprimirErroPosicaoOcupada(e);
            visualizador.imprimirCoordenadaInvalidaObj(i);
            i--;
            }
        }
    }


    public void iniciarSimulacao() {
        visualizador.boasVindas();

        visualizador.imprimirMatriz(roboNormal, roboInteligente, area, obstaculos);

        // Loop principal
        while ((!roboNormal.isAchouAlimento() && !roboInteligente.isAchouAlimento()) && 
               (!roboNormal.isExplodido() || !roboInteligente.isExplodido())) {

            processarTurno(roboNormal);
            processarTurno(roboInteligente);


            visualizador.imprimirMatriz(roboNormal, roboInteligente, area, obstaculos);

            try { Thread.sleep(1500); } catch (InterruptedException e) { }

            // Condição de parada caso os dois explodam
            if (roboNormal.isExplodido() && roboInteligente.isExplodido()) {
                visualizador.imprimirAmbosExplodiram();
                break;
            }
        }

        visualizador.imprimirFimDeSimulacao();
        visualizador.imprimirJogadasValidas(roboNormal.getCor(), roboNormal.getJogadasValidas());
        visualizador.imprimirJogadasInvalidas(roboNormal.getCor(), roboNormal.getJogadasInvalidas());
        visualizador.imprimirJogadasValidas(roboInteligente.getCor(), roboInteligente.getJogadasValidas());
        visualizador.imprimirJogadasInvalidas(roboInteligente.getCor(), roboInteligente.getJogadasInvalidas());
    }


    private void processarTurno(Robo robo) {
        // Se o robô já explodiu ou já achou a comida, ele não joga.
        if (robo.isExplodido() || robo.isAchouAlimento()) {
            return;
        }

        try {
            if (robo instanceof RoboNormal) {
                ((RoboNormal) robo).moverAleatorio();
            } else if (robo instanceof RoboInteligente) {
                ((RoboInteligente) robo).moverAleatorio(); // (Lembrando que este não lança exceção)
            }
        } catch (MovimentoInvalidoException e) {
            visualizador.imprimirMovimentoInvalido(robo.getCor(), e.getMessage());
        }

        // Só checa colisão se o robô não explodiu *neste turno* (caso de moverAleatorio do Normal)
        if (!robo.isExplodido()) {
            verificarColisao(robo);
        }

        // Só checa alimento se não explodiu após checar colisão
        if (!robo.isExplodido() && robo.verificarEcontroAlimento(area)) {
            visualizador.imprimirAchouAlimento(robo.getCor());
        }
    }

    private void verificarColisao(Robo robo) {
        Iterator<Obstaculo> iter = this.obstaculos.iterator();

        while (iter.hasNext()) {
            Obstaculo obs = iter.next();

            if (robo.getX() == obs.getX() && robo.getY() == obs.getY()) {
                // COLISÃO!
                obs.bater(robo); // Polimorfismo: chama bater() da Bomba ou Rocha

                if (obs instanceof Bomba && robo.isExplodido()) {
                    iter.remove(); // Remove a bomba do jogo
                    visualizador.imprimirExplodiu(robo.getCor());
                } else if (obs instanceof Rocha) {
                    visualizador.imprimirBateuRocha(robo.getCor());
                }
                break; // Só pode colidir com 1 obstáculo por turno
            }
        }
    }
}