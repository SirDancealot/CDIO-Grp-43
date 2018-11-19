package dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Tile;

public class Chance extends Tile {

    /**
     * representing the 4 chance tiles on the board.
     * @param tilename parsed to superclass constructor {@code Tile}.
     * @param tileinfo parsed to superclass constructor {@code Tile}.
     */
    public Chance (String tilename, String tileinfo) {
        super (tilename, tileinfo);

    }

    /**
     * (NOT FINISHED) Draws a chance card, and gives the effect of the card.
     * @param p The current player.
     * @return returns true if everything goes right, ergo the current player hasn't lost.
     */
    public boolean landOnTile(Player p) {
        // insert drawChanceCard method
        return true;

    }

}
