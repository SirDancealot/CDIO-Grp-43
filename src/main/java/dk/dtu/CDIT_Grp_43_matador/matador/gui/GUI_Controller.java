package dk.dtu.CDIT_Grp_43_matador.matador.gui;

import gui_codebehind.GUI_BoardController;
import gui_codebehind.GUI_FieldFactory;
import gui_fields.GUI_Board;
import gui_fields.GUI_Field;
import gui_main.GUI;

import java.awt.*;

public class GUI_Controller {

    private static GUI_Controller gui_controller = new GUI_Controller();
    private GUI_Controller() {
        new Modified_GUI();
    }


    // Getters and setters

    public static GUI_Controller getGui_controller() {
        return gui_controller;
    }

    public static void setGui_controller(GUI_Controller gui_controller) {
        GUI_Controller.gui_controller = gui_controller;
    }
}


