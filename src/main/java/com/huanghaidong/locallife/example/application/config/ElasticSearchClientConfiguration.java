package com.huanghaidong.locallife.example.application.config;

import com.huanghaidong.locallife.example.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

@Configuration
@Slf4j
public class ElasticSearchClientConfiguration {


    @Value("${elasticsearch.clusternodes}")
    private String clusterNodes;




    private RestHighLevelClient restHighLevelClient;

    @Bean
    public RestHighLevelClient restHighLevelClient() {

        RestClientBuilder restClientBuilder = RestClient.builder(localHttpHosts());

        restHighLevelClient = new RestHighLevelClient(restClientBuilder);

        return restHighLevelClient;
    }

    private HttpHost[] localHttpHosts() {

        String[] clusterNodeArray = clusterNodes.split(Constants.COMMA);

        HttpHost[] httpHosts = new HttpHost[clusterNodeArray.length];

        for (int i = 0; i < clusterNodeArray.length; i ++) {

            String[] hostAndPort = clusterNodeArray[i].split(Constants.COLON);

            httpHosts[i] = new HttpHost(hostAndPort[0], Integer.parseInt(hostAndPort[1]));
        }

        return httpHosts;
    }

    @PreDestroy
    public void close() {
        try {
            restHighLevelClient.close();
        } catch (Exception e) {
            log.error("restHighLevelClient close error", e);
        }
    }
}
