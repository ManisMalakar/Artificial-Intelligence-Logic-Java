package logic;

import java.util.Hashtable;

public class Constant implements Unifiable {

    private String printName = null;

    private static int nextId = 1;

    private int id;

    public Constant() {
        this.id = nextId++;
    }

    public Constant(String printName) {
        this();
        this.printName = printName;
    }
    
    public SubstitutionSet unify(Unifiable p, SubstitutionSet s) {
        // We can unify with another constant by comparing
        if (this == p)
            return new SubstitutionSet(s);
        
        if (p instanceof Variable)
        	return p.unify(this, s);
        
		return null;
	}
    
    public PCExpression replaceVariables(SubstitutionSet s){
		return this;
	}

    public PCExpression standardizeVariablesApart(Hashtable<Variable, Variable> newVars){
    	 return this;
    }
    
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Constant))
            return false;
        return id == ((Constant) o).id;
    }

    public String toString() {
        if (printName != null)
            return printName;
        return "a" + id;
    }
}
