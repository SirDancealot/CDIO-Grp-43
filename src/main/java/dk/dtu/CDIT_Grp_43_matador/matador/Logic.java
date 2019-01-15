package dk.dtu.CDIT_Grp_43_matador.matador;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Bank;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.*;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.OwnableProperties.Property;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.DiceCup;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.GameBoard;

public class Logic {

    private static Logic INSTANCE = new Logic();
    private final int TURNLIMIT = 500;

    private Bank bank = Bank.getInstance();
    private GameController game;
    private Player[] players;
    private DiceCup diceCup;
    private GameBoard board;
    private boolean endOfGame = false;
    private boolean rolled = false;
    private boolean checkForDeadPlayers;
    private int turns = 0;
    private int currPlayerIndex = 0;
    private int[] deadPlayers;

    // Gui display string
    private String turnInfo;


    // Turn base variables

    private Logic(){}

    /**
     * Initializes the sigleton class {@code Logic}
     * @param players The players in the current game
     */

    public void init(Player[] players){
        this.players = players;
        Player.setPlayers(players);
        diceCup = DiceCup.getInstance();
        board = GameBoard.getInstance();
        endOfGame = false;
        game = GameController.getInstance();

        deadPlayers = new int[players.length];

        for(int i = 0; i < deadPlayers.length; i++){
            deadPlayers[i] = 0;
        }

        turnInfo = "updateScore:1220,2300,100,4400;displayDies:1,2;movePlayer:0,3,0,3,0;displayOwner:0,3,false;setHouse:0,3,true,2;setHotel:0,3,false,true;turnMessage:Hey dette er lækkert;chanceCardMessage:Ryk til start";
    }

    /**
     * A funktion that updates the logic in the game and should be called every logic frame
     */

    public void tick() {

        while (!rolled) {
            String[] options = {"Rul",};

            if (players[currPlayerIndex].isInJail()) {

                String[] choice = expandArray(options, "Betal for at komme ud");

                if (players[currPlayerIndex].hasFreeJail())
                    expandArray(options, "Brug chance kort");
            }

            if(canBuyHouse()){
                expandArray(options, "Køb hus(e)");
            }
            if(canSellHouse()){
                expandArray(options, "Sælg hus(e)");
        }
            if(canPawn()){
                expandArray(options, "Pantsæt");
            }
            if(canUnPawn()){
                expandArray(options, "Ophæv pantsætning");
            }
            if (((Ownable)board.getGameTiles()[players[currPlayerIndex].getCurrPos()]).isBuyable()){
                expandArray(options, "Køb");
            }

            updateGui(turnInfo);
            String choice = getChoice("Du er i fængsel. Hvad vil du nu?", false, options);
            beforeRoll(choice);

        }

        while (rolled) {

            String[] options = {"Slut tur"};

            if(canBuyHouse()){
                expandArray(options, "Køb hus(e)");
            }
            if(canSellHouse()){
                expandArray(options, "Sælg hus(e)");
            }
            if(canPawn()){
                expandArray(options, "Pantsæt");
            }
            if(canUnPawn()){
                expandArray(options, "Ophæv pantsætning");
            }
            if (((Ownable)board.getGameTiles()[players[currPlayerIndex].getCurrPos()]).isBuyable()){
                expandArray(options, "Køb");
            }

            updateGui(turnInfo);
            String choice = getChoice("Hvad vil du nu?", false, options);
            afterRoll(choice);
        }
    }

    private void beforeRoll(String choice ) {

        switch (choice) {
            case "Rul":
                diceCup.roll();
                if(diceCup.ThreeSame()){
                    players[currPlayerIndex].setInJail(true);
                    players[currPlayerIndex].moveTo("jail");
                    rolled = true;
                } else if(diceCup.isSame()) {
                    players[currPlayerIndex].setInJail(false);
                    players[currPlayerIndex].move(diceCup.getDiceIntValues());

                } else if (!players[currPlayerIndex].isInJail()) {
                    players[currPlayerIndex].move(diceCup.getDiceIntValues());
                    rolled = true;
                }
                rolled = true;
                break;

            case "Betal for at komme ud":
                ((Jail) (board.getTileByName("Jail"))).payToExit(players[currPlayerIndex]);
                break;

            case "Brug chance kort":
                ((Jail) (board.getTileByName("Jail"))).cardToExit(players[currPlayerIndex]);
                break;

            case "Sælg hus(e)":
                sellHouse();
                break;

            case "Køb hus(e)":
                buyHouse();
                break;

            case "Pantsæt":
                pawn();
                break;

            case "Ophæv pantsætning":
                unPawn();
                break;
        }
    }

