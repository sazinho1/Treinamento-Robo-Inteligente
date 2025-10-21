package excecoes;

public class PosicaoOcupadaException extends Exception {
    public PosicaoOcupadaException(String item) {
        super("Posição inválida: já existe " + item + " nesta coordenada.");
    }
}
