package com.serachmetrics.testcontainerelasticsearchspring;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@AllArgsConstructor
@Document(indexName = "developer")
public class Developer {
    @Id
    private String id;
    private String name;
}