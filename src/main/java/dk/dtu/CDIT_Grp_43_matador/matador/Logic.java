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
    private String turnString = "";
    private String turnMessage = "";
    private int currentMortgageProperty;

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

        //turnInfo = "updateScore:1220,2300,100,4400;displayDies:1,2;movePlayer:0,3,0,3,0;displayOwner:0,3,false;setHouse:0,3,true,2;setHotel:0,3,false,true;turnMessage:Hey dette er lækkert;chanceCardMessage:Ryk til start";
    }

    /**
     * A funktion that updates the logic in the game and should be called every logic frame
     */

    public void tick() {

        while (!rolled) {
            String[] options = {"Rul"};

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

            String choice = getChoice((players[currPlayerIndex].isInJail() ? "Du er i fængsel hvad vil du nu?" : players[currPlayerIndex].getName()+" hvad vil du nu?"), false, options);
            beforeRoll(choice);
        }

        while (rolled) {

            String[] options = {"Slut tur"};

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
                if (((Ownable)board.getGameTiles()[players[currPlayerIndex].getCurrPos()]).getOwner() == null)
                    options = new String[] {"Køb", "Sæt på auktion"};
            }

            turnStringGenerator("updateScore");
            updateGui();
            String choice = getChoice(players[currPlayerIndex].getName()+" hvad vil du nu?", false, options);
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

                if(board.getGameTiles()[players[currPlayerIndex].getCurrPos()].getType()=="Chance" || board.getGameTiles()[players[currPlayerIndex].getCurrPos() - players[currPlayerIndex].getCardMove()].getType()=="Chance"){
                    turnStringGenerator("chanceCardMessage");
                    updateGui();
                }

                rolled = true;

                for(int i = 0;i < diceCup.getDiceIntValues(); i++){
                    if(i>=40){
                        i = 0;
                    }
                    board.getGameTiles()[players[currPlayerIndex].getCurrPos()+i].passedTile(players[currPlayerIndex]);
                }

                board.getGameTiles()[players[currPlayerIndex].getCurrPos()].landOnTile(players[currPlayerIndex]);
                addToTurnMessage(players[currPlayerIndex].getName()+" slog "+diceCup.getDiceIntValues()+" og landede på "+game.getBord().getGameTiles()[players[currPlayerIndex].getCurrPos()].getTileName());
                turnStringGenerator("updateScore", "movePlayer","displayDies","turnMessage");
                updateGui();
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
        }
    }

    private void afterRoll(String choice){

        switch (choice){
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
                turnStringGenerator("updateScore","displayOwner");
                updateGui();
                break;

            case "Køb":
                ((Ownable)board.getGameTiles()[players[currPlayerIndex].getCurrPos()]).buyTile(players[currPlayerIndex]);
                turnStringGenerator("displayOwner", "updateScore");
                updateGui();
                break;
            case "Sæt på auktion":
                bank.auctions(players, board.getGameTiles()[players[currPlayerIndex].getCurrPos()]);
                turnStringGenerator("updateScore");
                updateGui();
                break;
            case "Slut tur":
                endTurn();
                turnStringGenerator("updateScore","resetMessage");
                updateGui();
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
                else if (tile instanceof Ownable && !(tile instanceof Property))
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
        currentMortgageProperty = board.getTileByName(chosenPawn).getTileIndex();
        bank.pawnTile(players[currPlayerIndex], board.getTileByName(chosenPawn));
        turnStringGenerator( "mortgage");
        updateGui();
    }

    private boolean canPawn(){

        for (Tile tile : players[currPlayerIndex].getOwnedTiles()) {
            if(tile instanceof Property)
                if (((Property)tile).getHouseLevel()==0 && !((Property) tile).isPawned()){
                    return true;
                } else if (tile instanceof Ownable && !(tile instanceof Property)) {
                    return true;
            }
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

    // String creater

    //turnInfo = "updateScore:1220,2300,100,4400;displayDies:1,2;movePlayer:0,3,0,3,0;displayOwner:0,3,false;setHouse:0,3,true,2;setHotel:0,3,false,true;turnMessage:Hey dette er lækkert;chanceCardMessage:Ryk til start";

    public void turnStringGenerator(String... options){

        for(int i = 0; i < options.length; i++){

            String option = options[i];
            switch (option){
                case "updateScore":
                    String score = "";
                    for(int j = 0; j < players.length; j++ ){
                        score += players[j].getScore();
                        if(j < players.length-1){
                            score +=",";
                        }
                    }
                    turnString += "updateScore:"+score+";";
                    break;
                case "displayDies":
                    String dies = "";
                    dies+=diceCup.getD1Val()+","+diceCup.getD2Val();
                    turnString += "displayDies:"+dies+";";
                    break;
                case "movePlayer":
                    String move = "";
                    move+= currPlayerIndex+","+players[currPlayerIndex].getCurrPos()+","+players[currPlayerIndex].getOldPos()+","+diceCup.getDiceIntValues()+","+players[currPlayerIndex].getCardMove();
                    turnString += "movePlayer:"+move+";";
                    break;
                case "setHouseLevel":
                    String setHouseLevel = "";
                    //(int currentPlayer, int playerPosition, boolean owned, int numberOfHouses)
                    turnString += "setHouseLevel:"+setHouseLevel+";";
                    break;
                case "turnMessage":
                    turnString += "turnMessage:"+turnMessage+";";
                    break;
                case "resetMessage":
                    turnMessage = "";
                    break;
                case "chanceCardMessage":
                    String chanceCardMessage = "Ryk til start";
                    turnString += "chanceCardMessage:"+chanceCardMessage+";";
                    break;
                case "mortgage":
                    String mortgage = currPlayerIndex+","+ currentMortgageProperty+",";
                    turnString += "mortgage:"+mortgage+"false"+";";
                    System.out.println("mortgage");
                    break;
                case "displayOwner":
                    String Owner = currPlayerIndex+","+ players[currPlayerIndex].getCurrPos()+",";
                    turnString += "displayOwner:"+Owner+"false"+";";
                    break;
            }
        }
    }

    public void addToTurnMessage(String information ){
        turnMessage += information;
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

    public void updateGui(){
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
