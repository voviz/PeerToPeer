import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

class Coin {

        private final String name;
        private final Map<Coin, Float> rates = new HashMap<>();

    public Coin(String name) {
        this.name = name.toUpperCase();

    }

    public String getName() {
        return this.name;
    }

    public void updateBuyPrices() {

    }

    public void initRates(List<Coin> coinList) {
        for (Coin coin : coinList) {
            rates.put(coin, 0f);
        }
    }

    public void updateRates() {
        float rate;
        for (Coin coin : rates.keySet()) {
             if (Objects.equals(coin.name, this.name)) {
                 rates.put(coin, 1f);
                 continue;
             }

             if (Objects.equals(this.name, "USDT")) {
                 rate = Converter.getBinanceRate(coin.name, this.name);
                 rates.put(coin, 1 / rate);
             }
             else if (Objects.equals(coin.name, "USDT")) {
                 rate = Converter.getBinanceRate(this.name, coin.name);
                 rates.put(coin, rate);
             }
             else if (Objects.equals(this.name, "BUSD")) {
                 rate = Converter.getBinanceRate(coin.name, this.name);
                 rates.put(coin, 1 / rate);
             }
             else if (Objects.equals(coin.name, "BUSD")) {
                 rate = Converter.getBinanceRate(this.name, coin.name);
                 rates.put(coin, rate);
             }
             else if (Objects.equals(this.name, "BTC")) {
                 rate = Converter.getBinanceRate(coin.name, this.name);
                 rates.put(coin, 1 / rate);
             }
             else if (Objects.equals(coin.name, "BTC")) {
                 rate = Converter.getBinanceRate(this.name, coin.name);
                 rates.put(coin, rate);
             }
             else if (Objects.equals(this.name, "ETH")) {
                 rate = Converter.getBinanceRate(coin.name, this.name);
                 rates.put(coin, 1 / rate);
             }
             else if (Objects.equals(coin.name, "ETH")) {
                 rate = Converter.getBinanceRate(this.name, coin.name);
                 rates.put(coin, rate);
             }
        }
    }

}
