package dk.dtu.CDIT_Grp_43_matador.matador.gui;

public class GUI_TEST {

    public static void main(String[] args) {
        GUI_Controller controller = new GUI_Controller();

        String[] names = {"william", "james", "Bob", "chris"};
        int money = 1000;
        controller.addplayers(names, money);

    }
}
