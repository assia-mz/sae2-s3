package excel;
import javax.swing.JTextArea;

public class EditeurFormule {

    // Attributs
    private Cellule celluleEditee;
    private JTextArea zoneEdition;

    // Constructeur
    public EditeurFormule(JTextArea zoneEdition) {
        this.zoneEdition = zoneEdition;
    }

    // Méthodes

    // Affiche la formule dans la zone d'édition
    public void afficherFormule(Cellule cellule) {
        celluleEditee = cellule;
        zoneEdition.setText(cellule.getFormuleTexte());
    }

    // Met à jour la formule de la cellule en fonction du contenu de la zone d'édition
    public void mettreAJourFormule() {
        if (celluleEditee != null) {
            String nouvelleFormule = zoneEdition.getText();
            celluleEditee.setFormuleTexte(nouvelleFormule);
        }
    }
}
