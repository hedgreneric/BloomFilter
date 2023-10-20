import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String args[]) throws FileNotFoundException {
//        BloomFilterRanPlus bf = new BloomFilterRanPlus(1000, 10);
//        for (int i = 0; i < 1000; i++){
//            String word = "hi" + i;
//            bf.add(word);
//        }
//        System.out.println(bf.appears("hi100"));
//
//
//        BloomFilterRanPlus bf2 = new BloomFilterRanPlus(1000, 10);
//        for (int i = 500; i < 1500; i++){
//            String word = "hi" + i;
//            bf2.add(word);
//        }
//        System.out.println(bf2.appears("hi100"));
//
//        System.out.println(Statistics.estimateSetSize(bf));
//        System.out.println(Statistics.estimateIntersectSize(bf, bf2));
        File f = new File("C:\\Users\\hedgr_v6euno5\\git-repos-isu\\pa1Data\\database.txt\\database.txt");
        File f1 = new File("C:\\Users\\hedgr_v6euno5\\git-repos-isu\\pa1Data\\DiffFile.txt\\DiffFile.txt");
        EmpericalComparison e = new EmpericalComparison(f, f1);
        e.isBloomFasterThanNaive("ARE THE GOALS OF", e.bd, e.nd);

    }
}