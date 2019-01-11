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

    public String printEffect(Player p) {
        String accepted = "\t" + p + " You recieve" +  recieveAmount + " kr, because your balance we're under" + maxValue + " kr";
        String notAccepted = "\t" + p + " Your balance is higher than" + maxValue + " kr and therefore receive nothing";

        return p.playerFortune() <= maxValue ? accepted : notAccepted;
    }
}
