package dk.dtu.CDIT_Grp_43_matador.matador.gui;

import dk.dtu.CDIT_Grp_43_matador.matador.LogicController;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import gui_codebehind.GUI_BoardController;
import gui_codebehind.GUI_FieldFactory;
import gui_codebehind.SwingComponentFactory;
import gui_fields.*;
import gui_main.GUI;

import javax.swing.*;
import java.awt.*;

public class GUI_Controller {

    private Modified_GUI gui;


    private GUI_Player[] allPlayer;
    private int numberOfPlayers = 0;
    private int langIndex = 0;
    private String[] names;
    private static GUI_Controller INSTANCE = new GUI_Controller();

    private GUI_Controller() {
         gui = new Modified_GUI();
    }


    // Start game

    public void setupGame(String[] lang){

        // Select language

        String rolledString = getGui().getUserButtonPressed("Select language", lang );
        switch (rolledString) {
            case "DK":
                System.out.println("DK");
                langIndex = 0;
                break;
            case "Eng":
                langIndex = 1;
                System.out.println("Eng");
                break;
        }

        // Number of players
        String number_of_players = getGui().getUserButtonPressed("Select the number of players", "1", "2", "3", "4" );
        switch (number_of_players) {
            case "1":
                System.out.println("1 player");
                numberOfPlayers = 1;
                break;
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
    public void updateDisplay(int rolled, int currentPlayer, int currentPlayerPosition, int score, int tileIndex){
        String rolledString = getGui().getUserButtonPressed("Player "+Integer.toString(currentPlayer+1)+" it´s your turn, please Roll dices", "Roll" );
        getGui().setDie(rolled);
        setScore(getAllPlayer(), currentPlayer, score);
        movePlayer(getAllPlayer(), currentPlayer, currentPlayerPosition, rolled);
        displayOwner(getAllPlayer(), currentPlayer, tileIndex);
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

    // Display all players
    public void displayPlayers(GUI_Player[] playersInGame){
        for(int i = 0; i < playersInGame.length; i++){
            gui.getFields()[0].setCar(playersInGame[i], true);
        }
    }

    // Move current player
    public void movePlayer(GUI_Player[] allPlayer, int currentPlayer, int playerPosition, int rolled){
        gui.getFields()[playerPosition].setCar(allPlayer[currentPlayer], false);
        gui.getFields()[playerPosition+rolled].setCar(allPlayer[currentPlayer], true);
    }

    // Set score
    public void setScore(GUI_Player[] allPlayer, int currentPlayer, int score){
        allPlayer[currentPlayer].setBalance(score);
    }

    // displayOwner

   public void displayOwner(GUI_Player[] allPlayer, int currentPlayer, int tileIndex){
       GUI_Field f = gui.getFields()[tileIndex];
       if (f instanceof GUI_Ownable) {
           GUI_Ownable o = (GUI_Ownable)f;
           o.setBorder(allPlayer[currentPlayer].getPrimaryColor(), allPlayer[currentPlayer].getSecondaryColor());
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


