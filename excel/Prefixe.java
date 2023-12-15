package excel;

import java.util.ArrayDeque;

public class Prefixe {
    public static void main(String[] args) {
        ArrayDeque<Node<String>> pile = new ArrayDeque<>();
        for (String arg : args) {
            try {
                Integer.parseInt(arg);
                pile.addFirst(new Node<String>(arg));
            } catch (NumberFormatException e) {
                if (pile.size() < 2) {
                    System.err.println("Invalid stack size.");
                    return;
                }
                
                Node<String> n2 = pile.pollFirst();
                Node<String> n1 = pile.pollFirst();
                Node<String> n3 = new Node<String>(arg);
                n3.addSubNode(n1);
                n3.addSubNode(n2);
                
                pile.addFirst(n3);
            }
        }
        pile.pollFirst().showNode();
        System.out.println("");
    }
}