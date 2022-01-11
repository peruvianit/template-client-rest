package it.istruzione.template_client_rest.client.endpoints;

import java.net.URI;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class RecuperaMockSampleEndpoint {
    public static final String SERVICE_PATH = "/mock";

    private UriComponents uriComponents;

    private RecuperaMockSampleEndpoint(UriComponents uriComponents) {
        this.uriComponents = uriComponents;
    }

    public static RecuperaMockSampleEndpoint crea(String baseUrl) {
        try {
            UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(baseUrl + "/mock").build();
            return new RecuperaMockSampleEndpoint(uriComponents);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Errore nella creazione dell'endpont del servizio: /mock", ex);
        }
    }

    public URI getUri() {
        return this.uriComponents.encode().toUri();
    }
}
