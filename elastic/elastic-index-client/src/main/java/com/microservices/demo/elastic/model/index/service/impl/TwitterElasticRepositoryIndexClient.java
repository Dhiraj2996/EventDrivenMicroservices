package com.microservices.demo.elastic.model.index.service.impl;

import com.microservices.demo.elastic.model.index.impl.TwitterIndexModel;
import com.microservices.demo.elastic.model.index.repository.TwitterElasticsearchIndexRepository;
import com.microservices.demo.elastic.model.index.service.ElasticIndexClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(prefix = "elastic-config",name = "is-repository",havingValue = "true",matchIfMissing = true)
public class TwitterElasticRepositoryIndexClient implements ElasticIndexClient<TwitterIndexModel> {
    private static final Logger LOG= LoggerFactory.getLogger(TwitterElasticRepositoryIndexClient.class);
    private final TwitterElasticsearchIndexRepository twitterElasticsearchIndexRepository;

    public TwitterElasticRepositoryIndexClient(TwitterElasticsearchIndexRepository twitterElasticsearchIndexRepository) {
        this.twitterElasticsearchIndexRepository = twitterElasticsearchIndexRepository;
    }

    @Override
    public List<String> save(List<TwitterIndexModel> documents) {
        List<TwitterIndexModel> repoResponse =(List<TwitterIndexModel> ) twitterElasticsearchIndexRepository.saveAll(documents);
        List<String> documentIds = repoResponse.stream().map(TwitterIndexModel::getId).collect(Collectors.toList());
        LOG.info("Documents indexed successfully with type: {} and ids: {}", TwitterIndexModel.class.getName(),
                documentIds);
        return documentIds;
    }
}
