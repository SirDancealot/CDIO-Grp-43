package dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;

public abstract class CardEffect {
	protected boolean keepEffect = false;
	
	public CardEffect() {
	}
	
	public abstract boolean useEffect(Player p);
	
	public boolean isKeepEffect() {
		return keepEffect;
	}
}
