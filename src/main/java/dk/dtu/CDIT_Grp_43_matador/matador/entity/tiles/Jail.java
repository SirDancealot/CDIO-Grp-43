package dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles;

import java.util.ArrayList;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.ChanceCard;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;

public class Jail extends Tile {
    private int outOfJailPrice = 1;

    public Jail(String tilename, String tileinfo, int tileIndex){
        super(tilename, tileinfo, tileIndex);

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

    public boolean cardToExit(Player p) {
    	if (p.isInJail() && p.hasFreeJail()) {
			p.setInJail(false);
			p.returnFreeJail();
			return true;
	    } return false;
    }
}
