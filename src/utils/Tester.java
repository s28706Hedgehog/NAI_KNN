package utils;

import dataStructures.FileContent;
import dataStructures.ObservationPoint;
import dataStructures.ObservationPointUtils;
import gui.tableData.DataTableModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Tester {
    private final FileContent trainingContent;
    private final FileContent testContent;
    private int kNearest;
    private double accuracy;

    public Tester(FileContent fileContent, FileContent testContent) {
        this.trainingContent = fileContent;
        this.testContent = testContent;
        this.kNearest = 3;
    }

    public String getGuessKey(String[] newElement) {
        ArrayList<ObservationPoint> distanceList = new ArrayList<>();
        int dataLength = trainingContent.getColumns() - 1; // we skip the key in last column
        double[] elementData = new double[dataLength];
        double[] observationData = new double[dataLength];

        // getting element data
        for (int i = 0; i < dataLength; i++) {
            elementData[i] = Double.parseDouble(newElement[i].replace(",", "."));
        }

        for (String[] arr : trainingContent.getRowsData()) { // iterating over training data
            for (int i = 0; i < dataLength; i++) {
                observationData[i] = Double.parseDouble(arr[i].replace(",", "."));
            }
            // distanceList.add(new dataStructures.ObservationPoint(utils.MathMagic.calculateEuclideanDistance(elementData, observationData), arr[dataLength]));
            distanceList.add(new ObservationPoint(MathMagic.cityDistance(elementData, observationData), arr[dataLength]));
        }

        return ObservationPointUtils.findType(distanceList, this.kNearest);
    }

    public void testData(DataTableModel dataTableModel) {
        this.accuracy = 0.0;
        double countCorrect = 0, countIncorrect = 0;

        Color rowBackground;
        for (String[] row : this.testContent.getRowsData()) {
            // System.out.println("Test: " + Arrays.toString(Arrays.copyOfRange(row, 0, row.length - 1)));
            // String[] row = this.testContent.getRowsData().get(26);
            // guess -> iris versicolor
            // by distance -> iris virginica

            String guessKey = getGuessKey(Arrays.copyOfRange(row, 0, row.length - 1)).trim(); // copy without key
            String properKey = row[row.length - 1].trim();

            // System.out.println("Is equal: " + guessKey + " | " + properKey + " = " + guessKey.equals(properKey));
            // System.out.println(guessKey);
            // System.out.println(properKey);
            if (guessKey.equals(properKey)) {
                // Correct answer
                countCorrect++;
                rowBackground = Color.GREEN;
                System.out.println("Correct\n");
                // System.out.println(" for: " + Arrays.toString(row) + ", guessKey: " + guessKey);
            } else {
                countIncorrect++;
                rowBackground = Color.RED;
                System.out.println("Wrong:");
                System.out.println(" for: " + Arrays.toString(row) + ", guessKey: " + guessKey + "\n");
            }
            dataTableModel.addRow(row, rowBackground);
        }

        this.accuracy = countCorrect / (countIncorrect + countCorrect);
        System.out.println("Accuracy: " + this.accuracy);
    }

    public void setkNearest(int kNearest) {
        this.kNearest = kNearest;
    }

    public double getAccuracy() {
        return accuracy;
    }
}
