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
        key = key.toLowerCase();
        Scanner scan = new Scanner(diff);
        while (scan.hasNextLine()){
            String record = scan.nextLine();
            record = record.toLowerCase();
            String[] recordElements = record.split(" ");
            String elementInRecord = recordElements[0] + " " + recordElements[1] + " " + recordElements[2] + " " + recordElements[3];
            if (elementInRecord.equals(key)){
                return record;
            }
        }
        scan.close();
        return "Not in diff, Searched DB";
    }

}
