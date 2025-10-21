package principal;

import excecoes.*;
import io.*;
import robos.*;
import simulacoes.AreaDeJogo;
import simulacoes.SimulacaoBasica;

public class Main1 {
    public static void main(String[] args) {
        AreaDeJogo area = null;
        ConsoleReceptor receptor = new ConsoleReceptor();
        ConsoleVisualizador visualizador = new ConsoleVisualizador();
        RoboNormal robo = new RoboNormal("azul", visualizador);
        
        try {
            visualizador.determinarPosicaoXDoAlimento();
            int xAlimento = receptor.determinarPosicaoXDoAlimento();
        
            visualizador.determinarPosicaoYDoAlimento();
            int yAlimento = receptor.determinarPosicaoYDoAlimento();

            area = new AreaDeJogo(xAlimento, yAlimento);
        
        } catch (CoordenadaInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        if (area == null) {
            visualizador.encerrarProgramaSemFruta();
            return;
        }

        SimulacaoBasica simulador = new SimulacaoBasica(robo, area, receptor, visualizador);
        simulador.iniciarSimulacao();
    }
}