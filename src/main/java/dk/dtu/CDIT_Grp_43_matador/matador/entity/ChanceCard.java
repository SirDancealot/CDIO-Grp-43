package dk.dtu.CDIT_Grp_43_matador.matador.entity;

import dk.dtu.CDIT_Grp_43_matador.matador.util.InformationExchanger;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.GameBoard;

public class ChanceCard {
    private static final GameBoard BOARD = GameBoard.getInstance();
    private static final InformationExchanger infExch = InformationExchanger.getInstance();
    private final String value;
    private boolean moveTo = false;
    private String moveToTag;

    private boolean move = false;
    private int moveAmt;

    private boolean money = false;
    private int moneyAmt;

    private boolean payAll = false;
    private int payAllAmt;

    private boolean keep = false;
    private boolean freeJail = false;

    public ChanceCard(String value) {
        String[] tags = value.split(";");
        for (String tag : tags) {
            String[] tagValues = tag.split(":");
            switch (tagValues[0] ) {
                case "moveTo" :
                    moveTo = true;
                    moveToTag = tagValues[1];
                    break;
                case "move" :
                    move = true;
                    moveAmt = Integer.valueOf(tagValues[1]);
                    break;
                case "money" :
                    money = true;
                    moneyAmt = Integer.valueOf(tagValues[1]);
                    break;
                case "payAll" :
                    payAll = true;
                    payAllAmt = Integer.valueOf(tagValues[1]);
                    break;
                case "keep" :
                    keep = true;
                    break;
                case "freeJail" :
                    freeJail = true;
                    break;
                default :
                    break;
            }
        }
        this.value = value;
    }

    public boolean useCard(Player p) {
    	String cardString = "";
        if (move) {
        	cardString += "moveing: " + moveAmt + "\n";
            p.move(moveAmt);
            return BOARD.landOnTile(p);
        }
        if (money) {
        	cardString += "recieving money: " + moneyAmt + "\n";
            if (!p.addMoney(moneyAmt)) {
            	System.out.println(cardString);
                return false;
            }
        }
        if (moveTo) {
        	cardString += "moveing to tile: " + moveToTag + "\n";
            movePlayerTo(p);
            return BOARD.landOnTile(p);
        }
        if (payAll) {
        	cardString += "paying all players: " + payAllAmt + "\n";
           if (!payAllPlayers(p)) {
        	   System.out.println(cardString);
               return false;
           }
        }
        System.out.println(cardString);
        return true;
    }
    private void movePlayerTo(Player p) {
        Tile tile = BOARD.getTileByName(moveToTag);
        System.out.println(tile);
        System.out.println(p);
        int moveDestAmt = tile.getTileIndex()-p.getCurrPos();
        if (moveDestAmt < 0)
            moveDestAmt+=BOARD.getBoardSize();
        p.move(moveDestAmt);
    }
    
    private boolean payAllPlayers(Player p) {
        boolean succeded = true;
        for (Player player : infExch.getPlayers()){
           if (!p.payMoney(player, payAllAmt))
               succeded = false;
        }
        return succeded;
    }
    public String getCardDescription() {
        return value;
    }
}
