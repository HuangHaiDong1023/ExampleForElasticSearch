
package com.hellobike.locallife.example.deploy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.hellobike.locallife.example")
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
