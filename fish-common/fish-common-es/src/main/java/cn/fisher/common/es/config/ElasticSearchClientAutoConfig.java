package cn.fisher.common.es.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fisher
 */
@Configuration
public class ElasticSearchClientAutoConfig {

    @Autowired
    private ElasticsearchProperties elasticsearchProperties;

    @Bean
    public RestHighLevelClient restHighLevelClient(){
        HttpHost[] hosts = elasticsearchProperties.getUris().stream()
                .map(HttpHost::create)
                .toArray(HttpHost[]::new);
        RestClientBuilder restClientBuilder = RestClient.builder(hosts)
                .setCompressionEnabled(true)
                .setHttpClientConfigCallback(new UserNamePasswordCallBack(elasticsearchProperties));
        return new RestHighLevelClient(restClientBuilder);
    }

    private static class UserNamePasswordCallBack implements RestClientBuilder.HttpClientConfigCallback {

        private ElasticsearchProperties elasticsearchProperties;

        public UserNamePasswordCallBack(ElasticsearchProperties elasticsearchProperties) {
            this.elasticsearchProperties = elasticsearchProperties;
        }

        @Override
        public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(
                    elasticsearchProperties.getUsername(), elasticsearchProperties.getPassword()
            );
            //密码
            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, credentials);
            return httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
        }
    }



}
