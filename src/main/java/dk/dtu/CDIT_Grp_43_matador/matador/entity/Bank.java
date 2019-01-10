package dk.dtu.CDIT_Grp_43_matador.matador.entity;

import dk.dtu.CDIT_Grp_43_matador.matador.GameController;
import dk.dtu.CDIT_Grp_43_matador.matador.LogicController;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.Ownable;
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
        if (tile.getOwner() != p || tile.getHouseLevel() == 5 || p.getScore() < tile.getHousePrice()) {
           return false;
        }

        p.withDrawMoney(tile.getHousePrice());
        tile.addHouse();

        
    }

    public boolean pawnTile(Player p, Ownable tile) {
        if (tile.getOwner() == p && tile.getHouseLevel() == 0 && tile.isBuyable()) {
            tile.setPawned(true);
            return p.addMoney(tile.getTileValue()/2);
        }
        return false;
    }

    public boolean unPawnTile(Player p, Ownable tile) {
        if (tile.getOwner() == p && tile.isPawned && p.getScore() >= (int)(tile.getTileValue()*0.6)) {
            tile.setPawned(false);
            return p.withDrawMoney((int)(tile.getTileValue()*0.6));
        }
        return false;
    }
}
