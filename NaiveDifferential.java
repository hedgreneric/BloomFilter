import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NaiveDifferential {

    File db;
    File diff;
    public NaiveDifferential(File databaseFile, File diffFile) throws FileNotFoundException {
        this.db = databaseFile;
        this.diff = diffFile;
    }
    public String retrieveRecord(String key) throws FileNotFoundException {
        Scanner scan = new Scanner(diff);
        while (scan.hasNextLine()){
            String record = scan.nextLine();
            String elementInRecord = scan.next()+ " " + scan.next() + " " + scan.next() + " " + scan.next();
            if (elementInRecord.equals(key)){
                return record;
            }
        }
        scan.close();
        return "Not in diff, Searched DB";
    }

}
