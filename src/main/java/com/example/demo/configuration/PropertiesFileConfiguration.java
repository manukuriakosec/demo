package com.example.demo.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "loan")
@Getter
@Setter
public class PropertiesFileConfiguration {
    private int minAge;
    private int maxAge;
    private int cibilScore;
    private int income;
}
