package logic;

public interface Goal extends PCExpression {
	public AbstractSolutionNode getSolver(RuleSet rules, SubstitutionSet parentSolution);
}
