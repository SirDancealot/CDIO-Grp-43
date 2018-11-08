package dk.dtu.CDIT_Grp_43_matador.matador.GUI;


import dk.dtu.CDIT_Grp_43_matador.matador.Matador;
import dk.dtu.CDIT_Grp_43_matador.matador.util.GameTextures;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.io.IOException;
import javax.swing.*;


import static dk.dtu.CDIT_Grp_43_matador.matador.util.GameTextures.createGameBoardTextures;

public class MatodorGUI{

    private Console console = System.console();

    private JFrame gameScreen;
    private Container screenContainer;
    private GUI_Elements elements = new GUI_Elements();

    // Colers

    private Color black = Color.black;
    private Color white = Color.white;
    private Color test = Color.blue;

    // Gui elements

    // Start Button
    private JPanel startButtonHolder;
    private JButton startButton;
    private Font startButtonFort;

    // Add player 1
    private JPanel player1NameInputHolder;
    private JTextArea player1NameInput;
    private Font player1NameInputTextAreaFort;
    private JPanel player1NameInputButtonHolder;
    private JButton player1NameInputButton;
    private Font player1NameInputButtonFort;
    private JPanel player1AddTitelHolder;
    private JLabel player1AddTitel;
    private Font player1AddTitelFont;

    // Add player 2
    private JPanel player2NameInputHolder;
    private JTextArea player2NameInput;
    private Font player2NameInputTextAreaFort;
    private JPanel player2NameInputButtonHolder;
    private JButton player2NameInputButton;
    private Font player2NameInputButtonFort;
    private JPanel player2AddTitelHolder;
    private JLabel player2AddTitel;
    private Font player2AddTitelFont;

    // Game BG

    private JPanel BG_holder;
    private ImageIcon BG_Image;
    private JLabel BG_container;

    // Game message

    private JPanel gameMessageHolder;
    private JLabel gameMessage;
    private Font gameMessageFont;

    // Roll button

    private JPanel rollButtonHolder;
    private JButton rollButton;
    private Font rollButtonFort;


    // TextArea
    private JPanel gameMessageAreaHolder;
    private JTextArea gameMessageArea;
    private JScrollPane gameMessageScoll;

    public JTextArea getTextArea() {
        return gameMessageArea;
    }

    // Display player 1 Name

    private JPanel displayPlayer1NameHolder;
    private JLabel displayPlayer1Name;
    private Font displayPlayer1NameFont;

    // Display player 1 Score

    private JPanel displayPlayer1ScoreHolder;
    private JLabel displayPlayer1Score;
    private Font displayPlayer1ScoreFont;

    // Display player 2 Name

    private JPanel displayPlayer2NameHolder;
    private JLabel displayPlayer2Name;
    private Font displayPlayer2NameFont;

    // Display player 2 Score

    private JPanel displayPlayer2ScoreHolder;
    private JLabel displayPlayer2Score;
    private Font displayPlayer2ScoreFont;

    // Display winner of game

    private JPanel displayWinnerOfGameHolder;
    private JLabel displayWinnerOfGame;
    private Font displayWinnerOfGameFont;

    // winner
    private JPanel winnerBgImageHolder;
    private ImageIcon winnerBgImage;
    private JLabel winnerBgImageContainer;

