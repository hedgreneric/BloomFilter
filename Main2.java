public class Main2 {

    public static void main(String args[]){
        MultiMultiBloomFilter bf = new MultiMultiBloomFilter(100, 10);
        for (int i = 0; i < 100; i++){
            String word = "hi" + i;
            bf.add(word);
        }
        System.out.println(bf.appears("hi1000"));
    }
}