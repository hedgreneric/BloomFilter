public class Main {

    public static void main(String args[]){
        BloomFilterRanPlus bf = new BloomFilterRanPlus(1000, 10);
        for (int i = 0; i < 1000; i++){
            String word = "hi" + i;
            bf.add(word);
        }
        System.out.println(bf.appears("hi100"));


        BloomFilterRanPlus bf2 = new BloomFilterRanPlus(1000, 10);
        for (int i = 500; i < 1500; i++){
            String word = "hi" + i;
            bf2.add(word);
        }
        System.out.println(bf2.appears("hi100"));

//        System.out.println(Statistics.estimateSetSize(bf));
//        System.out.println(Statistics.estimateIntersectSize(bf, bf2));

    }
}