package it.istruzione.template_client_rest.client.exception;

import it.istruzione.template_client_rest.commons.error.ErroreResponse;
import org.springframework.web.client.ResourceAccessException;

public class TemplateServiceClientException extends RuntimeException {
    private static final long serialVersionUID = 5708981120890812995L;

    public TemplateServiceClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public TemplateServiceClientException(String message) {
        super(message);
    }

    public static TemplateServiceClientException serverErrorNotHandled(ErroreResponse erroreResponse) {
        return new TemplateServiceClientException("Errore server non gestita dal client: " + erroreResponse);
    }

    public static TemplateServiceClientException serverResponseError(Exception cause) {
        return new TemplateServiceClientException("Errore di risposta del server", cause);
    }

    public static TemplateServiceClientException connectionError(ResourceAccessException cause) {
        return new TemplateServiceClientException("Errore nella connessione verso il servizio", (Throwable)cause);
    }

    public static TemplateServiceClientException unauthorized() {
        return new TemplateServiceClientException("Autorizzazione negata verso il servizio");
    }
}