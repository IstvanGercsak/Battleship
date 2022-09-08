package org.example;

import java.util.Scanner;

public class Battleship {

    static class Table {
        int x_wide = 11;
        int y_height = 11;
        int countShipX = 17;
        String[][] table = new String[x_wide + 1][y_height + 1];

    }

    static class Ship {

        String name;
        int length;
        String position;

        public Ship(String name, int length) {
            this.name = name;
            this.length = length;
        }

    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Table gameTable = new Table();
        Table gameFogTable = new Table();
        Ship aircraftCarrier = new Ship("Aircraft Carrier", 5);
        Ship battleship = new Ship("Battleship", 4);
        Ship submarine = new Ship("Submarine", 3);
        Ship cruiser = new Ship("Cruiser", 3);
        Ship destroyer = new Ship("Destroyer", 2);

        fillTable(gameTable);
        printTable(gameTable);

        System.out.println();
        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):");
        aircraftCarrier.position = scanner.nextLine().toUpperCase();
        //aircraftCarrier.position = "F3 F7";
        putShipOnTheTable(aircraftCarrier, gameTable);

        System.out.println();
        System.out.println("Enter the coordinates of the Battleship (4 cells):");
        battleship.position = scanner.nextLine().toUpperCase();
        //battleship.position = "A1 D1";
        putShipOnTheTable(battleship, gameTable);

        System.out.println("Enter the coordinates of the Submarine (3 cells):");
        submarine.position = scanner.nextLine().toUpperCase();
        //submarine.position = "J10 J8";
        putShipOnTheTable(submarine, gameTable);

        System.out.println();
        System.out.println("Enter the coordinates of the Cruiser (3 cells):");
        cruiser.position = scanner.nextLine().toUpperCase();
        //cruiser.position = "B9 D9";
        putShipOnTheTable(cruiser, gameTable);

        System.out.println();
        System.out.println("Enter the coordinates of the Destroyer (2 cells):");
        destroyer.position = scanner.nextLine().toUpperCase();
        //destroyer.position = "I2 J2";
        putShipOnTheTable(destroyer, gameTable);

        System.out.println();
        System.out.println("The game starts!");
        fillTable(gameFogTable);
        printTable(gameFogTable);

        System.out.println();
        System.out.println("Take a shot!");
        System.out.println();
        String shot = scanner.nextLine().toUpperCase();
        shot(shot, gameTable, gameFogTable);

