package be.jonasboon.fileinputconverter.input;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

@Slf4j
public abstract class InputConverter<T> {
    public String fileDirectory;
    List<T> objectsFromFile = new ArrayList<T>();

    public InputConverter(String fileDirectory) {
        this.fileDirectory = fileDirectory;
    }

    /**
     * @param lineFromFile
     * This is a line from the input file. It's the implementations job to
     * convert it to the object it desires.
     * @return
     * The Object that will be converted from the file.
     * This is the generic mentioned above.
     */
    abstract T mapLineToObject(String lineFromFile);

    public List<T> getAllObjectsFromFile(String fileName){
        readFile(fileName);
        return objectsFromFile;
    }

    private void readFile(String fileName) {
            BufferedReader reader = makeReader(fileName);
            addLinesToList(reader);
    }

    private void addLinesToList(BufferedReader bis) {
        try {
            String line;
            while((line = bis.readLine()) != null) {
                objectsFromFile.add(mapLineToObject(line));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private BufferedReader makeReader(String fileName){
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileDirectory + fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            return new BufferedReader(inputStreamReader);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
