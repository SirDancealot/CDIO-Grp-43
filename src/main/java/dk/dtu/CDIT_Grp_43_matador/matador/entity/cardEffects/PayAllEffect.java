package dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;

public class PayAllEffect extends CardEffect {
	private int payAllAmt;
	public PayAllEffect(int payAllAmt) {
		this.payAllAmt = payAllAmt;
	}
	
	@Override
	public boolean useEffect(Player p) {
		return p.payAll(payAllAmt);
	}
}
