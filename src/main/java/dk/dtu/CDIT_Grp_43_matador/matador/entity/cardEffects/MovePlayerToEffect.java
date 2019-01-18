package dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;

public class MovePlayerToEffect extends CardEffect {
	private String moveToTag;
	
	public MovePlayerToEffect(String moveToTag) {
		this.moveToTag = moveToTag;
	}
	
	@Override
	public boolean useEffect(Player p) {
		if(moveToTag.equals("Jail")) {
			p.setInJail(true);
			return  p.moveTo(moveToTag);
		}
		return p.moveTo(moveToTag);
	}
	
	@Override
	public String toString() {
		return super.toString() + "MovePlayerToEffect";
	}

	@Override
	public String print(Player p) {
		return "\t" + p + " rykker til feltet: " + moveToTag + "\n";
	}
}
