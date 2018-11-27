package dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.ChanceCard;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Tile;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.ChanceCardDeck;

public class Chance extends Tile {
    public String type = "Chance";
    private static ChanceCardDeck deck = ChanceCardDeck.getInstance();


    /**
     * representing the 4 chance tiles on the board.
     * @param tilename parsed to superclass constructor {@code Tile}.
     * @param tileinfo parsed to superclass constructor {@code Tile}.
     */
    public Chance (String tilename, String tileinfo, int tileIndex) {
        super (tilename, tileinfo, tileIndex);

    }

    /**
     * (NOT FINISHED) Draws a chance card, and gives the effect of the card.
     * @param p The current player.
     * @return returns true if everything goes right, ergo the current player hasn't lost.
     */
    public boolean landOnTile(Player p) {
        ChanceCard currCard = deck.nextCard();
        System.out.println(currCard);
        boolean succeded = currCard.useCard(p);
        if (currCard.isKeep())
        	p.addKeepingCard(currCard);
        else
        	deck.returnCardToDeck(currCard);
        super.landOnTile(p);
        return succeded;
    }

}