        System.out.println();
        System.out.println("You sank the last ship. You won. Congratulations!");

    }

    public static void fillTable(Table gameTable) {

        // Fill the fog
        for (int i = 0; i <= gameTable.x_wide; i++) {
            for (int j = 0; j <= gameTable.y_height; j++) {
                gameTable.table[i][j] = "~";
            }
        }

        // Fill the coordinates
        gameTable.table[0][0] = " ";

        gameTable.table[0][1] = "1";
        gameTable.table[0][2] = "2";
        gameTable.table[0][3] = "3";
        gameTable.table[0][4] = "4";
        gameTable.table[0][5] = "5";
        gameTable.table[0][6] = "6";
        gameTable.table[0][7] = "7";
        gameTable.table[0][8] = "8";
        gameTable.table[0][9] = "9";
        gameTable.table[0][10] = "10";

        gameTable.table[1][0] = "A";
        gameTable.table[2][0] = "B";
        gameTable.table[3][0] = "C";
        gameTable.table[4][0] = "D";
        gameTable.table[5][0] = "E";
        gameTable.table[6][0] = "F";
        gameTable.table[7][0] = "G";
        gameTable.table[8][0] = "H";
        gameTable.table[9][0] = "I";
        gameTable.table[10][0] = "J";


    }

    public static void printTable(Table gameTable) {
        for (int i = 0; i < gameTable.x_wide; i++) {
            System.out.println();
            for (int j = 0; j < gameTable.y_height; j++) {
                System.out.print(gameTable.table[i][j] + " ");
            }
        }
        System.out.println();
    }

    public static void putShipOnTheTable(Ship ship, Table gameTable) {

        Scanner scanner = new Scanner(System.in);
        String[] parts = ship.position.split(" ");
        boolean tooClose = false;

        // First input like F5
        String beginningPointY = parts[0];
        // Second input like C5
        String endPointX = parts[1];

        // Letter from position
        String beginningPointLetter = beginningPointY.substring(0, 1);
        String endPointLetter = endPointX.substring(0, 1);

        // Number from position
        String[] numberOneSplit = beginningPointY.split(beginningPointLetter);
        String[] numberTwoSplit = endPointX.split(endPointLetter);

        // Number to int
        int numberOneS = Integer.parseInt(numberOneSplit[1]);
        int numberTwoS = Integer.parseInt(numberTwoSplit[1]);

        int positionInArrayFirst = 0;
        // Get ship x,y start position in array
        for (int i = 0; i < gameTable.table.length; i++) {
            if (gameTable.table[i][0].equals(beginningPointLetter)) {
                positionInArrayFirst = i;
            }
        }

        int positionInArraySecond = 0;
        // Get ship x,y second position in array
        for (int i = 0; i < gameTable.table.length; i++) {
            if (gameTable.table[i][0].equals(endPointLetter)) {
                positionInArraySecond = i;
            }
        }

        for (int i = numberTwoS; i <= numberOneS; i++) {
            if (
                    gameTable.table[positionInArrayFirst + 1][i + 1].equals("O") ||
                            gameTable.table[positionInArrayFirst + 1][i - 1].equals("O") ||
                            gameTable.table[positionInArrayFirst - 1][i + 1].equals("O") ||
                            gameTable.table[positionInArrayFirst - 1][i - 1].equals("O") ||
                            gameTable.table[positionInArrayFirst][numberOneS + 1].equals("O") ||
                            gameTable.table[positionInArrayFirst][numberTwoS - 1].equals("O")
            ) {
                tooClose = true;
                break;
            }
        }
        for (int i = numberOneS; i <= numberTwoS; i++) {
            if (
                    gameTable.table[positionInArrayFirst + 1][i + 1].equals("O") ||
                            gameTable.table[positionInArrayFirst + 1][i - 1].equals("O") ||
                            gameTable.table[positionInArrayFirst - 1][i + 1].equals("O") ||
                            gameTable.table[positionInArrayFirst - 1][i - 1].equals("O") ||
                            gameTable.table[positionInArrayFirst][numberOneS + 1].equals("O") ||
                            gameTable.table[positionInArrayFirst][numberTwoS - 1].equals("O")
            ) {
                tooClose = true;
                break;
            }
        }

        if (tooClose) {

            System.out.println();
            System.out.println("Error! You placed it too close to another one. Try again:");
            ship.position = scanner.nextLine().toUpperCase();
            putShipOnTheTable(ship, gameTable);

        } else if (!beginningPointLetter.equals(endPointLetter) && numberOneS != numberTwoS) {

            System.out.println();
            System.out.println("Error! Wrong ship location! Try again:");
            System.out.println();
            ship.position = scanner.nextLine().toUpperCase();
            putShipOnTheTable(ship, gameTable);

        } else {

            // Overlap
            if (gameTable.table[positionInArrayFirst][numberOneS].equals("O") || gameTable.table[positionInArraySecond][numberTwoS].equals("O")) {

                System.out.println();
                System.out.println("Error! You placed it too close to another one. Try again:");
                ship.position = scanner.nextLine().toUpperCase();
                putShipOnTheTable(ship, gameTable);

            }
            if (beginningPointLetter.equals(endPointLetter)) {
                if (numberOneS - numberTwoS == ship.length - 1 || numberTwoS - numberOneS == ship.length - 1) {
                    if (numberOneS > numberTwoS) {
                        for (int i = numberTwoS; i <= numberOneS; i++) {
                            gameTable.table[positionInArrayFirst][i] = "O";
                        }
                    }
                    if (numberTwoS > numberOneS) {
                        for (int i = numberOneS; i <= numberTwoS; i++) {
                            gameTable.table[positionInArraySecond][i] = "O";
                        }
                    }

                    printTable(gameTable);

                } else {

                    System.out.println("Error! Wrong length of the " + ship.name + "! Try again:");
                    ship.position = scanner.nextLine().toUpperCase();
                    putShipOnTheTable(ship, gameTable);

                }
            }
            if (numberOneS == numberTwoS) {
                if (positionInArrayFirst - positionInArraySecond == ship.length - 1 || positionInArraySecond - positionInArrayFirst == ship.length - 1) {
                    if (positionInArrayFirst < positionInArraySecond) {
                        for (int i = positionInArrayFirst; i <= positionInArraySecond; i++) {
                            gameTable.table[i][numberOneS] = "O";
                        }
                    }
                    if (positionInArraySecond < positionInArrayFirst) {
                        for (int i = positionInArraySecond; i <= positionInArrayFirst; i++) {
                            gameTable.table[i][numberTwoS] = "O";
                        }
                    }

                    printTable(gameTable);

                } else {
                    System.out.println("Error! Wrong length of the " + ship.name + "! Try again:");
                    ship.position = scanner.nextLine().toUpperCase();
                    putShipOnTheTable(ship, gameTable);
                }
            }
        }
    }

    public static void shot(String shot, Table gameTable, Table gameFogTable) {

        Scanner scanner = new Scanner(System.in);
        String shotLetter = shot.substring(0, 1);
        String[] parts = shot.split(shotLetter);
        int shotNumber = 0;

        try {
            shotNumber = Integer.parseInt(parts[1]);
        } catch (Exception e) {
            System.out.println("Take a shot!");
            shot = scanner.nextLine().toUpperCase();
            shot(shot, gameTable, gameFogTable);
        }

        int positionShotLetter = 0;
        String myString = "";

        for (int i = 0; i < gameTable.table.length; i++) {
            myString += gameTable.table[i][0];
            if (gameTable.table[i][0].equals(shotLetter)) {
                positionShotLetter = i;
            }
        }

        if (gameFogTable.table[positionShotLetter][shotNumber].equals("X")) {

            printTable(gameFogTable);
            System.out.println();
            System.out.println("You hit a ship! Try again:");
            System.out.println();
            shot = scanner.nextLine().toUpperCase();
            shot(shot, gameTable, gameFogTable);

        } else {

            for (int i = 0; i < gameTable.countShipX; i++) {

                if (!myString.contains(shotLetter) || shotNumber > gameTable.table.length - 2) {

                    System.out.println();
                    System.out.println("Error! You entered the wrong coordinates! Try again:");
                    System.out.println();
                    shot = scanner.nextLine().toUpperCase();
                    shot(shot, gameTable, gameFogTable);

                }
                if (gameTable.table[positionShotLetter][shotNumber].equals("O") || gameTable.table[positionShotLetter][shotNumber].equals("X")) {

                    gameFogTable.table[positionShotLetter][shotNumber] = "X";
                    gameTable.table[positionShotLetter][shotNumber] = "X";
                    gameTable.countShipX--;

                    if (
                            !gameTable.table[positionShotLetter - 1][shotNumber].equals("O") &&
                                    !gameTable.table[positionShotLetter + 1][shotNumber].equals("O") &&
                                    !gameTable.table[positionShotLetter][shotNumber - 1].equals("O") &&
                                    !gameTable.table[positionShotLetter][shotNumber + 1].equals("O")

                    ) {

                        if (gameTable.countShipX == 0) {

                            printTable(gameFogTable);
                            break;

                        }

                        printTable(gameFogTable);
                        System.out.println();
                        System.out.println("You sank a ship! Specify a new target:");
                        System.out.println();
                        shot = scanner.nextLine().toUpperCase();
                        shot(shot, gameTable, gameFogTable);

                    } else {

                        printTable(gameFogTable);
                        System.out.println();
                        System.out.println("You hit a ship! Try again:");
                        System.out.println();
                        shot = scanner.nextLine().toUpperCase();
                        shot(shot, gameTable, gameFogTable);

                    }
                } else {

                    gameFogTable.table[positionShotLetter][shotNumber] = "M";
                    gameTable.table[positionShotLetter][shotNumber] = "M";
                    printTable(gameFogTable);
                    System.out.println();
                    System.out.println("You missed! Try again:");
                    System.out.println();
                    shot = scanner.nextLine().toUpperCase();
                    shot(shot, gameTable, gameFogTable);

                }
            }
        }
    }
}