/*
 * This file provides two methods to be used with wordDictionary
 * to facilitate writing strings to a file and reading them back.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtilities {
    public static List<String> readFileFromDisk(String pathname){
        List<String> result=null;
        try{
            result = Files.readAllLines(Paths.get(pathname), Charset.defaultCharset());
        }
        catch(Exception e){
            System.out.println("Couldn't read file "+pathname);
            e.printStackTrace();
        }

        return result;
    }


    public static void writeLinesToFile(String fileName, List<String> toWrite) {
        File toCreate = new File(fileName);
        Writer writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(toCreate));
            for (String lineToWrite : toWrite)
                writer.write(lineToWrite + "\r\n");
        } catch (Exception e) {
            System.out.println("Unable to write file");
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch(IOException e){
                //e.printStackTrace();
            }
        }

    }

}
