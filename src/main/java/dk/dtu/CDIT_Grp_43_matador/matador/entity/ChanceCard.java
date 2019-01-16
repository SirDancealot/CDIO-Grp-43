package dk.dtu.CDIT_Grp_43_matador.matador.entity;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects.*;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.ChanceCardDeck;

public class ChanceCard {
	private static final ChanceCardDeck cardDeck = ChanceCardDeck.getInstance();
	
	private CardEffect[] cardEffects;
	private boolean keepCard;
	private boolean freeJail = false;
	
	public ChanceCard(CardEffect[] cardEffects) {
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
		System.out.println(printCard(p));
		//printCard(p);
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
	
	public void setFreeJail(boolean freeJail) {
		this.freeJail = freeJail;
	}
	
	public void returnToDeck() {
    	cardDeck.returnCardToDeck(this);
    }
	
	private String printCard(Player p) {
		String result = p + " used a card with the effect ";
		if (cardEffects.length > 1)
			result += "s";
		result += "\n";
		for (CardEffect cardEffect : cardEffects)
			result += cardEffect.print(p) + " which is " + cardEffect.getClass().getName();

		return result;
	}
}
