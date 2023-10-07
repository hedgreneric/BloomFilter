import java.util.Arrays;
import java.util.BitSet;

public class BloomFilterFNV {
    public int setSize;

    public int bitsPerElement;

    public BitSet bitArray;

    public int dataSize;

    static long fnvInit = 0xcbf29ce484222325L;

    static long fnvPrime = 0x100000001b3L;


    public BloomFilterFNV(int setSize, int bitsPerElement) {
        this.setSize = setSize;
        this.bitsPerElement = bitsPerElement;
        bitArray = new BitSet(filterSize());
        dataSize = 0;
    }

    public void add (String s){
        s = s.toLowerCase();
        for(int i = 0; i < numHashes(); i++){
            int index = (int) hash(s, i);
            bitArray.set(index);
        }
        dataSize++;
    }

    public boolean appears (String s){
        return true;
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
        return true;
    }

    public static long hash(String s, int i){
        String data = s + i;
        byte[] byteArray = data.getBytes();
        long init = fnvInit;
        for (int j = 0; j < data.length(); j++) {
            init = data.charAt(j) ^ init;
            init *= ((init * fnvPrime) % Math.pow(2, 64));
        }
        return init;
    }


}
