package io;

import ambiente.AreaDeJogo;
import java.util.ArrayList;
import obstaculos.Obstaculo;
import robos.Robo;

public class ConsoleVisualizador {

    //Cores que serão usadas
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CINZA = "\u001B[90m";
    public static final String ANSI_VERDE_BRILHANTE = "\u001B[1;32m";
    public static final String ANSI_AZUL_BRILHANTE = "\u001B[1;34m";
    public static final String ANSI_VERMELHO_BRILHANTE = "\u001B[1;31m";
    public static final String ANSI_AMARELO_BRILHANTE = "\u001B[1;33m";
    public static final String ANSI_PRETO_FUNDO_CINZA = "\u001B[47;30m";
    
    public void boasVindas(){
        System.out.println("------------------SEJA BEM VINDO AO PROGRAMA DE TESTE DE ROBÔS DE POO!------------------\n");
    }
    
    public void imprimirConjuntoCaracteres(){
        System.out.println("------------------------------------------------------");
    }
    
    public void imprimirPosicao(int x, int y, String cor) {
        System.out.println("Robô (" + cor + ") moveu para (" + x + ", " + y + ")\n");
    }

    public void imprimirAchouAlimento(String cor) {
        System.out.println("Robô (" + cor + ") achou o alimento!\n");
    }

    public void determinarPosicaoXDoAlimento() {
        System.out.println("Digite a posição \"x\" do alimento: (com  0 =< x =< 3)");
    }

    public void determinarPosicaoYDoAlimento() {
        System.out.println("Digite a posição \"y\" do alimento: (com  0 =< y =< 3)");
    }

    public void mover() {
        System.out.println("Para mover robô escolha um comando:\n1) up\n2) down\n3) right\n4) left\n");
    }

    public void encerrarProgramaSemFruta() {
        System.out.println("Não foi possível iniciar o jogo: coordenadas inválidas.");
    }

    public void imprimirErroGenerico(String mensagemErro) {
        System.err.println(mensagemErro);
    }

    public static void imprimirErroInputInvalido() {
        System.err.println(ANSI_VERMELHO_BRILHANTE + "Erro: Entrada inválida. Por favor, digite um número inteiro." + ANSI_RESET);
    }

    public static void imprimirErroPosicaoOcupada(Exception e){
        System.err.println(ANSI_VERMELHO_BRILHANTE + "Erro: " + e.getMessage() + ANSI_RESET);
    }

    public void imprimirMovimentoInvalido(String corRobo, String mensagemErro) {
        System.err.println(ANSI_VERMELHO_BRILHANTE + "Robô " + corRobo + " (Tentativa inválida): " + mensagemErro + ANSI_RESET);
    }

    public void imprimirJogadasValidas(String cor, int jogadas) {
        System.out.println("Robô (" + cor + ") fez: " + jogadas + " jogadas válidas.\n");
    }

    public void imprimirJogadasInvalidas(String cor, int jogadas) {
        System.out.println("Robô (" + cor + ") fez: " + jogadas + " jogadas inválidas.\n");
    }

    public static void imprimirErroCoordenadaInvalida(Exception e){
        System.out.println("Erro: " + e.getMessage());
    }

    public void imprimirEscolherObstaculos(){
        System.out.println("Quantos obstáculos serão adicionados? (Máx 9)");
    }

    public static void ImprimirNumObstaculosInvalido(){
        System.out.println("Erro: Número de obstáculos inválido. Tente Novamente");
    }

    public static void ImprimirBombaOuRocha(int i){
        System.out.println("\n--- Obstáculo " + i + " ---");
        System.out.println("Digite 'B' para Bomba ou 'R' para Rocha:");
    }

    public void ImprimirPosicaoObstaculo(String eixo) {
    System.out.print("Digite a posição \"" + eixo + "\" do obstáculo (0-3): ");
    }

    public void ImprimirRochaAdicionada(int x, int y){
        System.out.println("Rocha adicionada em (" + x + ", " + y + ")");
    }

    public void ImprimirBombaAdicionada(int x, int y){
        System.out.println("Bomba adicionada em (" + x + ", " + y + ")");
    }

    public void ImprimirTipoOBstaculoInvalido(){
        System.out.println("Tipo inválido. Tente novamente (use 'B' ou 'R').\n");
    }

    public void imprimirCoordenadaInvalidaObj(int i){
        System.out.println("Coordenadas inválidas para o obejto " + (i+1) + ".\n");
    }

    public void imprimirExplodiu(String cor){
        System.out.println("O robô " + cor + " explodiu. \n");
    }

    public void imprimirAmbosExplodiram() {
        System.out.println(ANSI_VERMELHO_BRILHANTE + "AMBOS OS ROBÔS EXPLODIRAM! Fim de jogo." + ANSI_RESET);
        System.out.println(); //Quebra a linha
    }

    public void imprimirBateuRocha(String cor){
        System.out.println("O robô " + cor + " bateu em uma rocha. \n");
    }


    public void imprimirMatriz(Robo r1, Robo r2, AreaDeJogo area, ArrayList<Obstaculo> obstaculos) {
        int tamanho = AreaDeJogo.getTAMANHO_AREA(); 
        String[][] matriz = new String[tamanho][tamanho];

        //Preenche o fundo com "." cinza
        for (int y = 0; y < tamanho; y++) {
            for (int x = 0; x < tamanho; x++) {
                matriz[y][x] = ANSI_CINZA + "." + ANSI_RESET;
            }
        }

        matriz[area.getyAlimento()][area.getxAlimento()] = ANSI_VERDE_BRILHANTE + "A" + ANSI_RESET;

        for (Obstaculo obs : obstaculos) {
            String id = obs.getId();
            if (id.equals("B")) {
                matriz[obs.getY()][obs.getX()] = ANSI_AMARELO_BRILHANTE + "B" + ANSI_RESET; // Bomba
            } else {
                matriz[obs.getY()][obs.getX()] = ANSI_PRETO_FUNDO_CINZA + "R" + ANSI_RESET; // Rocha
            }
        }

        //Coloca os Robôs (por último, para sobrepor)
        // Robô 1 (Normal / Azul)
        if (r1 != null && !r1.isExplodido()) {
            matriz[r1.getY()][r1.getX()] = ANSI_AZUL_BRILHANTE + "1" + ANSI_RESET;
        }
        
        // Robô 2 (Inteligente / Vermelho)
        if (r2 != null && !r2.isExplodido()) {
            // Se o Robô 1 já estava lá, mostra '2' por cima (ele tem a vez mais recente)
            matriz[r2.getY()][r2.getX()] = ANSI_VERMELHO_BRILHANTE + "2" + ANSI_RESET;
        }

        // 5. Imprime a matriz no console
        // (Imprimimos de y=3 até y=0 para o (0,0) ficar no canto inferior esquerdo)
        System.out.println("\n--- TABULEIRO [A=Alimento, 1=Normal, 2=Inteligente] ---");
        for (int y = tamanho - 1; y >= 0; y--) {
            System.out.print(y + " | "); // Imprime a coordenada Y
            for (int x = 0; x < tamanho; x++) {
                System.out.print(matriz[y][x] + " "); // Imprime tudo
            }
            System.out.println(); // Quebra a linha
        }
        System.out.println("  +----------");
        System.out.println("    0 1 2 3 "); // Imprime as coordenadas X
        System.out.println();// Quebra a linha
    }

    public void imprimirFimDeSimulacao() {
        System.out.println(ANSI_CINZA + "\n--- FIM DA SIMULAÇÃO ---" + ANSI_RESET);
    }
}
