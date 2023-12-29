package v2;

/**
 * La classe CellManager gère une cellule dans un tableau de feuilles de calcul.
 * Elle permet de définir une formule, de l'évaluer et d'obtenir la valeur calculée.
 */
public class CellManager {
    private Cellule cell;

    /**
     * Constructeur de la classe CellManager.
     *
     * @param name Nom de la cellule.
     * @param dicoCell Dictionnaire des références des cellules.
     * @param formula Formule associée à la cellule.
     */
    public CellManager(String name, DicoDesReferencesCellules dicoCell, String formula) {
        // Créer une nouvelle instance de Cellule avec le nom et le dictionnaire fournis
        this.cell = new Cellule(name, dicoCell);

        // Définir la formule pour la cellule
        this.cell.setFormule(formula);

        // Traiter la formule et construire l'arbre d'expression
        this.cell.parcourirFormule();
    }

    /**
     * Évalue la cellule en fonction de sa formule.
     */
    public void evaluateCell() {
        this.cell.evaluateCell();
    }

    /**
     * Obtient la valeur calculée de la cellule.
     *
     * @return La valeur calculée de la cellule.
     */
    public double getCellValue() {
        return this.cell.getValeur();
    }
}
