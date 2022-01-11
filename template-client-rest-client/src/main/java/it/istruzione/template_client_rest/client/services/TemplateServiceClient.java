package it.istruzione.template_client_rest.client.services;

import it.istruzione.template_client_rest.client.endpoints.RecuperaMockSampleEndpoint;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;

import it.istruzione.template_client_rest.client.exception.TemplateServiceClientException;
import it.istruzione.template_client_rest.commons.error.ErroreResponse;
import org.apache.commons.codec.binary.Base64;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TemplateServiceClient {
    private RestTemplate restTemplate;

    private ObjectMapper objectMapper;

    private RecuperaMockSampleEndpoint recuperaMockSampleEndpoint;

    private String codiceClient;

    private String basicHeaderString;

    public TemplateServiceClient(RestTemplate restTemplate, ObjectMapper objectMapper, String baseUrl, String username, String password, String codiceClient) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.codiceClient = codiceClient;
        initializeEndpoints(baseUrl);
        initializeBasicHeaderString(username, password);
    }

    public String recuperaMockSample() {
        URI uri = this.recuperaMockSampleEndpoint.getUri();
        try {
            return this.restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity((MultiValueMap)createHeaders()), new ParameterizedTypeReference<String>() {

            }).getBody();
        } catch (HttpStatusCodeException ex) {
            if (ex.getStatusCode() == HttpStatus.UNAUTHORIZED)
                throw TemplateServiceClientException.unauthorized();
            String responseBody = ex.getResponseBodyAsString();
            try {
                ErroreResponse erroreResponse = (ErroreResponse)this.objectMapper.readValue(responseBody, ErroreResponse.class);

                throw TemplateServiceClientException.serverErrorNotHandled(erroreResponse);
            } catch (IOException ioEx) {
                throw TemplateServiceClientException.serverResponseError(ioEx);
            }
        } catch (ResourceAccessException ex) {
            throw TemplateServiceClientException.connectionError(ex);
        }
    }

    private void initializeEndpoints(String baseUrl) {
        this.recuperaMockSampleEndpoint = RecuperaMockSampleEndpoint.crea(baseUrl);
    }

    private void initializeBasicHeaderString(String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        this.basicHeaderString = "Basic " + new String(encodedAuth);
    }

    private HttpHeaders createHeaders() {
        return createHeaders(this.codiceClient);
    }

    private HttpHeaders createHeaders(String codiceUtente) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", this.basicHeaderString);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("programma", this.codiceClient);
        httpHeaders.add("utente", codiceUtente);
        return httpHeaders;
    }
}
