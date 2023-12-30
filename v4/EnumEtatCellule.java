package v4;

/**
 * L'énumération EnumEtatCellule représente les différents états possibles d'une cellule dans une feuille de calcul.
 * - VIDE : La cellule est vide, aucune formule n'est définie.
 * - CALCULABLE : La cellule a une formule valide et peut être évaluée.
 * - INCALCULABLE : La cellule a une formule, mais elle dépend d'une ou plusieurs cellules incorrectes ou indisponibles ou contient une division par 0.
 * - INCORRECTE : La cellule a une formule incorrecte ou mal formée.
 */
public enum EnumEtatCellule {
    VIDE,
    CALCULABLE,
    INCALCULABLE,
    INCORRECTE
}
