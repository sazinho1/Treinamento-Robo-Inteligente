package simulacoes;

import ambiente.AreaDeJogo;
import excecoes.MovimentoInvalidoException;
import io.ConsoleReceptor;
import io.ConsoleVisualizador;
import robos.RoboNormal;

public class SimulacaoBasica {
    private final RoboNormal robo;
    private final AreaDeJogo area;
    private final ConsoleReceptor receptor;
    private final ConsoleVisualizador visualizador;

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
                    visualizador.imprimirErroGenerico(ex.getMessage());
            }
    
            } catch(MovimentoInvalidoException e) {
                visualizador.imprimirErroGenerico(e.getMessage());
            }

            if (robo.verificarEcontroAlimento(area)) {
                visualizador.imprimirAchouAlimento(robo.getCor());
            }

            try {
                Thread.sleep(1500); 
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        } while (!robo.verificarEcontroAlimento(area));
    }
}