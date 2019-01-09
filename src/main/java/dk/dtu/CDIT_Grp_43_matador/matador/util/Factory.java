package dk.dtu.CDIT_Grp_43_matador.matador.util;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import gui_fields.*;

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


    
    public GUI_Field[] createGuiFields() throws IOException {

        HashMap <String, String> tileInfo = TextReader.fileToHashMap("./res/AllTiles.txt");
        HashMap <String, String> rentInfo = TextReader.fileToHashMap("./res/Rent.txt");

        GUI_Field[] gui_fields = new GUI_Field[tileInfo.size()];

        for(int i = 0; i < gui_fields.length; i++){

            String[] thisTileInfo = tileInfo.get("Tile"+i).split(";");

            String type = thisTileInfo[0];
            System.out.println(type);

            switch (type) {
                case "type:Start":
                    System.out.println(i);
                    gui_fields[i] = new GUI_Start();
                    gui_fields[i].setTitle(thisTileInfo[2].split(":")[1]);
                    gui_fields[i].setSubText("");
                    gui_fields[i].setDescription("Du får " + thisTileInfo[1].split(":")[1] + " kr når du passere start");
                    gui_fields[i].setBackGroundColor(Color.RED);
                    gui_fields[i].setForeGroundColor(Color.WHITE);
                    break;
                case "type:Property":
                    System.out.println(i);
                    gui_fields[i] = new GUI_Street();
                    gui_fields[i].setTitle(thisTileInfo[3].split(":")[1]);
                    gui_fields[i].setSubText("Pris "+thisTileInfo[1].split(":")[1]+" kr");
                    String currentProperty = rentInfo.get(thisTileInfo[3].split(":")[1]);
                    gui_fields[i].setDescription("Leje af grund "+currentProperty.split(";")[0].split(":")[1]+ "<br>" +"med 1 hus "+currentProperty.split(";")[1].split(":")[1]+ "<br>" +"med 2 hus "+currentProperty.split(";")[2].split(":")[1]+ "<br>" +"med 3 hus "+currentProperty.split(";")[3].split(":")[1]+ "<br>" +"med 4 hus "+currentProperty.split(";")[4].split(":")[1]+ "<br>" +"med hotel "+currentProperty.split(";")[5].split(":")[1]);
                    gui_fields[i].setBackGroundColor(makeColor(thisTileInfo[2].split(":")[1]));
                    gui_fields[i].setForeGroundColor(Color.black);
                    break;
                case "type:Chance":
                    System.out.println(i);
                    gui_fields[i] = new GUI_Chance();
                    gui_fields[i].setTitle(thisTileInfo[1].split(":")[1]);
                    gui_fields[i].setSubText("");
                    gui_fields[i].setDescription("");
                    gui_fields[i].setBackGroundColor(Color.black);
                    gui_fields[i].setForeGroundColor(Color.WHITE);
                    break;
                case "type:Tax":
                    if(thisTileInfo.length == 4){
                        System.out.println(i);
                        gui_fields[i] = new GUI_Tax();
                        gui_fields[i].setTitle(thisTileInfo[3].split(":")[1]);
                        gui_fields[i].setSubText("");
                        gui_fields[i].setDescription("Betal "+thisTileInfo[1].split(":")[1]+" kr eller "+thisTileInfo[2].split(":")[1]+" procent af din net worth");
                        gui_fields[i].setBackGroundColor(Color.green);
                        gui_fields[i].setForeGroundColor(Color.black);
                        break;
                    }else{
                        System.out.println(i);
                        gui_fields[i] = new GUI_Tax();
                        gui_fields[i].setTitle(thisTileInfo[2].split(":")[1]);
                        gui_fields[i].setSubText("");
                        gui_fields[i].setDescription("Betal "+thisTileInfo[1].split(":")[1]+" kr ");
                        gui_fields[i].setBackGroundColor(Color.green);
                        gui_fields[i].setForeGroundColor(Color.black);
                        break;
                    }

                case "type:Ship":
                    System.out.println(i);
                    gui_fields[i] = new GUI_Shipping();
                    gui_fields[i].setTitle(thisTileInfo[2].split(":")[1]);
                    gui_fields[i].setSubText("Price "+thisTileInfo[1].split(":")[1]+" kr");
                    String currentShip = rentInfo.get("ship");
                    gui_fields[i].setDescription("Leje af grund "+currentShip.split(";")[0].split(":")[1]+ "<br>" +"Hvis 2 skibe ejes "+currentShip.split(";")[1].split(":")[1]+ "<br>" +"Hvis 3 skibe ejes "+currentShip.split(";")[2].split(":")[1]+ "<br>" +"Hvis 4 skibe ejes "+currentShip.split(";")[3].split(":")[1]);
                    gui_fields[i].setBackGroundColor(Color.CYAN);
                    gui_fields[i].setForeGroundColor(Color.black);
                    break;
                case "type:Jail":
                    System.out.println(i);
                    gui_fields[i] = new GUI_Jail();
                    gui_fields[i].setTitle(thisTileInfo[1].split(":")[1]);
                    gui_fields[i].setSubText("");
                    gui_fields[i].setDescription("Du er på besøg i fængslet");
                    gui_fields[i].setBackGroundColor(Color.DARK_GRAY);
                    gui_fields[i].setForeGroundColor(Color.WHITE);
                    break;
                case "type:Brewery":
                    System.out.println(i);
                    gui_fields[i] = new GUI_Brewery();
                    gui_fields[i].setTitle(thisTileInfo[2].split(":")[1]);
                    gui_fields[i].setSubText("Pris "+thisTileInfo[1].split(":")[1]+" kr");
                    String currentBrewery = rentInfo.get("brew");
                    gui_fields[i].setDescription("Hvis et bryggeri ejes, betales "+currentBrewery.split(";")[0].split(":")[1]+" gange saa meget som øjene viser"+ "<br><br>" +"Hvis begge bryggerier ejes, betales "+currentBrewery.split(";")[1].split(":")[1]+" gange saa meget som øjene viser");
                    gui_fields[i].setBackGroundColor(Color.DARK_GRAY);
                    gui_fields[i].setForeGroundColor(Color.WHITE);
                    break;
                case "type:GoToJail":
                    System.out.println(i);
                    gui_fields[i] = new GUI_Jail();
                    gui_fields[i].setTitle(thisTileInfo[1].split(":")[1]);
                    gui_fields[i].setSubText("");
                    gui_fields[i].setDescription("De fængles");
                    gui_fields[i].setBackGroundColor(Color.DARK_GRAY);
                    gui_fields[i].setForeGroundColor(Color.WHITE);
                    break;
                case "type:FreePark":
                    System.out.println(i);
                    gui_fields[i] = new GUI_Refuge();
                    gui_fields[i].setTitle(thisTileInfo[1].split(":")[1]);
                    gui_fields[i].setSubText("");
                    gui_fields[i].setDescription("");
                    gui_fields[i].setBackGroundColor(Color.DARK_GRAY);
                    gui_fields[i].setForeGroundColor(Color.WHITE);
                    break;
            }

        }
        return gui_fields;
    }
    public static Factory getInstance() {
		return INSTANCE;
	}

	public Color makeColor(String currentColor){

	    Color thisColor = Color.pink;

        switch (currentColor) {
            case "blue":
                thisColor = Color.blue;
                break;
            case "pink":
                thisColor = Color.pink;
                break;
            case "green":
                thisColor = Color.green;
                break;
            case "grey":
                thisColor = Color.gray;
                break;
            case "red":
                thisColor = Color.red;
                break;
            case "white":
                thisColor = Color.white;
                break;
            case "yellow":
                thisColor = Color.yellow;
                break;
            case "brown":
                thisColor = Color.orange;
                break;

        }
	    return thisColor;
    }



}
