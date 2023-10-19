import java.math.BigDecimal;
import java.math.RoundingMode;

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
        BigDecimal numerator = BigDecimal.valueOf(((long) m * (Z1 + Z2 - Z)));
        BigDecimal denominator = BigDecimal.valueOf(((long) Z1 * Z2));
        BigDecimal u = numerator.divide(denominator, 5, RoundingMode.HALF_UP);
        double logNum = Math.log(u.doubleValue());
        double logDenom = Math.log(1 - ((double) 1 / m));
        double logs = logNum / logDenom;
        return logs / (double) -k;
    }
}