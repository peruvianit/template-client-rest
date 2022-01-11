package it.istruzione.template_client_rest.commons.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public class SiNoDeserializer extends JsonDeserializer<Boolean> {
    public Boolean deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
        return Boolean.valueOf(!"N".equals(parser.getText()));
    }

    public Boolean getNullValue() {
        return Boolean.FALSE;
    }
}
