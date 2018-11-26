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
        fields[i] = new GUI_Start("Start", "Modtag: 2", "Modtag kr. 2,-\nnår de passerer start", new Color(255, 255, 255), Color.BLACK);
        //fields[i] = new GUI_Start("Start", "Modtag: 200", "Modtag kr. 200,-\nnår de passerer start", new Color(255, 255, 255), Color.BLACK);
        fields[var2++] = new GUI_Street("Burgerbaren", "Pris:  1", "Burgerbaren", "Leje:  1", new Color(139, 69, 19), Color.BLACK);
        fields[var2++] = new GUI_Street("Pizzeriaet", "Pris:  1", "Pizzeriaet", "Leje:  1", new Color(139, 69, 19), Color.BLACK);
        fields[var2++] = new GUI_Chance("?", "Prøv lykken", "Ta' et chancekort.", new Color(200, 200, 200), Color.BLACK);
        fields[var2++] = new GUI_Street("Slikbutikken", "Pris:  1", "Slikbutikken", "Leje:  1", new Color(176,196,222), Color.BLACK);
        fields[var2++] = new GUI_Street("Iskiosken", "Pris:  1", "AllÃ©gade", "Leje:  1", new Color(176,196,222), Color.BLACK);
        fields[var2++] = new GUI_Jail("default", "På besøg", "På besøg", "På besøg i fængslet", new Color(125, 125, 125), Color.BLACK);
        fields[var2++] = new GUI_Street("Museet", "Pris:  2", "Museet", "Leje:  2", new Color(220,20,60), Color.BLACK);
        fields[var2++] = new GUI_Street("Bibloteket", "Pris:  2", "Bibloteket", "Leje:  2", new Color(220,20,60), Color.BLACK);
        fields[var2++] = new GUI_Chance("?", "Prøv lykken", "Ta' et chancekort.", new Color(200, 200, 200), Color.BLACK);
        fields[var2++] = new GUI_Street("Skaterparken", "Pris:  2", "Skaterparken", "Leje:  2", new Color(255,215,0), Color.BLACK);
        fields[var2++] = new GUI_Street("Svømmehallen", "Pris:  2", "Svømmehallen", "Leje:  2", new Color(255,215,0), Color.BLACK);
        fields[var2++] = new GUI_Refuge("default", "GratisParkering", "Parkering", "Ta' en pause", new Color(255, 255, 255), Color.BLACK);
        fields[var2++] = new GUI_Street("Spillehallen", "Pris:  3", "Spillehallen", "Leje:  3", new Color(255,69,0), Color.BLACK);
        fields[var2++] = new GUI_Street("Biograf", "Pris:  3", "Biograf", "Leje:  3", new Color(255,69,0), Color.BLACK);
        fields[var2++] = new GUI_Chance("?", "Prøv lykken", "Ta' et chancekort.", new Color(200, 200, 200), Color.BLACK);
        fields[var2++] = new GUI_Street("Legetøjs-\nbutikken", "Pris:  3", "Legetøjsbutikken", "Leje:  3", new Color(255,255,0), Color.BLACK);
        fields[var2++] = new GUI_Street("Dyrehandlen", "Pris:  3", "Dyrehandlen", "Leje:  3", new Color(255,255,0), Color.BLACK);
        fields[var2++] = new GUI_Jail("default", "GåTilFængsel", "Fængsel", "De fængless\nslå to ens for at komme ud", new Color(125, 125, 125), Color.BLACK);
        fields[var2++] = new GUI_Street("Bowlinghallen", "Pris:  4", "Bowlinghallen", "Leje:  4", new Color(34,139,34), Color.BLACK);
        fields[var2++] = new GUI_Street("Zoo", "Pris:  4", "Zoo", "Leje:  4", new Color(34,139,34), Color.BLACK);
        fields[var2++] = new GUI_Chance("?", "Prøv lykken", "Ta' et chancekort.", new Color(200, 200, 200), Color.BLACK);
        fields[var2++] = new GUI_Street("Vandlandet", "Pris:  5", "Vandlandet", "Leje:  5", new Color(65,105,225), Color.BLACK);
        fields[var2++] = new GUI_Street("Strand-\npromenaden", "Pris:  5", "Strandpromenaden", "Leje:  5", new Color(65,105,225), Color.black);
        return fields;
    }
}
