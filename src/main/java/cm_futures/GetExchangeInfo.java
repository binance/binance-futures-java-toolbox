package cm_futures;
import com.binance.connector.client.impl.CMFuturesClientImpl;
import config.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetExchangeInfo {
    private static final Logger logger = LoggerFactory.getLogger(GetExchangeInfo.class);
    public static void main(String[] args) {
        CMFuturesClientImpl client = new CMFuturesClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);

        String result = client.market().exchangeInfo();
        logger.info(result);
    }
}