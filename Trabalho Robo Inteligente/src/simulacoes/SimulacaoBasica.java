package simulacoes;

import excecoes.MovimentoInvalidoException;
import io.ConsoleReceptor;
import io.ConsoleVisualizador;
import robos.RoboNormal;

public class SimulacaoBasica {
    private RoboNormal robo;
    private AreaDeJogo area;
    private ConsoleReceptor receptor;
    private ConsoleVisualizador visualizador;

    public SimulacaoBasica(RoboNormal robo, AreaDeJogo area, ConsoleReceptor receptor, ConsoleVisualizador visualizador) {
        this.robo = robo;
        this.area = area;
        this.receptor = receptor;
        this.visualizador = visualizador;
    }

    public void iniciarSimulacao() {
        visualizador.imprimirPosicao(robo.getX(), robo.getY(), robo.getCor());

        do { 
            visualizador.mover();
            String mover = receptor.mover();

            try {
                int movimentoInt = Integer.parseInt(mover); 
                robo.mover(movimentoInt);
    
            } catch(NumberFormatException e) {
                try {
                    robo.mover(mover);

                } catch(MovimentoInvalidoException ex) {
                    System.err.println(ex.getMessage());
            }
    
            } catch(MovimentoInvalidoException e) {
                System.err.println(e.getMessage());
            }

            if (robo.verificarEcontroAlimento(area)) {
                visualizador.imprimirAchouAlimento(robo.getCor());
            }

            try {
                Thread.sleep(1000); 
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        } while (!robo.verificarEcontroAlimento(area));
    }
}