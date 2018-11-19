/*package dk.dtu.CDIT_Grp_43_matador.jUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.ChanceCard;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.ChanceCardDeck;
import org.junit.jupiter.api.*;

import java.io.IOException;

public class ChanceCardDeckTests {
    private ChanceCardDeck sut;

    @BeforeEach
    public void setUp() throws Exception {
        sut = new ChanceCardDeck();

        try {
            sut.init();
        } catch (IOException e) {
            fail("Forventede ingen exception, måske mangler ressourcen");
        }
    }

    @Test
    public void GetCardFromDeck() {
        ChanceCard actual = sut.nextCard();

        assertNotNull(actual.getCardType());
        assertNotNull(actual.getCardDescription());
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
}*/