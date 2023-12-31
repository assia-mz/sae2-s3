package v4;

public class MainTest {
    public static void main(String[] args) {
        // Create a DicoDesReferencesCellules instance
        DicoDesReferencesCellules dicoCellules = new DicoDesReferencesCellules();

        // Create Cellule instances
        Cellule cellA1 = new Cellule("A1", dicoCellules);
        Cellule cellB1 = new Cellule("B1", dicoCellules);
        Cellule cellC1 = new Cellule("C1", dicoCellules);

        // Add Cellule instances to the DicoDesReferencesCellules
        dicoCellules.putDico("A1", cellA1);
        dicoCellules.putDico("B1", cellB1);
        dicoCellules.putDico("C1", cellC1);

        // Establish dependencies
        dicoCellules.addDependencies("B1", new Cellule[]{cellC1});

        // Enter a formula for the cells
        String Formula = "+ 1 A1";

        // Notify dependents on formula change
        String changedCellName = "B1";
        String newFormula = "* 5 A1";
        dicoCellules.notifyDependentsOnFormulaChange(changedCellName, newFormula);

        // Print the values after the formula change
        System.out.println("Value of A1: " + cellA1.getValeur());
        System.out.println("Value of B1: " + cellB1.getValeur());
        System.out.println("Value of C1: " + cellC1.getValeur());
    }
}
