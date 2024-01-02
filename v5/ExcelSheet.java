package v5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ExcelSheet extends JFrame {
    private Map<String, JLabel> cellLabels;
    private JTextField formulaField;
    private Stack<Pair<JLabel, Cellule>> visitedCellStack = new Stack<>();
    private DicoDesReferencesCellules dicoCell;

    public ExcelSheet() {
        setTitle("Feuille de calcul Excel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a panel for the JTextField
        JPanel formulaPanel = new JPanel(new FlowLayout());
        formulaField = new JTextField();
        formulaField.setPreferredSize(new Dimension(200, 25));
        formulaPanel.add(formulaField);

        // Create a panel for the cells using GridBagLayout
        JPanel cellPanel = new JPanel(new GridBagLayout());
        cellLabels = new HashMap<>();
        dicoCell = new DicoDesReferencesCellules();

        // Add row titles (numbers) on the left using GridBagLayout
        GridBagConstraints gbcRowTitles = new GridBagConstraints();
        gbcRowTitles.gridx = 0;
        gbcRowTitles.gridy = 1;
        gbcRowTitles.anchor = GridBagConstraints.EAST;
        gbcRowTitles.insets = new Insets(0, 5, 0, 5);

        for (int row = 0; row < 9; row++) {
            JLabel rowTitleLabel = new JLabel(Integer.toString(row + 1));
            rowTitleLabel.setHorizontalAlignment(JLabel.RIGHT);
            cellPanel.add(rowTitleLabel, gbcRowTitles);
            gbcRowTitles.gridy++;
        }

        // Add column titles (letters) at the top
        GridBagConstraints gbcColTitles = new GridBagConstraints();
        gbcColTitles.gridx = 2;
        gbcColTitles.gridy = 0;
        gbcColTitles.anchor = GridBagConstraints.NORTH;
        gbcColTitles.insets = new Insets(5, 0, 5, 0);

        for (int col = 0; col < 9; col++) {
            char columnLetter = (char) ('A' + col);
            JLabel colTitleLabel = new JLabel(Character.toString(columnLetter));
            colTitleLabel.setHorizontalAlignment(JLabel.CENTER);
            cellPanel.add(colTitleLabel, gbcColTitles);
            gbcColTitles.gridx++;

            for (int row = 0; row < 9; row++) {
                String cellName = getCellName(row, col);
                Cellule cell = new Cellule(cellName, dicoCell);
                dicoCell.putDico(cellName, cell);

                JLabel label = new JLabel();
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                label.setHorizontalAlignment(JLabel.CENTER);
                cellLabels.put(cellName, label);

                GridBagConstraints gbcCell = new GridBagConstraints();
                gbcCell.gridx = col + 2;
                gbcCell.gridy = row + 1;
                gbcCell.fill = GridBagConstraints.BOTH;
                gbcCell.insets = new Insets(0, 5, 0, 5);
                cellPanel.add(label, gbcCell);

                label.addMouseListener(new CellClickListener(cell));
            }
        }

        // Set weights to make cellPanel fill the available space
        GridBagConstraints gbcCellPanel = new GridBagConstraints();
        gbcCellPanel.gridx = 1;
        gbcCellPanel.gridy = 1;
        gbcCellPanel.weightx = 1.0;
        gbcCellPanel.weighty = 1.0;
        gbcCellPanel.fill = GridBagConstraints.BOTH;

        // Add the formula panel and cell panel to the main panel
        add(formulaPanel, BorderLayout.NORTH);
        add(cellPanel, gbcCellPanel);

        formulaField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyFormula();
            }
        });

        setSize(800, 800);
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
            Component component = e.getComponent();
            if (component instanceof JLabel) {
                JLabel clickedLabel = (JLabel) component;

                // Clear background color for previously clicked cell (if any)
                while (!visitedCellStack.isEmpty()) {
                    Pair<JLabel, Cellule> oldPair = visitedCellStack.pop();
                    JLabel oldLabel = oldPair.getFirst();
                    oldLabel.setBackground(null);
                    oldLabel.setOpaque(false);
                    oldLabel.repaint();
                }

                String cellName = clickedLabel.getText();
                System.out.println("Clicked on cell: " + cellName);

                // Update formula field with the formula for the clicked cell
                String formula = cell.getFormule();
                formulaField.setText(formula);

                // Set the background color based on EnumEtatCellule
                clickedLabel.setBackground(getColorForEtatCellule(cell.getEtatCellule()));
                clickedLabel.setOpaque(true);

                // Add the clicked cell and its associated Cellule to the stack
                visitedCellStack.push(new Pair<>(clickedLabel, cell));
            }
        }
    }

    private void applyFormula() {
        if (!visitedCellStack.isEmpty()) {
            Pair<JLabel, Cellule> selectedPair = visitedCellStack.peek();
            JLabel selectedLabel = selectedPair.getFirst();
            Cellule selectedCell = selectedPair.getSecond();

            // Display the formula for the selected cell
            String newFormula = formulaField.getText();
            System.out.println("Entered formula for " + selectedLabel.getText() + ": " + newFormula);

            // Check if the formula has changed
            String currentFormula = selectedCell.getFormule();
            if (!newFormula.equals(currentFormula)) {

                // Notify dependents about the formula change
                dicoCell.notifyDependentsOnFormulaChange(selectedLabel.getText(), newFormula);

                // Set the formula of the selected cell to the text in the formula field
                selectedCell.setFormule(newFormula);

                // Create a CellManager for the selected cell and evaluate the formula
                CellManager cellManager = new CellManager(selectedLabel.getText(), dicoCell, newFormula);

                // Check if the formula is calculable
                if (cellManager.isCalculable()) {
                    System.out.println("Result of evaluation for " + selectedLabel.getText() + ": " + cellManager.getCellValue());

                    // Update the value of the selected cell
                    selectedCell.setValeur(cellManager.getCellValue());

                    // Optionally, update the UI to reflect the new value in the cell
                    // For example, you can set the text of the JLabel to the new value
                    selectedLabel.setText(String.valueOf(selectedCell.getValeur()));
                } else {
                    // If the formula is not calculable, display the formula on the cell
                    selectedLabel.setText(newFormula);
                }
            }
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

    private Color getColorForEtatCellule(EnumEtatCellule etatCellule) {
        switch (etatCellule) {
            case VIDE:
                return Color.WHITE;
            case CALCULABLE:
                return Color.GREEN;
            case INCALCULABLE:
                return Color.ORANGE;
            case INCORRECTE:
                return Color.RED;
            default:
                return Color.WHITE;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ExcelSheet ExcelSheet = new ExcelSheet();
            ExcelSheet.setVisible(true);
        });
    }
}
