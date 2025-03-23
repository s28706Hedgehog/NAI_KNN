package gui;

import dataStructures.FileContent;
import gui.tableData.DataRenderer;
import gui.tableData.DataTableModel;
import utils.Tester;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AppFrame extends JFrame {
    private Tester tester;
    private DataTableModel dataTableModel;
    public static final Font MASTER_FONT = new Font("Arial", Font.PLAIN, 18);

    public AppFrame(FileContent trainContent, FileContent testContent) {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // https://coderanch.com/t/700660/java/global-font-size
        UIManager.put("OptionPane.font", MASTER_FONT);
        UIManager.put("Button.font", MASTER_FONT);
        UIManager.put("PopupMenu.font", MASTER_FONT);
        UIManager.put("Label.font", MASTER_FONT);
        UIManager.put("TextField.font", MASTER_FONT);
        UIManager.put("Table.font", MASTER_FONT);

        this.tester = new Tester(trainContent, testContent);
        // fancy visuals
        DataTableModel dataTableModel = createTableModel(trainContent);
        JTable table = createTable(dataTableModel);
        this.add(new JScrollPane(table));

        JPanel infoPanel = createInfoPanel();
        this.add(infoPanel);

        this.pack();
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public DataTableModel createTableModel(FileContent fileContent) {
        this.dataTableModel = new DataTableModel(fileContent.getRowsData(), fileContent.getHeaders()) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return this.dataTableModel;
    }

    public JTable createTable(DataTableModel dataTableModel) {
        JTable table = new JTable(dataTableModel);
        // Object.class because it may be Integer, Double, String or Unicorn
        table.setDefaultRenderer(Object.class, new DataRenderer(dataTableModel));
        return table;
    }

    public JPanel createInfoPanel() {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton testDataButton = new JButton("Test data");
        testDataButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // askForTestData();
                askForK();
                tester.testData(dataTableModel);
            }
        });


        JButton addOwnDataButton = new JButton("Add own data");
        addOwnDataButton.addActionListener(e -> {
            askForK();
            String ownDataInput = JOptionPane.showInputDialog(null, "Enter data, use spaces", "Input", JOptionPane.QUESTION_MESSAGE);
            testOwnData(ownDataInput.split(";"));
        });


        infoPanel.add(testDataButton);
        infoPanel.add(addOwnDataButton);
        infoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        return infoPanel;
    }

    private void testOwnData(String[] rowData) {
        String[] resultType = new String[rowData.length + 1];
        for (int i = 0; i < rowData.length; i++) {
            resultType[i] = rowData[i];
        }

        resultType[resultType.length - 1] = tester.getGuessKey(rowData);
        this.dataTableModel.addRow(resultType, Color.PINK);
    }

    private void askForK() {
        try {
            String userK = JOptionPane.showInputDialog(null, "Enter K value:", "Input", JOptionPane.QUESTION_MESSAGE);
            if (userK != null) {
                this.tester.setkNearest(Integer.parseInt(userK));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid integer for K value.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }
}
