package google.file;

import java.io.*;

/**
 * Created by yingtan on 11/8/15.
 */
public class ReadFile {

    public static void main(String[] args) {
        File file = new File("");
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufReader = new BufferedReader(reader);

            String str;

            while((str = bufReader.readLine()) != null) {
                System.out.println(str);
            }

        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();;
        }
        catch (java.io.IOException e) {
            e.printStackTrace();;
        }

    }
}
