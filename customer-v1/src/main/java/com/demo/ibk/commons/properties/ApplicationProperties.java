package com.demo.ibk.commons.properties;

import java.util.Map;
import java.util.Optional;

import com.demo.ibk.commons.errors.exceptions.NoSuchRestClientException;
import com.demo.ibk.commons.properties.base.restclient.RestClient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "configuration")
public class ApplicationProperties {

  private Map<String, String> errorMessages;

  private Map<String, RestClient> restClients;

  public String searchEndpoint(String serviceName) {
    return searchRestClient(serviceName).getEndpoint();
  }

  private RestClient searchRestClient(String serviceName) {
    return Optional.ofNullable(restClients.get(serviceName))
        .orElseThrow(NoSuchRestClientException::new);
  }
}
