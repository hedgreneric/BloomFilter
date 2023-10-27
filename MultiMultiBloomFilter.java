import java.math.BigInteger;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;

public class MultiMultiBloomFilter {
    public int setSize;

    public int bitsPerElement;

    BitSet[] multiBitArr;
    public BitSet bitArray;

    public int dataSize;

    public int randomPrime;

    public MultiMultiBloomFilter(int setSize, int bitsPerElement) {
        this.setSize = setSize;
        this.bitsPerElement = bitsPerElement;
        multiBitArr = new BitSet[(int) Math.ceil(numHashes())];
        for (int i = 0; i < numHashes(); i++) {
            multiBitArr[i] = new BitSet(setSize);
        }
        dataSize = 0;
        randomPrime = findPrimeLargerThanM(this.filterSize());
    }

    public void add (String s){
        s = s.toLowerCase();
        double x = numHashes();
        if (!this.appears(s)) {
            dataSize++;
        }

        for(int i = 0; i < numHashes(); i++){
            int index = (int) mmHash(s, i);
            multiBitArr[i].set(index);
        }
    }

    public boolean appears (String s){
        s = s.toLowerCase();
        double x = numHashes();
        boolean flag = true;
        for(int i = 0; i < numHashes(); i++){
            int index = (int) mmHash(s, i);
            if (!this.getBit(index, i)){
                flag = false;
            }
        }
        return flag;
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

    public boolean getBit(int index, int k) {
        return multiBitArr[k].get(index);
    }

    public long mmHash (String s, int i) {
        int m = this.filterSize();
        int x = s.hashCode();
        x = Math.abs(x);

        Random rand = new Random(x*i);
        long a = rand.nextInt(randomPrime);
        long b = rand.nextInt(randomPrime);

        return ((a * x + b)%randomPrime)%setSize;
    }

    public int findPrimeLargerThanM(int m){
        Random rand = new Random(m);
        BigInteger prime = BigInteger.valueOf(rand.nextInt(m/10) + m);
        while(!prime.isProbablePrime(100)){
            prime = BigInteger.valueOf(rand.nextInt(m/10) + m);
        }
        return prime.intValue();
    }

    public boolean isPrime(int n){
        boolean flag = true;
        for (int i = 2; i <= n / 2; ++i) {
            if (n % i == 0) {
                flag = false;
            }
        }
        return flag;
    }
}
