package simulacoes;

import ambiente.AreaDeJogo;
import excecoes.MovimentoInvalidoException;
import io.ConsoleVisualizador;
import robos.RoboNormal;

public class SimulacaoAleatoria {
    private final RoboNormal robo1;
    private final RoboNormal robo2;
    private final AreaDeJogo area;
    private final ConsoleVisualizador visualizador;

    public SimulacaoAleatoria(RoboNormal r1, RoboNormal r2, AreaDeJogo area, ConsoleVisualizador visualizador) {
        this.robo1 = r1;
        this.robo2 = r2;
        this.area = area;
        this.visualizador = visualizador;
    }

    public void iniciarSimulacao() {
        visualizador.imprimirPosicao(robo1.getX(), robo1.getY(), robo1.getCor());
        visualizador.imprimirPosicao(robo2.getX(), robo2.getY(), robo2.getCor());
        
        while(true) {
            boolean robo1Achou = false;

            try {
                robo1.moverAleatorio(); 
                
                if(robo1.verificarEcontroAlimento(area)) { 
                    visualizador.imprimirAchouAlimento(robo1.getCor());
                    robo1Achou = true;
                }

            } catch(MovimentoInvalidoException e) {
                visualizador.imprimirMovimentoInvalido(robo1.getCor(), e.getMessage());
            }

            if(robo1Achou) break;

            boolean robo2Achou = false;

            try {
                robo2.moverAleatorio();
                
                if(robo2.verificarEcontroAlimento(area)) { 
                    visualizador.imprimirAchouAlimento(robo2.getCor());
                    robo2Achou = true;
                }

            } catch(MovimentoInvalidoException e) {
               visualizador.imprimirMovimentoInvalido(robo2.getCor(), e.getMessage());;
            }

            if(robo2Achou)break;
            
            try {
                Thread.sleep(1500); 
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } 

        visualizador.imprimirJogadasValidas(robo1.getCor(), robo1.getJogadasValidas());
        visualizador.imprimirJogadasInvalidas(robo1.getCor(), robo1.getJogadasInvalidas());
        visualizador.imprimirJogadasValidas(robo2.getCor(), robo2.getJogadasValidas());
        visualizador.imprimirJogadasInvalidas(robo2.getCor(), robo2.getJogadasInvalidas());
    }
}