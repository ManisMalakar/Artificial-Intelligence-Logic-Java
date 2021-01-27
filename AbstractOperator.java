package logic;
import java.util.*;

public abstract class AbstractOperator implements Goal {
	
	private ArrayList<Goal> operands;
	
	public AbstractOperator(Goal... operands){
		Goal[] operandArray = operands;
		
		this.operands = new ArrayList<Goal>();
		
		for(int i = 0; i < operandArray.length ; i++){
			this.operands.add(operandArray[i]);
		}
	}
	
	public AbstractOperator(ArrayList<Goal> operands){
		this.operands = operands;
	}
	
	public int operandCount(){
		return operands.size();
	}
	
	public Goal getOperand(int i){
		return operands.get(i);
	}
	
	public Goal firstOperand(){
		return operands.get(0);
	}
	
	public ArrayList<Goal> tailOperands(){
		ArrayList<Goal> tail = new ArrayList<Goal>(operands);
		tail.remove(0);
		return tail;
	}
	
	public boolean isEmpty(){
		return operands.isEmpty();
	}

}
