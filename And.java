package logic;

import java.util.*;

public class And extends AbstractOperator {
	
	public And(Goal... operands){
		super(operands);
	}
	
	public And(ArrayList<Goal> operands){
		super(operands);
	}
	
	public AbstractSolutionNode getSolver(RuleSet rules, SubstitutionSet parentSolution){
		return new AndSolutionNode(this, rules, parentSolution);
	}
	
	public PCExpression standardizeVariablesApart(Hashtable<Variable, Variable> newVars){
		ArrayList<Goal> newOperands = new ArrayList<Goal>();
	
		for(int i = 0; i < operandCount(); i++)
			newOperands.add((Goal)getOperand(i).standardizeVariablesApart(newVars));
		
		return new And(newOperands);
	}

	public PCExpression replaceVariables(SubstitutionSet s){
		ArrayList<Goal> newOperands = new ArrayList<Goal>();
	
		for(int i = 0; i < operandCount(); i++)
			newOperands.add((Goal)getOperand(i).replaceVariables(s));
		
		return new And(newOperands);
	}

	
	public String toString(){
		String result = new String("(AND ");
		for(int i = 0; i < operandCount(); i++)
			result = result + getOperand(i).toString();
		return result;
	}
}
