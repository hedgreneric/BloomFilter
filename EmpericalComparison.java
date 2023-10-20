import java.io.File;
import java.io.FileNotFoundException;

public class EmpericalComparison {

    File db;
    File diff;
    BloomDifferential bd;
    NaiveDifferential nd;

    public EmpericalComparison(File db, File diff) throws FileNotFoundException {
        this.db = db;
        this.diff = diff;
        this.bd = new BloomDifferential(this.db, this.diff);
        this.nd = new NaiveDifferential(this.db, this.diff);
    }

    public boolean isBloomFasterThanNaive(String key, BloomDifferential bloom, NaiveDifferential naive) throws FileNotFoundException {
        long start = System.nanoTime();
        System.out.println(bloom.retrieveRecord(key));
        long end = System.nanoTime();
        long bloomDuration = (end - start);
        start = System.nanoTime();
        System.out.println(naive.retrieveRecord(key));
        end = System.nanoTime();
        long naiveDuration = (end - start);
        System.out.println("Bloom Duration: " + bloomDuration);
        System.out.println("Naive Duration: " + naiveDuration);
        return bloomDuration > naiveDuration;
    }
}
