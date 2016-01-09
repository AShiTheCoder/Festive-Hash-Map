
public class Die {
	private int sides;
	private int roll = 0;
	
	public Die(int s){
		sides = s;
	}
	public int roll(){
		roll = (int) (Math.random() * sides + 1);
		return roll;
	}
	public int getResult(){
		return roll;
	}
}
