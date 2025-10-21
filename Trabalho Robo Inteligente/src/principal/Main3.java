package principal;

import ambiente.AreaDeJogo;
import excecoes.CoordenadaInvalidaException;
import io.*;
import robos.*;
import simulacoes.SimulacaoInteligente;

public class Main3 {
    public static void main(String[] args) {
        AreaDeJogo area = null;
        ConsoleReceptor receptor = new ConsoleReceptor();
        ConsoleVisualizador visualizador = new ConsoleVisualizador();
        RoboNormal robo1 = new RoboNormal("azul", visualizador);
        RoboInteligente robo2 = new RoboInteligente("vermelho", visualizador);
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

        SimulacaoInteligente simulador = new SimulacaoInteligente(robo1, robo2, area, visualizador);
        simulador.iniciarSimulacao();
    }
}
