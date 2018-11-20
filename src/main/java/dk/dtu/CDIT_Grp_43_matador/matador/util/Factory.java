package dk.dtu.CDIT_Grp_43_matador.matador.util;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Tile;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.*;
import dk.dtu.CDIT_Grp_43_matador.matador.language.Lang;
import dk.dtu.CDIT_Grp_43_matador.matador.language.LanguageController;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.HashMap;

import static dk.dtu.CDIT_Grp_43_matador.matador.util.CustomStreamTokenizer.*;

public class Factory {
	private static final Factory INSTANCE = new Factory();
	
	private Factory() { }
	
    public Tile[] createTiles() throws IOException {
        HashMap <String, String> tileTags = TextReader.fileToHashMap("./res/Tiles.txt");
        Lang lang = LanguageController.getCurrentLanguage();
        Tile[] tiles = new Tile[tileTags.size()];

        for (int i = 0; i < tiles.length; i++) {
            String tileName = lang.getTag("Tile"+(i+1));
            String tileInfo = tileTags.get("Tile"+i);
            Tile tempTile;
            String tileType = tileInfo.split(";")[0].split(":")[1];
            switch (tileType) {
                case "Start":
                    tempTile = new Start(tileName, tileInfo, i);
                    break;
                case "Property":
                    tempTile = new Property(tileName,tileInfo, i);
                    break;
                case "Chance":
                    tempTile = new Chance(tileName, tileInfo, i);
                    break;
                case "Jail":
                    tempTile = new Jail(tileName,tileInfo, i);
                    break;
                case "FreePark":
                    tempTile = new FreeParking(tileName,tileInfo, i);
                    break;
                case "GoToJail":
                    tempTile = new GoToJail(tileName,tileInfo, i);
                    break;

                default:
                    tempTile = new FreeParking("","", -1);
                    break;
            }
            tiles[i] = tempTile;


        }



        return tiles;
    }

    public static Factory getInstance() {
		return INSTANCE;
	}
}
