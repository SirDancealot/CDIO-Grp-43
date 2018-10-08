package wrapperClasses;

public class Player {
	private static int aiNum = 1;
	private String name;
	private boolean isAI = false;
	
	public Player(String name) {
		this.name = name;
	}

	public Player() {
		this.name = "AI " + aiNum;
		aiNum++;
		this.isAI = true;
	}
	
	public void setAI(boolean isAI) {
		this.isAI = isAI;
	}
	
	public String getName() {
		return name;
	}
	
	public void promptPlayer(String msg) {
		if (isAI) {
			System.out.println(name);
		} else {
			System.out.println(name);
		}
	}
}
