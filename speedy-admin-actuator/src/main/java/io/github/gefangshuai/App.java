package io.github.gefangshuai;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableAdminServer
public class App extends SpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
