package be.jonasboon.fileinputconverter.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.String;

public abstract class InputConverter<T> implements IInputConverter{
    public String fileDirectory;
    List<T> objectsFromFile = new ArrayList<T>();

    /**
     * @param lineFromFile
     * This is a line from the input file. It's the implementations job to
     * convert it to the object it desires.
     * @return
     * The Object that will be converted from the file.
     * This is the generic mentioned above.
     */
    abstract T mapObjectFromFile(String lineFromFile);

    public List<T> getAllObjectsFromFile(String fileName){
        readFile(fileName);
        return objectsFromFile;
    }

    private void readFile(String fileName){
        try {
            Scanner scanner = new Scanner(lookUpFile(fileDirectory + fileName));
            while(scanner.hasNext()){
                String line = scanner.nextLine();
                System.out.println(line);
                objectsFromFile.add(mapObjectFromFile(line));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private File lookUpFile(String fileName) {
        File file = new File(fileName);
        return doesFileExist(file);
    }

    private File doesFileExist(File file){
        try {
            if (file.exists())
                return file;
            else
                throw new FileNotFoundException("File not found: " + file.getName());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
