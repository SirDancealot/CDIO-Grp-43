package dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.ChanceCard;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;

public class ChanceCardDeck {
    private ChanceCard[] cards;

    public void pullCard(Player p) {
        useTopCard(Player p);
        putCardInBottom();
    }

    private void putCardInBottom() {

    }
}
