package dk.dtu.CDIT_Grp_43_matador.matador.entity;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects.*;
import dk.dtu.CDIT_Grp_43_matador.matador.util.InformationExchanger;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.ChanceCardDeck;

public class ChanceCard {
	private static final ChanceCardDeck cardDeck = ChanceCardDeck.getInstance();
	private static final InformationExchanger infExch = InformationExchanger.getInstance();
	
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
		printCard(p);
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
	
	private void printCard(Player p) {
		infExch.addToCurrentTurnText(p + " used a card with the effect");
		if (cardEffects.length > 1)
			infExch.addToCurrentTurnText("s");
		infExch.addToCurrentTurnText("\n");
		for (CardEffect cardEffect : cardEffects) {
			infExch.addToCurrentTurnText(cardEffect.printEffect(p));
		}
	}
}
