package dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses;

import java.io.IOException;
import java.util.HashMap;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Tile;
import dk.dtu.CDIT_Grp_43_matador.matador.language.Lang;
import dk.dtu.CDIT_Grp_43_matador.matador.util.TextReader;

public class GameBoard {
    private Lang lang;
    private static final GameBoard INSTANCE = new GameBoard();
    
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
    }

    // Getters

    public Tile[] getGameTiles() {
        return gameTiles;
    }

	public static GameBoard getInstance() {
		return INSTANCE;
	}
	
	public Tile landOnTile(int tileNum) {
		return gameTiles[tileNum-1];
	}
}
