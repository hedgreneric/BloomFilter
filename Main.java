public class Main {

    public static void main(String args[]){
        BloomFilterFNV bf = new BloomFilterFNV(100, 10);
        for (int i = 0; i < 100; i++){
            String word = "hi" + i;
            bf.add(word);
        }
        System.out.println(bf.bitArray);
        System.out.println(bf.appears("hi100"));
    }
}
