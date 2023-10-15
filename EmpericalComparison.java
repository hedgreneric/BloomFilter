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
        bloom.retrieveRecord(key);
        long end = System.nanoTime();
        long bloomDuration = (start - end);
        start = System.nanoTime();
        naive.retrieveRecord(key);
        end = System.nanoTime();
        long naiveDuration = (start - end);
        return bloomDuration > naiveDuration;
    }
}
