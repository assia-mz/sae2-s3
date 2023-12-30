package v4;

import java.util.HashMap;
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

    public Map<String, Cellule> getDico() {
        return dico;
    }
}
