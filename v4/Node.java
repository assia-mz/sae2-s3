package v4;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe Node<T> représente un nœud dans un arbre générique.
 *
 * @param <T> Type de la valeur stockée dans le nœud.
 */
public class Node<T> {
    private T value;
    private List<Node<T>> subNodes;
    private boolean isLeftChild;

    /**
     * Constructeur de la classe Node.
     *
     * @param value       Valeur du nœud.
     * @param isLeftChild Indique si le nœud est un enfant gauche.
     */
    public Node(T value, boolean isLeftChild) {
        this.value = value;
        this.subNodes = new ArrayList<>();
        this.isLeftChild = isLeftChild;
    }

    /**
     * Obtient la valeur du nœud.
     *
     * @return Valeur du nœud.
     */
    public T getValue() {
        return value;
    }

    /**
     * Définit la valeur du nœud.
     *
     * @param value Nouvelle valeur du nœud.
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Obtient la liste des nœuds enfants du nœud actuel.
     *
     * @return Liste des nœuds enfants.
     */
    public List<Node<T>> getSubNodes() {
        return this.subNodes;
    }

    /**
     * Ajoute un nœud enfant à la liste des nœuds enfants.
     *
     * @param node Nœud à ajouter.
     * @return True si l'ajout est réussi, sinon False.
     */
    public boolean addSubNode(Node<T> node) {
        return subNodes.add(node);
    }

    /**
     * Supprime un nœud enfant de la liste des nœuds enfants.
     *
     * @param node Nœud à supprimer.
     * @return True si la suppression est réussie, sinon False.
     */
    public boolean remSubNode(Node<T> node) {
        return subNodes.remove(node);
    }

    /**
     * Affiche les informations du nœud, indiquant s'il s'agit d'un enfant gauche ou droit.
     */
    public void showNode() {
        System.out.print(value.toString() + " ");
        System.out.println(isLeftChild ? "(Gauche)" : "(Droite)");  // Indique si le nœud est un enfant gauche ou droit

        for (Node<T> subNode : subNodes) {
            subNode.showNode();
        }
    }
}
