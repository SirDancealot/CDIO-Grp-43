package dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;

public class FreeJailEffect extends CardEffect {
	public FreeJailEffect() {
		keepEffect = true;
	}
	
	@Override
	public boolean useEffect(Player p) {
		return true;
	}
}
