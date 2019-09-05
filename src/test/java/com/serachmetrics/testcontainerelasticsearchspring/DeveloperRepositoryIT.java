package com.serachmetrics.testcontainerelasticsearchspring;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.elasticsearch.ElasticsearchContainer;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DeveloperRepositoryIT {

    @ClassRule
    public static ElasticsearchContainer container = new ElasticsearchContainer();

    @Autowired
    private DeveloperRepository repository;

    @BeforeClass
    public static void before() {
        System.setProperty("spring.data.elasticsearch.cluster-name",
                "docker-cluster");
        System.setProperty("spring.data.elasticsearch.cluster-nodes",
                container.getContainerIpAddress() + ":" + container.getMappedPort(9300));
    }

    @Test
    public void testAdd() {
        Developer developer = new Developer("1", "name");
        Developer savedDeveloper = repository.save(developer);
        assertThat(savedDeveloper).isEqualToComparingFieldByField(developer);
    }
}
