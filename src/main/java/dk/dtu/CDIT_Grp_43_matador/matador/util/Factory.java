package dk.dtu.CDIT_Grp_43_matador.matador.util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.NewChanceCard;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects.*;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.*;
import dk.dtu.CDIT_Grp_43_matador.matador.language.Lang;
import dk.dtu.CDIT_Grp_43_matador.matador.language.LanguageController;


public class Factory {
	private static final Factory INSTANCE = new Factory();
	
	private Factory() { }
	
    public Tile[] createTiles() throws IOException {
        HashMap <String, String> tileTags = TextReader.fileToHashMap("./res/Tiles.txt");
        Lang lang = LanguageController.getCurrentLanguage();
        Tile[] tiles = new Tile[tileTags.size()];

        for (int i = 0; i < tiles.length; i++) {
            String tileName = "test";
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

    public NewChanceCard[] createCards() throws IOException {
    	HashMap <String, String> cardTags = TextReader.fileToHashMap("./res/Cards.txt");
    	NewChanceCard[] cards = new NewChanceCard[cardTags.size()];
    	for (int i = 0; i < cards.length; i++) {
    		String cardInfo = cardTags.get("Card"+i);
    		ArrayList<CardEffect> cardEffects = new ArrayList<CardEffect>();
    		String[] thisCardInfo = cardInfo.split(";");
    		for (String string : thisCardInfo) {
    			switch (string.split(":")[0]) {
				case "moveTo":
					cardEffects.add(new MovePlayerToEffect(string.split(":")[1]));
					break;
				case "move":
					cardEffects.add(new MovePlayerEffect(Integer.valueOf(string.split(":")[1])));
					break;
				case "money":
					cardEffects.add(new ChangeMoneyEffect(Integer.valueOf(string.split(":")[1])));
					break;
				case "payAll":
					cardEffects.add(new PayAllEffect(Integer.valueOf(string.split(":")[1])));
					break;
				case "freeJail":
					cardEffects.add(new FreeJailEffect());
					break;
				default:
					break;
				}
			}
    		cards[i] = new NewChanceCard(cardEffects);
		}
    	
    	
    	return cards;
    }
    
    public static Factory getInstance() {
		return INSTANCE;
	}
}
