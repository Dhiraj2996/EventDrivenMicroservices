package com.microservices.demo.twitter.to.kafka.service.runner.impl;

import com.microservices.demo.config.TwittertoKafkaServiceConfigData;
import com.microservices.demo.twitter.to.kafka.service.listener.TwitterListener;
import com.microservices.demo.twitter.to.kafka.service.runner.StreamRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import twitter4j.FilterQuery;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import javax.annotation.PreDestroy;
import java.util.Arrays;

@Component
@ConditionalOnProperty(name = "twitter-to-kafka-service.enable-mock-tweets", havingValue = "false")
public class TwitterKafkaStreamRunner implements StreamRunner {
    private static final Logger LOGGER= LoggerFactory.getLogger(TwitterKafkaStreamRunner.class);

    private TwittertoKafkaServiceConfigData twittertoKafkaServiceConfigData;
    private TwitterListener twitterListener;

    private TwitterStream stream;

    public TwitterKafkaStreamRunner(TwittertoKafkaServiceConfigData configData,
                                    TwitterListener listener) {
        this.twittertoKafkaServiceConfigData = configData;
        this.twitterListener = listener;
    }

    @Override
    public void start() throws TwitterException {
        stream= new TwitterStreamFactory().getInstance();
        stream.addListener(twitterListener);
        addFilter();

    }

    @PreDestroy
    public void shutDown(){
        if(stream!=null){
            LOGGER.info("Closing stream");
            stream.shutdown();
        }
    }

    private void addFilter() {
        String[] keywords = twittertoKafkaServiceConfigData.getTwitterKeywords().toArray(new String[0]);
        FilterQuery filterQuery=new FilterQuery(keywords);
        stream.filter(filterQuery);
        LOGGER.info("Filter added to stream :{}", Arrays.toString(keywords));
    }
}
