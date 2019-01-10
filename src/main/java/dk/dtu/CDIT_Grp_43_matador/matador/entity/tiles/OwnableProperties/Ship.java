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
        for (String infoTag: rentInfoTags) {
            shipMultiplier[Integer.valueOf(infoTag.split(":")[0])-1] = Integer.valueOf(infoTag.split(":")[1]);
        }
    }

    @Override
    public boolean landOnTile(Player p) {
        if (p == owner)
            return true;
       return p.withDrawMoney(shipMultiplier[tilesInSetOwned()-1]);
    }
}
