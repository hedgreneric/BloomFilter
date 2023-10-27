import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.BitSet;

import static java.lang.Math.log;

public class Statistics {

    BloomFilterFNV f1;
    BloomFilterFNV f2;


    public Statistics(BloomFilterFNV f1) {
        this.f1 = f1;
    }

    public Statistics(BloomFilterFNV f1, BloomFilterFNV f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    /*
    BloomFilterFNV
     */
    public static int estimateSetSize(BloomFilterFNV f) {
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

    public static double estimateIntersectSize(BloomFilterFNV f1, BloomFilterFNV f2) {
        if (f1.filterSize() != f2.filterSize() || f1.numHashes() != f2.numHashes()) {
            throw new IllegalArgumentException("The filters must have the same size and number of hash functions");
        }

        BitSet b1 = f1.bitArray;
        BitSet b2 = f2.bitArray;
        BitSet b = (BitSet) b1.clone();
        b.and(b2);

        int m = f1.filterSize();
        int k = (int) Math.ceil(f1.numHashes());
        int Z1 = m - b1.cardinality();
        int Z2 = m - b2.cardinality();
        int Z = m - b.cardinality();

        double numerator = m * (Z1 + Z2 - Z);
        double denominator = Z1 * Z2;
        double u = numerator / denominator;
        double logNum = Math.log(u);
        double logDen = -k * Math.log(1 - ((double) 1 / m));
        return logNum / logDen;
    }
}