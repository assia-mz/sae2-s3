package v3;

/*
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Stack;
import javax.swing.JLabel;

public class CellClickListener implements MouseListener {
    private Cellule cell;
    private JLabel selectedLabel;
    private Stack<JLabel> visitedCellStack = new Stack<>();

    public CellClickListener(Cellule cell) {
        this.cell = cell;
        this.selectedLabel = null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel clickedLabel = (JLabel) e.getSource();

        System.out.println("Stack size before pop: " + visitedCellStack.size());
        if (!visitedCellStack.empty()) {
            JLabel old = visitedCellStack.pop();
            old.setBackground(Color.RED);
            old.setOpaque(true);
            old.repaint();
            System.out.println(old.getText());
        }        

        visitedCellStack.push(clickedLabel);
        printStack(); // Call the new method to print the stack contents
        System.out.println(visitedCellStack.peek().getText());


        // Check if the clicked cell is the currently selected cell
        if (visitedCellStack.peek() == selectedLabel) {
            // Already selected, do nothing or perform additional actions
            return;
        }

        // Set the background color to green for the newly selected cell
        if (!visitedCellStack.empty()) {
            clickedLabel.setBackground(Color.GREEN);
            clickedLabel.setOpaque(true);
        }

        if (selectedLabel != null) {
            System.out.println("old:"+selectedLabel.getText());
        }

        // Update the currently selected label
        selectedLabel = clickedLabel;

        System.out.println("new:"+selectedLabel.getText());

        String cellName = clickedLabel.getText();
        System.out.println("Clicked on cell: " + cellName);
        // Additional actions with the clicked Cellule object (e.g., show details)
    }

    public void printStack() {
        System.out.println("Stack contents:");
        for (JLabel label : visitedCellStack) {
            System.out.println(label.getText());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}

*/