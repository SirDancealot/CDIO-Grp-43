package dk.dtu.CDIT_Grp_43_matador.matador.entity;

public class AuctionHouse {

    private final AuctionHouse INSTANCE = new AuctionHouse();

    private AuctionHouse() {}

    public AuctionHouse getInstance() {
        return INSTANCE;
    }

    private int housesInGame;
    private int hotelsInGame;

    

}
