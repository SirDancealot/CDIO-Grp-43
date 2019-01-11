package dk.dtu.CDIT_Grp_43_matador.matador;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Bank;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.Jail;
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
    boolean rolled = false;
    private int turns = 0;
    private int currPlayerIndex = 0;


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
    }

    /**
     * A funktion that updates the logic in the game and should be called every logic frame
     */

    public void tick() {

        while (!rolled) {

            if (players[currPlayerIndex].isInJail()) {
                if (players[currPlayerIndex].hasFreeJail()) {
                    String[] choices = {"Prøv at slå 2 ens", "Betal for at komme ud", "Brug chance kort", "Pantsæt", "Køb", "Sælg hus(e)"};
                    String choice = getChoice("Hvordan vil du komme ud af fængsel?", choices);
                    jailMoves(choice);
                } else {
                    String[] choices = {"Prøv at slå 2 ens", "Betal for at komme ud", "Pantsæt", "Køb", "Sælg hus(e)"};
                    String choice = getChoice("Du er i fængsel. Hvad vil du gøre nu?", choices);
                    jailMoves(choice);
                }

            }

            if (!players[currPlayerIndex].isInJail() && !rolled) {
                String[] choices = {"Pantsæt", "Køb", "Sælg hus(e)", "Slå med terningerne"};
                String choice = getChoice("Hvad vil du gøre nu?", choices);
                notRolledMoves(choice);
            }

        }

        if (rolled){
            players[currPlayerIndex].move(diceCup.getDiceIntValues());
        } else {
            diceCup.roll();
            rolled = true;
            players[currPlayerIndex].move(diceCup.getDiceIntValues());
        }

        while(rolled) {
            if(board.getGameTiles()[players[currPlayerIndex].getCurrPos()].isBuyable()) {
                String[] rolledChoices = {"Køb","Sæt på auktion" ,"Pantsæt", "Sælg hus(e)"};
                String rolledChoice = getChoice("Hvad vil du nu?", rolledChoices);
                rolledMoves(rolledChoice);
            }
        }


    }
    private void jailMoves (String choice) {

        switch (choice) {
            case "Prøv at slå 2 ens":
                diceCup.roll();
                if (diceCup.isSame()) {
                    players[currPlayerIndex].setInJail(false);
                    rolled = true;
                    break;
                }

            case "Betal for at komme ud":
                ((Jail)(board.getTileByName("Jail"))).payToExit(players[currPlayerIndex]);
                break;

            case "Brug chance kort":
                players[currPlayerIndex].setFreeJail(false);
                players[currPlayerIndex].setInJail(false);
                break;

            case "Pantsæt":

                break;

            case "Køb":

                break;

            case "Sælg hus(e)":

                break;

        }
    }

    private void notRolledMoves(String choice){

        switch (choice){
            case "Køb":

                break;
            case "Slå med terningerne":

                break;
            case "Sælg":

                break;
            case "Pantsæt":

                break;
        }


    }

    public void rolledMoves(String rolledChoice){

        switch (rolledChoice) {
            case "Køb":
                players[currPlayerIndex].withDrawMoney(board.getGameTiles()[players[currPlayerIndex].getCurrPos()].getTileValue());
                break;

            case "Sæt på auktion":

                break;

            case "Sælg":

                String Yeet;

                break;
            case "Sælg hus(e)":

                break;
        }

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


}
