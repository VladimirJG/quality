package ru.danilov.quality;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class QualityApplication {
    public static void main(String[] args) {
        SpringApplication.run(QualityApplication.class, args);
    }
}
