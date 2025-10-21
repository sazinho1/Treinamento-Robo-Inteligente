package principal;

import ambiente.AreaDeJogo;
import excecoes.*;
import io.*;
import robos.*;
import simulacoes.SimulacaoBasica;

public class Main1 {
    public static void main(String[] args) {
        AreaDeJogo area = null;
        ConsoleReceptor receptor = new ConsoleReceptor();
        ConsoleVisualizador visualizador = new ConsoleVisualizador();
        RoboNormal robo = new RoboNormal("azul", visualizador);
        visualizador.boasVindas();
        
        try {
            visualizador.determinarPosicaoXDoAlimento();
            int xAlimento = receptor.determinarPosicaoX();
        
            visualizador.determinarPosicaoYDoAlimento();
            int yAlimento = receptor.determinarPosicaoY();

            area = new AreaDeJogo(xAlimento, yAlimento);
        
        } catch (CoordenadaInvalidaException e) {
            ConsoleVisualizador.imprimirErroCoordenadaInvalida(e);
        }

        if (area == null) {
            visualizador.encerrarProgramaSemFruta();
            return;
        }

        SimulacaoBasica simulador = new SimulacaoBasica(robo, area, receptor, visualizador);
        simulador.iniciarSimulacao();
    }
}