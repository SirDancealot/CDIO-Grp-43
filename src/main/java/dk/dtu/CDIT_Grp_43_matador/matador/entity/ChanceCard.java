package dk.dtu.CDIT_Grp_43_matador.matador.entity;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects.*;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.ChanceCardDeck;

public class ChanceCard {
	private static ChanceCardDeck cardDeck;
	
	private CardEffect[] cardEffects;
	private boolean keepCard;
	private boolean freeJail = false;
	
	public ChanceCard(CardEffect[] cardEffects) {
		cardDeck = ChanceCardDeck.getInstance();
		this.cardEffects = cardEffects;
		for (CardEffect cardEffect : cardEffects) {
			if (cardEffect == null) {
				break;
			}
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
	
	public boolean isFreeJail() {
		return freeJail;
	}
	
	public void returnToDeck() {
    	cardDeck.returnCardToDeck(this);
    }
	
	public String printCard(Player p) {
		String result = "";
		for (CardEffect cardEffect : cardEffects)
			result += cardEffect.print(p);
		return result;
	}
}
