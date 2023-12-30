/*package v2;

public class MainTest {

    public static void main(String[] args) {
        // Create a dictionary of cell references
        DicoDesReferencesCellules dicoCell = new DicoDesReferencesCellules();

        // Create cells
        Cellule cellB2 = new Cellule("B2", dicoCell);

        // Add cells to the dictionary
        dicoCell.putDico("B2", cellB2);

        // Set formulas for cells
        cellB2.setFormule("* 7 2");

        // Test parcourirFormule and dependencies
        cellB2.parcourirFormule();

        // Show the expression tree for cell A1
        System.out.println("Expression tree for cell B2:");
        cellB2.showExpressionTree(cellB2);

        // Show dependencies for cell A1
        System.out.println("Dependencies for cell B2:");
        for (Cellule dependency : cellB2.getListeDesDependances()) {
            System.out.println(dependency.getName());
        }

        // Check for circular dependencies for cell A1
        if (cellB2.hasCircularDependency()) {
            System.out.println("Circular dependencies detected for cell B2.");
        } else {
            System.out.println("No circular dependencies for cell B2.");
        }

        // Evaluate the expression for cell A1
        cellB2.evaluateCell();
        System.out.println("Result of evaluating A1: " + cellB2.getValeur());
    }
}
*/