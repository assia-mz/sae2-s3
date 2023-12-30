package v4;

import java.util.List;

/**
 * La classe CellManager gère une cellule dans un tableau de feuilles de calcul.
 * Elle permet de définir une formule, de l'évaluer et d'obtenir la valeur calculée.
 */
public class CellManager {
    private Cellule cell;

    /**
     * Constructeur de la classe CellManager.
     *
     * @param name         Nom de la cellule.
     * @param dicoCell     Dictionnaire des références des cellules.
     * @param formula      Formule associée à la cellule.
     * @param dependencies Liste des dépendances de la cellule.
     */
    public CellManager(String name, DicoDesReferencesCellules dicoCell, String formula, List<Cellule> dependencies) {
        // Créer une nouvelle instance de Cellule avec le nom, le dictionnaire, et les dépendances fournis
        this.cell = new Cellule(name, dicoCell);
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
