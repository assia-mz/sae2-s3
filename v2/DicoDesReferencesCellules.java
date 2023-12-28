package v2;

import java.util.HashMap;

public class DicoDesReferencesCellules {
    private HashMap<String, Cellule> dicoRef;

    public DicoDesReferencesCellules() {
        this.dicoRef = new HashMap<>();
    }

    public Cellule putDico(String name, Cellule valueOfCell) {
        return this.dicoRef.put(name, valueOfCell);
    }

    public Cellule getCellule(String nameOfCellule) {
        System.out.println("Searching for cell: " + nameOfCellule);
        Cellule cell = this.dicoRef.get(nameOfCellule);
        System.out.println("Found cell: " + cell);
        return cell;
    }    

    // Added method to get the underlying dictionary
    public HashMap<String, Cellule> getDico() {
        return new HashMap<>(dicoRef); // Return a copy to prevent external modifications
    }
}
