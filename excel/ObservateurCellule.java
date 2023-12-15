package excel;

public interface ObservateurCellule {

    // Méthode appelée lorsqu'une cellule observable est mise à jour
    void miseAJour(Cellule cellule);
}
