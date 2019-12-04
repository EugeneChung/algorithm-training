package coursera.algorithms_part1.percolation;

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private final int trials;
    private double[] thresholds;
    private double cachedMean;
    private double cachedStddev;
    private double[] confidence95Interval;

    /**
     * Performs T independent computational experiments on an N-by-N grid.
     * @param n the size of grid
     * @param trials the number of trial
     */
    public PercolationStats(int n, int trials) {
        if (n <= 0) {
            throw new IllegalArgumentException("invalid N = " + n);
        }
        if (trials <= 0) {
            throw new IllegalArgumentException("invalid T = " + trials);
        }
        this.trials = trials;
        this.thresholds = new double[trials];
        this.cachedMean = -1.0d;
        this.cachedStddev = -1.0d;

        final int totalCount = n * n;

        for (int trial = 0; trial < trials; trial++) {
            Percolation percolation = new Percolation(n);

            int chosen, row, col;
            int openCount = 0;
            do {
                chosen = StdRandom.uniform(totalCount) + 1;
                col = chosen % n;
                if (col == 0) {
                    col = n;
                    row = chosen / n;
                } else {
                    row = chosen / n + 1;
                }
                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                    openCount++;
                }
            } while (openCount < n || !percolation.percolates());

            thresholds[trial] = (double) openCount / (double) totalCount;
        }
    }

    /**
     * Returns sample mean of percolation threshold.
     */
    public double mean() {
        if (this.cachedMean >= 0) {
            return this.cachedMean;
        }
        double sumOfThreshold = 0.0d;
        for (double threshold : thresholds) {
            sumOfThreshold += threshold;
        }
        this.cachedMean = sumOfThreshold / (double) trials;
        return this.cachedMean;
    }

    /**
     * Returns sample standard deviation of percolation threshold.
     */
    public double stddev() {
        if (this.cachedStddev >= 0) {
            return this.cachedStddev;
        }

        double mean = mean();
        double sumOfDeviationSquared = 0.0d;
        for (double threshold : thresholds) {
            double dev = mean - threshold;
            sumOfDeviationSquared += dev * dev;
        }
        this.cachedStddev = Math.sqrt(sumOfDeviationSquared / (trials - 1));
        return this.cachedStddev;
    }

    public double confidenceLo() {
        return getConfidence95Interval()[0];
    }

    public double confidenceHi() {
        return getConfidence95Interval()[1];
    }

    private double[] getConfidence95Interval() {
        if (confidence95Interval != null) {
            return confidence95Interval;
        }
        double mean = mean();
        double stddev = stddev();
        double distance = 1.96d * stddev / Math.sqrt(trials);
        double left = mean - distance;
        double right = mean + distance;
        confidence95Interval = new double[]{left, right};
        return confidence95Interval;
    }

    private static void log(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            log("Usage: java PercolationStats N T");
            log("       N is the size of grid.");
            log("       N and T are positive integers.");
            return;
        }
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        long start = System.nanoTime();
        PercolationStats stats = new PercolationStats(N, T);
        log("mean                    = " + stats.mean());
        log("stddev                  = " + stats.stddev());
        log("95% confidence interval = " + stats.confidenceLo() + ", " + stats.confidenceHi());
        log((System.nanoTime() - start) / 1000000 + "msec");
    }
}