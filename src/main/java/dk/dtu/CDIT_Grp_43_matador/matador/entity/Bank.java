package dk.dtu.CDIT_Grp_43_matador.matador.entity;

import dk.dtu.CDIT_Grp_43_matador.matador.GameController;
import dk.dtu.CDIT_Grp_43_matador.matador.Logic;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.Ownable;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.OwnableProperties.Property;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.Tile;

public class Bank {

    private static final Bank INSTANCE = new Bank();

    private Bank() {}

    public static Bank getInstance() {
        return INSTANCE;
    }

    private Logic logic = Logic.getINSTANCE();

    private int housesInGame;
    private int hotelsInGame;

    private final String[] options = {"Byd", "Stop med at byde"};

    public void auctions(Player[] players, Tile auctionTile) {
        Ownable workingTile;
        if (auctionTile instanceof Ownable)
            workingTile = (Ownable)auctionTile;
        else
            return;


        for (Player player : players) {
            player.setInAuction(true);
        }
        int playersBidding = players.length;
        int currentPlayerBidding = 0;
        int highestBid = -1;
        int highestBidPlayer = -1;

        logic.displayMessage("Auktion om " + workingTile.getTileName() + " er gået i gang");
        String bidString = "";
        while (playersBidding > 1) {
            if (players[currentPlayerBidding].isInAuction()) {
                bidString += "Hvad vil du " + players[currentPlayerBidding];
                String choice = logic.getChoice(bidString, options);
                bidString = "";
                if (choice.equals("Byd")) {
                    int bidAmount = logic.getUserInt("How much do you want to bid?");
                    if (bidAmount > highestBid) {
                        highestBid = bidAmount;
                        highestBidPlayer = currentPlayerBidding;
                        currentPlayerBidding++;
                    } else {
                        bidString += "Du har budt for lavt. Byd mindst " + (highestBid + 1);
                    }

                } else {
                    playersBidding--;
                    players[currentPlayerBidding].setInAuction(false);
                }
                currentPlayerBidding = currentPlayerBidding % players.length;
            }
        }
        workingTile.setOwner(players[highestBidPlayer]);
    }

    public boolean upgradeGround(Player p, Tile tile) {

        Property workingTile;
        if (tile instanceof  Property)
            workingTile = (Property)tile;
        else
            return false;

        if (workingTile.getOwner() != p || workingTile.getHouseLevel() == 5 || p.getScore() < workingTile.getHousePrice()) {
           return false;
        }
        if (p.withDrawMoney(workingTile.getHousePrice())) {
            workingTile.addHouseLevel();
            return true;
        }
        return false;
    }

    public boolean pawnTile(Player p, Tile tile) {
        Ownable workingTile;
        if (tile instanceof Ownable)
            workingTile = (Ownable)tile;
        else
            return false;
        if (workingTile instanceof Property)
            if (((Property)workingTile).getHouseLevel() != 0)
                return false;
        if (tile.getOwner() == p && tile.isBuyable()) {
            workingTile.setPawned(true);
            return p.addMoney(workingTile.getTileValue()/2);
        }
        return false;
    }

    public boolean unPawnTile(Player p, Tile tile) {
        Ownable workingTile;
        if (tile instanceof Ownable)
            workingTile = (Ownable)tile;
        else
            return false;
        if (workingTile.getOwner() == p && workingTile.isPawned() && p.getScore() >= (int)(workingTile.getTileValue()*0.6)) {
            workingTile.setPawned(false);
            return p.withDrawMoney((int)(workingTile.getTileValue()*0.6));
        }
        return false;
    }
}
