package it.istruzione.template_client_rest.commons.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class SiNoSerializer extends JsonSerializer<Boolean> {
    public void serialize(Boolean bool, JsonGenerator generator, SerializerProvider provider) throws IOException, JsonProcessingException {
        generator.writeString((bool != null && bool.booleanValue()) ? "S" : "N");
    }
}
