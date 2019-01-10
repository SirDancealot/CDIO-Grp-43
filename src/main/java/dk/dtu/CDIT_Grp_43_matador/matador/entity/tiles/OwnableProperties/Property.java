package dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.OwnableProperties;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.Ownable;

public class Properties extends Ownable {
    private int houseLevel = 0;


    public Properties(String tilename, String tileinfo, int tileIndex, String rentInfo) {
        super(tilename, tileinfo, tileIndex);


    }



    @Override
    public boolean landOnTile(Player p) {
        if (p == owner)
            return true;
        if (houseLevel == 0 && tile)



    }

    public int getHouseLevel() {
        return houseLevel;
    }
    public void addHouseLevel(){
        houseLevel++;
    }
}
