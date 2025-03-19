package com.example.renju.ui;

import com.example.renju.validator.WinChecker;
import com.example.renju.validator.WinResult;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

@Component
public class ConsoleInterface {
    private static final String TEST_CASES_PATH = "testcases";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Main Menu:");
            System.out.println("1. Check games");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();
            if (choice.equals("2")) {
                System.out.println("Exiting...");
                break;
            } else if (choice.equals("1")) {
                checkGames(scanner);
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }

    private static void checkGames(Scanner scanner) {
        while (true) {
            System.out.print("Enter number of cases (1-11) or 'back' to return: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("back")) {
                return;
            }

            try {
                int cases = Integer.parseInt(input);
                if (cases < 1 || cases > 11) {
                    System.out.println("Invalid number. Please enter a value between 1 and 11.");
                    continue;
                }

                for (int i = 1; i <= cases; i++) {
                    File file = new File(TEST_CASES_PATH + "/" + i + ".txt");
                    if (!file.exists()) {
                        System.out.println("Test case file not found: " + file.getName());
                        continue;
                    }

                    int[][] board = readBoardFromFile(file);
                    WinResult result = WinChecker.checkWin(board);
                    printResult(result);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static int[][] readBoardFromFile(File file) {
        int[][] board = new int[19][19];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null && row < 19) {
                String[] values = line.split(",");
                for (int col = 0; col < Math.min(values.length, 19); col++) {
                    board[row][col] = Integer.parseInt(values[col].trim());
                }
                row++;
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + file.getName());
        }
        return board;
    }

    private static void printResult(WinResult result) {
        int winner = result.getWinner();
        System.out.println("~~~~~\n" + winner);
        if (winner != 0) {
            System.out.println(result.getRow() + " " + result.getCol());
        }
    }
}
