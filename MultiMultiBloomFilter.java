import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;

public class MultiMultiBloomFilter {
    public int setSize;

    public int bitsPerElement;

    BitSet[] multiBitArr;
    public BitSet bitArray;

    public int dataSize;

    ArrayList<String> items = new ArrayList<String>();

    public MultiMultiBloomFilter(int setSize, int bitsPerElement) {
        this.setSize = setSize;
        this.bitsPerElement = bitsPerElement;
        multiBitArr = new BitSet[(int) Math.ceil(numHashes())];
        for (int i = 0; i < numHashes(); i++) {
            multiBitArr[i] = new BitSet(setSize);
        }
        dataSize = 0;
    }

    public void add (String s){
        s = s.toLowerCase();
        if (!this.appears(s)) {
            dataSize++;
        }
        for(int i = 0; i < numHashes(); i++){
            int index = mmHash(s, i);
            multiBitArr[i].set(index);
        }
        items.add(s);
    }

    public boolean appears (String s){
        s = s.toLowerCase();
        double x = numHashes();
        boolean flag = true;
        for(int i = 0; i < numHashes(); i++){
            int index = mmHash(s, i);
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

    public int mmHash (String s, int i) {
        int m = this.filterSize();
        int p = findPrimeLargerThanM(m, i);
        int x = s.hashCode()%p;
        x = Math.abs(x);

        Random rand = new Random(x*i);
        int a = rand.nextInt(p);
        int b = rand.nextInt(p);

        return (a * x + b)%p;
    }

    public int findPrimeLargerThanM(int m, int i){
        Random rand = new Random(i);
        int prime = rand.nextInt(m) + m;
        while(!isPrime(prime)){
            prime = rand.nextInt(m) + m;
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
