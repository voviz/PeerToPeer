import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

public class Converter {


    static Float getBinanceRate(String symbol1, String symbol2) {
        String url = String.format("https://api.binance.com/api/v3/avgPrice?symbol=%s%s", symbol1, symbol2);
        JsonNode json;
        try {
            URLConnection connection = new URL(url).openConnection();
            InputStream response = connection.getInputStream();
            String res =  new BufferedReader(new InputStreamReader(response))
                    .lines().collect(Collectors.joining());
            ObjectMapper objMapper = new ObjectMapper();
            json = objMapper.readTree(res);
        } catch (IOException e) {
            e.printStackTrace();
            return 0f;
        }

        return Float.parseFloat(json.get("price").asText());
    }
}
