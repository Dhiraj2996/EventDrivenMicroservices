package com.microservices.demo.twitter.to.kafka.service;

import com.microservices.demo.config.TwittertoKafkaServiceConfigData;
import com.microservices.demo.twitter.to.kafka.service.init.StreamInitializer;
import com.microservices.demo.twitter.to.kafka.service.runner.StreamRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TwitterToKafkaService implements CommandLineRunner {

    private static final Logger LOGGER=LoggerFactory.getLogger(TwitterToKafkaService.class);

    private final TwittertoKafkaServiceConfigData serviceContextData;
    private final StreamRunner streamRunner;
    private final StreamInitializer streamInitializer;
    public TwitterToKafkaService(TwittertoKafkaServiceConfigData serviceContextData, StreamRunner runner, StreamInitializer streamInitializer){
        this.serviceContextData=serviceContextData;
        this.streamRunner=runner;
        this.streamInitializer = streamInitializer;
    }

    public static void main(String[] args) {
        SpringApplication.run(TwitterToKafkaService.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info(" App starts");
        streamInitializer.init();
        streamRunner.start();
    }
}
