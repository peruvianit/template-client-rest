package it.istruzione.template_client_rest.commons.error.enums;

public enum ErrorEnum implements OperationError{
    ERRORE_INTERNO("TEMPLATE_ERR:ERRORE_INTERNO"),
    ERRORE_APPLICATIVO("TEMPLATE_ERR:ERRORE_APPLICATIVO"),
    RICHIESTA_NON_VALIDA("TEMPLATE_ERR:RICHIESTA_NON_VALIDA"),
    PARAMETRO_ASSENTE("TEMPLATE_ERR:PARAMETRO_ASSENTE"),
    NON_AUTORIZZATO("TEMPLATE_ERR:NON_AUTORIZZATO"),
    ERRORE_MOCK("TEMPLATE_ERR:ERRORE_MOCK");

    private final String codice;

    ErrorEnum(String codice) {
        this.codice = codice;
    }

    public String getCodice() {
        return this.codice;
    }
}
