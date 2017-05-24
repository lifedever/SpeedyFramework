package com.kanasinfo.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by gefangshuai on 2017/5/16.
 */
@Component
@ConfigurationProperties(prefix = "test")
public class TestConfig {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
