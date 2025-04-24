//Problem 4

import java.util.Scanner;

public class DirectedOrUndir {
    public static boolean isDirectedGraph(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n; i++) { //checking to see if matrix is square
            if (matrix[i].length != n) {
                return false;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != 0 && matrix[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the matrix (n): ");
        int n = scanner.nextInt();
        int[][] matrix = new int[n][n];

        System.out.println("Enter the elements of the matrix (0 or 1): ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        if (isDirectedGraph(matrix)) {
            System.out.println("The matrix represents a directed graph.");
        } else {
            System.out.println("The matrix does not represent a directed graph.");
        }

        scanner.close();
    }
}
