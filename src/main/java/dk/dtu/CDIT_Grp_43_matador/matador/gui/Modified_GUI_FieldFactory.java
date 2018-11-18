package dk.dtu.CDIT_Grp_43_matador.matador.gui;

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

public final class Modified_GUI_FieldFactory {
    private Modified_GUI_FieldFactory() {
    }

    public static GUI_Field[] makeFields() {
        GUI_Field[] fields = new GUI_Field[24];
        int i = 0;
        int var2 = i + 1;
        fields[i] = new GUI_Start("Start", "Modtag: 200", "Modtag kr. 200,-\nnår de passerer start", new Color(255, 255, 255), Color.BLACK);
        fields[var2++] = new GUI_Street("Burgerbaren", "Pris:  60", "Burgerbaren", "Leje:  20", new Color(139, 69, 19), Color.BLACK);
        fields[var2++] = new GUI_Street("Pizzeriaet", "Pris:  60", "Pizzeriaet", "Leje:  20", new Color(139, 69, 19), Color.BLACK);
        fields[var2++] = new GUI_Chance("?", "Prøv lykken", "Ta' et chancekort.", new Color(200, 200, 200), Color.BLACK);
        fields[var2++] = new GUI_Street("Slikbutikken", "Pris:  100", "Slikbutikken", "Leje:  40", new Color(176,196,222), Color.BLACK);
        fields[var2++] = new GUI_Street("Iskiosken", "Pris:  120", "AllÃ©gade", "Leje:  45", new Color(176,196,222), Color.BLACK);
        fields[var2++] = new GUI_Jail("default", "På besøg", "På besøg", "På besøg i fængslet", new Color(125, 125, 125), Color.BLACK);
        fields[var2++] = new GUI_Street("Museet", "Pris:  140", "Museet", "Leje:  50", new Color(220,20,60), Color.BLACK);
        fields[var2++] = new GUI_Street("Bibloteket", "Pris:  140", "Bibloteket", "Leje:  50", new Color(220,20,60), Color.BLACK);
        fields[var2++] = new GUI_Chance("?", "Prøv lykken", "Ta' et chancekort.", new Color(200, 200, 200), Color.BLACK);
        fields[var2++] = new GUI_Street("Skaterparken", "Pris:  180", "Skaterparken", "Leje:  60", new Color(255,215,0), Color.BLACK);
        fields[var2++] = new GUI_Street("Svømmehallen", "Pris:  180", "Svømmehallen", "Leje:  60", new Color(255,215,0), Color.BLACK);
        fields[var2++] = new GUI_Refuge("default", "GratisParkering", "GratisParkering", "Ta' en pause", new Color(255, 255, 255), Color.BLACK);
        fields[var2++] = new GUI_Street("Spillehallen", "Pris:  220", "Spillehallen", "Leje:  70", new Color(255,69,0), Color.BLACK);
        fields[var2++] = new GUI_Street("Biograf", "Pris:  220", "Biograf", "Leje:  70", new Color(255,69,0), Color.BLACK);
        fields[var2++] = new GUI_Chance("?", "Prøv lykken", "Ta' et chancekort.", new Color(200, 200, 200), Color.BLACK);
        fields[var2++] = new GUI_Street("Legetøjs-\nbutikken", "Pris:  240", "Legetøjsbutikken", "Leje:  80", new Color(255,255,0), Color.BLACK);
        fields[var2++] = new GUI_Street("Dyrehandlen", "Pris:  240", "Dyrehandlen", "Leje:  80", new Color(255,255,0), Color.BLACK);
        fields[var2++] = new GUI_Jail("default", "GåTilFængsel", "GåTilFængsel", "De fængless\nslå to ens for at komme ud", new Color(125, 125, 125), Color.BLACK);
        fields[var2++] = new GUI_Street("Bowlinghallen", "Pris:  300", "Bowlinghallen", "Leje:  95", new Color(34,139,34), Color.BLACK);
        fields[var2++] = new GUI_Street("Zoo", "Pris:  300", "Zoo", "Leje:  95", new Color(34,139,34), Color.BLACK);
        fields[var2++] = new GUI_Chance("?", "Prøv lykken", "Ta' et chancekort.", new Color(200, 200, 200), Color.BLACK);
        fields[var2++] = new GUI_Street("Vandlandet", "Pris:  320", "Vandlandet", "Leje:  100", new Color(65,105,225), Color.BLACK);
        fields[var2++] = new GUI_Street("Strand-\npromenaden", "Pris:  350", "Strandpromenaden", "Leje:  120", new Color(65,105,225), Color.black);
        return fields;
    }
}