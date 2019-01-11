package dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;

public class Tax extends Tile {
    private int taxFlat;
    private int taxPercent = 0;
    private final String[] options = {"Betal 10% af formue", "Betal 200 kr"};

    public Tax(String tileName, String tileinfo, int tileIndex) {
        super(tileName, tileinfo, tileIndex);
        String[] tileInfo = tileinfo.split(";");
        for (String string : tileInfo) {
            String[] split = string.split(":");
            switch (split[0]) {
                case "money":
                    this.taxFlat = Integer.valueOf(split[1]);
                case "percent":
                    this.taxPercent = Integer.valueOf(split[1]);
            }
        }
    }

        @Override
        public boolean landOnTile (Player p){
        if(taxPercent != 0){
            String choice = logic.getChoice("Betal skat", options);
            if (choice.equals("Betal 10% af formue"))
                return p.withDrawMoney((int)((taxPercent/100.0)*p.playerFortune()));
            }
        return p.withDrawMoney(taxFlat);
        }
    }
