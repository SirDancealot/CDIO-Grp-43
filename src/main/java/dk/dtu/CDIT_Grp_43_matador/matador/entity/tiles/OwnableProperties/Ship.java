package dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.OwnableProperties;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.Ownable;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.Tile;

public class Ship extends Ownable {
    private int[] shipMultiplier;


    public Ship(String tilename, String tileinfo, int tileIndex, String rentInfo) {
        super(tilename, tileinfo, tileIndex);
        String[] rentInfoTags = rentInfo.split(";");
        shipMultiplier = new int[rentInfoTags.length];
        for (String infoTag : rentInfoTags) {
            shipMultiplier[Integer.valueOf(infoTag.split(":")[0]) - 1] = Integer.valueOf(infoTag.split(":")[1]);
        }
    }

    /**
     * Method for landing on a ship tile
     *
     * @param p The current player.
     * @return true if p has enough money to pay rent
     */

    @Override
    public boolean landOnTile(Player p) {
        boolean payDouble = p.isPayDouble();
        p.setPayDouble(false);
        if (owner == null || pawned || p == owner)
            return true;

        lastPrice = shipMultiplier[tilesInSetOwned() - 1] * (payDouble ? 2 : 1);
        return p.withDrawMoney(lastPrice);
    }
}