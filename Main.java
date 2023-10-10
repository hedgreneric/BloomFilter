public class Main {

    public static void main(String args[]){
        BloomFilterFNV bf = new BloomFilterFNV(100, 10);
        bf.add("Afwfe");
        System.out.println(bf.bitArray);
        bf.add("B");
        System.out.println((bf.bitArray));
        bf.add("b");
        System.out.println((bf.bitArray));
    }
}
