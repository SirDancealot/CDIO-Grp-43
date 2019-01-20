package dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses;

import java.io.IOException;

import dk.dtu.CDIT_Grp_43_matador.matador.Logic;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.Ownable;
import dk.dtu.CDIT_Grp_43_matador.matador.util.Factory;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.Tile;

public class GameBoard {
    private static final GameBoard INSTANCE = new GameBoard();
    private static final Logic logic = Logic.getINSTANCE();
    private int boardSize;
    private Tile[] gameTiles;
	private Factory factory = Factory.getInstance();

	private GameBoard(){    }

	public void initBoard() throws IOException {
		gameTiles = factory.createTiles();
		boardSize = gameTiles.length;
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
			if(gameTiles[i].getType().equalsIgnoreCase(type)) {
				matchingTiles[matchingIndex] = gameTiles[i];
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
			if (tile instanceof Ownable) {
				if (tile.getSisterTag().equals(setTag)) {
					tileSet = new Tile[((Ownable)tile).getTilesInSet()];
					break;
				}
			}
		}
		int tilesFound = 0;
		for (Tile tile : gameTiles) {
			if (tile instanceof Ownable) {
				if (tile.getSisterTag().equals(setTag)) {
					tileSet[tilesFound] = tile;
					tilesFound++;
				}
			}
		}
		return tileSet;
	}

	public String getChoice(String msg, boolean list, String... buttons) {
		return logic.getChoice(msg, list, buttons);
	}
}
