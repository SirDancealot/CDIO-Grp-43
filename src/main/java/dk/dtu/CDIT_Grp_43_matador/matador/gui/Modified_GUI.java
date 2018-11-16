package dk.dtu.CDIT_Grp_43_matador.matador.gui;

import gui_codebehind.GUI_BoardController;
import gui_codebehind.GUI_FieldFactory;
import gui_fields.GUI_Board;
import gui_fields.GUI_Field;

import java.awt.*;

public class Modified_GUI {
    private final Color BASECOLOR = GUI_Board.BASECOLOR;
    private GUI_BoardController bc;
    private static boolean null_fields_allowed = false;



    public Modified_GUI(){
        GUI_Field[] fields = Modified_GUI_FieldFactory.makeFields();
        for(int i = 0; i < fields.length; i++){
            fields[i] = fields[i];
        }
        bc = new GUI_BoardController(fields);
    }
}
