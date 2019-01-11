package dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses;

import java.io.IOException;
import java.util.HashMap;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.Ownable;
import dk.dtu.CDIT_Grp_43_matador.matador.util.Factory;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.Tile;

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

	public Tile[] searchForTileType(String type) {
		int tilesOfType = 0;
		for(Tile tile : gameTiles) {
			if(tile.getType().equalsIgnoreCase(type)) {
				tilesOfType++;
			}
		}
		Tile[] matchingTiles = new Tile[tilesOfType];
		int matchingIndex = 0;
		for (int i = 0; i < gameTiles.length; i++) {
			if(gameTiles[i].getType() == type) {
				matchingTiles[i] = gameTiles[i];
				matchingIndex++;
			}
		}
		return matchingTiles;
	}

    // Getters

    public Tile[] getGameTiles() {
        return gameTiles;
    }

	public static GameBoard getInstance() {
		return INSTANCE;
	}
	
	public boolean landOnTile(Player p) {
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
		for (Tile tile : gameTiles) {
			if (tile.getTileName().equalsIgnoreCase(name))
				return tile;
		}
		return null;
	}

	public Tile[] getTileBySet(String setTag) {
		Tile[] tileSet = new Tile[0];
		for (Tile tile : gameTiles) {
			if (tile.getSisterTag().equals(setTag)) {
				tileSet = new Tile[((Ownable)tile).getTilesInSet()];
				break;
			}
		}
		int tilesFound = 0;
		for (Tile tile : gameTiles) {
			if (tile.getSisterTag().equals(setTag)) {
				tileSet[tilesFound] = tile;
				tilesFound++;
			}
		}
		return tileSet;
	}
}
