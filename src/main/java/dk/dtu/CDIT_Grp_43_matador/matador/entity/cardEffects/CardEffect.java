package dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;

public abstract class CardEffect {
	protected boolean keepEffect = false;
	protected boolean freeJailEffect = false;
	
	public CardEffect() {
	}
	
	public abstract boolean useEffect(Player p);
	
	public boolean isKeepEffect() {
		return keepEffect;
	}
	
	@Override
	public String toString() {
		return "This card effect is: ";
	}
	
	public abstract String print(Player p);
}
