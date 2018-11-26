package dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Tile;

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
            return p.withDrawMoney(outOfJailPrice);
        }
        return true;
    }
}
