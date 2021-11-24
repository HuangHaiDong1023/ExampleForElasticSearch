
package com.huanghaidong.locallife.example.deploy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.huanghaidong.locallife.example.infrastructure.mapper")
@SpringBootApplication(scanBasePackages = "com.huanghaidong.locallife.example")
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
