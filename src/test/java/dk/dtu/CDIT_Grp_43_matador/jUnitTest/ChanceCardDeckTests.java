package dk.dtu.CDIT_Grp_43_matador.jUnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.ChanceCard;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.ChanceCardDeck;

public class ChanceCardDeckTests {
    private ChanceCardDeck sut;

    @BeforeEach
    public void setUp() throws Exception {
        sut = ChanceCardDeck.getInstance();
    }

    @Test
    public void GetCardFromDeck() {
        ChanceCard actual = sut.nextCard();

        assertNotNull(actual);
    }

    @Test
    public void DecCardFromDeck() {
        int startSize = sut.size();
        sut.nextCard();
        int actual = sut.size();

        assertNotEquals(startSize, actual);
    }

    @Test
    public void ReturnCardToDeck() {
        int startSize = sut.size();
        ChanceCard expected = sut.nextCard();
        sut.returnCardToDeck(expected);
        int actual = sut.size();

        assertEquals(startSize, actual);
    }
}