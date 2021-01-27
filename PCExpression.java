package logic;

import java.util.Hashtable;

public interface PCExpression {
	public PCExpression replaceVariables(SubstitutionSet s);
	public PCExpression standardizeVariablesApart(Hashtable<Variable, Variable> newVars);
}
