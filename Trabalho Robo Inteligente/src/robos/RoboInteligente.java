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
    public void moverAleatorio() { 
        String[] movimentosValidos = {"up", "down", "right", "left"};
        
        // Loop infinito que só será quebrado quando um movimento válido for feito
        while (true) {
            String movimentoEscolhido;
            
            // Lógica para escolher um movimento que NÃO seja o último inválido
            do {
                int index = random.nextInt(movimentosValidos.length);
                movimentoEscolhido = movimentosValidos[index];
            } while (movimentoEscolhido.equals(this.ultimoMovimentoInvalido)); 

            try {
                // Tenta mover
                super.mover(movimentoEscolhido);
                
                // Se super.mover() NÃO lançou exceção, o movimento foi bom
                this.ultimoMovimentoInvalido = null; // Limpa o erro
                break; // Sai do loop 'while(true)' pq conseguiu mover

            } catch (MovimentoInvalidoException e) {
                // O movimento escolhido foi inválido
                // Guarda o movimento que falhou
                this.ultimoMovimentoInvalido = movimentoEscolhido.toLowerCase();
                
                // NÃO lança a exceção. O loop 'while(true)' vai rodar de novo e tentar outro movimento
            }
        }
    }
}