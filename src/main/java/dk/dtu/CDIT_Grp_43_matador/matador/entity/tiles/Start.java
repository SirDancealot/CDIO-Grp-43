package dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;

public class Start extends Tile {
    private int overStartBonus;
    private boolean wasStartElegible = false;

    /**
     * The subclass Start represents the start Tile on the game board.
     * @param tilename parsed to superclass constructor {@code Tile}.
     * @param tileinfo parsed to superclass constructor {@code Tile}.
     */
    public Start(String tilename, String tileinfo, int tileIndex) {
        super (tilename, tileinfo, tileIndex);
        String[] tileInfoTags = tileinfo.split(";");
        for (String string : tileInfoTags) {
        	String[] tagInfo = string.split(":");
			switch (tagInfo[0]) {
			case "passedValue":
				overStartBonus = Integer.valueOf(tagInfo[1]);
				break;
			default:
				break;
			}
		}
    }

    @Override
    public boolean landOnTile(Player p) {
        System.out.println("landed on start");
        boolean status = false;
        wasStartElegible = p.isStartMoneyElegible();
        if (wasStartElegible)
            System.out.println("and got money");
            status = p.addMoney(overStartBonus);
        p.setStartMoneyElegible(false);
        return super.landOnTile(p);
    }

    /**
     * What happens when the player passes start. The player receives the overStartBonus
     * to their balance through the addMoney method in the Player class.
     * @param p The current player.
     * @return returns true if everything went well.
     */
    @Override
    public boolean passedTile(Player p) {
        System.out.println("passed start");
        boolean status = false;
        wasStartElegible = p.isStartMoneyElegible();
        if (wasStartElegible)
            System.out.println("and got money");
            status = p.addMoney(overStartBonus);
        p.setStartMoneyElegible(true);

        return status;
    }

    @Override
    public String printLandOn(Player p) {
        String result = p + " landede på start men modtog ikke " + overStartBonus + " kr.";
        if (wasStartElegible)
            result = p + " landede på start og modtog " + overStartBonus + " kr.";
        return result;
    }

    @Override
    public String printPassed(Player p) {
        String result = p + " passerede start men modtog ikke " + overStartBonus + " kr.";
        if (wasStartElegible)
            result = p + " passerede på start og modtog " + overStartBonus + " kr.";
        return result;
    }

}
