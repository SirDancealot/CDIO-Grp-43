package dk.dtu.CDIT_Grp_43_matador.matador.gui;

import gui_fields.GUI_Field;
import gui_fields.GUI_Ownable;
import gui_fields.GUI_Player;
import gui_main.GUI;

public class GUI_TEST {

    public static void main(String[] args) {
        GUI_Controller controller = new GUI_Controller();

        String[] names = {"william", "james", "Bob", "chris"};
        int money = 1000;


        int currentPlayer = 0;
        int rolled = 4;
        String rolledString;
        controller.addplayers(names, money);
        controller.displayPlayers(controller.getAllPlayer());


        while (true){
            rolledString = controller.getGui().getUserButtonPressed("Roll dices", "Roll");
            if(rolledString == "Roll"){
                controller.getGui().setDie(rolled);
                controller.movePlayer(controller.getAllPlayer(), currentPlayer, controller.getPlayerPosition(), rolled);
                currentPlayer++;

                if(currentPlayer == names.length){
                    currentPlayer = 0;
                }
            }


        }

    }
}


