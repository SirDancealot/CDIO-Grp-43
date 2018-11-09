package dk.dtu.CDIT_Grp_43_matador.matador.GUI;

import static dk.dtu.CDIT_Grp_43_matador.matador.util.GameTextures.createGameBoardTextures;

public class GUI_Controller {

    public static void main(String[] args) {
        createGameBoardTextures();
        MatodorGUI game = new MatodorGUI(800, 800);

        game.getTextArea().append("hey");
    }
}
