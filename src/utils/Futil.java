package utils;

import dataStructures.FileContent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Futil {
    public static FileContent retrieveFileContent(String separator, boolean hasHeaders, String filePath) {
        String[] headers = null;
        ArrayList<String[]> rowsData = null;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            if (hasHeaders) {
                headers = bufferedReader.readLine().split(separator);
            }
            String line;
            rowsData = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                rowsData.add(line.split(separator));
            }
        } catch (IOException e) {
            System.out.println("Failed to load rows data from file: " + e.getMessage());
        }

        return new FileContent(headers, rowsData); // idc if you are null or not :D
    }
}
