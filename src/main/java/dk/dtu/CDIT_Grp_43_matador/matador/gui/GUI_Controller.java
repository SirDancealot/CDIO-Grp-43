package dk.dtu.CDIT_Grp_43_matador.matador.gui;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import gui_codebehind.GUI_BoardController;
import gui_codebehind.GUI_FieldFactory;
import gui_codebehind.SwingComponentFactory;
import gui_fields.GUI_Board;
import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_main.GUI;

import javax.swing.*;
import java.awt.*;

public class GUI_Controller {

    private Modified_GUI gui;
    private GUI_Player[] allPlayer;
    private int[] playerPosition;

    public GUI_Controller() {
         gui = new Modified_GUI();
    }


    // Created players
    public void addplayers(String[] names, int money) {

        Color[] primaryColor = {Color.blue, Color.red, Color.green, Color.MAGENTA};
        Color[] secondaryColor = {Color.blue, Color.red, Color.green, Color.MAGENTA};
        GUI_Car.Type[] carTypes = {GUI_Car.Type.CAR,GUI_Car.Type.TRACTOR, GUI_Car.Type.RACECAR, GUI_Car.Type.UFO };
        allPlayer = new GUI_Player[names.length];
        playerPosition = new int[names.length];

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
            playerPosition[i] = 0;
        }
    }

    // Move current player
    public void movePlayer(GUI_Player[] allPlayer, int currentPlayer, int[] playerPosition, int rolled){
        gui.getFields()[playerPosition[currentPlayer]].setCar(allPlayer[currentPlayer], false);
        gui.getFields()[playerPosition[currentPlayer]+rolled].setCar(allPlayer[currentPlayer], true);
        this.playerPosition[currentPlayer] = playerPosition[currentPlayer]+rolled;
    }

    // Getters and setters


    public int[] getPlayerPosition() {
        return playerPosition;
    }

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
}








/*
    public static void main(String[] args) {
        realExampleGame();
    }

    private static void realExampleGame() {
        GUI_Player mn = new GUI_Player("Mads", 30000);
        GUI_Player sh = new GUI_Player("Stig", 30000);
        GUI gui = new GUI();
        sleep();
        gui.addPlayer(mn);
        sleep();
        gui.addPlayer(sh);
        sleep();
        gui.getFields()[0].setCar(mn, true);
        sleep();
        gui.getFields()[0].setCar(sh, true);
        sleep();
        gui.setDice(1, 2);
        sleep();
        GUI_Field[] var3 = gui.getFields();
        int var4 = var3.length;

        int var5;
        GUI_Field f;
        for(var5 = 0; var5 < var4; ++var5) {
            f = var3[var5];
            f.setCar(mn, false);
        }

        gui.getFields()[1].setCar(mn, true);
        sleep();
        var3 = gui.getFields();
        var4 = var3.length;

        for(var5 = 0; var5 < var4; ++var5) {
            f = var3[var5];
            f.setCar(mn, false);
        }

        gui.getFields()[2].setCar(mn, true);
        sleep();
        var3 = gui.getFields();
        var4 = var3.length;

        for(var5 = 0; var5 < var4; ++var5) {
            f = var3[var5];
            f.setCar(mn, false);
        }

        gui.getFields()[3].setCar(mn, true);
        sleep();
        mn.setBalance(28000);
        GUI_Field f = gui.getFields()[3];
        if (f instanceof GUI_Ownable) {
            GUI_Ownable o = (GUI_Ownable)f;
            o.setBorder(mn.getPrimaryColor(), mn.getSecondaryColor());
        }

        sleep();
        gui.displayChanceCard("De har vundet vild med dans og skifter navn til Allan!");
    }

    public static void sleep() {
        sleep(1200);
    }

    public static void sleep(int n) {
        long t0 = System.currentTimeMillis();

        long t1;
        do {
            t1 = System.currentTimeMillis();
        } while(t1 - t0 < (long)n);

    }
}
*/


