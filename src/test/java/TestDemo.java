import com.alibaba.fastjson.JSONObject;
import com.hellobike.locallife.example.deploy.Application;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class TestDemo {

    @Resource
    private RestHighLevelClient client;

    private static final String INDEX_NAME = "shop_by_api";

    @Test
    public void testCreateIndex() throws IOException {

        //创建一个index
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(INDEX_NAME);

        createIndexRequest.settings(Settings.builder()
                                    .put("index.number_of_shards", 5)
                                    .put("index.number_of_replicas", 0));

        CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);

        log.info("create index : {}, response : {}", INDEX_NAME, createIndexResponse.isAcknowledged());
    }

    @Test
    public void testDeleteIndex() throws IOException {

        //删除一个index
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(INDEX_NAME);

        AcknowledgedResponse acknowledgedResponse = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);

        log.info("acknowledgedResponse : {}", acknowledgedResponse.isAcknowledged());
    }

    @Test
    public void testIsIndexExist() throws IOException {

        //查询指定index是否存在
        GetIndexRequest getIndexRequest = new GetIndexRequest(INDEX_NAME);

        boolean exist = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);

        log.info("exist : {}", exist);
    }

    @Test
    public void testInsert() throws IOException {

        //插入数据
        IndexRequest indexRequest = new IndexRequest(INDEX_NAME);
        indexRequest.id("1");

        JSONObject shop = new JSONObject();
        shop.put("shopId", 1);
        shop.put("shopName", "哈啰生活门店1");
        shop.put("merchantId", "20012421");

        indexRequest.source(shop.toJSONString(), XContentType.JSON);

        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);

        log.info("indexResponse status : {}", indexResponse.status());
    }

    @Test
    public void testUpdate() throws IOException {

        UpdateRequest updateRequest = new UpdateRequest(INDEX_NAME, "1");

        JSONObject shop = new JSONObject();
        shop.put("shopId", 1);
        shop.put("shopName", "哈啰生活门店2");
        shop.put("merchantId", "20012421");

        updateRequest.doc(shop.toJSONString(), XContentType.JSON);

        UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);

        log.info("updateResponse : {}", updateResponse.status());
    }

    @Test
    public void testSearchIndex() throws IOException {

        //针对指定index，查询id为1的数据
        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);

        searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.termQuery("shopId", 1)));

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits searchHits = searchResponse.getHits();
        log.info("searchHits : {}", searchHits.getTotalHits().value);

        String sourceString = searchHits.getHits()[0].getSourceAsString();
        log.info("sourceString : {}", sourceString);

    }
}
