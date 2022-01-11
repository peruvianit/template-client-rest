package it.istruzione.template_client_rest.client.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import it.istruzione.template_client_rest.client.services.TemplateServiceClient;
import it.istruzione.template_client_rest.commons.json.SiNoDeserializer;
import it.istruzione.template_client_rest.commons.json.SiNoSerializer;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.TimeZone;

@Configuration
public class TemplateClientRestConfig {

    @Bean(name = {"templateClientRest"})
    public TemplateServiceClient getTemplateServiceClient(@Value("${template-client.services.client.proxy.enable}") Boolean proxyEnable,
                                                             @Value("${template-client.services.client.proxy.host}") String proxyHost,
                                                             @Value("${template-client.services.client.proxy.port}") Integer proxyPort,
                                                             @Value("${template-client.services.client.url}") String serviceBaseUrl,
                                                             @Value("${template-client.services.client.httpClient.connections}") Integer httpClientConnections,
                                                             @Value("${template-client.services.client.httpClient.connectionTimeout}") Integer httpClientConnectionTimeout,
                                                             @Value("${template-client.services.client.httpClient.readTimeout}") Integer httpClientReadTimeout,
                                                             @Value("${template-client.services.client.username}") String username,
                                                             @Value("${template-client.services.client.password}") String password,
                                                             @Value("${template-client.services.client.codice}") String codiceClient) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setTimeZone(TimeZone.getDefault());
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        SimpleModule module = new SimpleModule("Boolean");
        module = module.addDeserializer(Boolean.class, (JsonDeserializer) new SiNoDeserializer());
        module = module.addSerializer(Boolean.class, (JsonSerializer) new SiNoSerializer());
        objectMapper.registerModule((Module)module);

        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
        connManager.setMaxTotal(httpClientConnections.intValue());
        connManager.setDefaultMaxPerRoute(httpClientConnections.intValue());
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create().setConnectionManager((HttpClientConnectionManager) connManager);

        if (proxyEnable.booleanValue())
            httpClientBuilder.setProxy(new HttpHost(proxyHost, proxyPort.intValue()));

        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory((HttpClient) closeableHttpClient);
        factory.setReadTimeout(httpClientReadTimeout.intValue());
        factory.setConnectionRequestTimeout(httpClientConnectionTimeout.intValue());
        factory.setConnectTimeout(httpClientConnectionTimeout.intValue());

        RestTemplate restTemplate = new RestTemplate((ClientHttpRequestFactory) factory);
        for (HttpMessageConverter<?> converter : (Iterable<HttpMessageConverter<?>>)restTemplate.getMessageConverters()) {
            if (converter instanceof MappingJackson2HttpMessageConverter)
                ((MappingJackson2HttpMessageConverter)converter).setObjectMapper(objectMapper);
        }
        TemplateServiceClient templateServiceClient = new TemplateServiceClient(restTemplate, objectMapper, serviceBaseUrl, username, password, codiceClient);
        return templateServiceClient;
    }

}
