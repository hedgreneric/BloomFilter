import java.util.Arrays;
import java.util.BitSet;

public class BloomFilterFNV {
    public int setSize;
    public int bitsPerElement;

    public BitSet bitArray;
    public int dataSize;

    public BloomFilterFNV(int setSize, int bitsPerElement) {
        this.setSize = setSize;
        this.bitsPerElement = bitsPerElement;
        bitArray = new BitSet(filterSize());
        dataSize = 0;
    }

    public void add (String s){
        
        dataSize++;
    }

    public boolean appears (String s){
        return true;
    }

    public int filterSize() {
        return setSize/bitsPerElement;
    }

    public int dataSize() {
        return dataSize;
    }

    public double numHashes() {
        return (Math.log(2) * filterSize()) / setSize;
    }

    public boolean getBit(int j){
        return true;
    }


}
