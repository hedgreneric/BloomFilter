import java.util.Arrays;
import java.util.BitSet;
import java.math.BigInteger;

public class BloomFilterFNV {
    public int setSize;

    public int bitsPerElement;

    public BitSet bitArray;

    public int dataSize;

//    static long fnvInit = 0xcbf29ce484222325L;
//    static long offsetBasis = 14695981039346656037L;

    static BigInteger offsetBasis = new BigInteger("14695981039346656037");
    static long fnvPrime = 0x3bL;


    public BloomFilterFNV(int setSize, int bitsPerElement) {
        this.setSize = setSize;
        this.bitsPerElement = bitsPerElement;
        bitArray = new BitSet(filterSize());
        dataSize = 0;
    }

    public void add (String s){
        s = s.toLowerCase();
        for(int i = 0; i < numHashes(); i++){
            int index = fnvHash(s, i).intValue();
            bitArray.set(index);
        }
        if (!appears(s)) {
            dataSize++;
        }
    }

    public boolean appears (String s){
        BigInteger index = fnvHash(s, 0);

        return bitArray.get(index.intValue());
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

    public static BigInteger fnvHash (String s, int i) {
        String data = s;
        BigInteger hash = offsetBasis;
        BigInteger bigIntFnvPrime = BigInteger.valueOf(fnvPrime);
        byte[] byteArr = data.getBytes();
        for (byte b : byteArr){
            BigInteger bigIntByte = BigInteger.valueOf(b);
            hash = hash.multiply(bigIntFnvPrime);
            hash = bigIntByte.xor(hash);
        }
        return hash;
    }
}
