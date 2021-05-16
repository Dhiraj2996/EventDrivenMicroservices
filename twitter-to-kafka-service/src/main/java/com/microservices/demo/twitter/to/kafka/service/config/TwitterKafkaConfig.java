package com.microservices.demo.twitter.to.kafka.service.config;

import com.microservices.demo.config.TwittertoKafkaServiceConfigData;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = TwittertoKafkaServiceConfigData.class)
public class TwitterKafkaConfig {
}
