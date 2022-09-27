
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class App {
    public static void main(String[] args) {
        BinanceCoinsClass.initCoins();
        new App().run();
    }

    private void run() {

        List<String> coins = new ArrayList<>(Arrays.asList("USDT", "BTC", "ETH", "BNB", "BUSD"));

        Map<String,  Float> usdtRates = getRates("USDT", coins);

//        for (Map.Entry<String, Float> item : usdtRates.entrySet()) {
//            System.out.printf("USDT to %s, price - %s\n", item.getKey(), item.getValue());
//        }

        BinanceParser bp = new BinanceParser();

        List<String> coinsList = Stream.of(BinanceCoins.values()).map(BinanceCoins::getAbb).toList();
        List<String> banksList = Stream.of(BinancePayMethods.values()).map(BinancePayMethods::getPayId).toList();
        List<Map<String, String>> resSpreads =  new ArrayList<>();
//        for (String bankBuy : banksList) {
//            for (String bankSell : banksList) {
//                for (String coinBuy : coinsList) {
//                    for (String coinSell : coinsList) {
//                        resSpreads.add(new HashMap<>() {{
//                            put("coinBuy", null);
//                            put
//                        }})
//                    }
//                }
//            }
//        }



        List<Thread> threadList = new ArrayList<>();
        for (String bankBuy : banksList) {
            for (String bankSell : banksList) {
                for (String coinBuy : coinsList) {
                    for(String coinSell : coinsList) {
                        Float spread;
                        Runnable runnable = () -> resSpreads.add(new HashMap<>() {{
                            put("coinBuy", coinBuy);
                            put("coinSell", coinSell);
                            put("bankBuy", bankBuy);
                            put("bankSell", bankSell);
                            put("spread", Float.toString(bp.getSpreadMaker(coinBuy, coinSell, bankBuy, bankSell, 0)));
                        }});
                        Thread thread = new Thread(runnable);
                        thread.start();
                        threadList.add(thread);
                    }
                }
            }
        }

        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(resSpreads.isEmpty());
        for (Map<String, String> item : resSpreads) {
            System.out.println(String.format("%s %s %s %s %s", item.get("coinBuy"), item.get("coinSell"), item.get("bankBuy"),
                    item.get("bankSell"), item.get("spread")));
        }
    }


    public Map<String, Float> getRates(String coin, List<String> coins) {
        Map<String, Float> result = new HashMap<>();

        for (String coin2 : coins) {
            if (coin2.equals(coin)) continue;

            result.put(coin2, Converter.getBinanceRate(coin2, coin));
            }
        return result;
    }

}
