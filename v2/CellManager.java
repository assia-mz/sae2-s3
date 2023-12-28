package v2;

public class CellManager {
    private Cellule cell;

    public CellManager(String name, DicoDesReferencesCellules dicoCell, String formula) {
        // Create a new instance of Cellule with the provided name and dictionary
        this.cell = new Cellule(name, dicoCell);

        // Set the formula for the cell
        this.cell.setFormule(formula);

        // Process the formula and build the expression tree
        this.cell.parcourirFormule();
    }

    // You can expose additional methods or delegate calls to the underlying Cellule instance as needed
    public void evaluateCell() {
        this.cell.evaluateCell();
    }

    public double getCellValue() {
        return this.cell.getValeur();
    }
}
