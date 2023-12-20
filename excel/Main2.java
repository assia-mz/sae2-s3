package excel;

public class Main2 {
    public static void main(String[] args) {
        // Create a Cellule
        Cellule cellule = new Cellule();

        // Create a PrefixCalculator
        PrefixCalculator prefixCalculator = new PrefixCalculator();

        // Add a formula to the Cellule
        String formula = "* + 2 3 B4"; // Example formula: (2 + 3) * 4
        cellule.setFormuleTexte(formula);

        // Build the expression tree for the formula
        try {
            prefixCalculator.buildExpressionTree(cellule.getFormuleTexte(), cellule);
        } catch (IllegalArgumentException e) {
            System.err.println("Error building expression tree: " + e.getMessage());
            return;
        }

        // Show the expression tree
        /*System.out.println("Expression Tree:");
        prefixCalculator.showExpressionTree(cellule);*/

        // Check for cycles in the expression tree
        cellule.cyclesInTree();

        // Update the state based on the result of checking for cycles
        cellule.updateEtatStatut(2);

        // Get and display the result
        //double result = prefixCalculator.getResult(cellule);
        //System.out.println("\nResult: " + result);

        System.out.println("\n Etat cellule: " + cellule.getEtatCellule());
    }
}