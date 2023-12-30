package v2;

import java.util.HashMap;

/**
 * La classe DicoDesReferencesCellules représente un dictionnaire des références des cellules dans une feuille de calcul.
 * Elle permet d'associer des noms de cellules à leurs instances respectives.
 */
public class DicoDesReferencesCellules {
    private HashMap<String, Cellule> dicoRef;

    /**
     * Constructeur de la classe DicoDesReferencesCellules.
     * Initialise un nouveau dictionnaire de références de cellules.
     */
    public DicoDesReferencesCellules() {
        this.dicoRef = new HashMap<>();
    }

    /**
     * Ajoute une entrée dans le dictionnaire associant un nom de cellule à une instance de Cellule.
     *
     * @param name        Le nom de la cellule.
     * @param valueOfCell L'instance de Cellule associée au nom.
     * @return L'instance précédente de Cellule associée à ce nom (ou null si aucune).
     */
    public Cellule putDico(String name, Cellule valueOfCell) {
        return this.dicoRef.put(name, valueOfCell);
    }

    /**
     * Obtient l'instance de Cellule associée au nom de la cellule spécifié.
     *
     * @param nameOfCellule Le nom de la cellule à rechercher.
     * @return L'instance de Cellule associée au nom de la cellule (ou null si non trouvée).
     */
    public Cellule getCellule(String nameOfCellule) {
        Cellule cell = this.dicoRef.get(nameOfCellule);
        return cell;
    }

    /**
     * Obtient une copie du dictionnaire des références de cellules.
     *
     * @return Une copie du dictionnaire des références de cellules.
     */
    public HashMap<String, Cellule> getDico() {
        return new HashMap<>(dicoRef);
    }
}
