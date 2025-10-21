package principal;

import ambiente.AreaDeJogo;
import excecoes.CoordenadaInvalidaException;
import io.ConsoleReceptor;
import io.ConsoleVisualizador;
import java.util.ArrayList;
import robos.*;
import simulacoes.SimulacaoComObstaculo;

public class Main4 {
    public static void main(String[] args) throws CoordenadaInvalidaException{
        AreaDeJogo area = null;
        ConsoleReceptor receptor = new ConsoleReceptor();
        ConsoleVisualizador visualizador = new ConsoleVisualizador();
        RoboNormal robo1 = new RoboNormal("azul", visualizador);
        RoboInteligente robo2 = new RoboInteligente("vermelho", visualizador);
        ArrayList obstaculos = new ArrayList<>();
        visualizador.boasVindas();

        SimulacaoComObstaculo simulacao = new SimulacaoComObstaculo(robo1, robo2, receptor, visualizador);

        boolean setupOk = simulacao.prepararSimulacao();

        if (setupOk) {
            simulacao.iniciarSimulacao();
        } else {
            visualizador.encerrarProgramaSemFruta();
        }
    }     
}
