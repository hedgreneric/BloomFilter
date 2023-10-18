import java.util.BitSet;

import static java.lang.Math.log;

public class Statistics<T> {

    T f1;
    T f2;


    public Statistics(T f1) {
        this.f1 = f1;
    }

    public Statistics(T f1, T f2) {
        this.f1 = f1;
        this.f2 = f2;
    }



    /*
    BloomFilterFNV
     */
    public static int estimateSetSize (BloomFilterFNV f) {
        int m = f.filterSize();
        int k = (int) Math.ceil(f.numHashes());
        int Z = 0;
        for (int i = 0; i < m; i++) {
            if (!f.getBit(i)) {
                Z++;
            }
        }
        return (int) (-m / (double) k * log(1 - Z / (double) m));

    }

    public static double estimateIntersectSize (BloomFilterFNV f1, BloomFilterFNV f2) {
        if (f1.filterSize() != f2.filterSize() || f1.numHashes() != f2.numHashes()) {
            throw new IllegalArgumentException("The filters must have the same size and number of hash functions");
        }

        int m = f1.filterSize();
        int k = (int) Math.ceil(f1.numHashes());
        int Z1 = 0;
        int Z2 = 0;
        int Z = 0;

        for (int i = 0; i < m; i++) {
            if (!f1.getBit(i)) {
                Z1++;
            }
            if (!f2.getBit(i)) {
                Z2++;
            }
            if (!f1.getBit(i) && !f2.getBit(i)) {
                Z++;
            }
        }
        double u = (m * (Z1 + Z2 - Z)) / (double) (Z1 * Z2);
        double a = Math.log(u) / Math.log(1 - ((double) 1/m));
        return a / (double) -k;
    }


    /*
        BloomFilterRan
    */
    public static int estimateSetSize (BloomFilterRan f) {
        int m = f.filterSize();
        int k = (int) Math.ceil(f.numHashes());
        int Z = 0;
        for (int i = 0; i < m; i++) {
            if (!f.getBit(i)) {
                Z++;
            }
        }
        return (int) (-m / (double) k * log(1 - Z / (double) m));

    }

    public static double estimateIntersectSize (BloomFilterRan f1, BloomFilterRan f2) {
        if (f1.filterSize() != f2.filterSize() || f1.numHashes() != f2.numHashes()) {
            throw new IllegalArgumentException("The filters must have the same size and number of hash functions");
        }

        int m = f1.filterSize();
        int k = (int) Math.ceil(f1.numHashes());
        int Z1 = 0;
        int Z2 = 0;
        int Z = 0;

        for (int i = 0; i < m; i++) {
            if (!f1.getBit(i)) {
                Z1++;
            }
            if (!f2.getBit(i)) {
                Z2++;
            }
            if (!f1.getBit(i) && !f2.getBit(i)) {
                Z++;
            }
        }
        double u = (m * (Z1 + Z2 - Z)) / (double) (Z1 * Z2);
        double a = Math.log(u) / Math.log(1 - ((double) 1/m));
        return a / (double) -k;
    }


    /*
    BloomFilterRanPlus
     */
    public static int estimateSetSize (BloomFilterRanPlus f) {
        int m = f.filterSize();
        int k = (int) Math.ceil(f.numHashes());
        int Z = 0;
        for (int i = 0; i < m; i++) {
            if (!f.getBit(i)) {
                Z++;
            }
        }
        return (int) (-m / (double) k * log(1 - Z / (double) m));

    }

    public static double estimateIntersectSize (BloomFilterRanPlus f1, BloomFilterRanPlus f2) {
        if (f1.filterSize() != f2.filterSize() || f1.numHashes() != f2.numHashes()) {
            throw new IllegalArgumentException("The filters must have the same size and number of hash functions");
        }

        int m = f1.filterSize();
        int k = (int) Math.ceil(f1.numHashes());
        int Z1 = 0;
        int Z2 = 0;
        int Z = 0;

        for (int i = 0; i < m; i++) {
            if (!f1.getBit(i)) {
                Z1++;
            }
            if (!f2.getBit(i)) {
                Z2++;
            }
            if (!f1.getBit(i) && !f2.getBit(i)) {
                Z++;
            }
        }
        double u = (m * (Z1 + Z2 - Z)) / (double) (Z1 * Z2);
        double a = Math.log(u) / Math.log(1 - ((double) 1/m));
        return a / (double) -k;
    }

    /*
    MultiMultiBloomFilter
     */
    public static double estimateSetSize (MultiMultiBloomFilter f) {
        int m = f.filterSize();
        int n = f.multiBitArr[0].length();
        BitSet arr = f.multiBitArr[0];
        int k = (int) Math.ceil(f.numHashes());
        double Z = 0;
        boolean containsI = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                if (!f.getBit(i, j)) {
                    Z++;
                }
            }
        }
        double firstPart = -n/k;
        double secondPart = Math.log(1 - (Z / (k*n)));
        return firstPart * secondPart;

    }

    public static double estimateIntersectSize (MultiMultiBloomFilter f1, MultiMultiBloomFilter f2) {
        if (f1.filterSize() != f2.filterSize() || f1.numHashes() != f2.numHashes()) {
            throw new IllegalArgumentException("The filters must have the same size and number of hash functions");
        }

        int m = f1.filterSize();
        int k = (int) Math.ceil(f1.numHashes());
        int Z1 = 0;
        int Z2 = 0;
        int Z = 0;
        for (int j = 0; j < k; j++) {
            for (int i = 0; i < m; i++) {
                if (!f1.getBit(i, j)) {
                    Z1++;
                }
                if (!f2.getBit(i, j)) {
                    Z2++;
                }
                if (!f1.getBit(i, j) && !f2.getBit(i, j)) {
                    Z++;
                }
            }
        }
        double u = (m * (Z1 + Z2 - Z)) / (double) (Z1 * Z2);
        double a = Math.log(u) / Math.log(1 - ((double) 1/m));
        return a / (double) -k;
    }
}
