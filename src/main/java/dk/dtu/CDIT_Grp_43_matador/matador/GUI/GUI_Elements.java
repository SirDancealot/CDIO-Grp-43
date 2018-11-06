package dk.dtu.CDIT_Grp_43_matador.matador.GUI;

import dk.dtu.CDIT_Grp_43_matador.matador.util.GameTextures;

import javax.swing.*;
import java.awt.*;

public class GUI_Elements {

    public JPanel CreateNewPanel(int x, int y, int width, int height, Color panelColor, String jpanelText, Color jpanelColor, String titleFont, int fontSize){

        JPanel panel = new JPanel();
        JLabel label = new JLabel(jpanelText);
        Font titelFont = new Font(titleFont, Font.PLAIN, fontSize);
        panel.setBounds(x, y, width, height);
        panel.setBackground(panelColor);
        label.setForeground(jpanelColor);
        label.setFont(titelFont);
        panel.add(label);

        return panel;
    }

    public JPanel CreateNewButton(int x, int y, int width, int height, Color panelColor, String buttonText, Color buttonBgColor, Color buttonFgColor, String titleFont, int fontSize){

        JPanel panel = new JPanel();
        JButton button = new JButton(buttonText);
        Font titelFont = new Font(titleFont, Font.PLAIN, fontSize);

        panel.setBounds(x, y, width, height);
        panel.setBackground(panelColor);
        button.setBackground(buttonBgColor);
        button.setForeground(buttonFgColor);
        button.setFont(titelFont);
        button.setFocusPainted(false);
        panel.add(button);

        return panel;
    }

    public JPanel createBg() {

        ImageIcon icon = new ImageIcon(GameTextures.gameBackground);
        if (icon != null) {
            JPanel panel = new JPanel();
            JLabel label = new JLabel();
            label.setIcon(icon);
            panel.add(label);
            panel.setBounds(0, -5, 600, 605);
            return panel;
        } else {
            System.err.println("Couldn't find file: " );
            return null;
        }
    }

    public JPanel createImageIcon(int imageIndex) {

        ImageIcon icon = new ImageIcon(GameTextures.tileTextures[imageIndex]);

        if (icon != null) {
            JPanel panel = new JPanel();
            JLabel label = new JLabel();

            label.setIcon(icon);
            panel.setBounds(0, 0, 100, 100);
            panel.add(label);

            System.out.println("panel createt");
            return panel;
        } else {
            System.err.println("Couldn't find file: " );
            return null;
        }
    }

    

    public JPanel CreateTextArea(int x, int y, int width, int height, int rows, int columes){

        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        JTextArea textArea = new JTextArea(rows, columes);
        textArea.setEnabled(false);
        textArea.setSelectedTextColor(Color.black);
        textArea.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setViewportView(textArea);
        panel.setBounds(x, y, width, height);
        panel.add(scroll);
        return panel;

    }


}
