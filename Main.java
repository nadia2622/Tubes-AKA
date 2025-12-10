public class Main {
    public static void main(String[] args) {

        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        // ---- Brute Force ----
        long startBF = System.nanoTime();
        int resultBF = BruteForce.maxSubarray(arr);
        long endBF = System.nanoTime();
        long timeBF = endBF - startBF;

        // ---- Kadane ----
        long startKD = System.nanoTime();
        int resultKD = Kadane.maxSubarray(arr);
        long endKD = System.nanoTime();
        long timeKD = endKD - startKD;

        // ---- Output ----
        System.out.println("=== Maximum Subarray Comparison ===\n");

        System.out.println("Brute Force Result : " + resultBF);
        System.out.println("Brute Force Time   : " + timeBF + " ns\n");

        System.out.println("Kadane Result      : " + resultKD);
        System.out.println("Kadane Time        : " + timeKD + " ns\n");

        System.out.println("Kadane lebih cepat sekitar " +
                (double) timeBF / timeKD + "x dari Brute Force");
    }
}