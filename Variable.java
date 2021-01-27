package logic;

import java.util.Hashtable;

public class Variable implements Unifiable {

    private String printName = null;
    private static int nextId = 1;  //Allows anonymous variables, indicated only by id. Also, it is useful in tracing solutions after standardizing variables apart.
    private int id;


    public Variable() {
    		//System.out.println(nextId);
        this.id = nextId++;
    }

    public Variable(String printName) {
        this();
        this.printName = printName;
    }
    
    //Copy constructor. Currently considered to be better style than cloning.
    public Variable(Variable v){
        	this();
    		this.printName = v.printName;
    }
    

	public SubstitutionSet unify(Unifiable p, SubstitutionSet s){
        // Unification with self always succeeds
        if (this == p)
            return s;

        // Find existing binding and unify with it, if one exists
        if(s.isBound(this))
        		return s.getBinding(this).unify(p, s);

        // No binding? make a new one and succeed
        SubstitutionSet sNew = new SubstitutionSet(s);
        sNew.add(this, p);
        return sNew;
		
	}
	
	public PCExpression replaceVariables(SubstitutionSet s){
		
		if(s.isBound(this))
			return s.getBinding(this).replaceVariables(s);
		else
			return this;

	}
	
	public PCExpression standardizeVariablesApart(Hashtable<Variable, Variable> newVars){
		Variable newVar = newVars.get(this);  //Try to see if the current expression already has a substitute variable.
		if(newVar == null){					// if not create one.
			newVar = new Variable(this);
			newVars.put(this, newVar);
		}
		return newVar;
	}

    public String toString() {
        if (printName != null)
            return printName + "_" + id;
        return "V" + id;
    }

}
