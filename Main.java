import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String args[]) throws FileNotFoundException {
        BloomFilterFNV bf = new BloomFilterFNV(10000, 10);
        for (int i = 0; i < 10000; i++){
            String word = "hi" + i;
            bf.add(word);
        }
        System.out.println(bf.appears("hi100"));
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
//        File f = new File("C:\\Users\\Maxwe\\Downloads\\pa1Data\\pa1Data\\database.txt\\database.txt");
//        File f1 = new File("C:\\Users\\Maxwe\\Downloads\\pa1Data\\pa1Data\\DiffFile.txt\\DiffFile.txt");
//        EmpericalComparison e = new EmpericalComparison(f, f1);
//        System.out.println("max is a boss");
//        e.isBloomFasterThanNaive("max is a boss", e.bd, e.nd);
//        System.out.println("max is a g");
//        e.isBloomFasterThanNaive("max is a g", e.bd, e.nd);
//        System.out.println("eric is a boss");
//        e.isBloomFasterThanNaive("eric is a boss", e.bd, e.nd);
//        System.out.println("gavin is a boss");
//        e.isBloomFasterThanNaive("gavin is a boss", e.bd, e.nd);
//        System.out.println("tate is a boss");
//        e.isBloomFasterThanNaive("tate is a boss", e.bd, e.nd);

    }
}