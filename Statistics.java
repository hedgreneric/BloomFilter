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
        double t = a / (double) -k;
        return t;
    }
}
