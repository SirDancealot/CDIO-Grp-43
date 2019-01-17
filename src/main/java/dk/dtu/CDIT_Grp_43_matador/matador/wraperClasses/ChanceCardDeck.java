package dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.ChanceCard;
import dk.dtu.CDIT_Grp_43_matador.matador.util.Factory;

import java.io.IOException;
import java.util.Queue;

public class ChanceCardDeck {
	private Queue<ChanceCard> cards;
    private static final ChanceCardDeck INSTANCE = new ChanceCardDeck();
    private ChanceCard currCard;
    /**
     * Sets up the deck of chance cards.
     */
    private ChanceCardDeck() { 
    	try {
			cards = Factory.getInstance().createCards();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    /**
     * A method to get the next card in the deck.
     * @return The next card in the deck
     */
    public ChanceCard nextCard() {
        ChanceCard card = cards.poll();
        currCard = card;
        return card;
    }

    /**
     * Puts the card back in the deck, at the bottom.
     * @param card The card to return to the deck
     */
    public void returnCardToDeck(ChanceCard card) {
        cards.add(card);
    }

    /**
     * Method to get the amount of cards in the deck.
     * @return the size of the deck.
     */
    public int size() {
    	return cards.size();
    }

    public static ChanceCardDeck getInstance() {
		return INSTANCE;
	}

    public ChanceCard getCurrCard() {
        return currCard;
    }
}