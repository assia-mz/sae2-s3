package v2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.Stack;
import java.util.HashMap;

public class ExcelSheetUI extends JFrame {
    private Map<String, JLabel> cellLabels;
    private JTextField formulaField;
    private Stack<JLabel> visitedCellStack = new Stack<>();

    public ExcelSheetUI() {
        // Set up the main JFrame
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

        // Create Cellule objects for each cell and corresponding JLabels
        DicoDesReferencesCellules dicoCell = new DicoDesReferencesCellules();
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

        // Set a custom size for the JFrame
        setSize(600, 600);

        // Center the JFrame on the screen
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
            System.out.println("Stack size before pop: " + visitedCellStack.size());
            if (!visitedCellStack.empty()) {
                JLabel old = visitedCellStack.pop();
                old.setBackground(Color.RED);
                old.setOpaque(true);
                old.repaint();
                System.out.println(old.getText());
            }
            Component component = findComponentAt(e.getX(), e.getY());
            if (component instanceof JLabel) {
                JLabel clickedLabel = (JLabel) component;
                String cellName = clickedLabel.getText();
                System.out.println("Clicked on cell: " + cellName);

                // Set the formula field text to the formula of the clicked cell
                formulaField.setText(cell.getFormule());

                // Add the clicked cell to the stack and print the stack
                clickedLabel.setBackground(Color.GREEN);
                clickedLabel.setOpaque(true);
                visitedCellStack.push(clickedLabel);
                printStack();
            }
        }
    }

    public void printStack() {
        System.out.println("Stack contents:");
        for (JLabel label : visitedCellStack) {
            System.out.println(label.getText());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ExcelSheetUI excelSheetUI = new ExcelSheetUI();
            excelSheetUI.setVisible(true);
        });
    }
}
