package dk.dtu.CDIT_Grp_43_matador.matador.gui;

import java.io.IOException;

import gui_fields.GUI_Field;
import gui_fields.GUI_Ownable;
import gui_fields.GUI_Player;
import gui_main.GUI;

public class GUI_TEST {

    public static void main(String[] args) throws IOException {
        GUI_Controller controller = GUI_Controller.getINSTANCE();

        String[] lang = {"DK","Eng"};
        controller.setupGame(lang);
        controller.addplayers(controller.getNames(), 20);
        controller.displayPlayers(controller.getAllPlayer());

    }
}


