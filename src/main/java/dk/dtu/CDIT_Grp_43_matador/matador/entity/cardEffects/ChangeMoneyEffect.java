package dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;

public class ChangeMoneyEffect extends CardEffect {
	private int moneyChange;
	
	public ChangeMoneyEffect(int moneyChange) {
		this.moneyChange = moneyChange;
	}
	
	@Override
	public boolean useEffect(Player p) {
		return p.addMoney(moneyChange);
	}
	
	@Override
	public String toString() {
		return super.toString() + "changeMoneyEffect";
	}

	@Override
	public String print(Player p) {
		String recieveMoney = "\t" + p + " modtager " + moneyChange + "\n";
		String payMoney = "\t" + p + " mister " + (-moneyChange) + "\n";
		return (moneyChange > 0) ? recieveMoney : payMoney;
	}
}
