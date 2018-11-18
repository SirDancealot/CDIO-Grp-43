package dk.dtu.CDIT_Grp_43_matador.matador.entity;

public class ChanceCard {
    private final String key;
    private final String value;

    public ChanceCard(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getCardType() {
        return key;
    }

    public String getCardDescription() {
        return value;
    }
}