package cm_futures;
import com.binance.connector.client.exceptions.BinanceClientException;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.impl.CMFuturesClientImpl;
import config.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.LinkedHashMap;

public class GetAccount {
    private static final Logger logger = LoggerFactory.getLogger(GetAccount.class);
    public static void main(String[] args) {
        LinkedHashMap<String,Object> parameters = new LinkedHashMap<>();

        CMFuturesClientImpl client = new CMFuturesClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);

        try {
            String result = client.account().accountInformation(parameters);
            logger.info(result);
        }
        catch (BinanceConnectorException e) {
            logger.error("fullErrMessage: {}", e.getMessage(), e);
        }
        catch (BinanceClientException e) {
            logger.error("fullErrMessage: {} \nerrMessage: {} \nerrCode: {} \nHTTPStatusCode: {}",
                    e.getMessage(), e.getErrMsg(), e.getErrorCode(), e.getHttpStatusCode(), e);
        }
    }
}
