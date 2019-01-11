package dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;

public class KKPEffect extends CardEffect {
    private int houseFee;
    private int hotelFee;
    private int houseNHotelPrice;

    public KKPEffect(int houseFee, int hotelFee) {
        this.houseFee = houseFee;
        this.hotelFee = hotelFee;
    }

    @Override
    public boolean useEffect(Player p) {
        int[] houseAndHotel = p.getHouseAndHotelsOwned();
        int housePrice = houseAndHotel[0] * houseFee;
        int hotelPrice = houseAndHotel[1] * hotelFee;
        houseNHotelPrice = housePrice + hotelPrice;
        return p.withDrawMoney(houseNHotelPrice);
    }

    @Override
    public String toString() {
        return super.toString() + "KKPEffect";
    }

    @Override
    public String printEffect(Player p) {
        String noHousesAndHotels = "\t" + p + " since you have no houses or hotels, you pay nothing";
        String eitherOr = "\t" + p + " since you have either a hotel or houses, you pay" + houseNHotelPrice;
        return houseNHotelPrice == 0 ? noHousesAndHotels : eitherOr;
    }
}
