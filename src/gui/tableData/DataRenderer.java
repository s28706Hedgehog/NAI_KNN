package gui.tableData;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class DataRenderer extends DefaultTableCellRenderer {
    private final DataTableModel dataTableModel;

    public DataRenderer(DataTableModel dataTableModel){
        this.dataTableModel = dataTableModel;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        component.setBackground(dataTableModel.getColor(table.convertRowIndexToModel(row)));
        return component;
    }
}
