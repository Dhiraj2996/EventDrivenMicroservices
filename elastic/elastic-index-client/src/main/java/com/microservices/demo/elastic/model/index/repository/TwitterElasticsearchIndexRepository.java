package com.microservices.demo.elastic.model.index.repository;

import com.microservices.demo.elastic.model.index.impl.TwitterIndexModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TwitterElasticsearchIndexRepository extends ElasticsearchRepository<TwitterIndexModel,String> {
}
