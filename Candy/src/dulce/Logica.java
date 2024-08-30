package dulce;

import java.util.Scanner;

public class Logica {

    public static void main(String[] args) {       
        Scanner scanner = new Scanner(System.in);

        System.out.println("Por favor, ingrese el numero de filas y columnas separados por un espacio:");
        int filas = scanner.nextInt();
        int columnas = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        int[][] matrizPrincipal = new int[filas][columnas];
        int[] matrizTemporal = new int[columnas];
        int[][] mayoresFilas = new int[1][columnas];
        int maximoFila;
        int posicionActual = 0;

        // Solicitar la matriz al usuario
        for (int i = 0; i < filas; i++) {
            boolean esValida = false;
            while (!esValida) {
                System.out.println("Ingrese " + columnas + " numeros para la fila " + (i + 1) + " separados por espacios:");
                String entradaFila = scanner.nextLine();
                String[] elementos = entradaFila.trim().split("\\s+");

                if (elementos.length == columnas) {
                    esValida = true;
                    for (int j = 0; j < elementos.length; j++) {
                        matrizPrincipal[i][j] = Integer.parseInt(elementos[j]);
                    }
                } else {
                    System.out.println("Error de formato. Asegurese de ingresar " + columnas + " numeros separados por espacios.");
                }
            }
        }

        // Procesar la matriz
        for (int i = 0; i < filas; i++) {
            matrizTemporal[0] = matrizPrincipal[i][0];
            matrizTemporal[1] = matrizPrincipal[i][1];
            matrizTemporal[2] = matrizPrincipal[i][0] + matrizPrincipal[i][2];

            for (int col = 3; col < columnas; col++) {
                if (matrizPrincipal[i][col-2] >= matrizPrincipal[i][col-3]) {
                    matrizTemporal[col] = matrizTemporal[col-2] + matrizPrincipal[i][col];
                } else {
                    matrizTemporal[col] = matrizTemporal[col-3] + matrizPrincipal[i][col];
                }
            }

            maximoFila = Math.max(matrizTemporal[columnas - 1], matrizTemporal[columnas - 2]);
            System.out.println("El mayor numero de dulces en la fila " + (i + 1) + " es: " + maximoFila);

            mayoresFilas[0][posicionActual] = maximoFila;
            posicionActual++;
        }

        matrizTemporal[0] = mayoresFilas[0][0];
        matrizTemporal[1] = mayoresFilas[0][1];
        matrizTemporal[2] = mayoresFilas[0][0] + mayoresFilas[0][2];

        for (int col = 3; col < columnas; col++) {
            if (mayoresFilas[0][col-2] >= mayoresFilas[0][col-3]) {
                matrizTemporal[col] = matrizTemporal[col-2] + mayoresFilas[0][col];
            } else {
                matrizTemporal[col] = matrizTemporal[col-3] + mayoresFilas[0][col];
            }
        }

        maximoFila = Math.max(matrizTemporal[columnas - 1], matrizTemporal[columnas - 2]);
        System.out.println("El maximo numero de dulces recolectados por el nino es: " + maximoFila);
    }
}
