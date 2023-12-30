package v4;

/**
 * L'interface CelluleListener définit une méthode qui sera appelée lorsqu'une cellule est mise à jour.
 * Les classes qui implémentent cette interface peuvent réagir aux mises à jour de cellules.
 */
public interface CelluleListener {
    /**
     * Méthode appelée lorsqu'une cellule est mise à jour.
     *
     * @param cellule La cellule qui a été mise à jour.
     */
    void onCellUpdated(Cellule cellule);
}
