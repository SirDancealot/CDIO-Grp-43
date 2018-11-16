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
        fields[i] = new GUI_Start("Start", "Modtag: 200", "Modtag kr. 200,-\nnÃ¥r de passerer start", Color.RED, Color.BLACK);
        fields[var2++] = new GUI_Street("RÃ¸dovrevej", "Pris:  60", "RÃ¸dovrevej", "Leje:  20", new Color(75, 155, 225), Color.BLACK);
        fields[var2++] = new GUI_Street("Hvidovrevej", "Pris:  60", "Hvidovrevej", "Leje:  20", new Color(75, 155, 225), Color.BLACK);
        fields[var2++] = new GUI_Chance("?", "PrÃ¸v lykken", "Ta' et chancekort.", new Color(204, 204, 204), Color.BLACK);
        fields[var2++] = new GUI_Street("Valby\nLanggade", "Pris:  100", "Valby Langgade", "Leje:  40", new Color(255, 135, 120), Color.BLACK);
        fields[var2++] = new GUI_Street("AllÃ©gade", "Pris:  120", "AllÃ©gade", "Leje:  45", new Color(255, 135, 120), Color.BLACK);
        fields[var2++] = new GUI_Jail("default", "FÃ¦ngsel", "FÃ¦ngsel", "PÃ¥ besÃ¸g i fÃ¦ngslet", new Color(125, 125, 125), Color.BLACK);
        fields[var2++] = new GUI_Street("Frederiks-\nberg AllÃ©", "Pris:  140", "Frederiksberg AllÃ©", "Leje:  50", new Color(102, 204, 0), Color.BLACK);
        fields[var2++] = new GUI_Street("BÃ¼lowsvej", "Pris:  140", "BÃ¼lowsvej", "Leje:  50", new Color(102, 204, 0), Color.BLACK);
        fields[var2++] = new GUI_Chance("?", "PrÃ¸v lykken", "Ta' et chancekort.", new Color(204, 204, 204), Color.BLACK);
        fields[var2++] = new GUI_Street("Hellerupvej", "Pris:  180", "Hellerupvej", "Leje:  60", new Color(153, 153, 153), Color.BLACK);
        fields[var2++] = new GUI_Street("Strandvejen", "Pris:  180", "Strandvejen", "Leje:  60", new Color(153, 153, 153), Color.BLACK);
        fields[var2++] = new GUI_Refuge("default", "Helle", "Helle", "Ta' en pause", Color.WHITE, Color.BLACK);
        fields[var2++] = new GUI_Street("Trianglen", "Pris:  220", "Trianglen", "Leje:  70", Color.RED, Color.BLACK);
        fields[var2++] = new GUI_Street("Ã˜sterbro-\ngade", "Pris:  220", "Ã˜sterbrogade", "Leje:  70", Color.RED, Color.BLACK);
        fields[var2++] = new GUI_Chance("?", "PrÃ¸v lykken", "Ta' et chancekort.", new Color(204, 204, 204), Color.BLACK);
        fields[var2++] = new GUI_Street("GrÃ¸nningen", "Pris:  240", "GrÃ¸nningen", "Leje:  80", Color.RED, Color.BLACK);
        fields[var2++] = new GUI_Street("GrÃ¸nningen", "Pris:  240", "GrÃ¸nningen", "Leje:  80", Color.RED, Color.BLACK);
        fields[var2++] = new GUI_Jail("default", "GÃ¥ i fÃ¦ngsel", "GÃ¥ i fÃ¦ngsel", "De fÃ¦ngsles\nSlÃ¥ to ens for at komme ud", new Color(125, 125, 125), Color.BLACK);
        fields[var2++] = new GUI_Street("Amagertorv", "Pris:  300", "Amagertorv", "Leje:  95", new Color(255, 255, 50), Color.BLACK);
        fields[var2++] = new GUI_Street("Vimmel-\nskaftet", "Pris:  300", "Vimmelskaftet", "Leje:  95", new Color(255, 255, 50), Color.BLACK);
        fields[var2++] = new GUI_Chance("?", "PrÃ¸v lykken", "Ta' et chancekort.", new Color(204, 204, 204), Color.BLACK);
        fields[var2++] = new GUI_Street("Nygade", "Pris:  320", "Nygade", "Leje:  100", new Color(255, 255, 50), Color.BLACK);
        fields[var2++] = new GUI_Street("Frederiks-\nberggade", "Pris:  350", "Frederiksberggade", "Leje:  120", new Color(150, 60, 150), Color.WHITE);
        return fields;
    }
}