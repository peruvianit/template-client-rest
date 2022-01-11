package it.istruzione.template_client_rest.commons.error.exception;

/**
 * Ritorno dei Errore gestiti
 */
public abstract class ErrorTemplateClientRestException extends Exception{
    protected ErrorTemplateClientRestException() {}

    protected ErrorTemplateClientRestException(String message, Throwable cause) {
        super(message, cause);
    }

    protected ErrorTemplateClientRestException(String message) {
        super(message);
    }

    protected ErrorTemplateClientRestException(Throwable cause) {
        super(cause);
    }

    public abstract ErrorTemplateClientRestException getCodiceErrore();
}
