package v4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DicoDesReferencesCellules {
    private Map<String, Cellule> dico;
    private Map<String, Cellule[]> dependencies;

    public DicoDesReferencesCellules() {
        this.dico = new HashMap<>();
        this.dependencies = new HashMap<>();
    }

    public void putDico(String name, Cellule cellule) {
        dico.put(name, cellule);
    }

    public Cellule getCellule(String name) {
        return dico.get(name);
    }

    public void addDependencies(String name, Cellule[] dependences) {
        dependencies.put(name, dependences);
    }

    public Cellule[] getDependencies(String name) {
        return dependencies.get(name);
    }

    public List<Cellule> getAllCells() {
        return new ArrayList<>(dico.values());
    }

    public List<Cellule> findCellsDependingOn(Cellule targetCellule) {
        List<Cellule> cellsDependingOn = new ArrayList<>();
    
        for (Map.Entry<String, Cellule[]> entry : dependencies.entrySet()) {
            Cellule[] dependences = entry.getValue();
            if (dependences != null) {
                for (Cellule dependence : dependences) {
                    if (dependence.equals(targetCellule)) {
                        cellsDependingOn.add(dico.get(entry.getKey()));
                        break;  // Break to avoid duplicate entries
                    }
                }
            }
        }
    
        return cellsDependingOn;
    }

    public void notifyDependentsOnFormulaChange(String changedCellName, String newFormula) {
        List<Cellule> dependents = findCellsDependingOn(getCellule(changedCellName));
        for (Cellule dependent : dependents) {
            dependent.formulaChanged(newFormula);
        }
    }
    

    // Add the new getDependencies method here
    public List<Cellule> getListDependencies(String name) {
        Cellule[] dependenciesArray = dependencies.get(name);
        List<Cellule> dependenciesList = new ArrayList<>();

        if (dependenciesArray != null) {
            dependenciesList.addAll(Arrays.asList(dependenciesArray));
        }

        return dependenciesList;
    }

    public Map<String, Cellule> getDico() {
        return dico;
    }

    public void printDependencies() {
        System.out.println("Dependencies:");
        for (Map.Entry<String, Cellule[]> entry : dependencies.entrySet()) {
            String cellName = entry.getKey();
            Cellule[] dependences = entry.getValue();
            System.out.print(cellName + ": [");
            if (dependences != null) {
                for (Cellule dependence : dependences) {
                    System.out.print(dependence.getName() + ", ");
                }
            }
            System.out.println("]");
        }
    }
}
