/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Scanner;
/**
 *
 * @author Jhonathan
 */
public class PezCandyMaximization {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Solicitar el número de dispensadores y el número de caramelos que Arup comerá
        System.out.println("Por favor, ingrese el numero de dispensadores y el numero de caramelos que  comera, separados por un espacio:");
        int n = sc.nextInt(); // Número de dispensadores
        int k = sc.nextInt(); // Número de caramelos que Arup comerá
        sc.nextLine(); // Limpiar buffer
        
        // Validar que el número de dispensadores y caramelos sea válido
        if (n < 1 || n > 1000 || k < 1 || k > 12 * n) {
            System.out.println("Error: numero de dispensadores o caramelos fuera del rango permitido.");
            return;
        }
        
        int[][] dispensers = new int[n][12];  // Matriz donde cada fila es un dispensador y cada columna es un nivel de caramelos
        
        // Pedir los valores de los caramelos para cada dispensador
        System.out.println("\nIngrese los valores de los 12 caramelos de cada dispensador en una sola linea:");
        
        for (int i = 0; i < n; i++) {
            System.out.println("Dispensador " + (i + 1) + ":");
            String[] input = sc.nextLine().split(" ");
            
            // Validar que la fila tenga exactamente 12 valores
            if (input.length != 12) {
                System.out.println("Error: cada dispensador debe contener exactamente 12 valores.");
                return;
            }

            for (int j = 0; j < 12; j++) {
                dispensers[i][j] = Integer.parseInt(input[j]);

                // Validar que los valores estén en el rango permitido (1-300)
                if (dispensers[i][j] < 1 || dispensers[i][j] > 300) {
                    System.out.println("Error: valor de caramelo fuera del rango permitido (1-300).");
                    return;
                }
            }
        }

        // Llamada a la función de maximización
        int resultado = maxPezValue(n, k, dispensers);
        System.out.println("\nEl valor maximo de gusto que puede obtener comiendo " + k + " caramelos es: " + resultado);
        
        sc.close();
    }

    // Función para calcular el valor máximo usando programación dinámica
    public static int maxPezValue(int n, int k, int[][] dispensers) {
        // dp[i] = máximo valor posible comiendo exactamente i caramelos
        int[] dp = new int[k + 1];
        
        // Para cada dispensador (fila de la matriz)
        for (int j = 0; j < n; j++) {
            int[] cumulativeValues = new int[13]; // Para acumular los valores de los primeros x caramelos del dispensador
            for (int i = 0; i < 12; i++) {
                cumulativeValues[i + 1] = cumulativeValues[i] + dispensers[j][i];
            }
            
            // Actualizar dp de atrás hacia adelante para evitar sobreescritura
            for (int candiesEaten = k; candiesEaten > 0; candiesEaten--) {
                // Para cada número de caramelos que podemos tomar de este dispensador (de 1 a 12)
                for (int x = 1; x <= Math.min(12, candiesEaten); x++) {
                    dp[candiesEaten] = Math.max(dp[candiesEaten], dp[candiesEaten - x] + cumulativeValues[x]);
                }
            }
        }
        
        return dp[k];  // El valor máximo que se puede obtener comiendo k caramelos
    }
}