    // Turns
    private int turn = 0;
    private int currPlayer;

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
        startScreen();
        screenUpdate();
    }

    private void screenSetup() {

        // can not figure out how to add our items in another way .. right now do not have time
        //bg = elements.createBg();
        //rollButton = elements.CreateNewButton(160, 380, 100, 55, Color.black, "Roll", Color.black, Color.white, "Times New Roman", 30 );
        //gameMessage = elements.CreateNewPanel(35, 600, 530, 150, Color.white, "Game message", Color.black, "Times New Roman", 20);
        //textArea = elements.CreateTextArea(35, 630, 530,120,6,45);
        //startButton = elements.CreateNewButton(0, 300, 800, 200, Color.black, "Start Game", Color.black, Color.white, "Times New Roman", 90 );


        // StartButton
        startButtonHolder = new JPanel();
        startButton = new JButton("Start Game");
        startButtonFort = new Font("Times New Roman", Font.PLAIN, 90);
        startButtonHolder.setBounds(0, 300, 800, 200);
        startButtonHolder.setBackground(Color.black);
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        startButton.setFont(startButtonFort);
        startButton.setFocusPainted(false);
        startButtonHolder.add(startButton);

        // StartButton Action
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPlayer1();
                screenUpdate();
            }
        });


        // Player1 adding text

        player1AddTitelHolder = new JPanel();
        player1AddTitel = new JLabel("Enter name of Player 1");
        player1AddTitelFont = new Font("Times New Roman", Font.PLAIN, 45);
        player1AddTitelHolder.setBounds(0, 250, 800, 60);
        player1AddTitelHolder.setBackground(black);
        player1AddTitel.setForeground(white);
        player1AddTitel.setFont(player1AddTitelFont);
        player1AddTitelHolder.add(player1AddTitel);

        // Player1NameInput

        player1NameInputHolder = new JPanel();
        player1NameInputHolder.setBackground(Color.white);
        player1NameInput = new JTextArea(1, 10);
        player1NameInputTextAreaFort = new Font("Times New Roman", Font.PLAIN, 35);
        player1NameInput.setFont(player1NameInputTextAreaFort);
        player1NameInput.setSelectedTextColor(Color.black);
        player1NameInputHolder.setBounds(150, 330, 500, 60);
        player1NameInputHolder.add(player1NameInput);

        // Player1NameInputButton
        player1NameInputButtonHolder = new JPanel();
        player1NameInputButton = new JButton("Set name");
        player1NameInputButtonFort = new Font("Times New Roman", Font.PLAIN, 40);
        player1NameInputButtonHolder.setBounds(0, 420, 800, 100);
        player1NameInputButtonHolder.setBackground(Color.black);
        player1NameInputButton.setBackground(Color.black);
        player1NameInputButton.setForeground(Color.white);
        player1NameInputButton.setFont(player1NameInputButtonFort);
        player1NameInputButton.setFocusPainted(false);
        player1NameInputButtonHolder.add(player1NameInputButton);

        // Player1Button Action
        player1NameInputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPlayer2();
                screenUpdate();
            }
        });


        // Player2 adding text

        player2AddTitelHolder = new JPanel();
        player2AddTitel = new JLabel("Enter name of Player 2");
        player2AddTitelFont = new Font("Times New Roman", Font.PLAIN, 45);
        player2AddTitelHolder.setBounds(0, 250, 800, 60);
        player2AddTitelHolder.setBackground(black);
        player2AddTitel.setForeground(white);
        player2AddTitel.setFont(player2AddTitelFont);
        player2AddTitelHolder.add(player2AddTitel);

        // Player1NameInpu2

        player2NameInputHolder = new JPanel();
        player2NameInputHolder.setBackground(Color.white);
        player2NameInput = new JTextArea(1, 10);
        player2NameInputTextAreaFort = new Font("Times New Roman", Font.PLAIN, 35);
        player2NameInput.setFont(player2NameInputTextAreaFort);
        player2NameInput.setSelectedTextColor(Color.black);
        player2NameInputHolder.setBounds(150, 330, 500, 60);
        player2NameInputHolder.add(player2NameInput);

        // Player1NameInputButton
        player2NameInputButtonHolder = new JPanel();
        player2NameInputButton = new JButton("Set name");
        player2NameInputButtonFort = new Font("Times New Roman", Font.PLAIN, 40);
        player2NameInputButtonHolder.setBounds(0, 420, 800, 100);
        player2NameInputButtonHolder.setBackground(Color.black);
        player2NameInputButton.setBackground(Color.black);
        player2NameInputButton.setForeground(Color.white);
        player2NameInputButton.setFont(player2NameInputButtonFort);
        player2NameInputButton.setFocusPainted(false);
        player2NameInputButtonHolder.add(player2NameInputButton);

        // Player2Button Action
        player2NameInputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameScreen();
                screenUpdate();
            }
        });

        // BG image

        BG_Image = new ImageIcon(GameTextures.gameBackground);
        BG_holder = new JPanel();
        BG_container = new JLabel();
        BG_container.setIcon(BG_Image );
        BG_holder .add(BG_container);
        BG_holder.setBounds(0, -5, 600, 605);

        // Game message

        gameMessageHolder = new JPanel();
        gameMessage = new JLabel("Game message");
        gameMessageFont = new Font("Times New Roman", Font.PLAIN, 20);
        gameMessageHolder.setBounds(35, 600, 530, 150);
        gameMessageHolder.setBackground(white);
        gameMessage.setForeground(black);
        gameMessage.setFont(gameMessageFont);
        gameMessageHolder.add(gameMessage);

        // Roll button

        rollButtonHolder = new JPanel();
        rollButton = new JButton("Roll");
        rollButtonFort = new Font("Times New Roman", Font.PLAIN, 30);
        rollButtonHolder.setBounds(160, 380, 100, 55);
        rollButtonHolder.setBackground(Color.black);
        rollButton.setBackground(Color.black);
        rollButton.setForeground(Color.white);
        rollButton.setFont(rollButtonFort);
        rollButton.setFocusPainted(false);
        rollButtonHolder.add(rollButton);

        // rollButton Action
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLoop();
                screenUpdate();
            }
        });

        // GameMessageArea
        gameMessageAreaHolder = new JPanel();
        gameMessageAreaHolder.setBackground(Color.white);
        gameMessageArea = new JTextArea(6, 45);
        gameMessageArea.setSelectedTextColor(Color.black);
        gameMessageArea.setWrapStyleWord(true);
        gameMessageArea.setEditable(false);
        gameMessageScoll = new JScrollPane(gameMessageArea);
        gameMessageScoll.setViewportView(gameMessageArea);
        gameMessageAreaHolder.setBounds(35, 630, 530, 120);
        gameMessageAreaHolder.add(gameMessageScoll);


        // Display player 1 name

        displayPlayer1NameHolder = new JPanel();
        displayPlayer1Name = new JLabel("Player 1");
        displayPlayer1NameFont = new Font("Times New Roman", Font.PLAIN, 40);
        displayPlayer1NameHolder.setBounds(600, 100, 150, 50);
        displayPlayer1NameHolder.setBackground(black);
        displayPlayer1Name.setForeground(white);
        displayPlayer1Name.setFont(displayPlayer1NameFont);
        displayPlayer1NameHolder.add(displayPlayer1Name);

        //Display player 1 score

        displayPlayer1ScoreHolder = new JPanel();
        displayPlayer1Score = new JLabel("1000");
        displayPlayer1ScoreFont = new Font("Times New Roman", Font.PLAIN, 32);
        displayPlayer1ScoreHolder.setBounds(625, 160, 100, 50);
        displayPlayer1ScoreHolder.setBackground(white);
        displayPlayer1Score.setForeground(black);
        displayPlayer1Score.setFont(displayPlayer1ScoreFont);
        displayPlayer1ScoreHolder.add(displayPlayer1Score);

        // Display player 2 name

        displayPlayer2NameHolder = new JPanel();
        displayPlayer2Name = new JLabel("Player 2");
        displayPlayer2NameFont = new Font("Times New Roman", Font.PLAIN, 40);
        displayPlayer2NameHolder.setBounds(600, 240, 150, 50);
        displayPlayer2NameHolder.setBackground(black);
        displayPlayer2Name.setForeground(white);
        displayPlayer2Name.setFont(displayPlayer2NameFont);
        displayPlayer2NameHolder.add(displayPlayer2Name);

        //Display player 2 score

        displayPlayer2ScoreHolder = new JPanel();
        displayPlayer2Score = new JLabel("1000");
        displayPlayer2ScoreFont = new Font("Times New Roman", Font.PLAIN, 32);
        displayPlayer2ScoreHolder.setBounds(625, 300, 100, 50);
        displayPlayer2ScoreHolder.setBackground(white);
        displayPlayer2Score.setForeground(black);
        displayPlayer2Score.setFont(displayPlayer2ScoreFont);
        displayPlayer2ScoreHolder.add(displayPlayer2Score);


        // Display winner of game

        displayWinnerOfGameHolder = new JPanel();
        displayWinnerOfGame = new JLabel("Not you james, william !!!");
        displayWinnerOfGameFont = new Font("Times New Roman", Font.PLAIN, 25);
        displayWinnerOfGameHolder.setBounds(100, 500, 400, 35);
        displayWinnerOfGameHolder.setBackground(white);
        displayWinnerOfGame.setForeground(black);
        displayWinnerOfGame.setFont(displayWinnerOfGameFont);
        displayWinnerOfGameHolder.add(displayWinnerOfGame);

        // Display winner image

        winnerBgImage = new ImageIcon(GameTextures.winning);
        winnerBgImageHolder = new JPanel();
        winnerBgImageContainer = new JLabel();
        winnerBgImageContainer.setIcon(winnerBgImage);
        winnerBgImageHolder.add(winnerBgImageContainer);
        winnerBgImageHolder.setBounds(100, 100, 400, 400);

    }

    public void startScreen(){
        screenContainer.add(startButtonHolder);
    }


    public void addPlayer1(){
        screenContainer.remove(startButtonHolder);
        screenContainer.add(player1AddTitelHolder);
        screenContainer.add(player1NameInputHolder);
        screenContainer.add(player1NameInputButtonHolder);
    }


    public void addPlayer2(){
        Matador.getPlayers()[0].setName(player1NameInput.getText());
        screenContainer.remove(player1AddTitelHolder);
        screenContainer.remove(player1NameInputHolder);
        screenContainer.remove(player1NameInputButtonHolder);
        screenContainer.add(player2AddTitelHolder);
        screenContainer.add(player2NameInputHolder);
        screenContainer.add(player2NameInputButtonHolder);
    }


    public void gameScreen(){
        Matador.getPlayers()[1].setName(player2NameInput.getText());
        screenContainer.remove(player2AddTitelHolder);
        screenContainer.remove(player2NameInputHolder);
        screenContainer.remove(player2NameInputButtonHolder);

        screenContainer.add(displayPlayer1NameHolder);
        screenContainer.add(displayPlayer1ScoreHolder);
        screenContainer.add(displayPlayer2NameHolder);
        screenContainer.add(displayPlayer2ScoreHolder);

        // Test

        //screenContainer.add(winnerBgImageHolder);
        //screenContainer.add(displayWinnerOfGameHolder);

        // Test

        screenContainer.add(gameMessageAreaHolder);
        screenContainer.add(gameMessageHolder);
        screenContainer.add(rollButtonHolder);
        screenContainer.add(BG_holder);

        Matador.getGame().getTextArea().append(Matador.getLang().getTag("Player:turnRoll")+" "+Matador.getPlayers()[0].getName()+"," + Matador.getLang().getTag("Player:enterRoll"));
        Matador.getGame().getTextArea().append("\n");
    }

    public void endGame(int currPlayer){
        screenContainer.remove(rollButtonHolder);

        if(currPlayer ==0){
            displayWinnerOfGame.setText("not you "+ Matador.getPlayers()[1].getName()+", "+Matador.getPlayers()[0].getName());
        }else{
            displayWinnerOfGame.setText("not you "+Matador.getPlayers()[0].getName()+", "+Matador.getPlayers()[1].getName());
        }
        screenContainer.remove(BG_holder);
        screenContainer.add(winnerBgImageHolder);
        screenContainer.add(displayWinnerOfGameHolder);
        screenContainer.add(BG_holder);
        screenUpdate();
    }


    public void screenUpdate(){
        gameScreen.revalidate();
        gameScreen.repaint();
    }

    public void gameLoop(){

        if(turn%2==0){
            Matador.getPlayers()[0].playerRollDice();
            System.out.println("player1");
            displayPlayer1Score.setText(Integer.toString(Matador.getPlayers()[0].getScore()));
            //displayPlayer2Score.setText(Integer.toString(Matador.getPlayers()[1].getScore()));
            turn++;
            currPlayer = 0;
            Matador.getGame().getTextArea().append(Matador.getLang().getTag("Player:turnRoll")+" "+Matador.getPlayers()[1].getName()+"," + Matador.getLang().getTag("Player:enterRoll"));
            Matador.getGame().getTextArea().append("\n");
        }else{
            Matador.getPlayers()[1].playerRollDice();
            System.out.println("player2");
            //displayPlayer1Score.setText(Integer.toString(Matador.getPlayers()[0].getScore()));
            displayPlayer2Score.setText(Integer.toString(Matador.getPlayers()[1].getScore()));
            turn++;
            currPlayer = 1;
            Matador.getGame().getTextArea().append(Matador.getLang().getTag("Player:turnRoll")+" "+Matador.getPlayers()[0].getName()+"," + Matador.getLang().getTag("Player:enterRoll"));
            Matador.getGame().getTextArea().append("\n");
        }

        if(Matador.getPlayers()[currPlayer].hasWon()){
            endGame(currPlayer);
            Matador.getGame().getTextArea().append(Matador.getPlayers()[currPlayer].toString()+" "+ Matador.getLang().getTag("Matador:wonIn") +" " + turn +" "+ Matador.getLang().getTag("Matador:turns"));


        }
        }

}
