package dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses;

import java.io.IOException;
import java.util.HashMap;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Tile;
import dk.dtu.CDIT_Grp_43_matador.matador.language.Lang;
import dk.dtu.CDIT_Grp_43_matador.matador.util.TextReader;

public class GameBoard {
    private Lang lang;
    private static final GameBoard INSTANCE = new GameBoard();
    private int boardSize;
    private Tile[] gameTiles;
    private HashMap<String, String> tileInfo;
    
    public void setLang(Lang lang) {
		this.lang = lang;
	}
    
    private GameBoard(){    }

    public void initBoard() throws IOException {
    	tileInfo = TextReader.fileToHashMap("res/Tiles.txt");
    	gameTiles = new Tile[tileInfo.size()];
    	boardSize = gameTiles.length;
    	int tileNum = 1;
    	while(lang.getTag("Tile" + tileNum) != null) {
    		gameTiles[tileNum-1] = new Tile(lang.getTag("Tile" + tileNum), tileInfo.get("Tile" + tileNum));
    		tileNum++;
    	}
    }

	/**
	 * Calls the funktion {@code passedTile} on the given tile
	 * @param p The {@code Player} that passed the tile
	 * @param pos The index of the tile that was passed
	 */
	public void passedTile(Player p, int pos){
    	gameTiles[pos].passedTile(p);
	}

    // Getters

    public Tile[] getGameTiles() {
        return gameTiles;
    }

	public static GameBoard getInstance() {
		return INSTANCE;
	}
	
	public boolean landOnTile(Player p) {return gameTiles[p.getCurrPos()].landOnTile(p); }

	public int getBoardSize() {
		return boardSize;
	}
}
