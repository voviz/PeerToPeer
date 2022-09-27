import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCoin {
    Coin usdt = new Coin("usdt");

    @Test
    void addBanksTest() {
        List<String> banks = new ArrayList<>(Arrays.asList("sber", "tink"));

    }

    @Test
    void removeBanksTest() {
        // TODO
    }

    @Test
    void getBanksTest() {
        // TODO
    }
}
