package dk.dtu.CDIT_Grp_43_matador.matador.GUI;


import dk.dtu.CDIT_Grp_43_matador.matador.util.GameTextures;

import java.awt.*;
import javax.swing.*;


import static dk.dtu.CDIT_Grp_43_matador.matador.util.GameTextures.createGameBoardTextures;

public class MatodorGUI{

    private JFrame gameScreen;
    private Container screenContainer;
    private GUI_Elements elements = new GUI_Elements();

    // Gui elements
    private JPanel gameTitle;
    private JPanel startButton;
    private JPanel bg;
    private JPanel tile;

    private int screenLeft, screenTop, screenHeight, screenWidth;


    public MatodorGUI(int width, int height){

        gameScreen = new JFrame();
        gameScreen.setResizable(false);
        gameScreen.setSize(width, height);
        gameScreen.setLayout(null);
        gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameScreen.setVisible(true);
        screenContainer = gameScreen.getContentPane();

        screenLeft = gameScreen.getInsets().left;
        screenTop = gameScreen.getInsets().top;
        screenHeight = gameScreen.getInsets().bottom;
        screenWidth = gameScreen.getInsets().right;

        System.out.println(screenLeft);
        System.out.println(screenTop);
        System.out.println(screenHeight);
        System.out.println(screenWidth);


        screenSetup();

    }

    private void screenSetup() {
        bg = elements.createBg();
        //tile = elements.createImageIcon(0);
        //gameTitle = elements.CreateNewPanel(100, 100, 600, 150, Color.black, "Matador", Color.white, "Times New Roman", 90);
        //startButton = elements.CreateNewButton(300, 400, 200, 100, Color.black, "Start", Color.black, Color.white, "Times New Roman", 30 );

        //screenContainer.add(gameTitle);
        //screenContainer.add(startButton);
        //screenContainer.add(tile);
        screenContainer.add(bg);

        System.out.println("all good ");

    }

    public static void main(String[] args) {
        createGameBoardTextures();
          new MatodorGUI(516, 539);
    }



}
