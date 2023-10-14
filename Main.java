public class Main {

    public static void main(String args[]){
        BloomFilterFNV bf = new BloomFilterFNV(100, 10);
        for (int i = 0; i < 100; i++){
            String word = "hi" + i;
            bf.add(word);
        }
        //System.out.println(bf.bitArray);
        System.out.println(bf.appears("hi100"));


        BloomFilterFNV bf2 = new BloomFilterFNV(100, 10);
        for (int i = 100; i < 200; i++){
            String word = "hi" + i;
            bf2.add(word);
        }
        //System.out.println(bf.bitArray);
        System.out.println(bf2.appears("hi100"));


        System.out.println(Statistics.estimateSetSize(bf));
        System.out.println(Statistics.estimateIntersectSize(bf, bf2));

    }
}
