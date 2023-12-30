package v2;

public class CelluleTest {
    public static void main(String[] args) {
        // Create a dictionary for cell references
        DicoDesReferencesCellules dicoCell = new DicoDesReferencesCellules();

        // Create cells
        Cellule cellA1 = new Cellule("A1", dicoCell);
        Cellule cellB1 = new Cellule("B1", dicoCell);
        Cellule cellC1 = new Cellule("C1", dicoCell);

        // Add dependencies
        cellB1.addDependance(cellA1);
        cellC1.addDependance(cellB1);

        // Set formulas
        cellA1.setFormule("+ 1 1");
        cellB1.setFormule("+ A1 1");
        cellC1.setFormule("+ B1 1");

        // Evaluate cells
        CellManager cellManagerA1 = new CellManager("A1", dicoCell, cellA1.getFormule());
        CellManager cellManagerB1 = new CellManager("B1", dicoCell, cellB1.getFormule());
        CellManager cellManagerC1 = new CellManager("C1", dicoCell, cellC1.getFormule());

        cellManagerA1.evaluateCell();
        cellManagerB1.evaluateCell();
        cellManagerC1.evaluateCell();

        // Print results
        System.out.println("Result of A1: " + cellManagerA1.getCellValue());
        System.out.println("Result of B1: " + cellManagerB1.getCellValue());
        System.out.println("Result of C1: " + cellManagerC1.getCellValue());
    }
}
