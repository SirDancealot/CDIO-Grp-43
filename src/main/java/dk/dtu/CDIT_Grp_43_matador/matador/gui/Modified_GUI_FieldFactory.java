package dk.dtu.CDIT_Grp_43_matador.matador.gui;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Tile;
import dk.dtu.CDIT_Grp_43_matador.matador.util.Factory;
import gui_fields.GUI_Brewery;
import gui_fields.GUI_Chance;
import gui_fields.GUI_Field;
import gui_fields.GUI_Jail;
import gui_fields.GUI_Refuge;
import gui_fields.GUI_Shipping;
import gui_fields.GUI_Start;
import gui_fields.GUI_Street;
import gui_fields.GUI_Tax;
import java.awt.Color;
import java.io.IOException;

public final class Modified_GUI_FieldFactory {


    private Modified_GUI_FieldFactory() {
    }

    public static GUI_Field[] makeFields() {
        GUI_Field[] fields = new GUI_Field[24];

        int i = 0;
        int var2 = i + 1;
        fields[i] = new GUI_Start("Start", "Receive: 2", "Receive 2 \n" +"when you pass start", new Color(255, 255, 255), Color.BLACK);
        fields[var2++] = new GUI_Street("The Burger bar", "Price: 1", "The Burger bar", "Rent: 1", new Color(139, 69, 19), Color.BLACK);
        fields[var2++] = new GUI_Street("The Pizzeria", "Price: 1", "The Pizzeria", "Rent: 1", new Color(139, 69, 19), Color.BLACK);
        fields[var2++] = new GUI_Chance("?", "Chance", "Take a Chance card.", new Color(200, 200, 200), Color.BLACK);
        fields[var2++] = new GUI_Street("The Candy shop", "Price: 1", "The Candy shop", "Rent: 1", new Color(176,196,222), Color.BLACK);
        fields[var2++] = new GUI_Street("The Ice cream booth", "Price: 1", "The Ice cream booth", "Rent: 1", new Color(176,196,222), Color.BLACK);
        fields[var2++] = new GUI_Jail("default", "Visiting", "Visiting", "Visiting the prison", new Color(125, 125, 125), Color.BLACK);
        fields[var2++] = new GUI_Street("The Museum", "Price: 2", "The Museum", "Rent: 2", new Color(220,20,60), Color.BLACK);
        fields[var2++] = new GUI_Street("The Library", "Price: 2", "The Library", "Rent: 2", new Color(220,20,60), Color.BLACK);
        fields[var2++] = new GUI_Chance("?", "Chance", "Take a Chance card.", new Color(200, 200, 200), Color.BLACK);
        fields[var2++] = new GUI_Street("The Skater park", "Price: 2", "The Skater park", "Rent: 2", new Color(255,215,0), Color.BLACK);
        fields[var2++] = new GUI_Street("The pool", "Price: 2", "The Swimming pool", "Rent: 2", new Color(255,215,0), Color.BLACK);
        fields[var2++] = new GUI_Refuge("default", "FreeParking", "Parking", "Take a break", new Color(255, 255, 255), Color.BLACK);
        fields[var2++] = new GUI_Street("The Arcade", "Price: 3", "The Arcade", "Rent: 3", new Color(255,69,0), Color.BLACK);
        fields[var2++] = new GUI_Street("The Cinema", "Price: 3", "The Cinema", "Rent: 3", new Color(255,69,0), Color.BLACK);
        fields[var2++] = new GUI_Chance("?", "Chance", "Take a Chance card.", new Color(200, 200, 200), Color.BLACK);
        fields[var2++] = new GUI_Street("The Toy store", "Pris: 3", "The Toy store", "Rent: 3", new Color(255,255,0), Color.BLACK);
        fields[var2++] = new GUI_Street("The Pet shop", "Price: 3", "The Pet shop", "Rent: 3", new Color(255,255,0), Color.BLACK);
        fields[var2++] = new GUI_Jail("default", "GoToJail", "Jail", "You are imprisoned, paid 2 to be released", new Color(125, 125, 125), Color.BLACK);
        fields[var2++] = new GUI_Street("The Bowling alley", "Price: 4", "The Bowling alley", "Rent: 4", new Color(34,139,34), Color.BLACK);
        fields[var2++] = new GUI_Street("The Zoo", "Price: 4", "The Zoo", "Rent: 4", new Color(34,139,34), Color.BLACK);
        fields[var2++] = new GUI_Chance("?", "Chance", "Take a Chance card.", new Color(200, 200, 200), Color.BLACK);
        fields[var2++] = new GUI_Street("The Water park", "Price: 5", "The Water park", "Rent: 5", new Color(65,105,225), Color.BLACK);
        fields[var2++] = new GUI_Street("The Beach promenade", "Price: 5", "The Beach promenade", "Rent: 5", new Color(65,105,225), Color.black);
        return fields;
    }
}
