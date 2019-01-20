package dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.ChanceCard;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;

public class Chance extends Tile {


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
    	super.landOnTile(p);
        ChanceCard currCard = p.nextCard();
        System.out.println(currCard);
        boolean succeded = currCard.useCard(p);
        if (currCard.isKeepCard())
        	p.addKeepingCard(currCard);
        else
            currCard.returnToDeck();
        return succeded;
    }

    @Override
    public String printLandOn(Player p) {
        String result = p + " landede på prøv lykken og trak et chancekort";
        return result;
    }

    @Override
    public String printPassed(Player p) {
        String result = "";
        return result;
    }

}
