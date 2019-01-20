package dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;

public class MatadorlegatEffect extends CardEffect {
    private int recieveAmount;
    private int maxValue;

    public MatadorlegatEffect(int recieveAmount, int maxValue) {
        this.recieveAmount = recieveAmount;
        this.maxValue = maxValue;
    }


    @Override
    public boolean useEffect(Player p) {
        if(p.playerFortune() <= maxValue) {
            p.addMoney(recieveAmount);
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + "MatadorlegatEffect";
    }

    public String print(Player p) {
        String accepted = "\t" + p + " du modtager " +  recieveAmount + " kr, da din konto var under " + maxValue + " kr";
        String notAccepted = "\t" + p + " din konto er højere end " + maxValue + " kr og derfor modtager du ingenting";

        return p.playerFortune() <= maxValue ? accepted : notAccepted;
    }
}
