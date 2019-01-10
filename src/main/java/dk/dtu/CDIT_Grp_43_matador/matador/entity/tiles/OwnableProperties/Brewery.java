package dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.OwnableProperties;


import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.Ownable;
import dk.dtu.CDIT_Grp_43_matador.matador.util.TextReader;


public class Brewery extends Ownable {
    private int[] dieMultiplier;

    public Brewery(String tilename, String tileinfo, int tileIndex, String rentInfo) {
        super(tilename, tileinfo, tileIndex);
        TextReader.getRent();
        String[] rentInfoTags = rentInfo.split(";");
        dieMultiplier = new int[rentInfoTags.length];
        for (String infoTag: rentInfoTags) {
            dieMultiplier[Integer.valueOf(infoTag.split(":")[0])-1] = Integer.valueOf(infoTag.split(":")[1]);
        }

    }

    /**
     * Method for landing on brewery tile
     * @param p The current player.
     * @return true if player is the owner,
     * else return player pays roll * dieMultiplier for amount of breweries owned
     */

    @Override
    public boolean landOnTile(Player p) {
        if (p == owner)
            return true;
        return p.withDrawMoney(p.getRoll()*dieMultiplier[tilesInSetOwned()-1]);
    }
}












