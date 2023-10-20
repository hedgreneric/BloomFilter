import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BloomDifferential {
    File db;
    File diff;

    int diffCount = 0;

    int dbCount = 0;

    BloomFilterFNV filter;
    public BloomDifferential(File databaseFile, File diffFile) throws FileNotFoundException {
        this.db = databaseFile;
        this.diff = diffFile;
        Scanner scan = new Scanner(diff);
        while(scan.hasNextLine()){
            scan.nextLine();
            diffCount++;
        }
        scan.close();
        this.filter = createFilter();
    }

    public BloomFilterFNV createFilter() throws FileNotFoundException {
        BloomFilterFNV filter = new BloomFilterFNV(diffCount, 10);
        Scanner scan = new Scanner(diff);
        while (scan.hasNextLine()){
            String element = scan.next()+ " " + scan.next() + " " + scan.next() + " " + scan.next();
            filter.add(element);
            scan.nextLine();
        }
        scan.close();
        return filter;
    }

    public String retrieveRecord(String key) throws FileNotFoundException {
        if(filter.appears(key)){
            Scanner scan = new Scanner(diff);
            while (scan.hasNextLine()){
                String record = scan.nextLine();
                String elementInRecord = scan.next()+ " " + scan.next() + " " + scan.next() + " " + scan.next();
                if (elementInRecord.equals(key)){
                    return record;
                }
            }
            scan.close();
        }
        return "Not in bloom filter, searched DB";
    }
}
