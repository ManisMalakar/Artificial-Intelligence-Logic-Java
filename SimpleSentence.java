package logic;

import java.util.Hashtable;

public class SimpleSentence implements Unifiable, Goal {

    private Unifiable[] terms;

    public SimpleSentence(Constant functor, Unifiable... args) {
        this.terms = new Unifiable[args.length + 1];
        terms[0] = functor;
        System.arraycopy(args, 0, terms, 1, args.length);
    }
    
    private SimpleSentence(Unifiable... args){
    		terms = args;
    }

    public String toString() {
        String s = null;
        for (Unifiable p : terms)
            if (s == null)
                s = p.toString();
            else
                s += " " + p;
        if (s == null)
            return "false";
        return "(" + s + ")";
    }
    
    public int length(){
    		return terms.length;
    }
    
    public Unifiable getTerm(int index){
    		return terms[index];
    }
    
	public SubstitutionSet unify(Unifiable p, SubstitutionSet s) {

        if(p instanceof Variable)
        		return p.unify(this, s);
        
        // Unification with another List
        if (p instanceof SimpleSentence) {
            SimpleSentence s2 = (SimpleSentence) p;
            // Compare list lengths
            if (this.length() != s2.length())
                return null;
            
            // Unify pairs 
            SubstitutionSet sNew = new SubstitutionSet(s);
            for (int i = 0; i < this.length(); i++)
            {
            		sNew = this.getTerm(i).unify(s2.getTerm(i), sNew);
            		if(sNew == null)
            			return null;
            }
            return sNew;
        }
		
		return null;
	}

    public PCExpression replaceVariables(SubstitutionSet s){
		Unifiable[] newTerms = new Unifiable[terms.length]; //create an array for new terms.
		for(int i = 0; i < length(); i++)
			newTerms[i] = (Unifiable)terms[i].replaceVariables(s);
			
	    return new SimpleSentence(newTerms); //create a new simple sentence with new variables.
    }
    
	public PCExpression standardizeVariablesApart(Hashtable<Variable, Variable> newVars){
		Unifiable[] newTerms = new Unifiable[terms.length]; //create an array for new terms.
		for(int i = 0; i < length(); i++){
			newTerms[i] = (Unifiable)terms[i].standardizeVariablesApart(newVars); //standardize apart each term. Only variables will be affected.
		}
		
		return new SimpleSentence(newTerms); //create a new simple sentence with new variables.
	}

    public AbstractSolutionNode getSolver(RuleSet rules, SubstitutionSet parentSolution){
    		return new SimpleSentenceSolutionNode(this, rules, parentSolution);
    }
}
