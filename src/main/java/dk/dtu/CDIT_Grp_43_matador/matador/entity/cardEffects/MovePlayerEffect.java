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
		String forward = "\t" + p + " moves " + moveAmt + " forwards\n";
		String backwards = "\t" + p + " moves " + (-moveAmt) + " backwards\n";
		
		return moveAmt > 0 ? forward : backwards;
	}
}
