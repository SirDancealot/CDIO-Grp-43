package dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;

public class MovePlayerEffect extends CardEffect {
	private int moveAmt;
	public MovePlayerEffect(int moveAmt) {
		this.moveAmt = moveAmt;
	}
	
	@Override
	public boolean useEffect(Player p) {
		return p.move(moveAmt);
	}
	
	@Override
	public String toString() {
		return super.toString() + "MovePlayerEffect";
	}

	@Override
	public String print(Player p) {
		String forward = "\t" + p + " rykker " + moveAmt + " fremad\n";
		String backwards = "\t" + p + " rykker " + (-moveAmt) + " bagud\n";
		
		return moveAmt > 0 ? forward : backwards;
	}
}
