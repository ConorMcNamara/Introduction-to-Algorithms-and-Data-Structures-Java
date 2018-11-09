import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final int n;
    private final int trials;
    private final double[] numPercolations;
    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;

    public PercolationStats(int n, int trials) {
        if (n <= 0) {
            throw new IllegalArgumentException("Cannot have a matrix with non-positive length and width");
        }
        if (trials <= 0) {
            throw new IllegalArgumentException("Cannot have non-positive number of trials");
        }
        this.n = n;
        this.trials = trials;
        this.numPercolations = new double[trials];
        runTrials();
        mean = StdStats.mean(numPercolations);
        stddev = StdStats.stddev(numPercolations);
        double zValue = (1.96 * stddev()) / Math.sqrt(trials);
        confidenceLo = mean - zValue;
        confidenceHi = mean + zValue;

    }


    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLo() {
        return confidenceLo;
    }

    public double confidenceHi() {
        return confidenceHi;
    }

    private int randomSample() {
        return StdRandom.uniform(1, n + 1);
    }

    private double runSingleTrial() {
        int numSteps = 0;
        Percolates perc = new Percolates(n);
        while (!perc.percolates()) {
            int randInt1 = randomSample();
            int randInt2 = randomSample();
            if (!perc.isOpen(randInt1, randInt2)) {
                perc.open(randInt1, randInt2);
                numSteps++;
            }
        }
        return (double) numSteps / (n * n);
    }


    private void runTrials() {
        for (int j = 0; j < trials; j++) {
            numPercolations[j] = runSingleTrial();
        }
    }


    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(N, T);
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = " + stats.confidenceLo() + ", " + stats.confidenceHi());
    }
}

