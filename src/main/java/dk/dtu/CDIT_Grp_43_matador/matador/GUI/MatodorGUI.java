package dk.dtu.CDIT_Grp_43_matador.matador.GUI;

import java.awt.*;
import javax.swing.*;

public class MatodorGUI {

    private JFrame gameScreen;
    private Container screenContainer;
    private GUI_Elements elements = new GUI_Elements();

    // Gui elements
    private JPanel gameTitle;
    private JPanel startButton;

    public static void main(String[] args) {
        new MatodorGUI(800, 600);
    }

    public MatodorGUI(int height, int width){


        gameScreen = new JFrame();
        gameScreen.setSize(height, width);
        gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameScreen.getContentPane().setBackground(Color.black);
        gameScreen.setLayout(null);
        gameScreen.setVisible(true);
        screenContainer = gameScreen.getContentPane();

        screenSetup();

    }

    private void screenSetup() {
        gameTitle = elements.CreateNewPanel(100, 100, 600, 150, Color.black, "Matador", Color.white, "Times New Roman", 90);
        startButton = elements.CreateNewButton(300, 400, 200, 100, Color.black, "Start", Color.black, Color.white, "Times New Roman", 30 );

        screenContainer.add(gameTitle);
        screenContainer.add(startButton);
    }


}
