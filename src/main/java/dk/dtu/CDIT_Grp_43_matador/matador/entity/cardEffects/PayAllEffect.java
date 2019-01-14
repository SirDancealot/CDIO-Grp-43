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
	
	@Override
	public String toString() {
		return super.toString() + "PayAllEffect";
	}

	@Override
	public String print(Player p) {
		String pay = "\t" + p + " pays " + payAllAmt + " to all players\n";
		String recieve = "\t" + p + " recieves " + (-payAllAmt) + " from all players\n";
		
		return payAllAmt > 0 ? pay : recieve;
	}
}
