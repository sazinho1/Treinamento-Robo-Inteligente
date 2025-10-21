package io;

import java.util.Scanner;

public class ConsoleReceptor {
    Scanner scanner = new Scanner(System.in);
    
    public int determinarPosicaoX() {
        int x = scanner.nextInt();
        scanner.nextLine();
        return x;
    }

    public int determinarPosicaoY() {
        int y = scanner.nextInt();
        scanner.nextLine();
        return y;
    }

    public int determinarNumeroObstaculos() {
        while(true){
            int num = scanner.nextInt();
            scanner.nextLine();
            if (num > 9 || num < 0){
                ConsoleVisualizador.ImprimirNumObstaculosInvalido();
            }
            else { return num; }
        }
    }

    public String receberTipoObstaculo() {
        return scanner.nextLine();
    }


    public String mover() {
        return scanner.nextLine();
    }
}