package dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Tile;

public class FreeParking extends Tile {
    /**
     * The basic setup for the Freeparking tile. It doesn't do anything at the moment,
     * but we've made it for reference and if we wish to add rules to it in the future.
     * @param tilename parsed to superclass constructor {@code Tile}.
     * @param tileinfo parsed to superclass constructor {@code Tile}.
     */
    public FreeParking(String tilename, String tileinfo) {
        super (tilename, tileinfo);
    }

    /**
     * Method used when the player lands on a tile. Doesn't do anything at the moment.
     * @param p The current player.
     * @return Returns true if the method goes as planned, meaning that the player hasn't lost.
     */
    public boolean passedTile (Player p) {
        return true;
    }
}
