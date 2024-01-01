package v5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * La classe ExcelSheetUI représente l'interface utilisateur d'une feuille de calcul simple.
 * Elle permet de saisir des formules dans les cellules, d'évaluer les formules, et de visualiser les résultats.
 */
public class ExcelSheetUI extends JFrame {
    private Map<String, JLabel> cellLabels;
    private JTextField formulaField;
    private Stack<Pair<JLabel, Cellule>> visitedCellStack = new Stack<>();
    private DicoDesReferencesCellules dicoCell;

    /**
     * Constructeur de la classe ExcelSheetUI.
     * Initialise l'interface utilisateur avec des panneaux pour les formules et les cellules.
     */
    public ExcelSheetUI() {
        setTitle("Feuille de calcul Excel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Créer un panneau pour le JTextField
        JPanel formulaPanel = new JPanel(new FlowLayout());
        formulaField = new JTextField();
        formulaField.setPreferredSize(new Dimension(200, 25)); // Définir la taille préférée
        formulaPanel.add(formulaField);

        // Créer un panneau pour les cellules en utilisant GridLayout
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
                cellLabels.put(cellName, label);

                cellPanel.add(label);
            }
        }

        // Ajouter le panneau de formule et le panneau de cellules au panneau principal
        add(formulaPanel, BorderLayout.NORTH);
        add(cellPanel, BorderLayout.CENTER);

        // Ajouter un écouteur de clavier au champ de formule pour la touche "Entrée"
        formulaField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyFormula();
            }
        });

        setSize(600, 600);
        setLocationRelativeTo(null);
    }

    /**
     * Obtient le nom de la cellule en fonction de la ligne et de la colonne.
     *
     * @param row Numéro de la ligne.
     * @param col Numéro de la colonne.
     * @return Le nom de la cellule.
     */
    private String getCellName(int row, int col) {
        char columnLetter = (char) ('A' + col);
        int rowNumber = row + 1;
        return String.valueOf(columnLetter) + rowNumber;
    }

    /**
     * La classe interne CellClickListener gère les clics de souris sur les cellules.
     * Elle met à jour le champ de formule et la pile de cellules visitées.
     */
    private class CellClickListener extends MouseAdapter {
        private Cellule cell;

        /**
         * Constructeur de la classe CellClickListener.
         *
         * @param cell     La cellule associée au listener.
         */
        public CellClickListener(Cellule cell) {
            this.cell = cell;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (!visitedCellStack.isEmpty()) {
                Pair<JLabel, Cellule> oldPair = visitedCellStack.pop();
                JLabel oldLabel = oldPair.getFirst();
                oldLabel.setBackground(null); // Clear background color
                oldLabel.setOpaque(false);
                oldLabel.repaint();
            }

            Component component = e.getComponent();
            if (component instanceof JLabel) {
                JLabel clickedLabel = (JLabel) component;
                String cellName = clickedLabel.getText();
                System.out.println("Cliqué sur la cellule : " + cellName);

                // Afficher la formule pour la cellule cliquée
                String formule = cell.getFormule();
                //System.out.println("Formule pour " + cellName + " : " + formule);

                // Définir le texte du champ de formule sur la formule de la cellule cliquée
                formulaField.setText(formule);

                // Ajouter la cellule cliquée et sa Cellule associée à la pile
                clickedLabel.setBackground(Color.GREEN);
                clickedLabel.setOpaque(true);
                visitedCellStack.push(new Pair<>(clickedLabel, cell));
                //listener.onCellUpdated(cell);
                //printStack();
            }
        }
    }

    /**
     * Affiche le contenu de la pile de cellules visitées.
     */
    private void printStack() {
        //System.out.println("Contenu de la pile :");
        for (Pair<JLabel, Cellule> pair : visitedCellStack) {
            JLabel label = pair.getFirst();
            Cellule cell = pair.getSecond();
            System.out.println(label.getText() + " : " + cell.getFormule());
        }
    }

    /**
     * Applique la formule du champ de formule à la cellule sélectionnée.
     * Évalue la formule et met à jour l'interface utilisateur en conséquence.
     */
    private void applyFormula() {
        if (!visitedCellStack.isEmpty()) {
            Pair<JLabel, Cellule> selectedPair = visitedCellStack.peek();
            JLabel selectedLabel = selectedPair.getFirst();
            Cellule selectedCell = selectedPair.getSecond();

            // Afficher la formule pour la cellule sélectionnée
            String newFormula = formulaField.getText();
            System.out.println("Formule entrée pour " + selectedLabel.getText() + " : " + newFormula);

            // Vérifier si la formule a changé
            String currentFormula = selectedCell.getFormule();
            if (!newFormula.equals(currentFormula)) {

                // Notify dependents about the formula change
                dicoCell.notifyDependentsOnFormulaChange(selectedLabel.getText(), newFormula);

                // Définir la formule de la cellule sélectionnée sur le texte du champ de formule
                selectedCell.setFormule(newFormula);

                // Créer un CellManager pour la cellule sélectionnée et évaluer la formule
                CellManager cellManager = new CellManager(selectedLabel.getText(), dicoCell, newFormula);
                cellManager.evaluateCell();
                System.out.println("Résultat de l'évaluation de " + selectedLabel.getText() + " : " + cellManager.getCellValue());

                // Mettre à jour la valeur de la cellule sélectionnée
                selectedCell.setValeur(cellManager.getCellValue());

                // Facultativement, mettre à jour l'interface utilisateur pour refléter la nouvelle valeur dans la cellule
                // Par exemple, vous pouvez définir le texte du JLabel sur la nouvelle valeur
                selectedLabel.setText(String.valueOf(selectedCell.getValeur()));

                //printDicoCellContents();
            }
        }
    }


    /**
     * Affiche le contenu du dictionnaire de cellules.
     */
    private void printDicoCellContents() {
        System.out.println("Contenu de dicoCell :");

        for (Map.Entry<String, Cellule> entry : dicoCell.getDico().entrySet()) {
            String cellName = entry.getKey();
            Cellule cell = entry.getValue();
            System.out.println(cellName + " : " + cell.getValeur());
        }
    }

    /**
     * Méthode principale pour exécuter l'application.
     *
     * @param args Arguments de la ligne de commande (non utilisés dans cet exemple).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ExcelSheetUI excelSheetUI = new ExcelSheetUI();
            excelSheetUI.setVisible(true);
        });
    }
}
