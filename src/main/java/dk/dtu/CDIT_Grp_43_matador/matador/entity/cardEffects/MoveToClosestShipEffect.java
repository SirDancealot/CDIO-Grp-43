package dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.Tile;

public class MoveToClosestShipEffect extends CardEffect {

    public MoveToClosestShipEffect() {
    }

    @Override
    public boolean useEffect(Player p) {
        //TODO fix the acquiring of the matchingTiles
        Tile[] matchingTiles = new Tile[0];


        int shortestDistance = Integer.MAX_VALUE;
        int[] shipPosition = new int[matchingTiles.length];
        for (int i = 0; i < matchingTiles.length; i++) {
            shipPosition[i] += matchingTiles[i].getTileIndex();
        }
        for (int i = 0; i < shipPosition.length; i++) {
            int distance = shipPosition[i] - p.getCurrPos();
            if(distance < 0)
                distance += 40;
            if(distance < shortestDistance) {
                shortestDistance = distance;
            }
        }
        p.move(shortestDistance);
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String printEffect(Player p) {
        return null;
    }
}
