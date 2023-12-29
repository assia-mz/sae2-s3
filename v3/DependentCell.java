package v2;

import java.util.Observable;
import java.util.Observer;

public class DependentCell extends Cellule implements Observer {
    public DependentCell(String name, DicoDesReferencesCellules DicoCell) {
        super(name, DicoCell);
    }

    // Override the update method to handle changes in the observed cell
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Cellule) {
            // Handle the update, e.g., reevaluate the formula
            Cellule observedCell = (Cellule) o;
            reevaluateFormula(observedCell);
        }
    }

    private void reevaluateFormula(Cellule observedCell) {
        // Implement the logic to reevaluate the formula or update the dependent cell
        // You may need to adapt this based on your specific implementation
    }
}
