package excel;
//import java.util.Observable;

//public class FeuilleDeCalcul /*extends Observable*/ {
    // Attributs
    /*private Cellule[][] tableau; // Tableau bidimensionnel de Cellules
    private Cellule celluleSelectionnee; // Cellule actuellement sélectionnée

    // Constructeur
    public FeuilleDeCalcul(int lignes, int colonnes) {
        tableau = new Cellule[lignes][colonnes];
        initialiserFeuille();
    }

    // Méthodes

    // Retourne la cellule à la position spécifiée
    public Cellule getCellule(int ligne, int colonne) {
        return tableau[ligne][colonne];
    }

    // Met en surbrillance la cellule sélectionnée
    public void selectionnerCellule(Cellule cellule) {
        celluleSelectionnee = cellule;
        // Notifie les observateurs de la sélection de la cellule
        //setChanged();
    }

    // Met à jour la cellule et notifie les observateurs
    public void miseAJourCellule(Cellule cellule) {
        cellule.evaluerFormule(); // Évalue la formule et met à jour la valeur
        // Notifie les observateurs de la mise à jour de la cellule
        /*setChanged();*/
        /*notifyObservers("Cellule mise à jour : " + cellule.getFormuleTexte());
    }

    // Initialisation de la feuille de calcul avec des cellules vides
    private void initialiserFeuille() {
        for (int i = 0; i < tableau.length; i++) {
            for (int j = 0; j < tableau[0].length; j++) {
                tableau[i][j] = new Cellule();
            }
        }
    }
}
*/