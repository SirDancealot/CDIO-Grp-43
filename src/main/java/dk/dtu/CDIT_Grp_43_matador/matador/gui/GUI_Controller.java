package dk.dtu.CDIT_Grp_43_matador.matador.gui;



import dk.dtu.CDIT_Grp_43_matador.matador.GameController;
import dk.dtu.CDIT_Grp_43_matador.matador.util.Factory;
import gui_fields.*;
import gui_main.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class GUI_Controller {
    private static GUI_Controller INSTANCE = new GUI_Controller();
    private GUI gui;
    private GameController game = GameController.getInstance();
    private GUI_Player[] allPlayer;
    private int numberOfPlayers = 0;
    private String[] names;

    
    private GUI_Controller() {}


    // Start game

	/**
	 * Initializes the gui with all the tiles to display.
	 */
    public void init(){
        GUI_Field[] gui_fields;
        try {
            gui_fields = Factory.getInstance().createGuiFields();
	        System.out.println("did it");
        } catch (IOException e) {
	        e.printStackTrace();
	        System.out.println("didn't do it");
	        gui_fields = new GUI_Field[0];
        }
        gui = new GUI(gui_fields);
        setChanceCard("Prøv lykken");
    }

	/**
	 * Asks the user for how many will be playing and what their player names will be.
	 * @throws IOException when an I/O error occurs.
	 */
	public void setupGame() throws IOException{
        // Number of players
        numberOfPlayers = Integer.valueOf(gui.getUserButtonPressed("Vælg antal spillere",   "3", "4", "5", "6" ));
        System.out.println(numberOfPlayers);

        // Add player names
        names = new String[numberOfPlayers];
        for(int i = 0; i < names.length; i++){

            names[i] = gui.getUserString("Tilføj spiller "+Integer.toString(i+1)+"´s navn");

            if(names[i].isEmpty()){
                names[i] = "Spiller "+Integer.toString(i+1);
            }
        }
    }


    // Created players

    /**
     * initializes an array of gui payers to be displayed in the gui with a given amount of money to start with.
     * @param money the amount of money each player starts with.
     */
    public void addplayers(int money) {
        Color[] primaryColor = {Color.blue, Color.red, Color.green, Color.MAGENTA, Color.pink, Color.yellow};
        Color[] secondaryColor = {Color.red, Color.green, Color.MAGENTA, Color.pink, Color.yellow, Color.blue,};
        GUI_Car.Type carTypes = GUI_Car.Type.CAR;
        allPlayer = new GUI_Player[names.length];
        for(int i = 0; i < names.length; i++){
            GUI_Car car = new GUI_Car(primaryColor[i], secondaryColor[i], carTypes, GUI_Car.Pattern.FILL);
            GUI_Player player = new GUI_Player(names[i], money, car);
            gui.addPlayer(player);
            allPlayer[i] = player;
        }
    }


    // Update GUI
	/**
	 * method to call when wanting to update the display with what happened last turn.
	 */
    public void updateDisplay(){
        //String turnInfo = game.getTurnInfo();

        String turnInfo = "updateScore:1220,2300,100,4400;displayDies:1,2;movePlayer:0,3,0,3,0;displayOwner:0,3,false;setHouse:0,3,true,2;setHotel:0,3,false,true;turnMessage:Hey dette er lækkert";
        String[] info = turnInfo.split(";");

        for (int i = 0; i < info.length; i++) {
            String[] thisInfo = info[i].split(":");
            String doshit = thisInfo[0];
            System.out.println("Turns "+i);
                switch (doshit) {
                    case "updateScore":
                        String[] score = thisInfo[1].split(",");
                        setScore(score);
                        System.out.println("Score uddated");
                        break;
                    case "displayDies":
                        String[] dies = thisInfo[1].split(",");
                        gui.setDice(Integer.parseInt(dies[0]), Integer.parseInt(dies[1]));
                        System.out.println("Dies displayed");
                        break;
                    case "movePlayer":
                        String[] movePlayer = thisInfo[1].split(",");
                        movePlayer(Integer.parseInt(movePlayer[0]),Integer.parseInt(movePlayer[1]),Integer.parseInt(movePlayer[2]),Integer.parseInt(movePlayer[3]),Integer.parseInt(movePlayer[4]));
                        System.out.println("Player moved");
                        break;
                    case "displayOwner":
                        String[] displayOwner = thisInfo[1].split(",");
                        displayOwner(Integer.parseInt(displayOwner[0]),Integer.parseInt(displayOwner[1]), tjekForBoolean(displayOwner[2]));
                        System.out.println("Displayed owner");
                        break;
                    case "setHouse":
                        String[] setHouse = thisInfo[1].split(",");
                        setHouse(Integer.parseInt(setHouse[0]),Integer.parseInt(setHouse[1]),tjekForBoolean(setHouse[2]),Integer.parseInt(setHouse[3]));
                        System.out.println("House set");
                        break;
                    case "setHotel":
                        String[] setHotel = thisInfo[1].split(",");
                        setHotel(Integer.parseInt(setHotel[0]),Integer.parseInt(setHotel[1]),tjekForBoolean(setHotel[2]),tjekForBoolean(setHotel[3]));
                        System.out.println("Hotel set");
                        break;
                    case "turnMessage":
                        String message = thisInfo[1];
                        displayMessage(message);
                        System.out.println("Turn message displayed");
                        break;
                }
        }
    }


    /**
	 * Displays all the gui players on the start tile.
	 */
    public void displayPlayers(){
        for(int i = 0; i < allPlayer.length; i++){
            gui.getFields()[0].setCar(allPlayer[i], true);
        }
    }


    /**
     * Method to be called when wanting to show text to the GUI (will interrupt everything else and wait for the player to press the 'ok' button)
     * @param msg a String containing what to display
     */
    public void displayMessage(String msg){
        gui.showMessage(msg);
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
    	}

    // Set score
    public void setScore(String[] playerScores){
    	for (int i = 0; i < allPlayer.length; i++) {
			allPlayer[i].setBalance(Integer.parseInt(playerScores[i]));
		}
    	
        //allPlayer[currentPlayer].setBalance(score);
    }

    // displayOwner

   public void displayOwner(int currentPlayer, int playerPosition, boolean owned){
        if(!owned){
            GUI_Field f = gui.getFields()[playerPosition];
            if (f instanceof GUI_Ownable) {
                GUI_Ownable o = (GUI_Ownable)f;
                o.setBorder(allPlayer[currentPlayer].getPrimaryColor(), allPlayer[currentPlayer].getSecondaryColor());
            }
        }
   }


   // SetChanceCard

    public void setChanceCard(String chanceString){
        gui.setChanceCard(chanceString);
    }

    // DisplayChanceCard

    public void displayChanceCard(){
        gui.displayChanceCard();
    }

    // Mortgage Properties

    public void doShit(){
        int[] propertiesOwned = {1,2,4};
        String[] properties = new String[propertiesOwned.length];

        for(int i = 0; i < propertiesOwned.length; i++){
            properties[i] = gui.getFields()[propertiesOwned[i]].getTitle();
        }
        String mortgagePropertie = gui.getUserSelection("Pantsætte ejendom", properties );
    }

    // Set house

    public void setHouse(int currentPlayer, int playerPosition, boolean owned, int numberOfHouses){
        if(!owned){
            GUI_Field f = gui.getFields()[playerPosition];
            if (f instanceof GUI_Ownable) {
                GUI_Ownable o = (GUI_Ownable)f;
                if(o instanceof GUI_Street){
                    GUI_Street s = (GUI_Street)o;
                    s.setHouses(numberOfHouses);
                }
            }
        }
    }

    // set Hotel

    public void setHotel(int currentPlayer, int playerPosition, boolean owned, boolean hasHotel){
        if(!owned){
            GUI_Field f = gui.getFields()[playerPosition];
            if (f instanceof GUI_Ownable) {
                GUI_Ownable o = (GUI_Ownable)f;
                if(o instanceof GUI_Street){
                    GUI_Street s = (GUI_Street)o;
                    s.setHotel(true);
                }
            }
        }
    }

    // String to boolean

    public Boolean tjekForBoolean(String state){
        if(state.equals("true")){
            return true;
        }else{
            return false;
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

    public String getUserInput(String msg) {
   	    return gui.getUserString(msg);
    }

    public int getUserInt (String msg) {
   	    return gui.getUserInteger(msg);
    }

    public GUI_Player[] getAllPlayer() {
        return allPlayer;
    }

    public void setAllPlayer(GUI_Player[] allPlayer) {
        this.allPlayer = allPlayer;
    }

}


