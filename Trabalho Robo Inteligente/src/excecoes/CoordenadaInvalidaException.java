package excecoes;

public class CoordenadaInvalidaException extends Exception {
    public CoordenadaInvalidaException(int x, int y) {
        super("Coordenada inválida (" + x + " " + y + "), valores só vão de 0 a 3.");
    }
}