package excel;
import java.util.HashMap;
import java.util.Map;

public class CellManager {
    private Map<String, Cellule> cells;

    public CellManager() {
        this.cells = new HashMap<>();
    }

    public Cellule createCellule(String reference, Double value, String formula) {
        Cellule cellule = new Cellule();
        cellule.setReference(reference);
        cellule.setValeur(value);
        cellule.setFormuleTexte(formula);
        cells.put(reference, cellule);
        return cellule;
    }

    public void calculateAll() {
        for (Cellule cellule : cells.values()) {
            calculateCell(cellule);
        }
    }

    public void showAll() {
        for (Cellule cellule : cells.values()) {
            System.out.println("Cellule " + cellule.getReference() + ": " + cellule.getValeur());
        }
    }

    private void calculateCell(Cellule cellule) {
        // If the cell has a formula, calculate its value
        if (cellule.getFormuleTexte() != null && !cellule.getFormuleTexte().isEmpty()) {
            PrefixCalculator prefixCalculator = new PrefixCalculator();
            try {
                prefixCalculator.buildExpressionTree(cellule.getFormuleTexte(), cellule);
                double result = prefixCalculator.getResult(cellule);
                cellule.setValeur(result);
                cellule.updateEtatStatut(2);
            } catch (IllegalArgumentException e) {
                System.err.println("Error calculating cell " + cellule.getReference() + ": " + e.getMessage());
                cellule.updateEtatStatut(4);
            }
        }
    }
}
