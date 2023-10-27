import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.math.BigInteger;
import java.util.Random;

public class BloomFilterRan {
    public int setSize;

    public int bitsPerElement;

    public BitSet bitArray;

    public int dataSize;

    public int randomPrime;

    public BloomFilterRan(int setSize, int bitsPerElement) {
        this.setSize = setSize;
        this.bitsPerElement = bitsPerElement;
        bitArray = new BitSet(filterSize());
        dataSize = 0;
        randomPrime = findPrimeLargerThanM(this.filterSize());
    }

    public void add (String s){
        s = s.toLowerCase();
        if (!this.appears(s)){
            dataSize++;
        }
        for(int i = 0; i < numHashes(); i++){
            int index = (int) ranHash(s, i);
            bitArray.set(index);
        }
    }

    public boolean appears (String s){
        s = s.toLowerCase();
        double x = numHashes();
        int finalIndex = 0;
        int numTimes = 0;
        for(int i = 0; i < numHashes(); i++){
            int index = (int) ranHash(s, i);
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

//    public int ranHash (String s, int i) {
//        int m = this.filterSize();
//        int p = findPrimeLargerThanM(m, i);
//        int x = s.hashCode()%p;
//        x = Math.abs(x);
//        Random rand = new Random(i);
//        int a = rand.nextInt(p);
//        int b = rand.nextInt(p);
//
//        return (a * x + b)%p;
//    }

    public long ranHash (String s, int i) {
        int m = this.filterSize();
        int x = s.hashCode();
        long y = (Math.abs(x));
        Random rand = new Random(i * x);
        long a = rand.nextInt(randomPrime);
        long b = rand.nextInt(randomPrime);

        return ((a*y) + b)%randomPrime;
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

