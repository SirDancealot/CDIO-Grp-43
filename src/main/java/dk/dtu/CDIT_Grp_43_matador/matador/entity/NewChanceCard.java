package dk.dtu.CDIT_Grp_43_matador.matador.entity;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects.*;

public class NewChanceCard {
	private CardEffect[] cardEffects;
	private boolean keepCard;
	
	public NewChanceCard(CardEffect[] cardEffects) {
		this.cardEffects = cardEffects;
		for (CardEffect cardEffect : cardEffects) {
			if (cardEffect.isKeepEffect())
				keepCard = true;
		}
	}
	
	public boolean useCard(Player p) {
		for (CardEffect cardEffect : cardEffects) {
			if (!cardEffect.useEffect(p))
				return false;
		}
		return true;
	}
	
	public boolean isKeepCard() {
		return keepCard;
	}
}
