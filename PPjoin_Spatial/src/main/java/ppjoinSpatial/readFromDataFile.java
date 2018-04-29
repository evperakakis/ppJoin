package ppjoinSpatial;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class readFromDataFile {

    public static ArrayList<Entry> read(ArrayList<Entry> list, String filePath) {

        String line; // This will reference one line at a time
        String toAddValue;
        String toAddX;
        String toAddY;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(filePath);

            // Wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
                toAddValue = StringUtils.substringBetween(line, "e=", ", g"); //get subString
                toAddX = StringUtils.substringBetween(line, "x=", ", y");
                toAddY = StringUtils.substringBetween(line, " y=", "]");

                if (toAddValue != null) {          //IF IS NEEDED TO AVOID NULL VALUES
                    //System.out.println(toAddValue + " ---> " + toAddX + " " + toAddY);
                    list.add(new Entry(toAddValue, Double.parseDouble(toAddX), Double.parseDouble(toAddY)));
                }

            }

            // Close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            filePath + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + filePath + "'");
        }


        return list;


    }

    public static ArrayList<Entry> readToNgrams(ArrayList<Entry> list, String filePath, int n) { // n for n-grams

        String line; // This will reference one line at a time
        String toAddValue;
        String toAddX;
        String toAddY;
        ArrayList<String> ngrams = new ArrayList<>();


        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(filePath);

            // Wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                toAddValue = StringUtils.substringBetween(line, "e=", ", g"); //get subString
                toAddX = StringUtils.substringBetween(line, "x=", ", y");
                toAddY = StringUtils.substringBetween(line, " y=", "]");

                if (toAddValue != null) {          //IF IS NEEDED TO AVOID NULL VALUES
                    for (int i = 0; i < toAddValue.length() - n + 1; i++) {
                        ngrams.add(toAddValue.substring(i, i + n));
                    }
                    list.add(new Entry(ngrams, Double.parseDouble(toAddX), Double.parseDouble(toAddY)));
                    ngrams.clear();
                }

            }

            // Close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            filePath + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + filePath + "'");
        }


        return list;


    }
}
