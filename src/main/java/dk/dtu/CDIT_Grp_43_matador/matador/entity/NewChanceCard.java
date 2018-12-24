package dk.dtu.CDIT_Grp_43_matador.matador.entity;

import java.util.ArrayList;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects.*;

public class NewChanceCard {
	private ArrayList<CardEffect> cardEffects;
	private boolean keepCard;
	
	public NewChanceCard(ArrayList<CardEffect> cardEffects) {
		this.cardEffects = cardEffects;
		for (CardEffect cardEffect : cardEffects) {
			if (cardEffect.isKeepEffect())
				keepCard = true;
		}
	}
	
	public boolean useCard(Player p) {
		for (CardEffect cardEffect : cardEffects) {
			if (!cardEffect.useEffect(p))
				return false;
		}
		return true;
	}
	
	public boolean isKeepCard() {
		return keepCard;
	}
}
