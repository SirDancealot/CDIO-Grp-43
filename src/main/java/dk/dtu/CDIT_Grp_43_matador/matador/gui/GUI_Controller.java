package dk.dtu.CDIT_Grp_43_matador.matador.gui;

import dk.dtu.CDIT_Grp_43_matador.matador.language.*;
import dk.dtu.CDIT_Grp_43_matador.matador.util.InformationExchanger;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import gui_fields.*;

import java.awt.*;
import java.io.IOException;

public class GUI_Controller {

    private Modified_GUI gui;


    private GUI_Player[] allPlayer;
    private int numberOfPlayers = 0;
    private int langIndex = 0;
    private String[] names;
    private static GUI_Controller INSTANCE = new GUI_Controller();
    private static InformationExchanger infExch = InformationExchanger.getInstance();
    
    private GUI_Controller() {
         gui = new Modified_GUI();
    }


    // Start game

    public void setupGame(String[] lang) throws IOException{

        // Number of players
        String number_of_players = getGui().getUserButtonPressed("Select the number of players",  "2", "3", "4" );
        switch (number_of_players) {
            case "2":
                System.out.println("2 player");
                numberOfPlayers = 2;
                break;
            case "3":
                System.out.println("3 player");
                numberOfPlayers = 3;
                break;
            case "4":
                System.out.println("4 player");
                numberOfPlayers = 4;
                break;
        }

        // Add player names
        names = new String[numberOfPlayers];
        for(int i = 1; i <= names.length; i++){
            names[i-1] = gui.getUserString("Add Player "+Integer.toString(i));
        }
    }



    // Update GUI
    public void updateDisplay(){
    	getGui().getUserButtonPressed(infExch.getCurrPlayer() + " it's your turn, please roll the die", "Roll" );
        getGui().setDie(infExch.getCurrPlayerRolled());
        movePlayer(getAllPlayer(), infExch.getCurrPlayerIndex(), infExch.getCurrPlayerNewPos(), infExch.getCurrPlayerOldPos(), infExch.getCurrPlayerRolled(), infExch.getCardMove());
        setScore(getAllPlayer(), infExch.getPlayers());
        displayOwner(getAllPlayer(), infExch.getCurrPlayerIndex(), infExch.getCurrPlayerNewPos(), infExch.isTileOwned());
        displayCurrentTurn(infExch.getCurrentTurnText());
    }

    // Created players
    public void addplayers(String[] names, int money) {
        Color[] primaryColor = {Color.blue, Color.red, Color.green, Color.MAGENTA};
        Color[] secondaryColor = {Color.blue, Color.red, Color.green, Color.MAGENTA};
        GUI_Car.Type[] carTypes = {GUI_Car.Type.CAR,GUI_Car.Type.TRACTOR, GUI_Car.Type.RACECAR, GUI_Car.Type.UFO };
        allPlayer = new GUI_Player[names.length];
        for(int i = 0; i < names.length; i++){
            GUI_Car car = new GUI_Car(primaryColor[i], secondaryColor[i], carTypes[i], GUI_Car.Pattern.FILL);
            GUI_Player player = new GUI_Player(names[i], money, car);
            gui.addPlayer(player);
            allPlayer[i] = player;
        }
    }

    public void displayCurrentTurn(String currentTurn){
        gui.showMessage(currentTurn);
    }


    // Display all players
    public void displayPlayers(GUI_Player[] playersInGame){
        for(int i = 0; i < playersInGame.length; i++){
            gui.getFields()[0].setCar(playersInGame[i], true);
        }
    }

    // Move current player
    public void movePlayer(GUI_Player[] allPlayer, int currentPlayer, int playerPositionAfterRoll, int playerPositionBeforeRoll, int playerRoll, int cardMove){
    	for (int i = 0; i < playerRoll; i++) {
    		gui.getFields()[(playerPositionBeforeRoll+i) % 24].setCar(allPlayer[currentPlayer], false);
    		gui.getFields()[(playerPositionBeforeRoll+i+1) % 24].setCar(allPlayer[currentPlayer], true);
    		try {
    			Thread.sleep(100);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
		}
    	if (cardMove != 0) {
    		try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		infExch.setCardMove(0);
    		for (int i = 0; i < cardMove; i++) {
    			gui.getFields()[(playerPositionBeforeRoll+playerRoll+i) % 24].setCar(allPlayer[currentPlayer], false);
    			gui.getFields()[(playerPositionBeforeRoll+playerRoll+i+1) % 24].setCar(allPlayer[currentPlayer], true);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
    	}
    	if ((playerPositionBeforeRoll+playerRoll+cardMove) % 24 != playerPositionAfterRoll) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            gui.getFields()[(playerPositionBeforeRoll+playerRoll+cardMove) % 24].setCar(allPlayer[currentPlayer], false);
            gui.getFields()[playerPositionAfterRoll].setCar(allPlayer[currentPlayer], true);
        }
    	
    	
    	
    	/*
    	for(int i = 0; i < 24; i++){
            gui.getFields()[i].setCar(allPlayer[currentPlayer], false);
        }
        gui.getFields()[playerPositionAfterRoll].setCar(allPlayer[currentPlayer], true);
    */}

    // Set score
    public void setScore(GUI_Player[] guiPlayer, Player[] logicPlayers){
    	for (int i = 0; i < guiPlayer.length; i++) {
			guiPlayer[i].setBalance(logicPlayers[i].getScore());
		}
    	
        //allPlayer[currentPlayer].setBalance(score);
    }

    // displayOwner

   public void displayOwner(GUI_Player[] allPlayer, int currentPlayer, int playerPosition, boolean owned){

        if(owned){
            GUI_Field f = gui.getFields()[playerPosition];
            if (f instanceof GUI_Ownable) {
                GUI_Ownable o = (GUI_Ownable)f;
                o.setBorder(allPlayer[currentPlayer].getPrimaryColor(), allPlayer[currentPlayer].getSecondaryColor());
            }
   }
   }

    // Getters and setters


    public GUI_Player[] getAllPlayer() {
        return allPlayer;
    }

    public void setAllPlayer(GUI_Player[] allPlayer, int roll) {
        this.allPlayer = allPlayer;
    }

    public Modified_GUI getGui() {
        return gui;
    }

    public void setGui(Modified_GUI gui) {
        this.gui = gui;
    }

    public static GUI_Controller getINSTANCE() {
        return INSTANCE;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public String[] getNames() {
        return names;
    }

    public int getLangIndex() {
        return langIndex;
    }
}


