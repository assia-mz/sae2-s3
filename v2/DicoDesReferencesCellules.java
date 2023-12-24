package v2;

import java.util.HashMap;

public class DicoDesReferencesCellules{

    private HashMap<String,Cellule> dicoRef;
    
    public DicoDesReferencesCellules(){
        this.dicoRef = new HashMap<String,Cellule>();
    }

    public Cellule putDico(String name, Cellule valueOfCell){
        return this.dicoRef.put(name, valueOfCell);
    } 

    public Cellule getCellule(String nameOfCellule){
		return this.dicoRef.get(nameOfCellule);
	}
}