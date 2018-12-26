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
}
