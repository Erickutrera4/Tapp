package ttapp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author erick
 */
import java.util.Scanner;

public class TresEnRaya {
    // Representación del tablero
    private static char[][] tablero = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};

    // Jugador actual (0 o 1)
    private static int jugadorActual = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Bucle principal del juego
        while (true) {
            // Imprimir el tablero
            imprimirTablero();

            // Obtener la jugada del jugador
            System.out.println("Turno del Jugador " + (jugadorActual + 1));
            System.out.print("Ingrese la fila (0, 1, 2): ");
            int fila = scanner.nextInt();
            System.out.print("Ingrese la columna (0, 1, 2): ");
            int columna = scanner.nextInt();

            // Verificar si la jugada es válida
            if (esJugadaValida(fila, columna)) {
                // Realizar la jugada
                realizarJugada(fila, columna);

                // Verificar si hay un ganador
                if (hayGanador()) {
                    imprimirTablero();
                    System.out.println("¡Jugador " + (jugadorActual + 1) + " ha ganado!");
                    break;
                }

                // Verificar si es un empate
                if (esEmpate()) {
                    imprimirTablero();
                    System.out.println("El juego ha terminado en empate.");
                    break;
                }

                // Cambiar al siguiente jugador
                jugadorActual = 1 - jugadorActual;
            } else {
                System.out.println("Jugada inválida. Intente de nuevo.");
            }
        }

        scanner.close();
    }

    // Imprimir el tablero
    private static void imprimirTablero() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tablero[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Verificar si la jugada es válida
    private static boolean esJugadaValida(int fila, int columna) {
        return fila >= 0 && fila < 3 && columna >= 0 && columna < 3 && tablero[fila][columna] == ' ';
    }

    // Realizar la jugada
    private static void realizarJugada(int fila, int columna) {
        char marca = (jugadorActual == 0) ? 'O' : 'X';
        tablero[fila][columna] = marca;
    }

    // Verificar si hay un ganador
    private static boolean hayGanador() {
        // Verificar filas y columnas
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] != ' ' && tablero[i][0] == tablero[i][1] && tablero[i][1] == tablero[i][2]) {
                return true; // Hay un ganador en la fila i
            }
            if (tablero[0][i] != ' ' && tablero[0][i] == tablero[1][i] && tablero[1][i] == tablero[2][i]) {
                return true; // Hay un ganador en la columna i
            }
        }

        // Verificar diagonales
        if (tablero[0][0] != ' ' && tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2]) {
            return true; // Hay un ganador en la diagonal principal
        }
        if (tablero[0][2] != ' ' && tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0]) {
            return true; // Hay un ganador en la diagonal secundaria
        }

        return false; // No hay ganador
    }

    // Verificar si el juego ha terminado en empate
    private static boolean esEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == ' ') {
                    return false; // Todavía hay espacios vacíos, el juego no ha terminado en empate
                }
            }
        }
        return true; // Todos los espacios están ocupados, el juego ha terminado en empate
    }
}

