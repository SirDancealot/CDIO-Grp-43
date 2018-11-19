package dk.dtu.CDIT_Grp_43_matador.matador.entity;

public class ChanceCard {
    private final String value;
    private boolean moveTo = false;
    private String moveToTag;

    private boolean move = false;
    private int moveAmt;

    private boolean money = false;
    private int moneyAmt;

    private boolean payAll = false;
    private int payAllAmt;

    private boolean keep = false;
    private boolean freeJail = false;

    public ChanceCard(String value) {
        String[] tags = value.split(";");
        for (String tag : tags) {
            String[] tagValues = tag.split(":");
            switch (tagValues[0] ) {
                case "moveTo" :
                    moveTo = true;
                    moveToTag = tagValues[1];
                    break;
                case "move" :
                    move = true;
                    moveAmt = Integer.valueOf(tagValues[1]);
                    break;
                case "money" :
                    money = true;
                    moneyAmt = Integer.valueOf(tagValues[1]);
                    break;
                case "payAll" :
                    payAll = true;
                    payAllAmt = Integer.valueOf(tagValues[1]);
                    break;
                case "keep" :
                    keep = true;
                    break;
                case "freeJail" :
                    freeJail = true;
                    break;
                default :
                    break;
            }
        }
        this.value = value;
    }
    public boolean useCard( ) {

}

    public String getCardDescription() {
        return value;
    }
}