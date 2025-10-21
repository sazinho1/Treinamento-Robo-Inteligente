package excecoes;

public class MovimentoInvalidoException extends Exception{
    public MovimentoInvalidoException() {
        super("Movimento inválido.");
    }
    
    private static String getMensagemFinal(String movimento) {
        if (movimento.startsWith("Comando desconhecido: ")) {
            return movimento; 
        } else {
            return "O movimento '" + movimento + "' é inválido, pois resulta em coordenada negativa ou excede a área de jogo.";
        }
    }

    public MovimentoInvalidoException(String movimento) {
        super(getMensagemFinal(movimento)); 
    }

    public MovimentoInvalidoException(int movimento) {
        super("O movimento '" + movimento + "' é inválido, pois resulta em coordenada negativa (x < 0 ou y < 0) ou excede a área de jogo (x < 4 ou y < 4).");
    }
}