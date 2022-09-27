import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinanceCoinsClass {

    private static List<Coin> coins;

    private BinanceCoinsClass() {
        Coin USDT = new Coin("USDT");
        Coin BUSD = new Coin("BUSD");
        Coin BTC = new Coin("BTC");
        Coin ETH = new Coin("ETH");
        Coin BNB = new Coin("BNB");
        coins = new ArrayList<>(Arrays.asList(USDT, BUSD, BTC, ETH, BNB));
        for (Coin coin : coins) {
            coin.initRates(coins);
            coin.updateRates();
        }
    }

    public static void initCoins() {
        new BinanceCoinsClass();
    }

    public static void updateRates() {
        for (Coin coin : coins) {
            coin.updateRates();
        }
    }

    public static Float getRate(String, )
}
