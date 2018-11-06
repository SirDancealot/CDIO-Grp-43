package dk.dtu.CDIT_Grp_43_matador.matador.GUI;

import dk.dtu.CDIT_Grp_43_matador.matador.util.GameTextures;

import javax.swing.*;
import java.awt.*;

public class GUI_Elements {

    public JPanel CreateNewPanel(int x, int y, int height, int width, Color panelColor, String jpanelText, Color jpanelColor, String titleFont, int fontSize){

        JPanel panel = new JPanel();
        JLabel label = new JLabel(jpanelText);
        Font titelFont = new Font(titleFont, Font.PLAIN, fontSize);

        panel.setBounds(x, y, height, width);
        panel.setBackground(panelColor);
        label.setForeground(jpanelColor);
        label.setFont(titelFont);
        panel.add(label);

        return panel;
    }

    public JPanel CreateNewButton(int x, int y, int height, int width, Color panelColor, String buttonText, Color buttonBgColor, Color buttonFgColor, String titleFont, int fontSize){

        JPanel panel = new JPanel();
        JButton button = new JButton(buttonText);
        Font titelFont = new Font(titleFont, Font.PLAIN, fontSize);

        panel.setBounds(x, y, height, width);
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



            System.out.println("panel createt");
            System.out.println(panel.getSize());
            System.out.println(label.getSize());

            panel.setBounds(0, -5, 500, 505);

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

}
