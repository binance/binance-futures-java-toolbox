package um_futures;
import com.binance.connector.client.exceptions.BinanceClientException;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.impl.UMFuturesClientImpl;
import config.PrivateConfig;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.LinkedHashMap;

/**
 * Example on how to place an order, get the order and cancel the order.
 */

public class Order {
    private static final Logger logger = LoggerFactory.getLogger(Order.class);

    //For production environment, uncomment the line below.
    //private static final UMFuturesClientImpl client = new UMFuturesClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
    private static final UMFuturesClientImpl client = new UMFuturesClientImpl(PrivateConfig.TESTNET_API_KEY, PrivateConfig.TESTNET_SECRET_KEY, PrivateConfig.BASE_URL);

    public static int placeOrder() {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BTCUSDT");
        parameters.put("side", "BUY");
        parameters.put("type", "LIMIT");
        parameters.put("timeInForce", "GTC");
        parameters.put("quantity", 0.001);
        parameters.put("price", 10000);

        String result = client.account().newOrder(parameters);
        logger.info(result);

        JSONObject jsonObject = new JSONObject(result);
        parameters.clear();
        return jsonObject.getInt("orderId");
    }

    public static void getOrder(int orderId) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol","BTCUSDT");
        parameters.put("orderId", orderId);

        String result = client.account().queryOrder(parameters);
        logger.info(result);
        parameters.clear();
    }

    public static void cancelOrder(int orderId) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol","BTCUSDT");
        parameters.put("orderId", orderId);

        String result = client.account().cancelOrder(parameters);
        logger.info(result);
        parameters.clear();
    }

    public static void main(String[] args) {

        try {
            int orderId = placeOrder();
            getOrder(orderId);
            cancelOrder(orderId);

        } catch (BinanceConnectorException e) {
            logger.error("fullErrMessage: {}", e.getMessage(), e);
        } catch (BinanceClientException e) {
            logger.error("fullErrMessage: {} \nerrMessage: {} \nerrCode: {} \nHTTPStatusCode: {}",
                    e.getMessage(), e.getErrMsg(), e.getErrorCode(), e.getHttpStatusCode(), e);
        } catch (JSONException err) {
            logger.error(err.toString());
        }
    }
}
