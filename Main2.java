public class Main2 {

    public static void main(String args[]){
        MultiMultiBloomFilter bf = new MultiMultiBloomFilter(100, 10);
        for (int i = 0; i < 100; i++){
            String word = "hi" + i;
            bf.add(word);
        }

        FalsePositives fp = new FalsePositives(bf);
        for (int i = 0; i < 1000; i++){
            String word = "hello" + i;
            fp.check(word, bf);
        }
        System.out.println("Report: ");
        System.out.println("Checks: " + fp.getTotalChecks());
        System.out.println("False Positives: " + fp.getFalsePositives());
        System.out.println("False Positive Rate: " + fp.getFalsePositiveRate());

    }
}