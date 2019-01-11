package dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.OwnableProperties;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.Ownable;
import dk.dtu.CDIT_Grp_43_matador.matador.util.TextReader;

public class Property extends Ownable {
    private int houseLevel = 0;
    private int[] propertyRents;
    private int housePrice;


    public Property(String tilename, String tileinfo, int tileIndex, String rentInfo) {
        super(tilename, tileinfo, tileIndex);
        String[] rentInfoTags = rentInfo.split(";");
        propertyRents = new int[rentInfoTags.length];
        for (String infoTag : rentInfoTags) {
            propertyRents[Integer.valueOf(infoTag.split(":")[0])] = Integer.valueOf(infoTag.split(":")[1]);
        }
        String[] tileInfo = tileinfo.split(";");
        for (String string: tileInfo) {
            String[] split = string.split(":");
            switch (split[0]) {
                case "housePrice":
                    this.housePrice = Integer.valueOf(split[1]);
            }
        }
    }

    /**
     * Method for landing on a property tile
     *
     * @param p The current player.
     * @return true if p has enough money to pay rent
     */

    @Override
    public boolean landOnTile(Player p) {
        boolean payDouble = p.isPayDouble();
        p.setPayDouble(false);

        if (pawned) {
        } else {
            if (p == owner)
                return true;
            if (houseLevel == 0 && tileSetowned()) {
                return p.withDrawMoney(2 * propertyRents[0] * (payDouble ? 2 : 1));
            }
        }return p.withDrawMoney(propertyRents[houseLevel] * (payDouble ? 2 : 1));
    }

    public int getHouseLevel() {
        return houseLevel;
    }

    public void addHouseLevel(){
        houseLevel++;
    }

    public void removeHouseLevel() {houseLevel--;}

    public int getHousePrice() { return housePrice;}
}
