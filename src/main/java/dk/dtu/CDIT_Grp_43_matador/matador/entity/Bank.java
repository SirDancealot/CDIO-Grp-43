package dk.dtu.CDIT_Grp_43_matador.matador.entity;

import dk.dtu.CDIT_Grp_43_matador.matador.GameController;
import dk.dtu.CDIT_Grp_43_matador.matador.LogicController;
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

        logic.printMessage("Auktion om " + auctionTile.getTileName() + " er gået i gang");
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
        if (tile.getOwner() == p) {
           return false;
        }
        p.withDrawMoney(tile.getHousePrice());
        tile.addHouse();

    }

    public void pawnBuilding(Player p, Tile tile) {
        if (p.getScore() == 0) {
            if (tile.getOwner() != p && p.playerFortune() < tile.value) {
                logic.printMessage("Du kan ikke pantsætte nok ejendomme og er gået fallit");
                (remove the player from the game)
            } else {
                logic.printMessage("Du har ikke råd til at betale leje og må pantsætte dine ejendomme.");
                //Skulle man kunne klikke på husene på brættet?
                // logic.getPlayerChoice("Vælg ejendom der skal pantsættes. Du skal pantsætte for mindst " + tile.value, (array af owned tiles));

            }
        }
    }



    public void pawnBuildingIdeas(Player p, Tile tile) {
    if (p.getScore() == 0 && tile.getOwner() != p) {
        if(p.playerFortune() < tile.getTileValue()) {
            logic.printMessage("Du kan ikke pantsætte nok ejendomme og er gået fallit");
            p.removeFromGame();
            housesInGame += p.housesOwned; // Hvor mange huse banken har.
            p.getOwnedTiles() = 0; // De ejendomme/skøder som spilleren ejede kan nu genkøbes.
        } else {
            while (p.getScore() < tile.getTileValue()) {
                logic.getPlayerChoice("Du har ikke råd til leje. Vælg ejendom der skal pantsættes ", array af p.getOwnedTiles());
                p.removeHouse; //fjern ejendom fra spiller;
                p.getScore() += house.value; //tilføj penge til spiller
            }
        }

    }
    }
}
