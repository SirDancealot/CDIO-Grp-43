package dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles;

import java.util.ArrayList;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.NewChanceCard;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;

public class Jail extends Tile {
    private int outOfJailPrice = 1;
    public String type = "Jail";

    public Jail(String tilename, String tileinfo, int tileIndex){
        super(tilename, tileinfo, tileIndex);

    }

    /**
     * Used when a player needs to pay to leave jail. Calling the withDrawMoney from the Player class
     * @param p The current player.
     * @return returns true if everything went as planned.
     */
    @Override
    public boolean passedTile(Player p) {
        if (p.isInJail()) {
        	p.setInJail(false);
        	ArrayList<NewChanceCard> playerKeepingCards = p.getKeepingCards();
        	boolean freeJail = false;
        	for (NewChanceCard chanceCard : playerKeepingCards) {
				if(chanceCard.isFreeJail()) {
					freeJail = true;
					chanceCard.returnToDeck();
					break;
				}
			}
        	if (!freeJail) {
        		infExch.addToCurrentTurnText(p + " payed " + outOfJailPrice + " to get out of jail\n");
        		return p.withDrawMoney(outOfJailPrice);
        	}
        	infExch.addToCurrentTurnText(p + " had a get out of jail free card and exited the jail for free\n");
        }
        return true;
    }
}
