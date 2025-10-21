package robos;

import excecoes.MovimentoInvalidoException;
import io.ConsoleVisualizador;
import java.util.Random;

public class RoboInteligente extends Robo {
    private String ultimoMovimentoInvalido = null;
    private final Random random;

    public RoboInteligente(String cor, ConsoleVisualizador visualizador) {
        super(cor, visualizador);
        this.random = new Random();
    }

    @Override
    public void mover(String direction) throws MovimentoInvalidoException {
        try {
            super.mover(direction);

            if (direction.toLowerCase().equals(this.ultimoMovimentoInvalido)) {
                this.ultimoMovimentoInvalido = null;
            }

        } catch(MovimentoInvalidoException e) {

            if(e.getMessage().toLowerCase().contains("coordenada negativa") || e.getMessage().toLowerCase().contains("excede a área")) {
                this.ultimoMovimentoInvalido = direction.toLowerCase();
            }
            
            throw e;
        }
    }

    @Override
    public void moverAleatorio() throws MovimentoInvalidoException {
        String movimentoEscolhido;
        String[] movimentosValidos = {"up", "down", "right", "left"};
        
        do {
            int index = random.nextInt(movimentosValidos.length);
            movimentoEscolhido = movimentosValidos[index];
            
        } while(movimentoEscolhido.equals(this.ultimoMovimentoInvalido)); 

        mover(movimentoEscolhido);
    }
}