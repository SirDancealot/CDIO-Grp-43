package dk.dtu.CDIT_Grp_43_matador.matador;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Bank;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.ChanceCard;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.*;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.OwnableProperties.Property;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.ChanceCardDeck;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.DiceCup;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.GameBoard;

import java.util.ArrayList;

public class Logic {

    private static Logic INSTANCE = new Logic();
    private final int TURNLIMIT = 500;

    private Bank bank;
    private GameController game;
    private ChanceCardDeck deck;
    private Player[] players;
    private DiceCup diceCup;
    private GameBoard board;
    private boolean endOfGame = false;
    private boolean rolled = false;
    private boolean turnEnded;
    private int turns = 0;
    private int currPlayerIndex = 0;
    private int[] deadPlayers;

    // Turn base variables
    private String turnString = "";
    private String turnMessage = "";
    private int currentMortgageProperty;
    private int currentOnMortgageProperty;
    private int currentTileUpgrade;
    private int currentTileLevel;
    private boolean currentLandedOnTileOwned = false;

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
        bank = Bank.getInstance();
        bank.initBank();
        deadPlayers = new int[players.length];
        deck = ChanceCardDeck.getInstance();
        for(int i = 0; i < deadPlayers.length; i++){
            deadPlayers[i] = 0;
        }
    }

    /**
     * A funktion that updates the logic in the game and should be called every logic frame
     */

    public void tick() {
        turnEnded = false;
        while (!turnEnded) {
            String[] options;
            if (!rolled)
                options = new String[] {"Rul"};
            else
                options = new String[] {"Slut tur"};

            if (players[currPlayerIndex].isInJail()) {

                options = expandArray(options, "Betal for at komme ud");

                if (players[currPlayerIndex].hasFreeJail())
                    options = expandArray(options, "Brug chance kort");
            }

            if(canBuyHouse()){
                options = expandArray(options, "Køb hus(e)");
            }
            if(canSellHouse()){
                options = expandArray(options, "Sælg hus(e)");
        }
            if(canPawn()){
                options = expandArray(options, "Pantsæt");
            }
            if(canUnPawn()){
                options = expandArray(options, "Ophæv pantsætning");
            }
            if (board.getGameTiles()[players[currPlayerIndex].getCurrPos()] instanceof Ownable){
                if (board.getGameTiles()[players[currPlayerIndex].getCurrPos()].getOwner() == null)
                    options = new String[] {"Køb", "Sæt på auktion"};
            }

            turnStringGenerator("updateScore");
            updateGui();
            String choice = getChoice((players[currPlayerIndex].isInJail() ? players[currPlayerIndex].getName()+"!! du er i fængsel, hvad vil du nu?" : players[currPlayerIndex].getName()+" hvad vil du nu?"), false, options);
            turnChoice(choice);
        }
    }

    /**
     * Turnchoce displays the options the players have each turn
     */

    private void turnChoice(String choice ) {

        switch (choice) {
            case "Rul":
                diceCup.roll();

                if(players[currPlayerIndex].isInJail()) {
                    maxJailTime();
                    rolled = true;
                }
                if(diceCup.threeSame()){
                    displayMessage("Slog 2 ens 3 gange i træk, og blev smidt i fængsel");
                    players[currPlayerIndex].setInJail(true);
                    players[currPlayerIndex].moveTo("Jail");
                    turnStringGenerator("displayDies");
                    updateGui();
                    turnStringGenerator("resetMessage");
                    addToTurnMessage(players[currPlayerIndex].getName()+" slog 2 ens 3 gange i træk og blev sendt i fængsels");
                    turnStringGenerator("turnMessage", "teleportPlayer");
                    updateGui();
                    rolled = true;
                } else if(diceCup.isSame() && players[currPlayerIndex].isInJail()) {
                    players[currPlayerIndex].setInJail(false);
                    for (int i = 0; i < diceCup.getDiceIntValues(); i++) {
                        board.getGameTiles()[(players[currPlayerIndex].getCurrPos() + i) % board.getBoardSize()].passedTile(players[currPlayerIndex]);
                    }
                    players[currPlayerIndex].move(diceCup.getDiceIntValues());
                } else if(!players[currPlayerIndex].isInJail()) {
                    for (int i = 0; i < diceCup.getDiceIntValues(); i++) {
                        board.getGameTiles()[(players[currPlayerIndex].getCurrPos() + i) % board.getBoardSize()].passedTile(players[currPlayerIndex]);
                    }
                    players[currPlayerIndex].move(diceCup.getDiceIntValues());
                    rolled = true;
                }

                if (diceCup.isSame() && !players[currPlayerIndex].isInJail())
                    rolled = false;

                if(players[currPlayerIndex].isInJail() && players[currPlayerIndex].getMaxJailRolls() > 0){
                    turnStringGenerator("updateScore","displayDies");
                    updateGui();
                }else {
                    turnStringGenerator("displayDies");
                    updateGui();
                    turnStringGenerator("updateScore", "movePlayer");
                    updateGui();
                    turnStringGenerator("resetMessage");
                }

                Tile tileBeforeLandOnTile = board.getGameTiles()[players[currPlayerIndex].getCurrPos()];
                board.getGameTiles()[players[currPlayerIndex].getCurrPos()].landOnTile(players[currPlayerIndex]);
                while (tileBeforeLandOnTile != board.getGameTiles()[players[currPlayerIndex].getCurrPos()]) {
                    turnStringGenerator("updateScore", "movePlayer");
                    updateGui();
                    turnStringGenerator("resetMessage");
                    board.getGameTiles()[players[currPlayerIndex].getCurrPos()].landOnTile(players[currPlayerIndex]);
                    tileBeforeLandOnTile = board.getGameTiles()[players[currPlayerIndex].getCurrPos()];
                }
                addToTurnMessage(players[currPlayerIndex].getName()+" slog "+diceCup.getDiceIntValues()+" og landede på "+game.getBord().getGameTiles()[players[currPlayerIndex].getCurrPos()].getTileName());

                if(!board.getGameTiles()[players[currPlayerIndex].getCurrPos()].isOwned() && board.getGameTiles()[players[currPlayerIndex].getCurrPos()].isBuyable()){
                    addToTurnMessage(", feltet du er landet på er ikke ejet, du har nu mulighed for at købe ejendommen eller sætte det på auktion.");
                }

                if(board.getGameTiles()[players[currPlayerIndex].getCurrPos()].isOwned()){

                    ArrayList<Tile> currentPlayersTiles = players[currPlayerIndex].getOwnedTiles();

                    for (Tile currentPlayersTile : currentPlayersTiles) {
                        if (currentPlayersTile == board.getGameTiles()[players[currPlayerIndex].getCurrPos()]) {
                            currentLandedOnTileOwned = true;
                        }
                    }

                    if(currentLandedOnTileOwned){
                        addToTurnMessage(", du ejer feltet og har nu givende muligheder ");
                        currentLandedOnTileOwned = false;
                    }else{
                        addToTurnMessage(", feltet er ejet og du skal nu betal leje til "+ board.getGameTiles()[players[currPlayerIndex].getCurrPos()].getOwner());
                    }
                }

                if (tileBeforeLandOnTile != board.getGameTiles()[players[currPlayerIndex].getCurrPos()])
                    board.getGameTiles()[players[currPlayerIndex].getCurrPos()].landOnTile(players[currPlayerIndex]);


                if(players[currPlayerIndex].isInJail() && players[currPlayerIndex].getMaxJailRolls() > 0){
                    turnStringGenerator("updateScore","turnMessage");
                    updateGui();
                }else {
                    turnStringGenerator("updateScore","turnMessage");
                    if (players[currPlayerIndex].getRoll() == 0)
                        turnStringGenerator("teleportPlayer");
                    updateGui();
                    turnStringGenerator("resetMessage");
                }

                if((board.getGameTiles()[players[currPlayerIndex].getCurrPos()].getType()).equals("Chance") || (board.getGameTiles()[(players[currPlayerIndex].getCurrPos() - players[currPlayerIndex].getCardMove() + board.getBoardSize()) % board.getBoardSize()].getType()).equals("Chance")){
                    turnStringGenerator("chanceCardMessage");
                    updateGui();
                }
                break;

            case "Betal for at komme ud":
                ((Jail) (board.getTileByName("Jail"))).payToExit(players[currPlayerIndex]);
                turnStringGenerator("updateScore");
                updateGui();
                break;

            case "Brug chance kort":
                ((Jail) (board.getTileByName("Jail"))).cardToExit(players[currPlayerIndex]);
                break;

            case "Sælg hus(e)":
                sellHouse();
                turnStringGenerator("updateScore");
                updateGui();
                break;

            case "Køb hus(e)":
                buyHouse();
                turnStringGenerator("updateScore");
                updateGui();
                break;

            case "Pantsæt":
                pawn();
                turnStringGenerator("updateScore","mortgage");
                updateGui();
                break;

            case "Ophæv pantsætning":
                unPawn();
                turnStringGenerator("updateScore");
                updateGui();
                break;
            case "Køb":
                if(players[currPlayerIndex].getScore() >= board.getGameTiles()[players[currPlayerIndex].getCurrPos()].getTileValue()) {
                    ((Ownable)board.getGameTiles()[players[currPlayerIndex].getCurrPos()]).buyTile(players[currPlayerIndex]);
                    turnStringGenerator("displayOwner", "updateScore");
                    updateGui();
                    break;
                } else {
                    turnStringGenerator("resetMessage");
                    addToTurnMessage("Du har ikke penge nok og må putte ejendommen på auktion");
                    turnStringGenerator("turnMessage");
                    updateGui();
                    break;
                }
            case "Sæt på auktion":
                bank.auctions(players, board.getGameTiles()[players[currPlayerIndex].getCurrPos()]);
                System.out.println("Auktion complete");
                turnStringGenerator("updateScore");
                updateGui();
                break;
            case "Slut tur":
                endTurn();
                turnEnded = true;
                turnStringGenerator("updateScore","resetMessage");
                updateGui();
                break;

        }
    }


    /**
     * Endturn funktion
     */


    private void endTurn(){
        if(players[currPlayerIndex].getScore() < 0){
            deadPlayers[currPlayerIndex] = 1;
            players[currPlayerIndex].setStillInGame(false);
            turnStringGenerator("removePlayerFromGame");
            updateGui();

            // Handle a dead player
            deadPlayer();
        }

        int deadPlayerCount = 0;

        for (int deadPlayer : deadPlayers) {

            if (deadPlayer == 1) {
                deadPlayerCount++;
            }

            if (deadPlayerCount == players.length - 1) {
                endOfGame = true;
            }
        }

        currPlayerIndex = ++currPlayerIndex % players.length;
        boolean checkForDeadPlayers = false;
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




    /**
     * BuyHouse funktions
     */

    private void buyHouse(){
        int upgradeableProperties = 0;

        for (Tile tile : players[currPlayerIndex].getOwnedTiles()){
            if( tile instanceof Property && ((Property) tile).tileSetowned()) {
                boolean accaptableBuilding = true;
                for ( Tile otherInSet : board.getTileBySet(tile.getSisterTag())) {
                    if (((Property) tile).getHouseLevel() > ((Property)otherInSet).getHouseLevel() || ((Property) tile).getHousePrice() > players[currPlayerIndex].getScore() || ((Property) tile).getHouseLevel() == 5 && ((Property) tile).getHousePrice() < players[currPlayerIndex].getScore() || ((Property) otherInSet).isPawned())
                        accaptableBuilding = false;
                }
                if (accaptableBuilding)
                    upgradeableProperties++;
            }
        }

        String[] upgradeableNames = new String[upgradeableProperties];
        int namesFound = 0;

        for (Tile tile : players[currPlayerIndex].getOwnedTiles()){
            if( tile instanceof Property && ((Property) tile).tileSetowned()){
                boolean acceptableBuilding = true;
                for ( Tile otherInSet : board.getTileBySet(tile.getSisterTag())) {
                    if (((Property) tile).getHouseLevel() > ((Property)otherInSet).getHouseLevel() || ((Property) tile).getHousePrice() > players[currPlayerIndex].getScore() || ((Property) tile).getHouseLevel() == 5 && ((Property) tile).getHousePrice() < players[currPlayerIndex].getScore() || (((Property) otherInSet).isPawned()))
                        acceptableBuilding = false;
                }
                if (acceptableBuilding)
                    upgradeableNames[namesFound++] = tile.getTileName();
            }
        }
        String chosenUpgrade = getChoice("Hvor vil sætte et hus?", true, upgradeableNames);
        bank.upgradeGround(players[currPlayerIndex], board.getTileByName(chosenUpgrade));
        currentTileUpgrade = board.getTileByName(chosenUpgrade).getTileIndex();
        currentTileLevel = ((Property)board.getTileByName(chosenUpgrade)).getHouseLevel();
        turnStringGenerator("setHouseLevel");
        updateGui();
    }

    private boolean canBuyHouse(){

        for (Tile tile : players[currPlayerIndex].getOwnedTiles()){
            if( tile instanceof Property && ((Property) tile).tileSetowned()) {
                boolean accaptableBuilding = true;
                for ( Tile otherInSet : board.getTileBySet(tile.getSisterTag())) {
                    if (((Property) tile).getHouseLevel() > ((Property)otherInSet).getHouseLevel() || ((Property) tile).getHousePrice() > players[currPlayerIndex].getScore() || ((Property) tile).getHouseLevel() == 5 && ((Property) tile).getHousePrice() < players[currPlayerIndex].getScore() || ((Property) otherInSet).isPawned())
                        accaptableBuilding = false;
                }
                if (accaptableBuilding)
                    return true;
            }
        }
        return false;
    }

    private void sellHouse(){

        int downgradeableProperties = 0;

        for (Tile tile : players[currPlayerIndex].getOwnedTiles()){
            if( tile instanceof Property && ((Property) tile).getHouseLevel() > 0) {
                boolean acceptableBuilding = true;
                for ( Tile otherInSet : board.getTileBySet(tile.getSisterTag())) {
                    if (((Property) tile).getHouseLevel() < ((Property)otherInSet).getHouseLevel())
                        acceptableBuilding = false;
                }
                if (acceptableBuilding)
                    downgradeableProperties++;
            }
        }

        String[] downgradeableNames = new String[downgradeableProperties];
        int i = 0;

        for (Tile tile : players[currPlayerIndex].getOwnedTiles()){
            if( tile instanceof Property && ((Property) tile).getHouseLevel() > 0){
                boolean acceptableBuilding = true;
                for ( Tile otherInSet : board.getTileBySet(tile.getSisterTag())) {
                    if (((Property) tile).getHouseLevel() < ((Property)otherInSet).getHouseLevel())
                        acceptableBuilding = false;
                }
                if (acceptableBuilding)
                    downgradeableNames[i++] = tile.getTileName();
            }
        }
        String chosenDowngrade = getChoice("Hvor vil sætte et hus?", true, downgradeableNames);
        bank.downgradeGround(players[currPlayerIndex], board.getTileByName(chosenDowngrade));
        currentTileUpgrade = board.getTileByName(chosenDowngrade).getTileIndex();
        currentTileLevel = ((Property)board.getTileByName(chosenDowngrade)).getHouseLevel();
        turnStringGenerator("setHouseLevel");
        updateGui();
    }

    private boolean canSellHouse(){
        int i = 0;
        for (Tile tile : players[currPlayerIndex].getOwnedTiles()){
            if( tile instanceof Property && ((Property) tile).getHouseLevel() > 0) {
                i++;
            }
        }
        return i>0;

    }



    /**
     * Pawn funktions
     */

    private void pawn(){

        int pawnable = 0;
        for (Tile tile : players[currPlayerIndex].getOwnedTiles()) {
            if (tile instanceof Ownable) {
                if (!(((Ownable) tile).isPawned())) {
                    if (tile instanceof Property) {
                        if (((Property) tile).tileSetowned()) {
                            boolean acceptableTile = true;
                            for (Tile setTile : board.getTileBySet(tile.getSisterTag())) {
                                if (((Property) setTile).getHouseLevel() != 0)
                                    acceptableTile = false;
                            }
                            if (acceptableTile)
                                pawnable++;
                        } else
                            pawnable++;
                    } else {
                        pawnable++;
                    }
                }
            }
        }

        String[] pawnableNames = new String[pawnable];
        int foudNames = 0;
        for (Tile tile : players[currPlayerIndex].getOwnedTiles()) {
            if (tile instanceof Ownable) {
                if (!(((Ownable) tile).isPawned())) {
                    if (tile instanceof Property) {
                        if (((Property) tile).tileSetowned()) {
                            boolean acceptableTile = true;
                            for (Tile setTile : board.getTileBySet(tile.getSisterTag())) {
                                if (((Property) setTile).getHouseLevel() != 0)
                                    acceptableTile = false;
                            }
                            if (acceptableTile)
                                pawnableNames[foudNames++] = tile.getTileName();
                        } else
                            pawnableNames[foudNames++] = tile.getTileName();
                    } else {
                        pawnableNames[foudNames++] = tile.getTileName();
                    }
                }
            }
        }

        String chosenPawn = getChoice("Hvilket hus vil du pantsætte?",true, pawnableNames);
        currentMortgageProperty = board.getTileByName(chosenPawn).getTileIndex();
        bank.pawnTile(players[currPlayerIndex], board.getTileByName(chosenPawn));
        turnStringGenerator( "mortgage");
        updateGui();
    }

    private boolean canPawn(){
        for (Tile tile : players[currPlayerIndex].getOwnedTiles()) {
            if (tile instanceof Ownable) {
                if (!(((Ownable) tile).isPawned())) {
                    if (tile instanceof Property) {
                        if (((Property) tile).tileSetowned()) {
                            boolean acceptableTile = true;
                            for (Tile setTile : board.getTileBySet(tile.getSisterTag())) {
                                if (((Property) setTile).getHouseLevel() != 0)
                                    acceptableTile = false;
                            }
                            if (acceptableTile)
                                return true;
                        } else
                            return true;
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void unPawn(){
        int unPawnable = 0;

        for (Tile tile : players[currPlayerIndex].getOwnedTiles()) {
            if (tile instanceof Ownable) {
                if (((Ownable) tile).isPawned()) {
                    unPawnable++;
                }
            }
        }

        String[] unPawnableNames = new String[unPawnable];
        int foundNames = 0;

        for (Tile tile : players[currPlayerIndex].getOwnedTiles()) {
            if(tile instanceof Ownable)
                if (((Ownable) tile).isPawned()) {
                    unPawnableNames[foundNames++] = tile.getTileName();
                }
        }
        String chosenUnPawn = getChoice("Hvilket hus vil du pantsætte?",true, unPawnableNames);
        bank.unPawnTile(players[currPlayerIndex], board.getTileByName(chosenUnPawn));
        currentOnMortgageProperty = board.getTileByName(chosenUnPawn).getTileIndex();
        turnStringGenerator( "onMortgage");
        updateGui();
    }

    private boolean canUnPawn(){

        for (Tile tile : players[currPlayerIndex].getOwnedTiles()) {
            if(tile instanceof Ownable)
                if (((Ownable) tile).isPawned())
                    return true;
        }
        return false;
    }

    // Controls what happens when a player dies

    private void deadPlayer(){
        Tile deadTile = board.getGameTiles()[players[currPlayerIndex].getCurrPos()];
        if(deadTile instanceof Ownable && deadTile.getOwner() != null){

            Player newPropertyOwner = deadTile.getOwner();
            ArrayList<Tile> deadPlayersTile = players[currPlayerIndex].getOwnedTiles();

            for (Tile aDeadPlayersTile : deadPlayersTile) {
                ((Ownable) aDeadPlayersTile).setOwner(newPropertyOwner);
                newPropertyOwner.addOwnedTile(aDeadPlayersTile);
                setNewOwner(newPropertyOwner.getName(), aDeadPlayersTile);
            }
        }else{
            int numberOfTilesGoingOnAuktion = players[currPlayerIndex].getOwnedTiles().size();
            ArrayList<Tile> auktionTiles = players[currPlayerIndex].getOwnedTiles();

            for(int i = 0; i < numberOfTilesGoingOnAuktion; i++){
                bank.auctions(players, auktionTiles.get(i));
                System.out.println("Auktion compleat");
                turnStringGenerator("updateScore");
                updateGui();
            }
        }
    }

    // Array funktion

    private String[] expandArray(String[] startArray ,String... expandArray){
        String[] allOptions = new String[startArray.length + expandArray.length];

        for(int i = 0; i < startArray.length;i++ ){
            allOptions[i] = startArray[i] ;
        }
        for(int i = 0; i < expandArray.length;i++ ){
            allOptions[startArray.length + i] = expandArray[i] ;
        }
        return allOptions;
    }

    // Max jail Time


    private void maxJailTime(){

        if (diceCup.isSame())
            players[currPlayerIndex].setMaxJailRolls(0);
        else
            players[currPlayerIndex].addMaxJailRolls(1);

        if (players[currPlayerIndex].getMaxJailRolls() == 3){
            ((Jail)board.getTileByName("Jail")).payToExit(players[currPlayerIndex]);
            players[currPlayerIndex].setMaxJailRolls(0);
        }
    }


    /**
     * Gui funktions
     */

    private void updateGui(){
        game.updateDisplay(turnString);
        turnString = "";
    }


    private void turnStringGenerator(String... options){

        for (String option : options) {

            switch (option) {
                case "updateScore":
                    String score = "";
                    for (int j = 0; j < players.length; j++) {
                        score += players[j].getScore();
                        if (j < players.length - 1) {
                            score += ",";
                        }
                    }
                    turnString += "updateScore:" + score + ";";
                    break;
                case "displayDies":
                    String dies = "";
                    dies += diceCup.getD1Val() + "," + diceCup.getD2Val();
                    turnString += "displayDies:" + dies + ";";
                    break;
                case "teleportPlayer":
                    String teleport = "";
                    teleport += currPlayerIndex + "," + players[currPlayerIndex].getCurrPos();
                    turnString += "teleportPlayer:" + teleport + ";";
                    break;
                case "movePlayer":
                    String move = "";
                    move += currPlayerIndex + "," + players[currPlayerIndex].getCurrPos() + "," + players[currPlayerIndex].getOldPos() + "," + players[currPlayerIndex].getRoll() + "," + players[currPlayerIndex].getCardMove();
                    turnString += "movePlayer:" + move + ";";
                    break;
                case "setHouseLevel":
                    String setHouseLevel = currPlayerIndex + "," + currentTileUpgrade + "," + currentTileLevel;
                    turnString += "setHouseLevel:" + setHouseLevel + ";";
                    break;
                case "turnMessage":
                    turnString += "turnMessage:" + turnMessage + ";";
                    break;
                case "resetMessage":
                    turnMessage = "";
                    break;
                case "chanceCardMessage":
                    String chanceCardMessage = deck.getCurrCard().printCard(players[currPlayerIndex]);
                    turnString += "chanceCardMessage:" + chanceCardMessage + ";";
                    break;
                case "mortgage":
                    String mortgage = currPlayerIndex + "," + currentMortgageProperty + ",";
                    turnString += "mortgage:" + mortgage + "false" + ";";
                    break;
                case "onMortgage":
                    String onMortgage = currPlayerIndex + "," + currentOnMortgageProperty + ",";
                    turnString += "onMortgage:" + onMortgage + "false" + ";";
                    System.out.println("onMortgage");
                    break;
                case "displayOwner":
                    String Owner = currPlayerIndex + "," + players[currPlayerIndex].getCurrPos() + ",";
                    turnString += "displayOwner:" + Owner + "false" + ";";
                    break;
                case "removePlayerFromGame":
                    String removePlayerFromGame = Integer.toString(currPlayerIndex);
                    turnString += "removePlayerFromGame:" + removePlayerFromGame + ";";
                    break;
            }
        }
    }

    private void addToTurnMessage(String information){
        turnMessage += information;
    }

    public void displayMessage(String msg){
        turnStringGenerator("resetMessage");
        addToTurnMessage(msg);
        turnStringGenerator("turnMessage");
        updateGui();
    }

    public void setOwnerAfterAuktion (int player, Tile boughtTile){
        ((Ownable)boughtTile).setPawned(false);
        String Owner = player+","+ boughtTile.getTileIndex()+",";
        turnString += "displayOwner:"+Owner+"false"+";";
        updateGui();
    }

    private void setNewOwner(String player, Tile boughtTile){
        String Owner = player+","+ boughtTile.getTileIndex()+",";
        turnString += "setNewOwner:"+Owner+"false"+";";
        updateGui();
        if (((Ownable)boughtTile).isPawned()) {
            String mortgage = currPlayerIndex + "," + boughtTile.getTileIndex() + ",";
            turnString += "mortgage:" + mortgage + "false" + ";";
            updateGui();
        }
    }




    // Gets userInput from Gui

    public String getChoice (String msg, Boolean list, String... buttons){
        return game.getChoice(msg, list, buttons);
    }

    public int getUserInt (String msg,int min, int max) {
        return game.getUserInt(msg, min, max);
    }

    // Getters and setters

    public static Logic getINSTANCE () {
        return INSTANCE;
    }
    public boolean isEndOfGame () {
        return endOfGame;
    }

    Player getWinner() {
        if (endOfGame)
            return players[currPlayerIndex];
        return null;
    }

    public Tile[] getTileBySet(String setTag) {
        return board.getTileBySet(setTag);
    }

    public ChanceCard nextCard() {
        return deck.nextCard();
    }

    public int getBoardSize() {
        return board.getBoardSize();
    }

    public Tile getTileByName(String tileName) {
        return board.getTileByName(tileName);
    }

    public Tile[] searchForTileType(String tag) {
        return board.searchForTileType(tag);
    }
}
