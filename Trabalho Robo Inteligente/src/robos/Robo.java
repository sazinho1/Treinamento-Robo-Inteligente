package robos;

import ambiente.AreaDeJogo;
import excecoes.MovimentoInvalidoException;
import io.ConsoleVisualizador;

public abstract class Robo {
    protected  String cor;
    protected int x;
    protected int y;
    protected int jogadasValidas;
    protected int jogadasInvalidas;
    private boolean achouAlimento;
    protected int prevX; 
    protected int prevY; 
    protected boolean explodido; 
    private ConsoleVisualizador visualizador;

    public Robo(String cor, ConsoleVisualizador visualizador) {
        this.cor = cor;
        this.x = 0;
        this.y = 0;
        jogadasValidas = 0;
        jogadasInvalidas = 0;
        this.achouAlimento = false;
        this.visualizador = visualizador;
        this.prevX = 0;
        this.prevY = 0;
        this.explodido = false; 
    }
    
    public String getCor() {
        return cor;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getJogadasValidas() {
        return jogadasValidas;
    }

    public int getJogadasInvalidas() {
        return jogadasInvalidas;
    }

    public boolean isAchouAlimento() {
        return achouAlimento;
    }

    public boolean isExplodido(){
        return this.explodido;
    }

    public void explodirRobo(){
        this.explodido = true;
    }
    
    public void voltarPosicaoAnterior(){
        this.x = prevX;
        this.y = prevY;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setAchouAlimento(boolean achouAlimento) {
        this.achouAlimento = achouAlimento;
    }

    public void processarMovimento(int novoX, int novoY, String comando) throws MovimentoInvalidoException{
        if (novoX < 0 || novoY < 0 || AreaDeJogo.getTAMANHO_AREA() <= novoX || AreaDeJogo.getTAMANHO_AREA() <= novoY) {
            jogadasInvalidas++;
            try {
                int num = Integer.parseInt(comando);
                throw new MovimentoInvalidoException(num);
            } catch(NumberFormatException e) {
                throw new MovimentoInvalidoException(comando);
            }
        }

        this.x = novoX;
        this.y = novoY;
        jogadasValidas++; 
        visualizador.imprimirPosicao(this.x, this.y, this.cor);
    }

    public void mover(String direction) throws MovimentoInvalidoException{
        this.prevX = this.x;
        this.prevY = this.y;
        //salva a posição atual do x e do y
        
        int novoX = this.x;
        int novoY = this.y;
        
        switch(direction.toLowerCase()) {
            case "up" -> novoY++;
            case "down" -> novoY--; 
            case "right" -> novoX++;
            case "left" -> novoX--;
            default -> throw new MovimentoInvalidoException("Comando desconhecido: " + direction);
        }

        processarMovimento(novoX, novoY, direction);
    }

    public void mover(int num) throws MovimentoInvalidoException {
        this.prevX = this.x;
        this.prevY = this.y;
        //salva a posição atual do x e do y

        int novoX = this.x;
        int novoY = this.y;
        
        switch(num) {
            case 1 -> novoY++;
            case 2 -> novoY--; 
            case 3 -> novoX++;
            case 4 -> novoX--;
            default -> throw new MovimentoInvalidoException("Comando desconhecido: " + num);
        }

        processarMovimento(novoX, novoY, String.valueOf(num));
    }

    public abstract void moverAleatorio() throws MovimentoInvalidoException;

    public boolean verificarEcontroAlimento(AreaDeJogo area) {
        boolean encontrou = this.x == area.getxAlimento() && this.y == area.getyAlimento();
        setAchouAlimento(encontrou);
        return encontrou;
    }

    public boolean verificarEcontroAlimento() {
        return isAchouAlimento();
    }
}

