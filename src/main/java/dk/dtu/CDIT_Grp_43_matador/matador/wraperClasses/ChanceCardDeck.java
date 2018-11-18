package dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.ChanceCard;
import dk.dtu.CDIT_Grp_43_matador.matador.util.TextReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ChanceCardDeck {
    private ArrayList<ChanceCard> cards;

    public ChanceCardDeck() {
        cards = new ArrayList<ChanceCard>();
    }

    public ChanceCard nextCard() {
        ChanceCard card = cards.get(0);
        ArrayList<ChanceCard> newCards = new ArrayList<ChanceCard>();
        for (int i = 1; i < cards.size(); i++) {
            newCards.add(cards.get(i));
        }
        cards = newCards;
        return card;
    }

    public void returnCardToDeck(ChanceCard card) {
        cards.add(card);
    }

    public int size() {
        return cards.size();
    }

    public void init() throws IOException {
        HashMap<String, String> chanceMap = TextReader.fileToHashMap("res/Cards.txt");
        for (Map.Entry<String, String> entry : chanceMap.entrySet()) {
            cards.add(new ChanceCard(entry.getKey(), entry.getValue()));
        }
        Collections.shuffle(cards);
    }
}