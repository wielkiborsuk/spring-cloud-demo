package com.norasoft.generator;

import feign.Request;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.HttpStatus;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AsciiGeneratorApplication implements EmbeddedServletContainerCustomizer {

    public static void main(String[] args) {
        SpringApplication.run(AsciiGeneratorApplication.class, args);
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.addErrorPages( new ErrorPage(HttpStatus.NOT_FOUND, "/404.html" ) );
    }

    @Bean
    Request.Options requestOptions(ConfigurableEnvironment env){
      int ribbonReadTimeout = env.getProperty("ribbon.ReadTimeout", int.class, 60000);
      int ribbonConnectionTimeout = env.getProperty("ribbon.ConnectTimeout", int.class, 30000);

      return new Request.Options(ribbonConnectionTimeout, ribbonReadTimeout);
    }
}
