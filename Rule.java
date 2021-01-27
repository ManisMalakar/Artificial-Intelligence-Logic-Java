package logic;

import java.util.Hashtable;

public class Rule implements PCExpression {

    private SimpleSentence head;
    private Goal body;

    public Rule(SimpleSentence head) {
        this(head, null);
    }

    public Rule(SimpleSentence head, Goal body) {
        this.head = head;
        this.body = body;
        
    }

    public SimpleSentence getHead(){
    		return head;
    }
    
    public Goal getBody(){
    		return body;
    }
    
    public PCExpression standardizeVariablesApart(Hashtable<Variable, Variable> newVars){
    		SimpleSentence newHead = (SimpleSentence)getHead().standardizeVariablesApart(newVars);
    		Goal newBody = null;
    		if(getBody() != null)
    			newBody = (Goal)getBody().standardizeVariablesApart(newVars);
    		return new Rule(newHead, newBody);
    }
    

	public PCExpression replaceVariables(SubstitutionSet s){
		SimpleSentence newHead = (SimpleSentence)getHead().replaceVariables(s);
		Goal newBody = null;
		if(getBody() != null)
			newBody = (Goal)getBody().replaceVariables(s);
		return new Rule(newHead, newBody);
		
	}

    public String toString() {
        if (body == null)
            return head.toString();
        return head + " :- " + body;
    }


}
