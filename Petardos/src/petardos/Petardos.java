/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package petardos;

/**
 *
 * @author Jhonathan
 */
import java.util.Scanner;

import java.util.Scanner;

public class Petardos {

    // Definimos los límites según el problema
    private static final int MAX_MAILBOXES = 11;  // k + 1, ya que k <= 10
    private static final int MAX_CRACKERS = 101; // m + 1, ya que m <= 100
    private static final int INFINITO = 10000000;

    // Matriz tridimensional para almacenar las soluciones parciales
    private static int[][][] dp = new int[MAX_MAILBOXES][MAX_CRACKERS][MAX_CRACKERS];

    public static void main(String[] args) {
        // Inicializamos las soluciones para casos base
        for (int i = 0; i < MAX_CRACKERS; i++) {
            for (int j = i; j < MAX_CRACKERS; j++) {
                if (i == j) {
                    dp[0][i][j] = 0; // Cuando no hay rango, no se requieren petardos
                } else {
                    dp[0][i][j] = dp[0][j][i] = INFINITO;
                }
            }
        }

        // Construimos la solución óptima usando programación dinámica
        for (int mailboxes = 1; mailboxes < MAX_MAILBOXES; mailboxes++) {
            for (int rango = 1; rango < MAX_CRACKERS; rango++) {
                for (int start = 0; start + rango < MAX_CRACKERS; start++) {
                    int mejorResultado = INFINITO;
                    for (int midpoint = start + 1; midpoint <= start + rango; midpoint++) {
                        int explota = dp[mailboxes - 1][start][midpoint - 1];
                        int noExplota = dp[mailboxes][midpoint][start + rango];
                        mejorResultado = Math.min(mejorResultado, Math.max(explota, noExplota) + midpoint);
                    }
                    dp[mailboxes][start][start + rango] = mejorResultado;
                }
            }
        }

        // Creamos un scanner para manejar la entrada de datos del usuario
        Scanner entradaUsuario = new Scanner(System.in);
        System.out.println("Por favor, ingrese el numero de casos de prueba:");
        int casosDePrueba = entradaUsuario.nextInt();

        // Procesamos cada caso de prueba
        while (casosDePrueba-- > 0) {
            System.out.println("Ingrese el numero de buzones y petardos separados por un espacio:");
            int numBuzones = entradaUsuario.nextInt();
            int numPetardos = entradaUsuario.nextInt();

            // Mostramos el resultado óptimo para el caso actual
            System.out.println("El numero minimo de petardos necesarios es: " + dp[numBuzones][0][numPetardos]);
        }

        // Cerramos el scanner
        entradaUsuario.close();
    }
}
