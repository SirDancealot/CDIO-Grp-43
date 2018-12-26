package dk.dtu.CDIT_Grp_43_matador.matador.util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.ChanceCard;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects.CardEffect;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects.ChangeMoneyEffect;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects.FreeJailEffect;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects.MovePlayerEffect;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects.MovePlayerToEffect;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects.PayAllEffect;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.Chance;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.FreeParking;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.GoToJail;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.Jail;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.Property;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.Start;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.Tile;
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

    
    public Queue<ChanceCard> createCards() throws IOException {
    	HashMap <String, String> cardTags = TextReader.fileToHashMap("./res/Cards.txt");
    	ArrayList<ChanceCard> tmpCards = new ArrayList<ChanceCard>();
    	for (int i = 0; i < cardTags.size(); i++) {
    		String cardInfo = cardTags.get("Card"+i);
    		String[] thisCardInfo = cardInfo.split(";");
    		CardEffect[] cardEffects = new CardEffect[thisCardInfo.length];
    		for (int j = 0; j < thisCardInfo.length; j++) {
    			switch (thisCardInfo[j].split(":")[0]) {
				case "moveTo":
					cardEffects[j] = new MovePlayerToEffect(thisCardInfo[j].split(":")[1]);
					break;
				case "move":
					cardEffects[j] = new MovePlayerEffect(Integer.valueOf(thisCardInfo[j].split(":")[1]));
					break;
				case "money":
					cardEffects[j] = new ChangeMoneyEffect(Integer.valueOf(thisCardInfo[j].split(":")[1]));
					break;
				case "payAll":
					cardEffects[j] = new PayAllEffect(Integer.valueOf(thisCardInfo[j].split(":")[1]));
					break;
				case "freeJail":
					cardEffects[j] = new FreeJailEffect();
					break;
				default:
					break;
    			}
			}
    		tmpCards.add(new ChanceCard(cardEffects));
		}
    	Collections.shuffle(tmpCards);
    	Queue<ChanceCard> cards = new LinkedList<ChanceCard>(); 
    	for (ChanceCard newChanceCard : tmpCards) {
			cards.add(newChanceCard);
		}
    	return cards;
    }
    
    
    public static Factory getInstance() {
		return INSTANCE;
	}
}
