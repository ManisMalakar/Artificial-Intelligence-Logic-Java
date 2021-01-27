package logic;

import java.util.Hashtable;

public class RuleSet {

    private Rule[] rules;

    public RuleSet(Rule... rules) {
        this.rules = rules;
    }
    
    public Rule getRuleStandardizedApart(int i)
    {
    		Rule rule = (Rule)rules[i].standardizeVariablesApart(new Hashtable<Variable, Variable>());
    		return rule;
    }
    
    public Rule getRule(int i)
    {
    		return rules[i];
    }
    
    public int getRuleCount()
    {
    		return rules.length;
    }
    
    public String toString() {
        String s = null;
        for (PCExpression r : rules)
            if (s == null)
                s = r.toString();
            else
                s += "; " + r;
        if (s == null)
            return "true";
        return "(" + s + ")";
    }

}
