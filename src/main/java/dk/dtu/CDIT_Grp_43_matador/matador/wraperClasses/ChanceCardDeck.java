package dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.ChanceCard;
import dk.dtu.CDIT_Grp_43_matador.matador.util.TextReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class ChanceCardDeck {
    private Queue<ChanceCard> cards;
    private static final ChanceCardDeck INSTANCE = new ChanceCardDeck();

    private ChanceCardDeck() { 
    	cards = new PriorityQueue<ChanceCard>();
    }

    public ChanceCard nextCard() {
        ChanceCard card = cards.poll();
        return card;
    }

    public void returnCardToDeck(ChanceCard card) {
        cards.add(card);
    }

    public void init() throws IOException {
        HashMap<String, String> chanceMap = TextReader.fileToHashMap("res/Cards.txt");
        ArrayList<ChanceCard> tmpCards = new ArrayList<ChanceCard>();
        for (Map.Entry<String, String> entry : chanceMap.entrySet()) {
            tmpCards.add(new ChanceCard(entry.getKey(), entry.getValue()));
        }
        Collections.shuffle(tmpCards);
        for (ChanceCard chanceCard : tmpCards) {
			cards.add(chanceCard);
		}
    }
    
    public int size() {
    	return cards.size();
    }

    public static ChanceCardDeck getInstance() {
		return INSTANCE;
	}
}