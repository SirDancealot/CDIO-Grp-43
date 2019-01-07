package dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;

public class Start extends Tile {
    private int overStartBonus = 2;
    public String type = "Start";

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
			switch (tagInfo[0].toLowerCase()) {
			case "passedValue":
				overStartBonus = Integer.valueOf(tagInfo[1]);
				break;

			default:
				break;
			}
		}
    }
    /**
     * What happens when the player passes start. The player receives the overStartBonus
     * to their balance through the addMoney method in the Player class.
     * @param p The current player.
     * @return returns true if everything went well.
     */
    @Override
    public boolean passedTile(Player p) {
    	infExch.addToCurrentTurnText(p + " passed over the start tile and recieves " + overStartBonus + "\n");
        return p.addMoney(overStartBonus);
    }

}
