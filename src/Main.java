import dataStructures.FileContent;
import gui.AppFrame;
import utils.Futil;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String userPath = System.getProperty("user.dir");
        // String testFilePath = userPath + "\\resources\\rawData\\iris_testV2.txt";
        // String trainFilePath = userPath + "\\resources\\rawData\\iris_trainingV2.txt";
        String testFilePath = userPath + "\\resources\\rawData\\iris_test.txt";
        String trainFilePath = userPath + "\\resources\\rawData\\iris_training.txt";

        FileContent testContent = Futil.retrieveFileContent("\t", false, testFilePath);
        FileContent trainContent = Futil.retrieveFileContent("\t", false, trainFilePath);
        // fileContentDebugInfo(testContent, trainContent);

        SwingUtilities.invokeLater(() -> new AppFrame(trainContent, testContent));
    }

    private static void fileContentDebugInfo(FileContent testContent, FileContent trainContent){
        System.out.println("Test content: ");
        testContent.printContent();

        System.out.println("Train content: ");
        trainContent.printContent();
    }
}