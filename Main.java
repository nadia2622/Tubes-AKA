import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Input jumlah elemen array
        System.out.print("Masukkan jumlah elemen array: ");
        int n = scanner.nextInt();

        if (n <= 0) {
            System.out.println("Jumlah elemen harus lebih dari 0.");
            return;
        }

        int[] arr = new int[n];

        // Input elemen array
        System.out.println("Masukkan elemen array:");
        for (int i = 0; i < n; i++) {
            System.out.print("Elemen ke-" + (i + 1) + ": ");
            arr[i] = scanner.nextInt();
        }

        // Menjalankan algoritma Kadane (Iteratif)
        int resultKadane = Kadane.maxSubarray(arr);

        // Menjalankan algoritma Divide and Conquer (Rekursif)
        int resultRecursive = MaxSubarrayRecursive.maxSubarray(arr);

        // Menampilkan array
        System.out.println("\nArray yang dimasukkan:");
        printArray(arr);

        // Menampilkan hasil
        System.out.println("\nHasil Maximum Subarray:");
        System.out.println("Kadane (Iteratif)          : " + resultKadane);
        System.out.println("Divide & Conquer (Rekursif): " + resultRecursive);

        scanner.close();
    }

    private static void printArray(int[] arr) {
        System.out.print("[ ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" ]");
    }
}