package com.flag.xu.website.repository;

import com.flag.xu.website.mongodb.MongoConnectPool;
import com.mongodb.async.client.MongoCollection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;

/**
 * @author xuj
 * @since 2017-05-02-17:16
 */
@Repository
public class TestMongoDbRepository {

    private static final Logger LOG = LogManager.getLogger(TestMongoDbRepository.class);
    private MongoConnectPool pool;

    @Autowired
    public TestMongoDbRepository(MongoConnectPool pool) {
        this.pool = pool;
    }

    public void insert() {
        MongoCollection<Document> collection = pool.getCollection("test", "hello");

        Document doc = new Document("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
                .append("info", new Document("x", 203).append("y", 102));
        collection.insertOne(doc, (result, t) -> LOG.info("Inserted! --- {} --- {}", result, t));
    }
}
