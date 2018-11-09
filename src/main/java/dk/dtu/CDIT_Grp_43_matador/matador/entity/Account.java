package dk.dtu.CDIT_Grp_43_matador.matador.entity;

public class Account {
    private int money;

    public Account(int initialAmount) {
        money = initialAmount;
    }

    @Override
    public String toString() {
        return Integer.toString(money);
    }

    // Getter and setters

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int addMoney(int money){
    	if (money < 0)
    		return withdrawMoney(money);
        this.money += money;
        return 1;
    }

    private int withdrawMoney (int money){
    	this.money -= Math.abs(money);
    	if (this.money < 0) {
    		this.money = 0;
    		return 0;
    	}
    	return 1;
    }

}