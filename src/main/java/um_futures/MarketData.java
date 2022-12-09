package um_futures;
import com.binance.connector.client.impl.UMFuturesClientImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.LinkedHashMap;

/**
 * Example on how to get market data - klines, aggTrades and trades.
 */

public class MarketData {
    private static final Logger logger = LoggerFactory.getLogger(MarketData.class);
    private static final UMFuturesClientImpl client = new UMFuturesClientImpl();

    public static void getKlines() {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol","BNBUSDT");
        parameters.put("interval", "1m");

        String result = client.market().klines(parameters);
        logger.info(result);
    }

    public static void getAggTrades() {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol","BNBUSDT");

        String result = client.market().aggTrades(parameters);
        logger.info(result);
    }

    public static void getTrades() {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol","BNBUSDT");

        String result = client.market().trades(parameters);
        logger.info(result);
    }

    public static void main(String[] args) {
        getKlines();
        getAggTrades();
        getTrades();
    }
}
