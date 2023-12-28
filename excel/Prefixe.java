/*package excel;

import java.util.ArrayDeque;

public class Prefixe {
    public static void main(String[] args) {
        ArrayDeque<Node<String>> stack = new ArrayDeque<>();

        for (String arg : args) {
            if (isNumeric(arg)) {
                stack.addFirst(new Node<String>(arg));
            } else {
                if (stack.size() < 2) {
                    System.err.println("Invalid stack size.");
                    return;
                }

                Node<String> n2 = stack.pollFirst();
                Node<String> n1 = stack.pollFirst();
                Node<String> n3 = new Node<String>(arg);
                n3.addSubNode(n1);
                n3.addSubNode(n2);

                stack.addFirst(n3);
            }
        }

        if (stack.size() != 1) {
            System.err.println("Invalid stack .");
        } else {
            stack.pollFirst().showNode();
            System.out.println("");
        }
    }

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
*/