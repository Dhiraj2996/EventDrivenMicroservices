package com.microservices.demo.elastic.model.index.util;

import com.microservices.demo.elastic.model.index.IndexModel;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ElasticIndexUtil<T extends IndexModel> {
    public List<IndexQuery> getIndexQueries(List<T> documents) {
        return documents.stream()
                .map(doc -> new IndexQueryBuilder()
                        .withId(doc.getId())
                        .withObject(doc)
                        .build()
                ).collect(Collectors.toList());
    }
}
