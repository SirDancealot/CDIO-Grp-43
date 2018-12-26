package dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;

public class MovePlayerToEffect extends CardEffect {
	private String moveToTag;
	
	public MovePlayerToEffect(String moveToTag) {
		this.moveToTag = moveToTag;
	}
	
	@Override
	public boolean useEffect(Player p) {
		p.moveTo(moveToTag);
		return true;
	}
	
	@Override
	public String toString() {
		return super.toString() + "MovePlayerToEffect";
	}
}
