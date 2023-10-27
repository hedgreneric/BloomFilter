import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String args[]) throws FileNotFoundException {
        BloomFilterFNV bf = new BloomFilterFNV(20000, 10);
        for (int i = 0; i < 20000; i++){
            String word = "hi" + i;
            bf.add(word);
        }
        System.out.println(bf.appears("hi100"));


        BloomFilterFNV bf2 = new BloomFilterFNV(20000, 10);
        for (int i = 5000; i < 20000; i++){
            String word = "hi" + i;
            bf2.add(word);
        }
        System.out.println(bf2.appears("hi100"));

        System.out.println(Statistics.estimateSetSize(bf));
        System.out.println(Statistics.estimateIntersectSize(bf, bf2));
//        File f = new File("C:\\Users\\hedgr_v6euno5\\git-repos-isu\\pa1Data\\database.txt\\database.txt");
//        File f1 = new File("C:\\Users\\hedgr_v6euno5\\git-repos-isu\\pa1Data\\DiffFile.txt\\DiffFile.txt");
//        EmpericalComparison e = new EmpericalComparison(f, f1);
//
//        System.out.println("\"are swamped with work\"");
//        e.isBloomFasterThanNaive("are swamped with work", e.bd, e.nd);
//
//        System.out.println("\n\"ARE THE GOALS OF\"");
//        e.isBloomFasterThanNaive("ARE THE GOALS OF", e.bd, e.nd);
//
//        System.out.println("\n\"are strongly deformed in\"");
//        e.isBloomFasterThanNaive("are strongly deformed in", e.bd, e.nd);
//
//        System.out.println("\n\"Are so crazy ,\"");
//        e.isBloomFasterThanNaive("Are so crazy ,", e.bd, e.nd);
//
//        System.out.println("\n\"are structured in particular\"");
//        e.isBloomFasterThanNaive("are structured in particular", e.bd, e.nd);
    }
}