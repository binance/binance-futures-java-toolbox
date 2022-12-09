package um_futures;
import com.binance.connector.client.impl.UMFuturesClientImpl;
import com.binance.connector.client.impl.UMWebsocketClientImpl;
import config.PrivateConfig;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Getting listenkey and establing a user data stream.
 */

public class UserDataStream {
    private static final Logger logger = LoggerFactory.getLogger(UserDataStream.class);
    public static void main(String[] args) {
        UMWebsocketClientImpl wsClient = new UMWebsocketClientImpl();
        UMFuturesClientImpl futuresClient = new UMFuturesClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);

        JSONObject obj = new JSONObject(futuresClient.userData().createListenKey());
        String listenKey = obj.getString("listenKey");
        logger.info("listenKey:" + listenKey);

        wsClient.listenUserStream(listenKey, ((event) -> {
            logger.info(event);
        }));
    }
}
