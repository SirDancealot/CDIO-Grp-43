package dk.dtu.CDIT_Grp_43_matador.matador.gui;

import gui_fields.GUI_Field;
import gui_fields.GUI_Ownable;
import gui_fields.GUI_Player;
import gui_main.GUI;

public class GUI_TEST {

    public static void main(String[] args) {
        GUI_Controller controller = GUI_Controller.getINSTANCE();

        String[] lang = {"DK","Eng"};
        controller.setupGame(lang);
        controller.addplayers(controller.getNames(), 1000);
        controller.displayPlayers(controller.getAllPlayer());

        controller.movePlayer(controller.getAllPlayer(), 0,0,5);
        controller.displayOwner(controller.getAllPlayer(), 0, 5);
        controller.movePlayer(controller.getAllPlayer(), 0,5,5);
        controller.displayOwner(controller.getAllPlayer(), 0, 10);

        controller.movePlayer(controller.getAllPlayer(), 1,0,5);
        controller.displayOwner(controller.getAllPlayer(), 0, 5);
    }
}


