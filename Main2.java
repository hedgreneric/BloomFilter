public class Main2 {

    public static void main(String args[]){
        BloomFilterRan bf = new BloomFilterRan(100, 10);
        for (int i = 0; i < 100; i++){
            String word = "hi" + i;
            bf.add(word);
        }
        System.out.println(bf.appears("hi1000"));
    }
}