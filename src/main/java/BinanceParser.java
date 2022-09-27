import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class BinanceParser {


    public BinanceParser() {
    }

    public Float getSpreadMaker(String coinBuy, String coinSell, String payMethodIdBuy, String payMethodIdSell, Integer transAmount) {
        String postDataBuy = this.createPostData(coinBuy, payMethodIdBuy, transAmount, "SELL");
        String postDataSell = this.createPostData(coinSell, payMethodIdSell, transAmount, "BUY");

        Float buyPrice = this.getPrice(postDataBuy);
        Float sellPrice = this.getPrice(postDataSell);
        if (Objects.equals(coinBuy, coinSell)) {
            return getSpread(sellPrice, buyPrice);
        }

        Float rate;
        if (Objects.equals(coinSell, "USDT")) {
            rate = Converter.getBinanceRate(coinBuy, coinSell);
            sellPrice *= rate;
        }
        else if (Objects.equals(coinBuy, "USDT")) {
            rate = Converter.getBinanceRate(coinSell, coinBuy);
            buyPrice *= rate;
        }
        else if (Objects.equals(coinSell, "BUSD")) {
            rate = Converter.getBinanceRate(coinBuy, coinSell);
            sellPrice *= rate;
        }
        else if (Objects.equals(coinBuy, "BUSD")) {
            rate = Converter.getBinanceRate(coinSell, coinBuy);
            buyPrice *= rate;
        }
        else if (Objects.equals(coinSell, "BTC")) {
            rate = Converter.getBinanceRate(coinBuy, coinSell);
            sellPrice *= rate;
        }
        else if (Objects.equals(coinBuy, "BTC")) {
            rate = Converter.getBinanceRate(coinSell, coinBuy);
            buyPrice *= rate;
        }
        else if (Objects.equals(coinSell, "ETH")) {
            rate = Converter.getBinanceRate(coinBuy, coinSell);
            sellPrice *= rate;
        }
        else if (Objects.equals(coinBuy, "ETH")) {
            rate = Converter.getBinanceRate(coinSell, coinBuy);
            buyPrice *= rate;
        }
        return getSpread(sellPrice, buyPrice);
    }

    public Float GetBuyPriceMaker(String coinName, String payMethodId, Integer transAmount) {
        String postData = this.createPostData(coinName, payMethodId, transAmount, "SELL");

        return getPrice(postData);
    }

    public Float getSellPriceMaker(String coinName, String payMethodId, Integer transAmount) {
        String postData = this.createPostData(coinName, payMethodId, transAmount, "BUY");

        return this.getPrice(postData);
    }

    private Float getPrice(String data) {
        URI uri;
        try {
            uri = new URI("https://p2p.binance.com/bapi/c2c/v2/friendly/c2c/adv/search");
        } catch(URISyntaxException e) {
            e.printStackTrace();
            return null;
        }

        // Создание клиента и запроса
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .headers("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .build();

        CompletableFuture<String> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);

        // Получение текста ответа
        String responseText;
        try {
            responseText = response.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }

        // Преобразование текста в JSON формат и поиск цены
        ObjectMapper objMapper = new ObjectMapper();
        JsonNode json;
        try {
            json = objMapper.readTree(responseText);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
        return Float.parseFloat(json.path("data").path(0).path("adv").get("price").toString().replaceAll("\"", ""));
    }

    private String createPostData(String coinName, String payMethodId, Integer transAmount, String tradeType) {
        return String.format("{\"proMerchantAds\":false,\"page\":1,\"rows\":10,\"payTypes\":[\"%s\"],\"countries\":[],\"" +
                        "publisherType\":null,\"transAmount\":\"%s\",\"asset\":\"%s\",\"fiat\":\"RUB\",\"tradeType\":\"%s\"}", payMethodId,
                transAmount, coinName, tradeType);
    }

    private Float getSpread(Float sellPrice, Float buyPrice) {
        return (sellPrice - buyPrice) / sellPrice * 100;
    }
}
