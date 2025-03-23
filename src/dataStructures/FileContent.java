package dataStructures;

import java.util.ArrayList;
import java.util.Arrays;

public class FileContent {
    private final int columns;
    private final String[] headers;
    private final ArrayList<String[]> rowsData;

    public FileContent(String[] headers, ArrayList<String[]> rowsData) {
        this.headers = (headers == null ? new String[rowsData.getFirst().length] : headers);

        this.rowsData = rowsData;
        // this.columns = headers.length; // this may be null / 0
        this.columns = rowsData.getFirst().length;
    }

    public void printContent(){
        for(String[] arr : rowsData){
            System.out.println(Arrays.toString(arr));
        }
    }

    public String[] getHeaders() {
        return headers;
    }

    public ArrayList<String[]> getRowsData() {
        return rowsData;
    }

    public int getColumns() {
        return columns;
    }
}
