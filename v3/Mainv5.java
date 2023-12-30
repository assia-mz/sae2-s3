/*package v2;

public class Mainv5 {
    public static void main(String[] args) {
        // Create a CellDictionary
        CellDictionary cellDictionary = new CellDictionary();

        // Create cells A1, B1, and C1
        Cellule2 cellA1 = new Cellule2("A1", cellDictionary);
        Cellule2 cellB1 = new Cellule2("B1", cellDictionary);
        Cellule2 cellC1 = new Cellule2("C1", cellDictionary);

        // Set formulas for cells A1 and B1
        cellA1.setFormula("+ B1 1");
        cellB1.setFormula("+ A1 1");

        cellA1.traverseFormula();
        cellB1.traverseFormula();

        // Print dependencies for A1 and B1
        cellA1.printDependencies();  // Should print: Dependencies for cell A1: B1
        cellB1.printDependencies();  // Should print: Dependencies for cell B1: A1

        // Check for circular dependencies
        System.out.println("Circular dependency for A1: " + cellA1.hasCircularDependency());  // Should print: Circular dependency for A1: true
        System.out.println("Circular dependency for B1: " + cellB1.hasCircularDependency());  // Should print: Circular dependency for B1: true

        // Add cell C1 as a dependency for A1
        cellA1.addDependency(cellC1);

        // Print dependencies for A1 again
        cellA1.printDependencies();  // Should print: Dependencies for cell A1: B1, C1

        // Check for circular dependencies again
        System.out.println("Circular dependency for A1: " + cellA1.hasCircularDependency());  // Should print: Circular dependency for A1: false
    }
}
*/