public class Main {

    public static void main(String args[]){
        BloomFilterFNV bf = new BloomFilterFNV(3, 8);
        bf.add("A");
        System.out.println(bf.bitArray);
        bf.add("B");
        System.out.println((bf.bitArray));
        bf.add("b");
        System.out.println((bf.bitArray));
    }
}
