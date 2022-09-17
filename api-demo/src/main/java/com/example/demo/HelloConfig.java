package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "hello.properties", encoding = "UTF-8")
public class HelloConfig {
    @Bean
    @ConfigurationProperties(prefix = "hello")
    public HelloProperties helloProperties(){
            return new HelloProperties();
    }
}
