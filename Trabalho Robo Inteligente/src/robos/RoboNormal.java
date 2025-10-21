package robos;

import excecoes.MovimentoInvalidoException;
import io.ConsoleVisualizador;
import java.util.Random;

public class RoboNormal extends Robo {

    private final Random random;

    public RoboNormal(String cor, ConsoleVisualizador visualizador) {
        super(cor, visualizador);
        this.random = new Random();
    }

    @Override
    public void moverAleatorio() throws MovimentoInvalidoException{
        int movimento = random.nextInt(1, 5);
        mover(movimento);
    }
}