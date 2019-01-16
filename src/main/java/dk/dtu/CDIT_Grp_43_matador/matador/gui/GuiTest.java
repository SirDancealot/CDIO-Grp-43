package dk.dtu.CDIT_Grp_43_matador.matador.gui;

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

        String turnInfo = "updateScore:1220,2300,100,4400;displayDies:1,2;movePlayer:0,1,0,1,0;setHouse:0,9,false,2;setHotel:0,3,false,true;turnMessage:Hey dette er lækkert;chanceCardMessage:Ryk til start;mortgage:0,8,false";
        gui.updateDisplay(turnInfo);
    }
}