    private void afterRoll(String choice){

        switch (choice){
            case "Sælg hus(e)":
                sellHouse();
                break;

            case "Køb hus(e)":
                buyHouse();
                break;

            case "Pantsæt":
                pawn();
                break;

            case "Ophæv pantsætning":
                unPawn();
                break;

            case "Køb":
                ((Ownable)board.getGameTiles()[players[currPlayerIndex].getCurrPos()]).buyTile(players[currPlayerIndex]);
                break;
            case "Sæt på auktion":
                bank.auctions(players, board.getGameTiles()[players[currPlayerIndex].getCurrPos()]);
                break;
            case "Slut tur":
                endTurn();
                break;
        }
    }

    public String[] expandArray(String[] startArray ,String... expandArray){
        String[] allOptions = new String[startArray.length + expandArray.length];

        for(int i = 0; i < startArray.length;i++ ){
            allOptions[i] = startArray[i] ;
        }
        for(int i = 0; i < expandArray.length;i++ ){
            allOptions[startArray.length + i] = expandArray[i] ;
        }
        return allOptions;
    }

    private void buyHouse(){
        int upgradeableProperties = 0;

        for (Tile tile : players[currPlayerIndex].getOwnedTiles()){
            if( tile instanceof Property && ((Property) tile).tileSetowned()) {
                upgradeableProperties++;
            }
        }

        String[] upgradeableNames = new String[upgradeableProperties];

        for (Tile tile : players[currPlayerIndex].getOwnedTiles()){
            int j = 0;
            if( tile instanceof Property && ((Property) tile).tileSetowned()){
                upgradeableNames[j] = tile.getTileName();
                j++;
            }
        }
        String chosenUpgrade = getChoice("Hvor vil sætte et hus?", false, upgradeableNames);
        bank.upgradeGround(players[currPlayerIndex], board.getTileByName(chosenUpgrade));
    }

    private boolean canBuyHouse(){

        for (Tile tile : players[currPlayerIndex].getOwnedTiles()){
            if( tile instanceof Property && ((Property) tile).tileSetowned()) {
                return true;
            }
        }
        return false;
    }

    private void sellHouse(){

        int downgradeableProperties = 0;

        for (Tile tile : players[currPlayerIndex].getOwnedTiles()){
            if( tile instanceof Property && ((Property) tile).getHouseLevel() > 0) {
                downgradeableProperties++;
            }
        }

        String[] downgradeableNames = new String[downgradeableProperties];

        for (Tile tile : players[currPlayerIndex].getOwnedTiles()){

            int i = 0;

            if( tile instanceof Property && ((Property) tile).getHouseLevel() > 0){
                downgradeableNames[i] = tile.getTileName();
                i++;
            }
        }
        String chosenDowngrade = getChoice("Hvor vil sætte et hus?", false, downgradeableNames);
        bank.downgradeGround(players[currPlayerIndex], board.getTileByName(chosenDowngrade));
    }

    private boolean canSellHouse(){

        int i = 0;

        int downgradeableProperties = 0;

        for (Tile tile : players[currPlayerIndex].getOwnedTiles()){
            if( tile instanceof Property && ((Property) tile).getHouseLevel() > 0) {
                i++;
            }
        }
        if(i>0){
            return true;
        } else {
            return false;
        }
    }

