package io;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleReceptor {
    Scanner scanner = new Scanner(System.in);
    
    public int determinarPosicaoX() {
        while (true) { //Loop Infinito
            try {
                int x = scanner.nextInt(); // Tenta ler o número
                scanner.nextLine(); // Limpa o buffer do scanner
                return x; //Retorna se for sucesso
            } catch (InputMismatchException e) {
                //Se falhar (não for um número)
                ConsoleVisualizador.imprimirErroInputInvalido(); //Avisa o usuário
                scanner.nextLine(); //Limpa o input inválido (ex: "abc") do buffer
            }
        }
    }

    public int determinarPosicaoY() {
        while (true) { // Loop infinito
            try {
                int y = scanner.nextInt();
                scanner.nextLine();
                return y; // Retorna se for sucesso
            } catch (InputMismatchException e) {
                ConsoleVisualizador.imprimirErroInputInvalido();
                scanner.nextLine(); // Limpa o buffer
            }
        }
    }

    public int determinarNumeroObstaculos() {
        while(true){
            try { 
                int num = scanner.nextInt();
                scanner.nextLine();
                if (num > 9 || num < 0){
                    ConsoleVisualizador.ImprimirNumObstaculosInvalido();
                }
                else { 
                    return num;
                }
            } catch (InputMismatchException e) {
                ConsoleVisualizador.imprimirErroInputInvalido();
                scanner.nextLine(); // Limpa o buffer
            }
        }
    }

    public String receberTipoObstaculo() {
        return scanner.nextLine();
    }


    public String mover() {
        return scanner.nextLine();
    }
}