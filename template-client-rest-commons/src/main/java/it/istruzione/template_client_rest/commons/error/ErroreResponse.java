package it.istruzione.template_client_rest.commons.error;

import it.istruzione.template_client_rest.commons.error.enums.ErrorEnum;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.http.HttpStatus;

import java.time.Instant;

/**
 * Ritorno dell'informazione wrapata del errore
 */
public class ErroreResponse {
    private long timestamp;

    private ErrorEnum codice;

    private String errore;

    private String dettaglio;

    private Integer status;

    protected ErroreResponse() {}

    public ErroreResponse(ErrorEnum codice, String errore, String dettaglio, HttpStatus status) {
        this.timestamp = Instant.now().toEpochMilli();
        this.codice = codice;
        this.errore = errore;
        this.dettaglio = dettaglio;
        this.status = Integer.valueOf(status.value());
    }

    public ErrorEnum getCodice() {
        return this.codice;
    }

    public String getErrore() {
        return this.errore;
    }

    public String getDettaglio() {
        return this.dettaglio;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public Integer getStatus() {
        return this.status;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
