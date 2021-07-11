package com.tneshcheret;

public class Task2 {
    public static void main(String[] args) {
        printMagicSquare(fillMagicSquare(8));
    }

    public static void printMagicSquare(int[][] magicSquare) {
        for (int i = 0; i < magicSquare.length; i++) {
            for (int j = 0; j < magicSquare.length; j++) {
                System.out.print(" " + magicSquare[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] fillMagicSquare(int n) {
        if (n % 2 != 0) {
            return fillMagicSquareOddOrder(n);
        }
        if (n % 2 == 0 && n % 4 != 0) {
            return fillMagicSquareSingleParity(n);
        } else {
            return fillMagicSquareDoubleParity(n);
        }
    }

    private static int[][] fillMagicSquareSingleParity(int n) {
        int half = n / 2;

        int[][] square = new int[n][n];
        int[][] tempMatrix;
        tempMatrix = fillMagicSquareOddOrder(half);

        for (int i = 0; i < half; i++) {
            for (int j = 0; j < half; j++) {
                square[i][j] = tempMatrix[i][j];
            }
        }
        for (int i = 0; i < half; i++) {
            for (int j = half; j < n; j++) {
                int x = j - half;
                square[i][j] = (tempMatrix[i][x] + 2 * half * half);
            }
        }
        for (int i = half; i < n; i++) {
            for (int j = 0; j < half; j++) {
                int x = i - half;

                square[i][j] = (tempMatrix[x][j] + 3 * half * half);
            }
        }
        for (int i = half; i < n; i++) {
            for (int j = half; j < n; j++) {
                int x = i - half, y = j - half;
                square[i][j] = (tempMatrix[x][y] + half * half);
            }
        }
        int move = 0;
        for (int i = 6; i < n; i++) {
            if ((i % 4 != 0) && (i % 2 == 0)) move++;
        }
        for (int j = square.length / 2 - move; j <= square.length / 2 + move - 1; j++) {
            for (int i = 0; i < tempMatrix.length; i++) {

                int key = square[i][j];
                square[i][j] = square[half + i][j];
                square[half + i][j] = key;
            }
        }
        for (int j = 0; j <= 1; j++) {
            if (j == 0) {
                int key = square[0][0];
                square[0][0] = square[half][0];
                square[half][0] = key;
            }
            if (j == 1) {
                int key = square[half - 1][0];
                square[half - 1][0] = square[n - 1][0];
                square[n - 1][0] = key;
            }
        }
        for (int j = half + 1; j < n - 1; j++) {
            for (int i = 1; i < half - 1; i++) {
                int key = square[i][1];
                square[i][1] = square[half + i][1];
                square[half + i][1] = key;
            }
        }
        return square;

    }

    public static int[][] fillMagicSquareOddOrder(int n) {
        int[][] magicSquare = new int[n][n];
        int startColumn = n / 2;
        magicSquare[0][startColumn] = 1;
        int startLine = 0;
        for (int i = 2; i < n * n + 1; i++) {
            int line = startLine - 1;
            if (line < 0) {
                line = n - 1;
            }
            int column = startColumn + 1;
            if (column >= n) {
                column = 0;
            }

            if (magicSquare[line][column] == 0) {
                magicSquare[line][column] = i;
                startLine = line;
                startColumn = column;
            } else {
                magicSquare[startLine + 1][startColumn] = i;
                startLine = startLine + 1;
            }
        }
        return magicSquare;
    }

    private static int[][] fillMagicSquareDoubleParity(int n) {
        int[][] square = fillWorkSquareAscendingOrder(n);
        int[][] tempSquare = fillWorkSquareDescendingOrder(n);

        int sizeBlock = 4;
        int diagonalX = 0;
        int diagonalY = 0;
        for (int i = 0; i < (n * n / 16); i++) {
            if (diagonalX == (int) Math.sqrt(n * n / 16)) {
                diagonalX = 0;
                diagonalY++;
            }
            for (int j = 0; j < 4; j++) {
                square[sizeBlock * diagonalY + j][sizeBlock * diagonalX + j] = tempSquare[sizeBlock * diagonalY + j][sizeBlock * diagonalX + j];
                square[sizeBlock * diagonalY + j][sizeBlock * diagonalX + sizeBlock - 1 - j] = tempSquare[sizeBlock * diagonalY + j][sizeBlock * diagonalX + sizeBlock - 1 - j];
            }
            diagonalX++;
        }
        return square;
    }

    public static int[][] fillWorkSquareAscendingOrder(int n) {
        int[][] square = new int[n][n];
        int value = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                square[i][j] = value;
                value++;
            }
        }
        return square;
    }

    public static int[][] fillWorkSquareDescendingOrder(int n) {
        int[][] square = new int[n][n];
        int value = n * n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                square[i][j] = value;
                value--;
            }
        }
        return square;
    }
}
