package dev.mayank.ishproxyservice.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@SuppressWarnings("unused")
public class AppConfig {

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("ts_admin@ish.com", "admin");
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        return restTemplateBuilder.basicAuthentication("ts_admin@ish.com", "admin").build();
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .filter(
                        ExchangeFilterFunctions.basicAuthentication("ts_admin@ish.com", "admin")
                )
                .build();
    }
}
