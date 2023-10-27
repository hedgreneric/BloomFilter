import java.math.BigDecimal;
import java.math.BigInteger;
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
        BigInteger Z1 = BigInteger.valueOf(m - b1.cardinality());
        BigInteger Z2 = BigInteger.valueOf(m - b2.cardinality());
        BigInteger Z = BigInteger.valueOf(m - b.cardinality());

        BigInteger e = Z1.add(Z2).subtract(Z);
        BigInteger numerator = BigInteger.valueOf(m).multiply(e);
        BigInteger denominator = Z1.multiply(Z2);

        BigDecimal num = new BigDecimal(numerator);
        BigDecimal den = new BigDecimal(denominator);

        BigDecimal u = num.divide(den, 5 ,RoundingMode.HALF_UP);
        double logNum = Math.log(u.doubleValue());
        double logDen = -k * Math.log(1 - ((double) 1 / m));
        return logNum / logDen;
    }
}