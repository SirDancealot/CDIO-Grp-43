package dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses;

import java.io.IOException;
import java.util.HashMap;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Tile;
import dk.dtu.CDIT_Grp_43_matador.matador.language.Lang;
import dk.dtu.CDIT_Grp_43_matador.matador.util.TextReader;

public class GameBoard {
    private Lang lang;
    private static final GameBoard INSTANCE = new GameBoard();
    
//	private String[] gameTilesNames = {"(Man kan ikke slå 1 med to terninger)","Tower","Crater ","Palace gates", "Cold Desert", "Walled city", "Monastery",
//            "Black cave", "Huts in the mountain", "The Werewall (werewolf-wall)", "The pit", "Goldmine"};
//    private int[] gameTilesValue = {0,250,-100,100, -20, 180, 0, -70, 60, -80, -50, 650};

    private Tile[] gameTiles;
    private HashMap<String, String> tileInfo;
    
    public void setLang(Lang lang) {
		this.lang = lang;
	}
    
    private GameBoard(){    }

    public void initBoard() throws IOException {
    	tileInfo = TextReader.fileToHashMap("res/Tiles.txt");
    	gameTiles = new Tile[tileInfo.size()];
    	int tileNum = 1;
    	while(lang.getTag("Tile" + tileNum) != null) {
    		gameTiles[tileNum-1] = new Tile(lang.getTag("Tile" + tileNum), tileInfo.get("Tile" + tileNum));
    		tileNum++;
    	}
    	   	
    	
//        for(int i = 0; i < gameTilesNames.length; i++){
//            gameTiles[i] = new Tile(gameTilesNames[i], gameTilesValue[i]);
//        }
    }

    // Getters

    public Tile[] getGameTiles() {
        return gameTiles;
    }

	public static GameBoard getInstance() {
		return INSTANCE;
	}

}
