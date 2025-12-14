public class Kadane {

    /**
     * Metode untuk mencari jumlah maksimum dari subarray berurutan
     * menggunakan Algoritma Kadane (pendekatan iteratif).
     *
     * Time Complexity  : O(n)
     * Space Complexity: O(1)
     */
    public static int maxSubarray(int[] arr) {

        // currentSum menyimpan jumlah maksimum subarray
        // yang BERAKHIR pada indeks saat ini (indeks i)
        int currentSum = arr[0];

        // maxSum menyimpan jumlah maksimum subarray
        // terbesar yang ditemukan sejauh ini (global maximum)
        int maxSum = arr[0];

        // Perulangan dimulai dari indeks ke-1
        // karena indeks ke-0 sudah digunakan untuk inisialisasi
        for (int i = 1; i < arr.length; i++) {

            /*
             * Pada setiap indeks i, ada dua pilihan:
             * 1. Memulai subarray baru dari arr[i]
             * 2. Melanjutkan subarray sebelumnya dengan menambahkan arr[i]
             *
             * Dipilih nilai yang paling besar untuk currentSum
             */
            currentSum = Math.max(arr[i], currentSum + arr[i]);

            /*
             * Memperbarui maxSum jika currentSum saat ini
             * lebih besar dari nilai maksimum sebelumnya
             */
            maxSum = Math.max(maxSum, currentSum);
        }

        // Mengembalikan jumlah maksimum subarray
        return maxSum;
    }
}