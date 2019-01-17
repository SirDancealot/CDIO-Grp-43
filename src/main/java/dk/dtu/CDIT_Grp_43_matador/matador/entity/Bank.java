package dk.dtu.CDIT_Grp_43_matador.matador.entity;

import dk.dtu.CDIT_Grp_43_matador.matador.GameController;
import dk.dtu.CDIT_Grp_43_matador.matador.Logic;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.Ownable;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.OwnableProperties.Property;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.Tile;

public class Bank {

    private static final Bank INSTANCE = new Bank();
    private Logic logic;
    private Bank() {}

    public static Bank getInstance() {
        return INSTANCE;
    }


    public void initBank(){
       logic = Logic.getINSTANCE();
    }
    private int housesInGame = 32;
    private int hotelsInGame = 12;

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

        logic.displayMessageFromBank("Auktion om " + workingTile.getTileName() + " er gået i gang");
        String bidString = "";


        while (playersBidding > 1) {
            if (players[currentPlayerBidding].isInAuction()) {
                bidString += "Hvad vil du " + players[currentPlayerBidding];
                String choice = logic.getChoice(bidString,false, options);
                bidString = "";
                if (choice.equals("Byd")) {
                    int bidAmount = logic.getUserInt("Hvor meget vil du byde?");
                    if (bidAmount > highestBid) {
                        highestBid = bidAmount;
                        highestBidPlayer = currentPlayerBidding;
                        currentPlayerBidding++;
                    } else {
                        bidString += "Du har budt for lavt. Byd mindst " + (highestBid + 1);
                    }
                } else {
                    players[currentPlayerBidding].setInAuction(false);
                    playersBidding--;
                    currentPlayerBidding++;
                }
            }else {
                currentPlayerBidding++;
            }
            currentPlayerBidding = currentPlayerBidding % players.length;
        }
        workingTile.setOwner(players[highestBidPlayer]);
        players[highestBidPlayer].addMoney(-highestBid);
        players[highestBidPlayer].addOwnedTile(workingTile);
        logic.setOwnerAfterAuktion(highestBidPlayer, workingTile);
    }

    public boolean upgradeGround(Player p, Tile tile) {

        Property workingTile;
        if (tile instanceof  Property)
            workingTile = (Property)tile;
        else
            return false;

        for (Tile otherTile : logic.getTileBySet(workingTile.getSisterTag())) {
            if (workingTile.getHouseLevel() > ((Property)otherTile).getHouseLevel()) {
                return false;
            }
        }

        if (workingTile.getOwner() != p || workingTile.getHouseLevel() == 5 || p.getScore() < workingTile.getHousePrice() || !workingTile.tileSetowned())
           return false;

        if (p.withDrawMoney(workingTile.getHousePrice())) {
            workingTile.addHouseLevel();
            if (workingTile.getHouseLevel() < 4) {
                housesInGame--;
            } else {
                hotelsInGame--;
                housesInGame += 4;
            }
            return true;
        }
        return false;
    }

    public boolean downgradeGround (Player p, Tile tile) {
        Property workingTile;
        if (tile instanceof Property)
            workingTile = (Property)tile;
        else
            return false;

        for (Tile otherTile : logic.getTileBySet(workingTile.getSisterTag())) {
            if (workingTile.getHouseLevel() < ((Property)otherTile).getHouseLevel()) {
                return false;
            }
        }

        if (workingTile.getOwner() != p || workingTile.getHouseLevel() <= 0)
            return false;

        if (p.addMoney(workingTile.getHousePrice()/2)) {
            workingTile.removeHouseLevel();
            if (workingTile.getHouseLevel() == 5) {
                if (housesInGame >= 4) {
                    hotelsInGame++;
                    housesInGame -= 4;
                } else {
                    return false;
                }
            } else {
                housesInGame++;
            }
        }
        return true;
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
