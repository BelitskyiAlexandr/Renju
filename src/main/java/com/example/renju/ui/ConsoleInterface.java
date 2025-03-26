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
    private static final int BOARD_SIZE = 19;
    private static final int MIN_CASES = 1;
    private static final int MAX_CASES = 11;

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
                if (cases < MIN_CASES || cases > MAX_CASES) {
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
                    if (board == null) {
                        System.out.println("Skipping file due to invalid data: " + file.getName());
                        continue;
                    }

                    WinResult result = WinChecker.checkWin(board);
                    printResult(result);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static int[][] readBoardFromFile(File file) {
        int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null && row < BOARD_SIZE) {
                String[] values = line.split(",");
                if (values.length != BOARD_SIZE) {
                    System.out.println("Error: Invalid number of columns in file: " + file.getName());
                    return null;
                }

                for (int col = 0; col < BOARD_SIZE; col++) {
                    try {
                        int value = Integer.parseInt(values[col].trim());
                        if (value < 0 || value > 2) {
                            System.out.println("Error: Invalid value at (" + row + ", " + col + ") in file: " + file.getName());
                            return null;
                        }
                        board[row][col] = value;
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid value at (" + row + ", " + col + ") in file: " + file.getName());
                        return null;
                    }
                }
                row++;
            }

            if (row != BOARD_SIZE) {
                System.out.println("Error: Invalid number of rows in file: " + file.getName());
                return null;
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + file.getName() + " - " + e.getMessage());
            return null;
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
