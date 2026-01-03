public class MaxSubarrayRecursive {

    /**
     * Metode utama untuk mencari jumlah maksimum subarray
     * menggunakan pendekatan Divide and Conquer (rekursif).
     *
     * Time Complexity  : O(n log n)
     * Space Complexity: O(log n) karena stack rekursi
     */
    public static long maxSubarray(long[] arr) {
        // Memanggil fungsi helper dengan batas indeks awal dan akhir array
        return maxSubarrayHelper(arr, 0, arr.length - 1);
    }

    /**
     * Fungsi rekursif untuk mencari maksimum subarray
     * pada rentang indeks left hingga right.
     */
    private static long maxSubarrayHelper(long[] arr, int left, int right) {

        // Base case:
        // Jika hanya ada satu elemen, maka elemen tersebut
        // merupakan maksimum subarray pada rentang ini
        if (left == right) {
            return arr[left];
        }

        // Menentukan titik tengah array
        int mid = (left + right) / 2;

        /*
         * Rekursi ke bagian kiri array:
         * mencari maksimum subarray pada indeks left hingga mid
         */
        long leftMax = maxSubarrayHelper(arr, left, mid);

        /*
         * Rekursi ke bagian kanan array:
         * mencari maksimum subarray pada indeks mid+1 hingga right
         */
        long rightMax = maxSubarrayHelper(arr, mid + 1, right);

        /*
         * Menghitung maksimum subarray yang melewati titik tengah
         * (menggabungkan bagian kiri dan kanan)
         */
        long crossMax = maxCrossingSum(arr, left, mid, right);

        /*
         * Hasil maksimum subarray pada rentang ini adalah
         * nilai maksimum dari:
         * 1. Maksimum di kiri
         * 2. Maksimum di kanan
         * 3. Maksimum yang melewati tengah
         */
        return Math.max(Math.max(leftMax, rightMax), crossMax);
    }

    /**
     * Fungsi untuk menghitung maksimum subarray
     * yang melewati titik tengah array.
     */
    private static long maxCrossingSum(long[] arr, int left, int mid, int right) {

        // Menghitung maksimum subarray dari tengah ke kiri
        long sum = 0;
        long leftSum = Integer.MIN_VALUE;

        for (int i = mid; i >= left; i--) {
            sum += arr[i];
            // Menyimpan jumlah maksimum dari sisi kiri
            leftSum = Math.max(leftSum, sum);
        }

        // Menghitung maksimum subarray dari tengah ke kanan
        sum = 0;
        long rightSum = Integer.MIN_VALUE;

        for (int i = mid + 1; i <= right; i++) {
            sum += arr[i];
            // Menyimpan jumlah maksimum dari sisi kanan
            rightSum = Math.max(rightSum, sum);
        }

        // Maksimum subarray yang melewati tengah
        // adalah gabungan sisi kiri dan kanan
        return leftSum + rightSum;
    }
}