package logic;

import java.util.Iterator;
import java.util.Vector;


public class UnifyTester {

	public static void main(String[] args) {
		Constant friend = new Constant("friend"),
				bill = new Constant("bill"),
				george = new Constant("george"),
				kate = new Constant("kate"),
				merry = new Constant("merry");
		
		Variable X = new Variable("X"), Y = new Variable("Y");
		
		Vector<Unifiable> expressions = new Vector<Unifiable>();
		
		expressions.add(new SimpleSentence(friend, bill, george));
		expressions.add(new SimpleSentence(friend, bill, kate));
		expressions.add(new SimpleSentence(friend, bill, merry));
		expressions.add(new SimpleSentence(friend, george, bill));
		expressions.add(new SimpleSentence(friend, george, kate));
		expressions.add(new SimpleSentence(friend, kate, merry));
		
		Unifiable goal = new SimpleSentence(friend, X, Y);
		
		Iterator<Unifiable> iter = expressions.iterator();
		SubstitutionSet s;
		
		System.out.println("Goal = " + goal);
		
		while(iter.hasNext()){
			Unifiable next = (Unifiable)iter.next();
			
			s = next.unify(goal, new SubstitutionSet());
			if(s != null)
				System.out.println(goal.replaceVariables(s));
			else
				System.out.println("False");
		}
		
		goal = new SimpleSentence(friend, bill, Y);
		
		iter = expressions.iterator();
		
		System.out.println("Goal = " + goal);
		
		while(iter.hasNext()){
			Unifiable next = (Unifiable)iter.next();
			
			s = next.unify(goal, new SubstitutionSet());
			if(s != null)
				System.out.println(goal.replaceVariables(s));
			else
				System.out.println("False");
		}
	}
}
