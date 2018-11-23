package dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.ChanceCard;
import dk.dtu.CDIT_Grp_43_matador.matador.util.TextReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ChanceCardDeck {
    private Queue<ChanceCard> cards;
    private static final ChanceCardDeck INSTANCE = new ChanceCardDeck();

    /**
     * Sets up the deck of chance cards.
     */
    private ChanceCardDeck() { 
    	cards = new LinkedList<ChanceCard>();
    }

    /**
     * A method to get the next card in the deck.
     * @return The next card in the deck
     */
    public ChanceCard nextCard() {
        ChanceCard card = cards.poll();
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
     * Creates an ArrayList, so that the deck can be shuffled.
     * @throws IOException When the file cannot be read.
     */
    public void init() throws IOException {
        HashMap<String, String> chanceMap = TextReader.fileToHashMap("res/Cards.txt");
        ArrayList<ChanceCard> tmpCards = new ArrayList<ChanceCard>();
        for (Map.Entry<String, String> entry : chanceMap.entrySet()) {
            tmpCards.add(new ChanceCard(entry.getValue()));
        }
        Collections.shuffle(tmpCards);
        for (ChanceCard chanceCard : tmpCards) {
			cards.add(chanceCard);
		}
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
}