    private void pawn(){

        int pawnable = 0;

        for (Tile tile : players[currPlayerIndex].getOwnedTiles()) {
            if(tile instanceof Property)
                if (((Property)tile).getHouseLevel()==0 && !((Property) tile).isPawned())
                    pawnable++;
                else if (tile instanceof Ownable && ((Ownable)tile).isPawned())
                    pawnable++;
        }
        String[] pawnableNames = new String[pawnable];

        for (Tile tile : players[currPlayerIndex].getOwnedTiles()) {

            int i = 0;

            if(tile instanceof Property)

                if (((Property)tile).getHouseLevel()==0 && !((Property) tile).isPawned()) {
                    pawnableNames[i] = tile.getTileName();
                    i++;
                } else if (tile instanceof Ownable && !((Ownable)tile).isPawned()) {
                    pawnableNames[i] = tile.getTileName();
                    i++;
                }
        }

        String chosenPawn = getChoice("Hvilket hus vil du pantsætte?",false, pawnableNames);
        bank.pawnTile(players[currPlayerIndex], board.getTileByName(chosenPawn));
    }

    private boolean canPawn(){
        int pawnable = 0;

        for (Tile tile : players[currPlayerIndex].getOwnedTiles()) {
            if(tile instanceof Property)
                if (((Property)tile).getHouseLevel()==0 && !((Property) tile).isPawned())
                    return true;
                else if (tile instanceof Ownable && ((Ownable)tile).isPawned())
                    return true;
        }
        return false;
    }

    private void unPawn(){
        int unPawnable = 0;

        for (Tile tile : players[currPlayerIndex].getOwnedTiles()) {
            if(tile instanceof Property)
                if (((Property) tile).isPawned())
                    unPawnable++;
                else if (tile instanceof Ownable && ((Ownable)tile).isPawned())
                    unPawnable++;
        }
        String[] unPawnableNames = new String[unPawnable];

        for (Tile tile : players[currPlayerIndex].getOwnedTiles()) {

            int i = 0;

            if(tile instanceof Property)

                if (((Property) tile).isPawned()) {
                    unPawnableNames[i] = tile.getTileName();
                    i++;

                }


        }
        String chosenUnPawn = getChoice("Hvilket hus vil du pantsætte?",false, unPawnableNames);
        bank.unPawnTile(players[currPlayerIndex], board.getTileByName(chosenUnPawn));
    }

    private boolean canUnPawn(){

        for (Tile tile : players[currPlayerIndex].getOwnedTiles()) {
            if(tile instanceof Property)
                if (((Property) tile).isPawned())
                    return true;
                else if (tile instanceof Ownable && ((Ownable)tile).isPawned())
                    return true;
        }
        return false;
    }

    private void endTurn(){
        if(players[currPlayerIndex].getScore() < 0){
            deadPlayers[currPlayerIndex] = 1;
        }

        int deadPlayerCount = 0;

        for (int i = 0; i < deadPlayers.length; i++){

            if(deadPlayers[i] == 1){
                deadPlayerCount++;
            }

            if(deadPlayerCount == players.length-1){
                endOfGame = true;
            }
        }

        currPlayerIndex = ++currPlayerIndex % players.length;
        checkForDeadPlayers = false;
        while (!checkForDeadPlayers){
            if(deadPlayers[currPlayerIndex] != 0){
                currPlayerIndex = ++currPlayerIndex % players.length;
            }else{
                checkForDeadPlayers = true;
            }
        }
        turns++;
        rolled = false;

        if(turns > TURNLIMIT){
            endOfGame = true;
        }
    }

    public void displayMessage (String msg){
        game.displayMessage(msg);
    }

    public String getChoice (String msg, Boolean list, String... buttons){
        return game.getChoice(msg, list, buttons);
    }

    public int getUserInt (String msg) {
        return game.getUserInt(msg);
    }

    public void updateGui(String turnString){
        game.updateDisplay(turnString);
        turnString = "";
    }

    public static Logic getINSTANCE () {
        return INSTANCE;
    }
    public boolean isEndOfGame () {
        return endOfGame;
    }

    public Player getWinner() {
        if (endOfGame)
            return players[currPlayerIndex];
        return null;
    }

    public Tile[] getTileBySet(String setTag) {
        return board.getTileBySet(setTag);
    }
}
