package dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles;

import dk.dtu.CDIT_Grp_43_matador.matador.Logic;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;

public class Tax extends Tile {
    private int taxFlat;
    private int taxPercent = 0;
    private final String[] options = {"Betal 10% af formue", "Betal 200 kr"};
    private int lastPayed = 0;
    private Logic logic = Logic.getINSTANCE();


    public Tax(String tileName, String tileinfo, int tileIndex) {
        super(tileName, tileinfo, tileIndex);
        String[] tileInfo = tileinfo.split(";");
        for (String string : tileInfo) {
            String[] split = string.split(":");
            switch (split[0]) {
                case "money":
                    this.taxFlat = Integer.valueOf(split[1]);
                    break;
                case "percent":
                    this.taxPercent = Integer.valueOf(split[1]);
                    break;
                default:
                    break;
            }
        }
    }

        @Override
        public boolean landOnTile (Player p) {
            if (taxPercent != 0) {
                String choice = logic.getChoice("Betal skat", false, options);
                if (choice.equals(options[0])) {
                    lastPayed = ((int) ((taxPercent / 100.0) * p.playerFortune()));
                    return p.withDrawMoney((int) ((taxPercent / 100.0) * p.playerFortune()));
                }
            }
            lastPayed = taxFlat;
            return p.withDrawMoney(taxFlat);
        }

    @Override
    public String printLandOn(Player p) {
        return p + " landede på et betal skat fælt og betalte " + lastPayed;
    }

    @Override
    public String printPassed(Player p) {
        return "";
    }
}
