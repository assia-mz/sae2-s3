/*package v2;

import java.util.*;
import javax.swing.SwingUtilities;


public class Mainv6 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create an instance of ExcelSheetUI
            ExcelSheetUI excelSheetUI = new ExcelSheetUI();

            // Create an instance of Cellule (e.g., cell A1)
            Cellule cellA1 = new Cellule("A1", excelSheetUI.getDicoCell());

            // Set a sample formula for the cell
            cellA1.setFormule("B1 + C1");

            // Set the value of cell B1
            Cellule cellB1 = new Cellule("B1", excelSheetUI.getDicoCell());
            cellB1.setValeur(10.0);

            // Set the value of cell C1
            Cellule cellC1 = new Cellule("C1", excelSheetUI.getDicoCell());
            cellC1.setValeur(20.0);

            // Add cell B1 and C1 as dependencies for cell A1
            cellA1.addDependance(cellB1);
            cellA1.addDependance(cellC1);

            // Evaluate the formula for cell A1
            cellA1.parcourirFormule();
            cellA1.evaluateCell();

            // Display the UI
            excelSheetUI.setVisible(true);

            // Optionally, print the dependencies and results
            System.out.println("Dependencies for cell A1:");
            cellA1.printDependencies();

            System.out.println("Result of evaluating A1: " + cellA1.getValeur());
        });
    }
}*/
