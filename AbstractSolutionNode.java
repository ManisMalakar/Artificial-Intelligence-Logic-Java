package logic;

public abstract class AbstractSolutionNode {
	
	private RuleSet rules;
	
	// saving the parent solution allows backtracking to the original state
	private SubstitutionSet parentSolution;
	
	//These variables allow the solution node to iterate over the rule set.
	private int ruleNumber = 0;
	
	public AbstractSolutionNode(RuleSet rules, SubstitutionSet parentSolution)
	{
		this.rules = rules;
		this.parentSolution = parentSolution;
	}
	
	public abstract SubstitutionSet nextSolution();
	
	protected Rule nextRule(){
		if(hasNextRule())
			return rules.getRuleStandardizedApart(ruleNumber++);
		else
			return null;
	}
	
	protected boolean hasNextRule(){
		return ruleNumber < rules.getRuleCount();
	}
	
	protected SubstitutionSet getParentSolution(){
		return parentSolution;
	}
	
	protected RuleSet getRuleSet(){
		return rules;
	}
	

}
