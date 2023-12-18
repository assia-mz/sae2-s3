package excel;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe Node représente un nœud générique dans un arbre.
 * @param <T> Le type de données stockées dans le nœud.
 */
public class Node<T> {
    /**
     * La valeur stockée dans le nœud.
     */
    private T value;

    /**
     * La liste des nœuds enfants.
     */
    private List<Node<T>> subNodes;

    /**
     * Initialise une nouvelle instance de la classe Node avec une valeur donnée.
     * @param value La valeur du nœud.
     */
    public Node(T value) {
        this.value = value;
        this.subNodes = new ArrayList<>();
    }

    /**
     * Obtient la valeur du nœud.
     * @return La valeur du nœud.
     */
    public T getValue() {
        return value;
    }

    /**
     * Définit la valeur du nœud.
     * @param value La nouvelle valeur du nœud.
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Obtient la liste des nœuds enfants.
     * @return La liste des nœuds enfants.
     */
    public List<Node<T>> getSubNodes() {
        return this.subNodes;
    }

    /**
     * Ajoute un nœud enfant à la liste.
     * @param node Le nœud à ajouter.
     * @return true si l'ajout a réussi, false sinon.
     */
    public boolean addSubNode(Node<T> node) {
        return subNodes.add(node);
    }

    /**
     * Supprime un nœud enfant de la liste.
     * @param node Le nœud à supprimer.
     * @return true si la suppression a réussi, false sinon.
     */
    public boolean remSubNode(Node<T> node) {
        return subNodes.remove(node);
    }

    /**
     * Affiche la valeur du nœud et ses nœuds enfants de manière récursive.
     */
    public void showNode() {
        System.out.print(value.toString() + " ");
        
        for (Node<T> subNode : subNodes) {
            subNode.showNode();
        }
    }
}
