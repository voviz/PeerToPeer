import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public enum BinanceCoins {
    ETH("ETH"),
    BTC("BTC"),
    USDT("USDT"),
    BUSD("BUSD"),
    BNB("BNB");

    private final String coinName;

    BinanceCoins(String name) {
        this.coinName = name;
    }

    public String getAbb() {
        return this.coinName;
    }
}
