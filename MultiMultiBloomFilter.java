import java.util.BitSet;
import java.util.Random;

public class MultiMultiBloomFilter {
    public int setSize;

    public int bitsPerElement;

    BitSet[] multiBitArr;
    public BitSet bitArray;

    public int dataSize;

    public MultiMultiBloomFilter(int setSize, int bitsPerElement) {
        this.setSize = setSize;
        this.bitsPerElement = bitsPerElement;
        multiBitArr = new BitSet[filterSize()];
        for (int i = 0; i < filterSize(); i++) {
            multiBitArr[i] = new BitSet();
        }
        dataSize = 0;
    }

    public void add (String s){
        s = s.toLowerCase();
        double x = numHashes();
        for(int i = 0; i < numHashes(); i++){
            int index = mmHash(s, i);
            multiBitArr[i].set(index);
        }
        dataSize++;
    }

    public boolean appears (String s){
        s = s.toLowerCase();
        double x = numHashes();
        boolean flag = true;
        for(int i = 0; i < numHashes(); i++){
            int index = mmHash(s, i);
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

    public boolean getBit(int j) {
        for (int i = 0; i < filterSize(); i++) {
            if (!multiBitArr[i].get(j)){
                return false;
            }
        }
        return true;
    }

    public int mmHash (String s, int i) {
        int m = this.filterSize();
        int p = findPrimeLargerThanM(m, i);
        int x = s.hashCode();
        x = Math.abs(x);

        // the first iteration i=0, but i must be positive
        Random rand = new Random(i);
        int a = rand.nextInt(i+1);
        int b = rand.nextInt(i+1);

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
