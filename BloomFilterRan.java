import java.util.Arrays;
import java.util.BitSet;
import java.math.BigInteger;
import java.util.Random;

public class BloomFilterRan {
    public int setSize;

    public int bitsPerElement;

    public BitSet bitArray;

    public int dataSize;

    public BloomFilterRan(int setSize, int bitsPerElement) {
        this.setSize = setSize;
        this.bitsPerElement = bitsPerElement;
        bitArray = new BitSet(filterSize());
        dataSize = 0;
    }

    public void add (String s){
        s = s.toLowerCase();
        double x = numHashes();
        for(int i = 0; i < numHashes(); i++){
            int index = ranHash(s, i);
            bitArray.set(index);
        }
        dataSize++;
    }

    public boolean appears (String s){
        s = s.toLowerCase();
        double x = numHashes();
        boolean flag = true;
        for(int i = 0; i < numHashes(); i++){
            int index = ranHash(s, i);
            if (this.getBit(index) == false){
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

    public boolean getBit(int j){
        return bitArray.get(j);
    }

    public int ranHash (String s, int i) {
        int m = this.filterSize();
        int p = findPrimeLargerThanM(m, i);
        int x = s.hashCode()%p;
        x = Math.abs(x);
        Random rand = new Random(i);
        int a = rand.nextInt(p);
        int b = rand.nextInt(p);

        return (a * x + b)%p;
    }

    public int findPrimeLargerThanM(int m, int i){
        Random rand = new Random(i);
        int prime = rand.nextInt(100) + m;
        while(!isPrime(prime)){
            prime = rand.nextInt(100) + m;
        }
        return prime;
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

