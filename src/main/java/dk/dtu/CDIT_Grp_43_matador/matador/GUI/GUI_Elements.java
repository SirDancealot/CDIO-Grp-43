package dk.dtu.CDIT_Grp_43_matador.matador.GUI;

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
        panel.add(button);

        return panel;
    }

}
