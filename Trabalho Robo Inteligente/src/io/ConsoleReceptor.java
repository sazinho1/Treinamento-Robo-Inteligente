package io;

import java.util.Scanner;

public class ConsoleReceptor {
    Scanner scanner = new Scanner(System.in);
    
    public int determinarPosicaoXDoAlimento() {
        int x = scanner.nextInt();
        scanner.nextLine();
        return x;
    }

    public int determinarPosicaoYDoAlimento() {
        int y = scanner.nextInt();
        scanner.nextLine();
        return y;
    }

    public String mover() {
        return scanner.nextLine();
    }
}