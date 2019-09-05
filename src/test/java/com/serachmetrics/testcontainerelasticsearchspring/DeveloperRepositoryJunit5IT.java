package com.serachmetrics.testcontainerelasticsearchspring;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
class DeveloperRepositoryJunit5IT {

    @Container
    private static final ElasticsearchContainer CONTAINER = new ElasticsearchContainer();

    @Autowired
    private DeveloperRepository repository;

    @BeforeAll
    static void setUp() {
        System.setProperty("spring.data.elasticsearch.cluster-name",
                "docker-cluster");
        System.setProperty("spring.data.elasticsearch.cluster-nodes",
                CONTAINER.getContainerIpAddress() + ":" + CONTAINER.getMappedPort(9300));
    }

    @Test
    void testAdd() {
        Developer developer = new Developer("1", "name");
        Developer savedDeveloper = repository.save(developer);
        assertThat(savedDeveloper).isEqualToComparingFieldByField(developer);
    }
}
