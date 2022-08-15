package com.lxa;

import com.alibaba.fastjson.JSON;
import com.lxa.pojo.User;
import com.lxa.utils.HtmlUtil;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class SpringbootEsJdApplicationTests {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    void contextLoads() throws IOException {
        User user = new User("lxa",25);
        IndexRequest request = new IndexRequest("lxa_index");

        request.id("1");
        request.timeout(TimeValue.timeValueSeconds(1));

        request.source(JSON.toJSONString(user), XContentType.JSON);

        IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        System.out.println(indexResponse.toString());
    }

    @Test
    public void testJD() throws Exception {
        new HtmlUtil().parseJD("java").forEach(System.out::println);
    }

}
