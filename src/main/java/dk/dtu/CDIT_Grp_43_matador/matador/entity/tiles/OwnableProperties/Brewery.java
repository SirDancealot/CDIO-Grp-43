package dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.OwnableProperties;


import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.Ownable;


public class Brewery extends Ownable {
    private int[] dieMultiplier;

    public Brewery(String tilename, String tileinfo, int tileIndex, String rentInfo) {
        super(tilename, tileinfo, tileIndex);
        String[] rentInfoTags = rentInfo.split(";");
        dieMultiplier = new int[rentInfoTags.length];
        for (String infoTag: rentInfoTags) {
            dieMultiplier[Integer.valueOf(infoTag.split(":")[0])-1] = Integer.valueOf(infoTag.split(":")[1]);
        }

    }

    /**
     * Method for landing on a brewery tile
     * @param p The current player.
     * @return true if p has enough money to pay rent
     */

    @Override
    public boolean landOnTile(Player p) {
        boolean payDouble = p.isPayDouble();
        p.setPayDouble(false);

        if(pawned) {}
        else{
            if (p == owner)
                return true;
        }return p.withDrawMoney(p.getRoll()*dieMultiplier[tilesInSetOwned()-1] * (payDouble ? 2 : 1));
    }
}












