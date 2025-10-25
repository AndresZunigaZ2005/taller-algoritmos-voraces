package co.edu.uniquindio.analisis;

import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // Denominaciones y disponibilidad inicial
    int[] denominaciones = {100000, 50000, 20000, 10000};
    int[] disponibles = {50, 100, 200, 300};

    int monto;

    do {
        System.out.print("\nIngrese la cantidad de dinero que desea retirar (múltiplo de 10000, 0 para salir): ");
        monto = sc.nextInt();

        if (monto == 0) {
            System.out.println("Gracias por usar el cajero. ¡Hasta luego!");
            break;
        }

        entregarDinero(monto, denominaciones, disponibles);

    } while (monto != 0);

    sc.close();
}

    // =======================
    // MÉTODO PRINCIPAL DE CAJERO
    // =======================
    public static void entregarDinero(int monto, int[] denominaciones, int[] disponibles) {
        if (monto % 10000 != 0) {
            System.out.println("❌ Error: La cantidad debe ser múltiplo de 10,000.");
            return;
        }

        int montoOriginal = monto;
        int[] entregados = new int[denominaciones.length];

        // Algoritmo devorador (Greedy)
        for (int i = 0; i < denominaciones.length; i++) {
            if (monto >= denominaciones[i]) {
                int necesarios = monto / denominaciones[i];
                int usar = Math.min(necesarios, disponibles[i]);
                entregados[i] = usar;
                monto -= usar * denominaciones[i];
                disponibles[i] -= usar;
            }
        }

        if (monto == 0) {
            System.out.println("\n✅ Dinero entregado correctamente:");
            for (int i = 0; i < denominaciones.length; i++) {
                if (entregados[i] > 0) {
                    System.out.println("Billetes de " + denominaciones[i] + ": " + entregados[i]);
                }
            }
            System.out.println("Total entregado: $" + montoOriginal);
        } else {
            System.out.println("\n⚠️ No hay suficientes billetes para entregar el monto solicitado.");
            // Restaurar los billetes al cajero (no se entrega nada)
            for (int i = 0; i < denominaciones.length; i++) {
                disponibles[i] += entregados[i];
            }
        }

        mostrarDisponibilidad(denominaciones, disponibles);
    }

    // =======================
    // MÉTODO PARA MOSTRAR DISPONIBILIDAD
    // =======================
    public static void mostrarDisponibilidad(int[] denominaciones, int[] disponibles) {
        System.out.println("\n📦 Billetes restantes en el cajero:");
        for (int i = 0; i < denominaciones.length; i++) {
            System.out.println("  " + denominaciones[i] + ": " + disponibles[i]);
        }
    }
}