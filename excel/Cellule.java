package excel;
import java.util.ArrayList;
import java.util.List;

public class Cellule {

    private String formuleTexte;
    private Double valeur;
    private EnumEtatCellule etatCellule;
    //private List<ObservateurCellule> observateurs;

    public Cellule() {
        this.valeur = null;
        this.etatCellule = EnumEtatCellule.VIDE;
    }

    public void evaluerFormule(){
        System.out.println("yahh");
    }

    public String getFormuleTexte() {
        return formuleTexte;
    }

    public void setFormuleTexte(String formuleTexte) {
        this.formuleTexte = formuleTexte;
        // Notify observers that the formula has been updated
        //notifierObservateurs();
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }

    public EnumEtatCellule getEtatCellule() {
        return etatCellule;
    }

    public void setEtatCellule(EnumEtatCellule etatCellule) {
        this.etatCellule = etatCellule;
    }

    /*public void ajouterObservateur(ObservateurCellule observateur) {
        observateurs.add(observateur);
    }*/

    /*public void supprimerObservateur(ObservateurCellule observateur) {
        observateurs.remove(observateur);
    }*/

    /*private void notifierObservateurs() {
        for (ObservateurCellule observateur : observateurs) {
            observateur.miseAJour(this);
        }
    }*/
}