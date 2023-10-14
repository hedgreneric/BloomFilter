import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.math.BigInteger;

public class BloomFilterFNV {
    public int setSize;

    public int bitsPerElement;

    public BitSet bitArray;

    public int dataSize;

    static BigInteger offsetBasis = new BigInteger("14695981039346656037");
    static BigInteger fnvPrime = new BigInteger("109951168211");

    ArrayList<String> items = new ArrayList<String>();


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
            int index = fnvHash(s, i);
            bitArray.set(index);
        }
        items.add(s);
    }

    public boolean appears (String s){
        s = s.toLowerCase();
        double x = numHashes();
        int finalIndex = 0;
        int numTimes = 0;
        for(int i = 0; i < numHashes(); i++){
            int index = fnvHash(s, i);
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

    public int fnvHash (String s, int i) {
        String data;
        BigInteger hash = offsetBasis;
        for (int j = 0; j < s.length(); j++){
            char character = s.charAt(j);
            data = String.valueOf(character + i);
            int hashcode = data.hashCode();
            hash = BigInteger.valueOf(hashcode).xor(hash);
            hash = hash.multiply(fnvPrime);
        }
        return Math.abs(hash.intValue()%filterSize());
    }
}
