package dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.Tile;

public class MoveToClosestShipEffect extends CardEffect {
    private String moveToTag;

    public MoveToClosestShipEffect(String moveToTag) {
        this.moveToTag = moveToTag;
    }

    @Override
    public boolean useEffect(Player p) {
        Tile[] matchingTiles = p.getTilesByTag(moveToTag);

        int shortestDistance = Integer.MAX_VALUE;
        int[] shipPosition = new int[matchingTiles.length];
        for (int i = 0; i < matchingTiles.length; i++) {
            shipPosition[i] = matchingTiles[i].getTileIndex();
        }
        for (int i = 0; i < shipPosition.length; i++) {
            int distance = shipPosition[i] - p.getCurrPos();
            if(distance < 0)
                distance += 40;
            if(distance < shortestDistance) {
                shortestDistance = distance;
            }
        }
        p.setPayDouble(true);
        p.move(shortestDistance);
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + "MoveToClosestShipEffect";
    }

    @Override
    public String print(Player p) {
        return "\t" + p + " moves to the tile: " + moveToTag + "\n";
    }
}
