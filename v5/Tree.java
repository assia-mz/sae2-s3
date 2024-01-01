package v5;

/**
 * La classe Tree représente un arbre générique.
 * @param <T> Le type de données des nœuds de l'arbre.
 */
public class Tree<T> {
    /**
     * Le nœud racine de l'arbre.
     */
    private Node<T> rootNode;

    /**
     * Initialise une nouvelle instance de la classe Tree avec un nœud racine nul.
     */
    public Tree() {
        rootNode = new Node<T>(null, false);
    }

    /**
     * Définit le nœud racine de l'arbre.
     * @param rootNode Le nouveau nœud racine.
     */
    public void setRootNode(Node<T> rootNode) {
        this.rootNode = rootNode;
    }

    /**
     * Récupère le nœud racine de l'arbre.
     * @return Le nœud racine de l'arbre.
     */
    public Node<T> getRootNode() {
        return rootNode;
    }

    /**
     * Affiche l'arbre en commençant par le nœud racine.
     */
    public void showTree() {
        rootNode.showNode();
    }
}