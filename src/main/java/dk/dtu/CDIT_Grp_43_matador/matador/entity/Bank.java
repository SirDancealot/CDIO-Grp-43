package dk.dtu.CDIT_Grp_43_matador.matador.entity;

import dk.dtu.CDIT_Grp_43_matador.matador.GameController;
import dk.dtu.CDIT_Grp_43_matador.matador.LogicController;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.Ownable;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.OwnableProperties.Property;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.Tile;

public class Bank {

    private final Bank INSTANCE = new Bank();

    private Bank() {}

    public Bank getInstance() {
        return INSTANCE;
    }

    private LogicController logic = LogicController.getINSTANCE();

    private int housesInGame;
    private int hotelsInGame;

    private final String[] options = {"Byd", "Stop med at byde"};

    public void auctions(Player[] players, Tile auctionTile) {

        for (Player player : players) {
            player.setInAuction(true);
        }
        int playersBidding = players.length;
        int currentPlayerBidding = 0;
        int highestBid = -1;
        int highestBidPlayer = -1;

        logic.displayMessage("Auktion om " + auctionTile.getTileName() + " er gået i gang");
        String bidString = "";
        while (playersBidding > 1) {
            if (players[currentPlayerBidding].isInAuction()) {
                bidString += "Hvad vil du " + players[currentPlayerBidding];
                String choice = logic.getPlayerChoice(bidString, options);
                bidString = "";
                if (choice.equals("Byd")) {
                    int bidAmount = logic.getPlayerInput();
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
        auctionTile.setOwner(players[highestBidPlayer]);
    }

    public boolean upgradeGround(Player p, Tile tile) {
        Property workingTile;
        if (tile instanceof  Property)
            workingTile = (Property)tile;
        else
            return false;

        if (tile.getOwner() != p || tile.getHouseLevel() == 5 || p.getScore() < workingTile.getHousePrice()) {
           return false;
        }

        p.withDrawMoney(workingTile.getHousePrice());
        workingTile.addHouseLevel();

        
    }

    public boolean pawnTile(Player p, Tile tile) {
        Ownable workingTile;
        if (tile instanceof Ownable)
            workingTile = (Ownable)tile;
        else
            return false;
        if (tile.getOwner() == p && tile.getHouseLevel() == 0 && tile.isBuyable()) {
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
