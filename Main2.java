public class Main2 {

    public static void main(String args[]){
        BloomFilterRanPlus bf = new BloomFilterRanPlus(1000, 50);
        for (int i = 0; i < 1000; i++){
            String word = "hi" + i;
            bf.add(word);
        }
        System.out.println(bf.bitArray);
        System.out.println(bf.appears("hi1"));
        int falsePositives = 0;
//        for (int i = 0; i < 100000; i++){
//            if (bf.appears("Does this somehow fit" + i)){
//                falsePositives++;
//            }
//        }
        System.out.println(falsePositives);
    }
}