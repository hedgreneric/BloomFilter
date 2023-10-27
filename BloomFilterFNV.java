import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.math.BigInteger;

public class BloomFilterFNV {
    public int setSize;

    public int bitsPerElement;

    public BitSet bitArray;

    public int dataSize;

//    static BigInteger offsetBasis = new BigInteger("14695981039346656037");
//    static BigInteger fnvPrime = new BigInteger("109951168211");
    private static final long FNV_64_INIT = 0xcbf29ce484222325L;
    private static final long FNV_64_PRIME = 0x100000001b3L;


    public BloomFilterFNV(int setSize, int bitsPerElement) {
        this.setSize = setSize;
        this.bitsPerElement = bitsPerElement;
        bitArray = new BitSet(filterSize());
        dataSize = 0;
    }

    public void add (String s){
        s = s.toLowerCase();
        if (!appears(s)) {
            dataSize++;
        }
        for(int i = 0; i < numHashes(); i++){
            int index = (int) fnvHash(s, i);
            bitArray.set(index);
        }
    }

    public boolean appears (String s){
        s = s.toLowerCase();
        double x = numHashes();
        int finalIndex = 0;
        int numTimes = 0;
        for(int i = 0; i < numHashes(); i++){
            int index = (int) fnvHash(s, i);
            if (this.getBit(index)){
                numTimes++;
            }
            finalIndex++;
        }
        return numTimes == finalIndex;
    }

    public int filterSize() {
        return setSize*bitsPerElement;
    }

    public int dataSize() {
        return dataSize;
    }

    public double numHashes() {
        return (Math.log(2) * filterSize()) / setSize;
    }

    public boolean getBit(int j){
        return bitArray.get(j);
    }

    public long fnvHash (String s, int i) {
        String data;
        long hash = FNV_64_INIT;
        for (int j = 0; j < s.length(); j++){
            char character = s.charAt(j);
            data = String.valueOf(character + i);
            int hashcode = data.hashCode();
            hash ^= hashcode;
            hash *= FNV_64_PRIME;
        }
        hash = Math.abs(hash%filterSize());
        return hash;
    }
}
