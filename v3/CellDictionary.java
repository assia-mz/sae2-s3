package v3;
/*
import java.util.HashMap;

public class CellDictionary {
    private HashMap<String, Cellule2> cellMap;

    public CellDictionary() {
        this.cellMap = new HashMap<>();
    }

    public void addCell(String name, Cellule2 cellValue) {
        this.cellMap.put(name, cellValue);
    }

    public String getCellName(Cellule2 cellValue) {
        System.out.println("Searching for Cellule2 with value: " + cellValue);
        for (String name : cellMap.keySet()) {
            if (cellMap.get(name).equals(cellValue)) {
                System.out.println("Found Cellule2 with name: " + name);
                return name;
            }
        }
        System.out.println("Cellule2 not found.");
        return null;
    }

    public Cellule2 getCellValue(String cellName) {
        System.out.println("Searching for Cellule2: " + cellName);
        Cellule2 Cellule2 = this.cellMap.get(cellName);
        System.out.println("Found Cellule2: " + Cellule2);
        return Cellule2;
    }

    // Added method to get the underlying dictionary
    public HashMap<String, Cellule2> getCellMap() {
        return new HashMap<>(cellMap); // Return a copy to prevent external modifications
    }
}
*/