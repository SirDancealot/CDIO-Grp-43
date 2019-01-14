package dk.dtu.CDIT_Grp_43_matador.matador.gui;

import dk.dtu.CDIT_Grp_43_matador.matador.gui.GUI_Controller;

import java.io.IOException;

class GuiTest {

    private static GUI_Controller gui = GUI_Controller.getINSTANCE();

    public static void main(String[] args) throws IOException {
        gui.init();
        gui.setupGame();
        int numPlayers = gui.getNumberOfPlayers();
        int startMoney = 1500;
        gui.addplayers(startMoney);
        gui.displayPlayers();
        gui.updateDisplay(); 
    }
}
