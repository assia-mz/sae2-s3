package v3;
/*
import java.awt.Color;
import java.util.*;

public class Cellule extends Observable {

    private static final String PROBLEM_CIRCULAIRE = "PROBLEME CIRCULAIRE";
    private static final String PROBLEM_CALCUL = "CALCUL IMPOSSIBLE";
    private static final String PROBLEM_FORMULE = "FORMULE INVALIDE";

    private static final Color COULEUR_CIRCULAIRE = new Color(206, 147, 216);
    private static final Color COULEUR_CALCUL = new Color(244, 143, 177);
    private static final Color COULEUR_FORMULE = new Color(229, 115, 115);
    private static final Color COULEUR_DEFAULT = Color.WHITE;

    private Color couleur;
    private DicoCellule dico;
    private String nom;
    private String valeur;
    private String contenu;
    private Arbre formule;
    private List<Cellule> dependantCells;

    private boolean isFormule;
    private boolean isFormuleError;
    private boolean isCircularProblem;
    private boolean isCalculImpossible;

    public Cellule(String nom, DicoCellule dico) {
        this.nom = nom;
        this.dico = dico;
        this.dependantCells = new ArrayList<>();
        this.isFormule = false;
        this.contenu = "";
        this.couleur = COULEUR_DEFAULT;
        setCellProperties();
    }

    public void setContenu(String contenu) {
        removeAllDependance();
        this.contenu = contenu;
        setFormuleAttributes();
        if (isFormule) {
            createFormule();
        }
        addAllDependance();
        setCircularProblemAttribute(this);
        updateValeur(this);
        notifyDependanceObservers();
    }

    public String getContenu() {
        return contenu;
    }

    public String getValeur() {
        return valeur;
    }

    public String getNom() {
        return nom;
    }

    public Color getColor() {
        return couleur;
    }

    public double getDouble() {
        return Double.parseDouble(valeur);
    }

	protected void updateValeur(Cellule origine){
		try{
			if (this.isCircularProblem == false){
				this.isCalculImpossible = false;
				this.isFormuleError = false;
				this.valeur = ""+this.formule.getValue();
			}
		}
		catch (NullPointerException e){
			this.isFormuleError = true;
		}
		catch (ArithmeticException e){
			this.isCalculImpossible = true;
		}
		catch (NumberFormatException e){
			this.isCalculImpossible = true;
		}

		this.setValeur();
		for (Cellule dependance : this.listDependance){
			if (dependance != origine){
				dependance.updateValeur(origine);
			}
		}
	}


	private void createFormule(){
		String[] args = this.contenu.split(" ");
		boolean flag = true;
		int i, taille = args.length;
		this.formule = new Arbre(this.dico);

		for (i=0; i<taille && flag; i++){
			flag = this.formule.add(args[i]);
		}
		if (flag==false){
			this.formule = null;
		}
	}

	private void setAttributIsFormule(){
		// eventuellement modifier le .split(" ") pour separer detecter les differents elements de la formule
		String[] args = this.contenu.split(" ");
		if (Calcul.getOperator(args[0]) != null || this.dico.getCellule(args[0]) != null){
			this.isFormule = true;
		}
		else{
			try {
                Double.parseDouble(args[0]);
                this.isFormule = true;
            } catch (NumberFormatException e) {
                this.isFormule = false;
            }
            this.isFormule = false;
            
		}
	}

	private boolean setAttributCircularProblem(Cellule origine){
		for (Cellule dependance : this.listDependance){
			if (dependance == origine){
				this.isCircularProblem = true;
				return true;
			}
			if (dependance.setAttributCircularProblem(origine)){
				this.isCircularProblem = true;
				return true;
			}
		}
		this.isCircularProblem = false;
		return false;
	}

    // Observable-related methods
    public void addDependanceObserver(Observer observer) {
        addObserver(observer);
    }

    public void removeDependanceObserver(Observer observer) {
        deleteObserver(observer);
    }

    private void notifyDependanceObservers() {
        setChanged();
        notifyObservers();
    }

    // Helper methods...

    private void setCellProperties() {
        couleur = COULEUR_DEFAULT;
        setValeur();
        setOpaque(true);
        setBackground(couleur);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private void setValeur() {
        couleur = COULEUR_DEFAULT;
        if (isFormule) {
            if (formule == null || isFormuleError) {
                valeur = PROBLEM_FORMULE;
                couleur = COULEUR_FORMULE;
            } else if (isCircularProblem) {
                valeur = PROBLEM_CIRCULAIRE;
                couleur = COULEUR_CIRCULAIRE;
            } else if (isCalculImpossible) {
                valeur = PROBLEM_CALCUL;
                couleur = COULEUR_CALCUL;
            }
        } else {
            valeur = contenu;
        }
        setText(valeur);
        setBackground(couleur);
    }

    private void setFormuleAttributes() {
        String[] args = contenu.split(" ");
        isFormule = false;
        if (Calcul.getOperator(args[0]) != null || dico.getCellule(args[0]) != null) {
            isFormule = true;
        } else {
            try {
                Double.parseDouble(args[0]);
                isFormule = true;
            } catch (NumberFormatException e) {
                isFormule = false;
            }
        }
    }

    private boolean setCircularProblemAttribute(Cellule origine) {
        for (Cellule dependance : dependantCells) {
            if (dependance == origine || dependance.setCircularProblemAttribute(origine)) {
                isCircularProblem = true;
                return true;
            }
        }
        isCircularProblem = false;
        return false;
    }

    private void addAllDependance() {
        if (isFormule && formule != null) {
            formule.addDependance(this);
        }
    }

    private void removeAllDependance() {
        if (isFormule && formule != null) {
            formule.removeDependance(this);
        }
    }

    protected boolean addDependance(Cellule nouvelle) {
        for (Cellule dependance : dependantCells) {
            if (nouvelle == dependance) {
                return true;
            }
        }
        return dependantCells.add(nouvelle);
    }

    protected boolean removeDependance(Cellule ancienne) {
        return dependantCells.remove(ancienne);
    }
}
*/