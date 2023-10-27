import java.util.BitSet;
import java.util.Arrays;

public class FalsePositives<T> {

    T bf;
    private int falsePositives;
    private int totalChecks;

    public FalsePositives(T bf){
        this.bf = bf;
        this.falsePositives = 0;
        this.totalChecks = 0;
    }

    /*
    BloomFilterFNV
     */
    public void check(String s, BloomFilterFNV bf) {
        totalChecks++;
        if (bf.appears(s)) {
            falsePositives++;
        }
    }

    /*
    BloomFilterRan
     */
    public void check(String s, BloomFilterRan bf) {
        totalChecks++;
        if (bf.appears(s)) {
            falsePositives++;
        }
    }

    /*
    BloomFilterRanPlus
     */
    public void check(String s, BloomFilterRanPlus bf) {
        totalChecks++;
        if (bf.appears(s)) {
            falsePositives++;
        }
    }

    /*
    MultiMultiBloomFilter
     */
    public void check(String s, MultiMultiBloomFilter bf) {
        totalChecks++;
        if (bf.appears(s)) {
            falsePositives++;
        }
    }

    public double getFalsePositiveRate() {
        return (double) falsePositives / totalChecks;
    }

    public int getTotalChecks(){
        return totalChecks;
    }
    public int getFalsePositives(){
        return falsePositives;
    }


}
