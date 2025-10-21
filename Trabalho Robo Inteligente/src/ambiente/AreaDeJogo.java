package ambiente;

import excecoes.CoordenadaInvalidaException;

public class AreaDeJogo {
    private static final int TAMANHO_AREA = 4;
    private int xAlimento;
    private int yAlimento;
    
    public AreaDeJogo(int x, int y) throws CoordenadaInvalidaException {
        if(x < 0 || TAMANHO_AREA <= x || y < 0 || TAMANHO_AREA <= y) {
            throw new CoordenadaInvalidaException(x, y);
        }

        this.xAlimento = x;
        this.yAlimento = y;
    }
    
    public static int getTAMANHO_AREA() {
        return TAMANHO_AREA;
    }

    public int getxAlimento() {
        return xAlimento;
    }

    public int getyAlimento() {
        return yAlimento;
    }

    
}