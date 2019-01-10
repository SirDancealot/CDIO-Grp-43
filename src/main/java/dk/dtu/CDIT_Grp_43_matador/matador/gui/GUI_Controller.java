package dk.dtu.CDIT_Grp_43_matador.matador.gui;


import dk.dtu.CDIT_Grp_43_matador.matador.GameController;
import dk.dtu.CDIT_Grp_43_matador.matador.util.Factory;
import dk.dtu.CDIT_Grp_43_matador.matador.util.InformationExchanger;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import gui_fields.*;
import gui_main.*;

import java.awt.*;
import java.io.IOException;

public class GUI_Controller {
    private static GUI_Controller INSTANCE = new GUI_Controller();


    private GUI gui;

    private GameController game = GameController.getInstance();
    private GUI_Player[] allPlayer;
    private int numberOfPlayers = 0;
    private String[] names;

    private static InformationExchanger infExch = InformationExchanger.getInstance();

    
    private GUI_Controller() {}


    // Start game

	/**
	 * Initializes the gui with all the tiles to display.
	 */
    public void init(){
        GUI_Field[] gui_fields;
        try {
            gui_fields = Factory.getInstance().createGuiFields();
        } catch (IOException e) {
	        gui_fields = new GUI_Field[0];
        }
        gui = new GUI(gui_fields);
    }

	/**
	 * Asks the user for how many will be playing and what their player names will be.
	 * @throws IOException when an I/O error occurs.
	 */
	public void setupGame() throws IOException{
        // Number of players
        numberOfPlayers = Integer.valueOf(gui.getUserButtonPressed("Select the number of players",  "2", "3", "4" ));

        // Add player names
        names = new String[numberOfPlayers];
        for(int i = 1; i <= names.length; i++){
            names[i-1] = gui.getUserString("Add Player "+Integer.toString(i));
        }
    }



    // Update GUI

	/**
	 * method to call when wanting to update the display with what happened last turn.
	 */
    public void updateDisplay(){
        String turnInfo = game.getTurnInfo();

    	/*
        gui.getUserButtonPressed(infExch.getCurrPlayer() + " it's your turn, please roll the die", "Roll" );
        gui.setDie(infExch.getCurrPlayerRolled());
        setScore(getAllPlayer(), infExch.getPlayers());
        movePlayer(getAllPlayer(), infExch.getCurrPlayerIndex(), infExch.getCurrPlayerNewPos(), infExch.getCurrPlayerOldPos(), infExch.getCurrPlayerRolled(), infExch.getCardMove());
        displayOwner(getAllPlayer(), infExch.getCurrPlayerIndex(), infExch.getCurrPlayerNewPos(), infExch.isTileOwned());
        displayMessage(infExch.getCurrentTurnText());
        */
    }

    // Created players

	/**
	 * initializes an array of gui payers to be displayed in the gui with a given amount of money to start with.
	 * @param money the amount of money each player starts with.
	 */
    public void addplayers(int money) {
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

	/**
	 * Method to be called when wanting to show text to the GUI (will interrupt everything else and wait for the player to press the 'ok' button)
	 * @param msg a String containing what to display
	 */
	public void displayMessage(String msg){
        gui.showMessage(msg);
    }


    // Display all players

	/**
	 * Displays all the gui players on the start tile.
	 */
    public void displayPlayers(){
        for(int i = 0; i < allPlayer.length; i++){
            gui.getFields()[0].setCar(allPlayer[i], true);
        }
    }

    // Move current player

	/**
	 * method to be called when updating the gui with a new position for a {@code Player}
	 * @param currentPlayer the index of the current player in the range {@code 0 <= currentPlayer < Players.length}
	 * @param playerPositionAfterRoll the index on the {@code GameBoard} of the {@code Player} after their turn is done.
	 * @param playerPositionBeforeRoll the index on the {@code GameBoard} of the {@code Player} before their turn is done.
	 * @param playerRoll the number the {@code Player} rolled with the dice.
	 * @param cardMove the amount of tiles the {@code Player} moved due to drawing {@code ChanceCard}.
	 */
    public void movePlayer(int currentPlayer, int playerPositionAfterRoll, int playerPositionBeforeRoll, int playerRoll, int cardMove){
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

	/**
	 * Method to be called when having to update the gui with the current score of each {@code Player}
	 * @param logicPlayers the array of {@code Player}s which are the ones used in the logic part of the game.
	 */
    public void setScore(Player[] logicPlayers){
    	for (int i = 0; i < allPlayer.length; i++) {
			allPlayer[i].setBalance(logicPlayers[i].getScore());
		}
    	
        //allPlayer[currentPlayer].setBalance(score);
    }

    // displayOwner

   public void displayOwner(int currentPlayer, int playerPosition, boolean owned){

        if(owned){
            GUI_Field f = gui.getFields()[playerPosition];
            if (f instanceof GUI_Ownable) {
                GUI_Ownable o = (GUI_Ownable)f;
                o.setBorder(allPlayer[currentPlayer].getPrimaryColor(), allPlayer[currentPlayer].getSecondaryColor());
            }
        }
   }

	/**
	 *
	 * @param msg the message to display to the player with the buttons
	 * @param buttons any number of strings the amount being how many buttons to display, and the value is what to write on the buttons
	 * @return returns the string that was written on the button that was pressed
	 */
   public String displayButtons(String msg, String... buttons) {
        return gui.getUserButtonPressed(msg, buttons);
   }

    // Getters and setters
    public static GUI_Controller getINSTANCE() {
        return INSTANCE;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public String[] getNames() {
        return names;
    }
}


