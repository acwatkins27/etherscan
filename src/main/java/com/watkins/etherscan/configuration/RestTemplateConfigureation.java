package com.watkins.etherscan.configuration;

import lombok.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfigureation {

    @Bean
    public RestTemplate restTemplate() {
        int httpConnectionTimeoutInSeconds = 30;
        int httpReadTimeoutInSeconds = 30;
        return new RestTemplateBuilder().setConnectTimeout(Duration.ofSeconds(httpConnectionTimeoutInSeconds))
                .setReadTimeout(Duration.ofSeconds(httpReadTimeoutInSeconds)).build();
    }
}
