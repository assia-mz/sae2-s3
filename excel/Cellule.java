package excel;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe Cellule représente une cellule dans une feuille de calcul.
 */
public class Cellule {

    /**
     * La formule texte associée à la cellule.
     */
    private String formuleTexte;

    /**
     * La valeur numérique de la cellule.
     */
    private Double valeur;

    /**
     * L'état actuel de la cellule (VIDE, CALCUL, ERREUR, etc.).
     */
    private EnumEtatCellule etatCellule;

    /**
     * Initialise une nouvelle instance de la classe Cellule.
     * La valeur est initialisée à null et l'état de la cellule est défini comme VIDE.
     */
    public Cellule() {
        this.valeur = null;
        this.etatCellule = EnumEtatCellule.VIDE;
    }

    /**
     * Obtient la formule texte de la cellule.
     * @return La formule texte de la cellule.
     */
    public String getFormuleTexte() {
        return formuleTexte;
    }

    /**
     * Définit la formule texte de la cellule.
     * @param formuleTexte La nouvelle formule texte de la cellule.
     */
    public void setFormuleTexte(String formuleTexte) {
        this.formuleTexte = formuleTexte;
        // Notifie les observateurs que la formule a été mise à jour
        //notifierObservateurs();
    }

    /**
     * Obtient la valeur numérique de la cellule.
     * @return La valeur numérique de la cellule.
     */
    public Double getValeur() {
        return valeur;
    }

    /**
     * Définit la valeur numérique de la cellule.
     * @param valeur La nouvelle valeur numérique de la cellule.
     */
    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }

    /**
     * Obtient l'état actuel de la cellule.
     * @return L'état actuel de la cellule.
     */
    public EnumEtatCellule getEtatCellule() {
        return etatCellule;
    }

    /**
     * Définit l'état actuel de la cellule.
     * @param etatCellule Le nouvel état de la cellule.
     */
    public void setEtatCellule(EnumEtatCellule etatCellule) {
        this.etatCellule = etatCellule;
    }

    // Les méthodes liées à l'Observer pattern sont commentées pour le moment.

    /*
    public void ajouterObservateur(ObservateurCellule observateur) {
        observateurs.add(observateur);
    }

    public void supprimerObservateur(ObservateurCellule observateur) {
        observateurs.remove(observateur);
    }

    private void notifierObservateurs() {
        for (ObservateurCellule observateur : observateurs) {
            observateur.miseAJour(this);
        }
    }
    */
}
