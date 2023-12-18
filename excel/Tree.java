package excel;

public class Tree<T> {
    private Node<T> rootNode;

    public Tree() {
        rootNode = new Node<T>(null);
    }

    public void setRootNode(Node<T> rootNode) {
        this.rootNode = rootNode;
    }

    public Node<T> getRootNode() {
        return rootNode;
    }
    
    public void showTree() {
        rootNode.showNode();
    }
}
