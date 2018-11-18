package dk.dtu.CDIT_Grp_43_matador.matador.gui;

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

    public GUI_Controller() {
         gui = new Modified_GUI();


    }




    // Created players
    public void addplayers(String[] names, int money) {

        Color[] primaryColor = {Color.blue, Color.red, Color.green, Color.MAGENTA};
        Color[] secondaryColor = {Color.blue, Color.red, Color.green, Color.MAGENTA};
        GUI_Car.Type[] carTypes = {GUI_Car.Type.CAR,GUI_Car.Type.TRACTOR, GUI_Car.Type.RACECAR, GUI_Car.Type.UFO };

        for(int i = 0; i < names.length; i++){
            GUI_Car car = new GUI_Car(primaryColor[i], secondaryColor[i], carTypes[i], GUI_Car.Pattern.FILL);
            GUI_Player player = new GUI_Player(names[i], money, car);
            gui.addPlayer(player);
        }

    }




}


