package it.istruzione.template_client_rest.commons.config;

import it.istruzione.template_client_rest.commons.error.exception.ErrorException;

public class ConfigurationComponenteException extends ErrorException {

    public ConfigurationComponenteException(String message) {
        super(message);
    }

    public static ConfigurationComponenteException crea(String nomeComponente) {
        return new ConfigurationComponenteException("Fallita configurazione del componente: " + nomeComponente);
    }
}
