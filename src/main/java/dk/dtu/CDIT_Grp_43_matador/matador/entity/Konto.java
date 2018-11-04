package dk.dtu.CDIT_Grp_43_matador.matador.entity;

public class Konto {


    private int initialAmount;

    public Konto(int initialAmount) {
        this.initialAmount = initialAmount;
    }

    @Override
    public String toString() {
        return Integer.toString(initialAmount);
    }

    // Getter and setters

    public int getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(int initialAmount) {
        this.initialAmount = initialAmount;
    }

    public void addMoneyToAccount(int Money){
        initialAmount += Money;
    }

    public String withDrawMoney (int Money){

        if(initialAmount >= Money){
            initialAmount -= Money;
            return Integer.toString(Money) + "withdraw from account balance is now " + Integer.toString(initialAmount);
        }else{
            return "balance to low, your account balance is "+Integer.toString(initialAmount)+" your tride to withdraw "+ Integer.toString(Money);
        }

    }

}
