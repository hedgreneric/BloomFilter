public class Main2 {

    public static void main(String args[]){
        BloomFilterRan bf = new BloomFilterRan(50000, 10);
        for (int i = 0; i < 50000; i++){
            String word = "hi" + i;
            bf.add(word);
        }


        FalsePositives fp = new FalsePositives(bf);
        for (int i = 0; i < 10000; i++){
            String word = "hello" + i;
            fp.check(word, bf);
        }
        System.out.println("Report: ");
        System.out.println("Checks: " + fp.getTotalChecks());
        System.out.println("False Positives: " + fp.getFalsePositives());
        System.out.println("False Positive Rate: " + fp.getFalsePositiveRate());

    }
}