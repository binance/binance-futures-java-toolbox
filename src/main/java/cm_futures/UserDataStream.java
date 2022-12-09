package cm_futures;
import com.binance.connector.client.impl.CMFuturesClientImpl;
import com.binance.connector.client.impl.CMWebsocketClientImpl;
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
        CMWebsocketClientImpl wsClient = new CMWebsocketClientImpl();
        CMFuturesClientImpl futuresClient = new CMFuturesClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);

        JSONObject obj = new JSONObject(futuresClient.userData().createListenKey());
        String listenKey = obj.getString("listenKey");
        logger.info("listenKey:" + listenKey);

        wsClient.listenUserStream(listenKey, ((event) -> {
            logger.info(event);
        }));
    }
}
