package dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;

public class MovePlayerEffect extends CardEffect {
	private int moveAmt;
	public MovePlayerEffect(int moveAmt) {
		this.moveAmt = moveAmt;
	}
	
	@Override
	public boolean useEffect(Player p) {
		p.move(moveAmt);
		return true;
	}
}
