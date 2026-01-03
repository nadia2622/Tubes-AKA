import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.lines.SeriesLines;
import org.knowm.xchart.style.markers.SeriesMarkers;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.awt.Color;

public class Main {
    public static void main(String[] args) {
        // Warm-up lebih agresif
        System.out.println("Memulai pemanasan sistem (Warm-up)...");
        Random rand = new Random(42);
        for (int i = 0; i < 200000; i++) {
            long[] dummy = generateRandomArray(100, rand);
            Kadane.maxSubarray(dummy);
            MaxSubarrayRecursive.maxSubarray(dummy);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n====================================================================");
        System.out.println("ANALISIS KOMPLEKSITAS: O(n) vs O(n log n)");
        System.out.println("====================================================================");

        System.out.print("\nMasukkan jumlah ukuran array yang ingin diuji: ");
        int nTotal = scanner.nextInt();

        int[] sizes = new int[nTotal];
        System.out.println("Masukkan " + nTotal + " ukuran array:");
        for (int i = 0; i < nTotal; i++) {
            System.out.print("  Ukuran [" + (i + 1) + "]: ");
            sizes[i] = scanner.nextInt();
        }

        List<Integer> xLabels = new ArrayList<>();
        List<Double> yIterative = new ArrayList<>();
        List<Double> yRecursive = new ArrayList<>();
        List<String> tableRows = new ArrayList<>();

        System.out.println("\nSedang mengukur performa berdasarkan ukuran array...\n");

        for (int size : sizes) {
            // Generate array acak dengan ukuran tertentu
            long[] testArray = generateRandomArray(size, rand);

            // Iterasi pengukuran disesuaikan dengan ukuran
            int iterations = Math.max(100, 50000 / size);

            // Ukur waktu Kadane (Iteratif - O(n))
            long startIter = System.nanoTime();
            for (int k = 0; k < iterations; k++) {
                Kadane.maxSubarray(testArray);
            }
            double avgIter = ((System.nanoTime() - startIter) / (double) iterations) / 1_000_000.0;

            // Ukur waktu Rekursif (O(n log n))
            long startRec = System.nanoTime();
            for (int k = 0; k < iterations; k++) {
                MaxSubarrayRecursive.maxSubarray(testArray);
            }
            double avgRec = ((System.nanoTime() - startRec) / (double) iterations) / 1_000_000.0;

            xLabels.add(size);
            yIterative.add(avgIter);
            yRecursive.add(avgRec);

            tableRows.add(String.format("%-15d | %-18.6f | %-18.6f", size, avgIter, avgRec));

            System.out.printf("n = %-6d → Iteratif: %.6f ms | Rekursif: %.6f ms\n",
                    size, avgIter, avgRec);
        }

        // Output Tabel
        System.out.println("\n====================================================================");
        System.out.printf("%-15s | %-18s | %-18s\n", "Ukuran Array", "Iteratif (ms)", "Rekursif (ms)");
        System.out.println("--------------------------------------------------------------------");
        for (String row : tableRows) {
            System.out.println(row);
        }
        System.out.println("====================================================================");

        tampilkanGrafik(xLabels, yIterative, yRecursive);
        scanner.close();
    }

    /**
     * Generate array acak dengan nilai antara -100 hingga 100
     */
    private static long[] generateRandomArray(int size, Random rand) {
        long[] arr = new long[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(201) - 100; // Range: -100 to 100
        }
        return arr;
    }

    private static void tampilkanGrafik(List<Integer> xLabels, List<Double> y1, List<Double> y2) {
        XYChart chart = new XYChartBuilder()
                .width(900)
                .height(650)
                .title("Algorithm Complexity: O(n) vs O(n log n)")
                .xAxisTitle("Ukuran Array (n)")
                .yAxisTitle("Waktu Eksekusi (ms)")
                .build();

        // Style grafik
        chart.getStyler().setChartBackgroundColor(Color.WHITE);
        chart.getStyler().setPlotBackgroundColor(new Color(250, 250, 250));
        chart.getStyler().setPlotGridLinesVisible(true);
        chart.getStyler().setPlotGridLinesColor(new Color(200, 200, 200));
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setLegendBackgroundColor(Color.WHITE);

        // PERBAIKAN: Format sumbu X agar tidak pakai notasi ilmiah
        chart.getStyler().setXAxisDecimalPattern("#,###");
        chart.getStyler().setYAxisDecimalPattern("#0.0000");

        // Konversi List<Integer> ke double[]
        double[] xData = xLabels.stream().mapToDouble(Integer::doubleValue).toArray();
        double[] y1Data = y1.stream().mapToDouble(Double::doubleValue).toArray();
        double[] y2Data = y2.stream().mapToDouble(Double::doubleValue).toArray();

        // Series untuk Kadane (O(n))
        XYSeries series1 = chart.addSeries("Kadane O(n)", xData, y1Data);
        series1.setLineStyle(SeriesLines.SOLID);
        series1.setMarker(SeriesMarkers.CIRCLE);
        series1.setLineColor(new Color(46, 134, 193)); // Biru
        series1.setMarkerColor(new Color(46, 134, 193));
        series1.setLineWidth(2.5f);

        // Series untuk Recursive (O(n log n))
        XYSeries series2 = chart.addSeries("Divide & Conquer O(n log n)", xData, y2Data);
        series2.setLineStyle(SeriesLines.SOLID);
        series2.setMarker(SeriesMarkers.DIAMOND);
        series2.setLineColor(new Color(231, 76, 60)); // Merah
        series2.setMarkerColor(new Color(231, 76, 60));
        series2.setLineWidth(2.5f);

        new SwingWrapper<>(chart).displayChart();
    }
}