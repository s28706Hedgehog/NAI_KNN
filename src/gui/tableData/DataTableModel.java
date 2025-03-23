package gui.tableData;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataTableModel extends DefaultTableModel {

    private final Map<Integer, Color> specialRows = new HashMap<>();

    public DataTableModel(String[][] learnRowData, String[] columnData) {
        super(learnRowData, columnData);
    }

    public DataTableModel(ArrayList<String[]> rowsData, String[] columnData) {
        super(convertListToArray(rowsData), columnData);
    }

    /* Weird one
    public DataTableModel(ArrayList<String[]> rowsData, String[] columnData) {
        super(rowsData.toArray(new String[0][]), columnData);
    }
    */

    private static String[][] convertListToArray(ArrayList<String[]> rowsData) {
        int rowCount = rowsData.size();
        int columnCount = rowsData.getFirst().length;

        String[][] arrayData = new String[rowCount][columnCount];

        for (int i = 0; i < rowCount; i++) {
            arrayData[i] = rowsData.get(i);
        }

        return arrayData;
    }

    public void addRow(Object[] rowData, Color color) { // Used for Green / Red if there's known correct answer
        super.addRow(rowData);
        int rowIndex = getRowCount() - 1; // Index of the new row
        specialRows.put(rowIndex, color);
    }

    public void addRow(Object[] rowData) { // Default WHITE if there's unknown correct answer
        super.addRow(rowData);
        int rowIndex = getRowCount() - 1; // Index of the new row
        specialRows.put(rowIndex, Color.WHITE);
    }

    public Color getColor(int index) {
        return specialRows.get(index);
    }
}
