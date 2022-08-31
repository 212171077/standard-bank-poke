package za.co.standard.bank.pokemon.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class CommonUtil {

    private static final Logger logger = LogManager.getLogger(CommonUtil.class);

    public Object convertJsonToObject(String json, Object objectClass) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, objectClass.getClass());
        } catch (Exception e) {
            logger.error("Error when converting Json to object: ", e);
            return null;
        }
    }

}
