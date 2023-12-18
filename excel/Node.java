package excel;
import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    private T value;
    private List<Node<T>> subNodes;

    public Node(T value) {
        this.value = value;
        this.subNodes = new ArrayList<>();
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public List<Node<T>> getSubNodes() {
        return this.subNodes;
    }

    public boolean addSubNode(Node<T> node) {
        return subNodes.add(node);
    }

    public boolean remSubNode(Node<T> node) {
        return subNodes.remove(node);
    }

    public void showNode() {
        System.out.print(value.toString() + " ");

        for (Node<T> subNode : subNodes) {
            subNode.showNode();
        }
    }
}
