package com.flag.xu.website.mongodb;

import com.mongodb.ServerAddress;
import com.mongodb.async.client.*;
import com.mongodb.connection.ClusterSettings;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * mongodb connect pool
 *
 * @author xuj
 * @since 2017-05-02-15:32
 */
public class MongoConnectPool implements AutoCloseable {

    private MongoClient mongoClient;

    private static MongoConnectPool pool = null;

    private MongoConnectPool(String mongodbHosts) {
        this.mongoClient = MongoClients.create(buildMongoClientSettings(mongodbHosts));
    }

    /**
     * 访问数据库database,指定需要访问的database的名称
     * 如果database不存在,mongoDB会在第一次存储数据时创建新的database
     *
     * @param dbName 访问的库名
     * @return 指定database实例
     */
    public MongoDatabase getDatabase(String dbName) {
        return mongoClient.getDatabase(dbName);
    }

    /**
     * 访问collection,指定database名字和collection名字
     * 如果collection不存在,mongoDB会在第一次存储数据时创建新的collection
     *
     * @param dbName database的名字
     * @param collectionName collection的名字
     * @return 指定collection的实例
     */
    public MongoCollection<Document> getCollection(String dbName, String collectionName) {
        return mongoClient.getDatabase(dbName).getCollection(collectionName);
    }

    /**
     * 构建${@link MongoConnectPool}
     *
     * @param mongodbHosts mongoDB部署的集群的hosts,host之间以","隔开
     * @return mongodb连接池实例
     */
    public static MongoConnectPool build(String mongodbHosts) {
        if (pool == null) {
            synchronized (MongoConnectPool.class) {
                if (pool == null) {
                    pool = new MongoConnectPool(mongodbHosts);
                }
            }
        }
        return pool;
    }

    @Override
    public void close() throws Exception {
        mongoClient.close();
        pool = null;
    }

    @NotNull
    private MongoClientSettings buildMongoClientSettings(@NotNull String mongodbHosts) {
        return MongoClientSettings.builder().clusterSettings(buildClusterSettings(mongodbHosts)).build();
    }

    @NotNull
    private ClusterSettings buildClusterSettings(String mongodbHosts) {
        String[] hosts = mongodbHosts.split(",");
        List<ServerAddress> hostAddresses = new ArrayList<>(hosts.length);
        for (String host : hosts) {
            String[] hostAndPort = host.split(":");
            if (hostAndPort.length == 2) {
                hostAddresses.add(new ServerAddress(hostAndPort[0], Integer.valueOf(hostAndPort[1])));
            } else {
                hostAddresses.add(new ServerAddress(host));
            }
        }
        return ClusterSettings.builder().hosts(hostAddresses).build();
    }
}
