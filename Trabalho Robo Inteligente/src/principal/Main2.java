package principal;

import ambiente.AreaDeJogo;
import excecoes.CoordenadaInvalidaException;
import io.ConsoleReceptor;
import io.ConsoleVisualizador;
import robos.RoboNormal;
import simulacoes.SimulacaoAleatoria;

public class Main2 {
    public static void main(String[] args) {
        AreaDeJogo area = null;
        ConsoleReceptor receptor = new ConsoleReceptor();
        ConsoleVisualizador visualizador = new ConsoleVisualizador();
        RoboNormal robo1 = new RoboNormal("azul", visualizador);
        RoboNormal robo2 = new RoboNormal("vermelho", visualizador);
        visualizador.boasVindas();

        try {
            visualizador.determinarPosicaoXDoAlimento();
            int xAlimento = receptor.determinarPosicaoX();
        
            visualizador.determinarPosicaoYDoAlimento();
            int yAlimento = receptor.determinarPosicaoY();

            area = new AreaDeJogo(xAlimento, yAlimento);
        
        } catch(CoordenadaInvalidaException e) {
            ConsoleVisualizador.imprimirErroCoordenadaInvalida(e);
        }

        if(area == null) {
            visualizador.encerrarProgramaSemFruta();
            return;
        }

        SimulacaoAleatoria simulador = new SimulacaoAleatoria(robo1, robo2, area, visualizador);
        simulador.iniciarSimulacao();
    }
}
