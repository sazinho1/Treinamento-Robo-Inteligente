package io;

public class ConsoleVisualizador {
    public void imprimirPosicao(int x, int y, String cor) {
        System.out.println("Robô (" + cor + ") moveu para (" + x + ", " + y + ")\n");
    }

    public void imprimirAchouAlimento(String cor) {
        System.out.println("Robô (" + cor + ") achou o alimento!\n");
    }

    public void determinarPosicaoXDoAlimento() {
        System.out.println("Digite a posição \"x\" do alimento: ");
    }

    public void determinarPosicaoYDoAlimento() {
        System.out.println("Digite a posição \"y\" do alimento: ");
    }

    public void mover() {
        System.out.println("Para mover robô escolha um comando:\n1) up\n2) down\n3) right\n4) left\n");
    }

    public void encerrarProgramaSemFruta() {
        System.out.println("Não foi possível iniciar o jogo: coordenadas inválidas.");
    }

    public void imprimirJogadasValidas(String cor, int jogadas) {
        System.out.println("Robô (" + cor + ") fez: " + jogadas + " jogadas válidas.");
    }

    public void imprimirJogadasInvalidas(String cor, int jogadas) {
        System.out.println("Robô (" + cor + ") fez: " + jogadas + " jogadas inválidas.");
    }

    public static void imprimirErroCoordenadaInvalida(Exception e){
        System.out.println("Erro: " + e.getMessage());
    }
}