package dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses;

import java.io.IOException;
import java.util.HashMap;

import dk.dtu.CDIT_Grp_43_matador.matador.util.Factory;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Tile;

public class GameBoard {
    private static final GameBoard INSTANCE = new GameBoard();
    private int boardSize;
    private Tile[] gameTiles;
    private Factory factory = Factory.getInstance();
    
    private GameBoard(){    }

    public void initBoard() throws IOException {
    	gameTiles = factory.createTiles();
    	boardSize = gameTiles.length;
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
	
	public boolean landOnTile(Player p) {
		System.out.println("in boardLandOnTile");
		System.out.println(p.getCurrPos());
		return gameTiles[p.getCurrPos()].landOnTile(p); 
	}

	public boolean isTileOwned(int tileIndex) {
		return gameTiles[tileIndex].isOwned();
	}

	public Player getTileOwner(int tileIndex) {
		return  gameTiles[tileIndex].getOwner();
	}

	public int getBoardSize() {
		return boardSize;
	}

	public Tile getTileByName(String name) {
		for ( Tile tile : gameTiles) {
			if (tile.getTileName()==name)
				return tile;
		}
		return null;
	}
}
