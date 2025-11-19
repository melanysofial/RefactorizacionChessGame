package com.directi.training.codesmells.smelly.chess;

import com.directi.training.codesmells.smelly.Position;
import java.util.Scanner;

public class ConsoleInputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public Position askPosition(String message) {
        System.out.print(message);
        int row = scanner.nextInt() - 1;
        int col = scanner.nextInt() - 1;
        return new Position(row, col);
    }
}
