package it.istruzione.template_client_rest.test;

import it.istruzione.template_client_rest.client.config.TemplateClientRestConfig;
import it.istruzione.template_client_rest.client.services.TemplateServiceClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(classes = { TemplateClientRestConfig.class })
public class ServiceClientTest {

    @Autowired
    TemplateServiceClient templateServiceClient;

    @Test
    public void mockupTest() {
        templateServiceClient.recuperaMockSample();
    }
}
