package it.istruzione.template_client_rest.commons.error.exception;

import it.istruzione.template_client_rest.commons.error.enums.ErrorEnum;

/**
 * Errore interni
 */
public abstract class ErrorException extends RuntimeException{
    protected ErrorException() {}

    protected ErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    protected ErrorException(String message) {
        super(message);
    }

    protected ErrorException(Throwable cause) {
        super(cause);
    }

    public ErrorEnum getCodiceErrore() {
        return ErrorEnum.ERRORE_INTERNO;
    }
}
