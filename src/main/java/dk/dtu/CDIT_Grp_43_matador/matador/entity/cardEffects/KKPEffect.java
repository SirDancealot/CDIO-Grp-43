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
    public String print(Player p) {
        String noHousesAndHotels = "\t" + p + " siden du ikke har nogen huse eller hoteller, betaler du ingenting";
        String eitherOr = "\t" + p + " siden du har enten huse eller hoteller, betaler du " + houseNHotelPrice + "kr";
        return houseNHotelPrice == 0 ? noHousesAndHotels : eitherOr;
    }
}
