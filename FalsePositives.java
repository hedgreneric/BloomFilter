import java.util.BitSet;
import java.util.Arrays;

public class FalsePositives {

    BloomFilterFNV bfFNV;
    private int falsePositives;
    private int totalChecks;

    public FalsePositives(BloomFilterFNV bf){
        this.bfFNV = bf;
        this.falsePositives = 0;
        this.totalChecks = 0;
    }

    public void check(String s) {
        totalChecks++;
        if (bfFNV.appears(s) && !bfFNV.items.contains(s)) {
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
