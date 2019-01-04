package dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;

public class ChangeMoneyEffect extends CardEffect {
	private int moneyChange;
	
	public ChangeMoneyEffect(int moneyChange) {
		this.moneyChange = moneyChange;
	}
	
	@Override
	public boolean useEffect(Player p) {
		p.addMoney(moneyChange);
		return true;
	}
	
	@Override
	public String toString() {
		return super.toString() + "changeMoneyEffect";
	}

	@Override
	public String printEffect(Player p) {
		String recieveMoney = "\t" + p + " recieves " + moneyChange + "\n";
		String payMoney = "\t" + p + " looses " + (-moneyChange) + "\n";
		return (moneyChange > 0) ? recieveMoney : payMoney;
	}
}