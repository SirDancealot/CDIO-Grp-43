package dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles;

import java.util.ArrayList;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.ChanceCard;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;

public class Jail extends Tile {
    private int outOfJailPrice = 1;

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
        	ArrayList<ChanceCard> playerKeepingCards = p.getKeepingCards();
        	boolean freeJail = false;
        	for (ChanceCard chanceCard : playerKeepingCards) {
				if(chanceCard.isFreeJail()) {
					freeJail = true;
					chanceCard.returnToDeck();
					break;
				}
			}
        	if (!freeJail) {
        		//infExch.addToCurrentTurnText(p + " payed " + outOfJailPrice + " to get out of jail\n");
        		return p.withDrawMoney(outOfJailPrice);
        	}
        	//infExch.addToCurrentTurnText(p + " had a get out of jail free card and exited the jail for free\n");
        }
        return true;
    }

	@Override
	public String printLandOn(Player p) {
    	String result;
		if (p.isInJail())
			result =  p + " er sat i fængsel";
		else
			result = p + " er på besøg i fængslet";
		return result;
	}

	@Override
	public String printPassed(Player p) {
		String result = "";
		return result;
	}

	public boolean payToExit(Player p) {
    	if (p.isInJail()) {
		    p.setInJail(false);
		    return p.withDrawMoney(outOfJailPrice);
	    }
		return true;
    }
}
