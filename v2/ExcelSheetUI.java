package v2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ExcelSheetUI extends JFrame {
    private Map<String, JLabel> cellLabels;
    private JTextField formulaField;
    private Stack<Pair<JLabel, Cellule>> visitedCellStack = new Stack<>();
    private DicoDesReferencesCellules dicoCell;

    public ExcelSheetUI() {
        setTitle("Excel Sheet");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a panel for the JTextField
        JPanel formulaPanel = new JPanel(new FlowLayout());
        formulaField = new JTextField();
        formulaField.setPreferredSize(new Dimension(200, 25)); // Set preferred size
        formulaPanel.add(formulaField);

        // Create a panel for the cells using GridLayout
        JPanel cellPanel = new JPanel(new GridLayout(9, 9));
        cellLabels = new HashMap<>();
        dicoCell = new DicoDesReferencesCellules();

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                String cellName = getCellName(row, col);
                Cellule cell = new Cellule(cellName, dicoCell);
                dicoCell.putDico(cellName, cell);

                JLabel label = new JLabel(cellName);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                label.addMouseListener(new CellClickListener(cell));
                cellLabels.put(cellName, label);

                cellPanel.add(label);
            }
        }

        // Add the formula panel and cell panel to the main panel
        add(formulaPanel, BorderLayout.NORTH);
        add(cellPanel, BorderLayout.CENTER);

        // Add a key listener to the formula field for "Enter" key
        formulaField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyFormula();
            }
        });

        setSize(600, 600);
        setLocationRelativeTo(null);
    }

    private String getCellName(int row, int col) {
        char columnLetter = (char) ('A' + col);
        int rowNumber = row + 1;
        return String.valueOf(columnLetter) + rowNumber;
    }

    private class CellClickListener extends MouseAdapter {
        private Cellule cell;

        public CellClickListener(Cellule cell) {
            this.cell = cell;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (!visitedCellStack.isEmpty()) {
                Pair<JLabel, Cellule> oldPair = visitedCellStack.pop();
                JLabel oldLabel = oldPair.getFirst();
                oldLabel.setBackground(Color.RED);
                oldLabel.setOpaque(true);
                oldLabel.repaint();
            }

            Component component = e.getComponent();
            if (component instanceof JLabel) {
                JLabel clickedLabel = (JLabel) component;
                String cellName = clickedLabel.getText();
                System.out.println("Clicked on cell: " + cellName);

                // Print the formula for the clicked cell
                String formula = cell.getFormule();
                System.out.println("Formula for " + cellName + ": " + formula);

                // Set the formula field text to the formula of the clicked cell
                formulaField.setText(formula);

                // Add the clicked cell and its associated Cellule to the stack
                clickedLabel.setBackground(Color.GREEN);
                clickedLabel.setOpaque(true);
                visitedCellStack.push(new Pair<>(clickedLabel, cell));
                printStack();
            }
        }
    }

    private void printStack() {
        System.out.println("Stack contents:");
        for (Pair<JLabel, Cellule> pair : visitedCellStack) {
            JLabel label = pair.getFirst();
            Cellule cell = pair.getSecond();
            System.out.println(label.getText() + ": " + cell.getFormule());
        }
    }

    private void applyFormula() {
    if (!visitedCellStack.isEmpty()) {
        Pair<JLabel, Cellule> selectedPair = visitedCellStack.peek();
        JLabel selectedLabel = selectedPair.getFirst();
        Cellule selectedCell = selectedPair.getSecond();

        // Print the formula for the selected cell
        String formula = formulaField.getText();
        System.out.println("Formula entered for " + selectedLabel.getText() + ": " + formula);

        // Set the formula of the selected cell to the formula field text
        selectedCell.setFormule(formula);

        // Create a CellManager for the selected cell and evaluate the formula
        CellManager cellManager = new CellManager(selectedLabel.getText(), dicoCell, formula);
        cellManager.evaluateCell();
        System.out.println("Result of evaluating " + selectedLabel.getText() + ": " + cellManager.getCellValue());

        // Update the value of the selected cell
        selectedCell.setValeur(cellManager.getCellValue());

        // Optionally, update the UI to reflect the new value in the cell
        // For example, you can set the text of the JLabel to the new value
        selectedLabel.setText(String.valueOf(selectedCell.getValeur()));

        // Print the list of dependencies for the selected cell
        System.out.println("LAAAAAAAAAAAAAAAAAAAAAAAAAAA");

        //printDicoCellContents();
    }
}

    
    private void printDicoCellContents() {
        System.out.println("Contents of dicoCell:");
    
        for (Map.Entry<String, Cellule> entry : dicoCell.getDico().entrySet()) {
            String cellName = entry.getKey();
            Cellule cell = entry.getValue();
            System.out.println(cellName + ": " + cell.getValeur());
        }
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ExcelSheetUI excelSheetUI = new ExcelSheetUI();
            excelSheetUI.setVisible(true);
        });
    }
}
