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
    private JPanel gameMessage;
    private JPanel startButton;
    private JPanel bg;

    public JTextArea getTextArea() {
        return textArea;
    }

    private JTextArea textArea;


    public MatodorGUI(int width, int height){

        gameScreen = new JFrame();
        gameScreen.setResizable(false);
        gameScreen.setSize(width, height);
        gameScreen.setLayout(null);
        gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameScreen.setVisible(true);
        screenContainer = gameScreen.getContentPane();
        screenContainer.setBackground(Color.black);

        screenSetup();
    }

    private void screenSetup() {
        bg = elements.createBg();
        startButton = elements.CreateNewButton(160, 380, 100, 55, Color.black, "Roll", Color.black, Color.white, "Times New Roman", 30 );
        gameMessage = elements.CreateNewPanel(35, 600, 530, 150, Color.white, "Game message", Color.black, "Times New Roman", 20);
        //textArea = elements.CreateTextArea(35, 630, 530,120,6,45);

        // I couldn´t make JTextArea under GUI_Elements tries for like 4 houers.

        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        textArea = new JTextArea(6, 45);
        textArea.setEnabled(false);
        textArea.setSelectedTextColor(Color.black);
        textArea.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setViewportView(textArea);
        panel.setBounds(35, 630, 530, 120);
        panel.add(scroll);


        screenContainer.add(panel);
        screenContainer.add(gameMessage);
        screenContainer.add(startButton);
        screenContainer.add(bg);

    }
}
