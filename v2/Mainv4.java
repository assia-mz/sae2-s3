package v2;

public class Mainv4 {
    public static void main(String[] args) {
        // Create a DicoDesReferencesCellules (assuming you have a class like this)
        DicoDesReferencesCellules dicoCell = new DicoDesReferencesCellules();

        // Create cells A1, B1, and C1
        Cellule cellA1 = new Cellule("A1", dicoCell);
        Cellule cellB1 = new Cellule("B1", dicoCell);
        Cellule cellC1 = new Cellule("C1", dicoCell);

        // Set formulas for cells A1 and B1
        cellA1.setFormule("+ B1 1");
        cellB1.setFormule("+ A1 1");

        cellA1.parcourirFormule();
        cellB1.parcourirFormule();

        // Print dependencies for A1 and B1
        cellA1.printDependencies();  // Should print: Dependencies for cell A1: B1
        cellB1.printDependencies();  // Should print: Dependencies for cell B1: A1

        // Check for circular dependencies
        System.out.println("Circular dependency for A1: " + cellA1.hasCircularDependency());  // Should print: Circular dependency for A1: true
        System.out.println("Circular dependency for B1: " + cellB1.hasCircularDependency());  // Should print: Circular dependency for B1: true

        // Add cell C1 as a dependency for A1
        cellA1.addDependance(cellC1);

        // Print dependencies for A1 again
        cellA1.printDependencies();  // Should print: Dependencies for cell A1: B1, C1

        // Check for circular dependencies again
        System.out.println("Circular dependency for A1: " + cellA1.hasCircularDependency());  // Should print: Circular dependency for A1: false
    }
}
