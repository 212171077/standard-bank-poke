package za.co.standard.bank.pokemon.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class PokemonClient {

    private static final Logger logger = LogManager.getLogger(PokemonClient.class);

    public String callGetMethod(String strUrl) {

        String json = null;
        try {
            logger.debug("Calling Get Request [URL: {}]", strUrl);
            URL url = new URL(strUrl);
            String readLine;
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            logger.debug("GET Response [URL: {}, Code: {}, Message: {}]", strUrl, responseCode,
                connection.getResponseMessage());

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
                StringBuffer stringBuffer = new StringBuffer();
                while ((readLine = in.readLine()) != null) {
                    stringBuffer.append(readLine);
                }

                in.close();

                json = stringBuffer.toString();
                logger.info("URL: {}, JSON Response: {}", strUrl, json);
            }
        } catch (Exception e) {
            logger.error("ERROR: ", e);
        }

        return json;

    }


}