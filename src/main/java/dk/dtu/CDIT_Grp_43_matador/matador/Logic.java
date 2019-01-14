package dk.dtu.CDIT_Grp_43_matador.matador;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Bank;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.*;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.OwnableProperties.Property;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.DiceCup;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.GameBoard;

public class Logic {

    private static Logic INSTANCE = new Logic();
    private final int TURNLIMIT = 100;

    private Bank bank = Bank.getInstance();
    private GameController game = GameController.getInstance();
    private Player[] players;
    private DiceCup diceCup;
    private GameBoard board;
    private boolean endOfGame = false;
    private boolean rolled = false;
    private boolean checkForDeadPlayers;
    private int turns = 0;
    private int currPlayerIndex = 0;
    private int[] deadPlayers;




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

        deadPlayers = new int[players.length];

        for(int i = 0; i < deadPlayers.length; i++){
            deadPlayers[i] = 0;
        }
    }

    /**
     * A funktion that updates the logic in the game and should be called every logic frame
     */

    public void tick() {

        while (!rolled) {
            String[] options = {"Sælg hus(e)" ,"Køb hus(e)", "Pantsæt", "Rul",};

            if (players[currPlayerIndex].isInJail()) {

                String[] choice = expandArray(options, "Betal for at komme ud");

                if (players[currPlayerIndex].hasFreeJail())
                    expandArray(options, "Brug chance kort");
            }

            String choice = getChoice("Du er i fængsel. Hvad vil du nu?", options);
            beforeRoll(choice);

        }

        while (rolled) {

            String[] options = {"Sælg hus(e)", "Køb hus(e)", "Pantsæt", "Ophæv pantsætning", "Slut tur"};

            if (board.getGameTiles()[players[currPlayerIndex].getCurrPos()].isBuyable()){
                expandArray(options, "Køb", "Sæt på auktion");
            }
            String choice = getChoice("Hvad vil du nu?", options);
            afterRoll(choice);
        }
    }

    private void beforeRoll(String choice ) {

        switch (choice) {
            case "Rul":
                diceCup.roll();
                if (diceCup.isSame()) {
                    players[currPlayerIndex].setInJail(false);
                }
                rolled = true;
                break;

            case "Betal for at komme ud":
                ((Jail) (board.getTileByName("Jail"))).payToExit(players[currPlayerIndex]);
                break;

            case "Brug chance kort":
                players[currPlayerIndex].setFreeJail(false);
                players[currPlayerIndex].setInJail(false);
                break;
            case "Sælg hus(e)":

                players[currPlayerIndex].getOwnedTiles();

                break;
            case "Køb hus(e)":

                break;

            case "Pantsæt":

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
                        String chosenPawn = getChoice("Hvilket hus vil du pantsætte?", pawnableNames);
                        bank.pawnTile(players[currPlayerIndex], board.getTileByName(chosenPawn));
                }

                break;

                case "Ophæv pantsætning":



        }
    }

    private void afterRoll(String choice){

        switch (choice){
            case "Sælg hus(e)":

                break;
            case "Køb hus(e)":

                break;
            case "Pantsæt":

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
                    String chosenPawn = getChoice("Hvilket hus vil du pantsætte?", pawnableNames);
                    bank.pawnTile(players[currPlayerIndex], board.getTileByName(chosenPawn));
                }

                break;
            case "Køb":
                ((Ownable)board.getGameTiles()[players[currPlayerIndex].getCurrPos()]).buyTile(players[currPlayerIndex]);
                break;
            case "Sæt på auktion":
                bank.auctions(players, board.getGameTiles()[players[currPlayerIndex].getCurrPos()]);
                break;
            case "Slut tur":

                if(players[currPlayerIndex].getScore() < 0){
                    deadPlayers[currPlayerIndex] = 1;
                }

                int deadPlayerCount = 0;

                for (int i = 0; i < deadPlayers.length; i++){

                    if(deadPlayers[i] == 1){
                        deadPlayerCount++;
                    }
                    
                    if(deadPlayerCount == players.length-1){
                        // endgame funktion
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

    public void sell(String sellChoice){
    }
    
    public String getChoice (String msg, String... buttons){
        return game.getChoice(msg, buttons);
    }

    public void displayMessage (String msg){
        game.displayMessage(msg);
    }

    public int getUserInt (String msg) {
        return game.getUserInt(msg);
    }


    public static Logic getINSTANCE () {
        return INSTANCE;
    }

    public boolean isEndOfGame () {
        return endOfGame;
    }

    public Tile[] getTileBySet(String setTag) {
        return board.getTileBySet(setTag);
    }
    public int getDiceSum(){
        return diceCup.getDiceIntValues();
    }
}
