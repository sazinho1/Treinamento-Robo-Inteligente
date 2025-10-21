package simulacoes;

import ambiente.AreaDeJogo;
import excecoes.*;
import io.ConsoleVisualizador;
import robos.*;


public class SimulacaoInteligente {
    private final RoboNormal robo1;
    private final RoboInteligente robo2;
    private final AreaDeJogo area;
    private final ConsoleVisualizador visualizador;

    public SimulacaoInteligente(RoboNormal r1, RoboInteligente r2, AreaDeJogo area, ConsoleVisualizador visualizador){
        this.robo1 = r1;
        this.robo2 = r2;
        this.area = area;
        this.visualizador = visualizador;
    }


    public void iniciarSimulacao() {
        visualizador.imprimirPosicao(robo1.getX(), robo1.getY(), robo1.getCor());
        visualizador.imprimirPosicao(robo2.getX(), robo2.getY(), robo2.getCor());

            // Loop continua enquanto um OU outro NÃO achou o alimento
            while (!robo1.isAchouAlimento() || !robo2.isAchouAlimento()) {
                
                // Turno do Robô Normal
                if (!robo1.isAchouAlimento()) {
                    try {
                        ((RoboNormal)robo1).moverAleatorio(); 
                        // Cast é necessário se a var for do tipo Robo

                        /*(Cast é tipo garantir pro compilador que a variavel
                         "robo1" é do tipo "RoboNormal", pra poder usar o metodo
                          moverAleatorio sem ter risco de não compilar ou dar erro.)*/

                        if (robo1.verificarEcontroAlimento(area)) {
                            visualizador.imprimirAchouAlimento(robo1.getCor());
                        }

                    } catch (MovimentoInvalidoException e) {
                        ConsoleVisualizador.imprimirErroCoordenadaInvalida(e);
                    }
                }

                // Turno do Robô Inteligente
                if (!robo2.isAchouAlimento()) {
                    // NÃO precisa de try-catch aqui, pois o novo moverAleatorio() trata os erros!
                    ((RoboInteligente)robo2).moverAleatorio();
                    if (robo2.verificarEcontroAlimento(area)) {
                        visualizador.imprimirAchouAlimento(robo2.getCor());
                    }
                }

                // ---------> Chamar o visualizador da Matriz 4x4 aqui <---------
                // visualizador.imprimirMatriz(robo1, robo2, area, obstaculos);
                
                try { Thread.sleep(1500); } catch (InterruptedException e) { /* ... */ }
            }

        //Mostra os movimentos válidos e inválidos ao final.
        visualizador.imprimirConjuntoCaracteres();
        visualizador.imprimirJogadasValidas(robo1.getCor(), robo1.getJogadasValidas());
        visualizador.imprimirJogadasInvalidas(robo1.getCor(), robo1.getJogadasInvalidas());
        visualizador.imprimirJogadasValidas(robo2.getCor(), robo2.getJogadasValidas());
        visualizador.imprimirJogadasInvalidas(robo2.getCor(), robo2.getJogadasInvalidas());

        }
    }