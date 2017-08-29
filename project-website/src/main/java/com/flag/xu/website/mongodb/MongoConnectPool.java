package com.flag.xu.website.mongodb;

import com.mongodb.ServerAddress;
import com.mongodb.async.client.*;
import com.mongodb.connection.ClusterSettings;
import com.mongodb.connection.ConnectionPoolSettings;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * mongodb connect pool
 *
 * @author xuj
 * @since 2017-05-02-15:32
 */
public class MongoConnectPool implements AutoCloseable {

    private MongoClient mongoClient;

    private static MongoConnectPool pool = null;

    private String mongodbHosts;

    /**
     * 连接维护工作运行周期
     */
    private long maintenanceFrequency = 60 * 1000;

    /**
     * 第一次维护工作运行延迟
     */
    private long maintenanceInitialDelay;

    /**
     * 连接最大空闲时间
     */
    private long maxConnectionIdleTime;

    /**
     * 连接最大存活时间
     */
    private long maxConnectionLifeTime;

    /**
     * 最大连接数
     */
    private int maxSize = 100;

    /**
     * 最大等待连接数
     */
    private int maxWaitQueueSize = 512;

    /**
     * 最大等待时间
     */
    private long maxWaitTime = 2 * 60 * 1000;

    /**
     * 最小连接数
     */
    private int minSize;

    private ConnectionPoolSettings connectionPoolSettings;

    private MongoConnectPool(String mongodbHosts) {
        this.mongodbHosts = mongodbHosts;
    }

    /**
     * 访问数据库database,指定需要访问的database的名称
     * 如果database不存在,mongoDB会在第一次存储数据时创建新的database
     *
     * @param dbName 访问的库名
     * @return 指定database实例
     */
    public MongoDatabase getDatabase(String dbName) {
        return getMongoClient().getDatabase(dbName);
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
        return getMongoClient().getDatabase(dbName).getCollection(collectionName);
    }

    /**
     * 构建${@link MongoConnectPool}
     *
     * @param mongodbHosts mongoDB部署的集群的hosts,host之间以","隔开
     * @return mongodb连接池实例
     */
    public static MongoConnectPool getInstance(String mongodbHosts) {
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

    private MongoClient getMongoClient() {
        if (mongoClient == null) {
            synchronized (MongoConnectPool.class) {
                if (mongoClient == null) {
                    mongoClient = MongoClients.create(buildMongoClientSettings(mongodbHosts));
                }
            }
        }
        return mongoClient;
    }

    @NotNull
    private MongoClientSettings buildMongoClientSettings(@NotNull String mongodbHosts) {
        return MongoClientSettings.builder()
                .clusterSettings(buildClusterSettings(mongodbHosts))
                .connectionPoolSettings(buildConnectionPoolSettings())
                .build();
    }

    private ConnectionPoolSettings buildConnectionPoolSettings() {
        return ConnectionPoolSettings.builder()
                .maintenanceFrequency(maintenanceFrequency, TimeUnit.MILLISECONDS)
                .maintenanceInitialDelay(maintenanceInitialDelay, TimeUnit.MILLISECONDS)
                .maxConnectionIdleTime(maxConnectionIdleTime, TimeUnit.MILLISECONDS)
                .maxConnectionLifeTime(maxConnectionLifeTime, TimeUnit.MILLISECONDS)
                .maxSize(maxSize)
                .maxWaitQueueSize(maxWaitQueueSize)
                .maxWaitTime(maxWaitTime, TimeUnit.MILLISECONDS)
                .minSize(minSize)
                .build();
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

    public long getMaintenanceFrequency() {
        return maintenanceFrequency;
    }

    public void setMaintenanceFrequency(long maintenanceFrequency) {
        this.maintenanceFrequency = maintenanceFrequency;
    }

    public long getMaintenanceInitialDelay() {
        return maintenanceInitialDelay;
    }

    public void setMaintenanceInitialDelay(long maintenanceInitialDelay) {
        this.maintenanceInitialDelay = maintenanceInitialDelay;
    }

    public long getMaxConnectionIdleTime() {
        return maxConnectionIdleTime;
    }

    public void setMaxConnectionIdleTime(long maxConnectionIdleTime) {
        this.maxConnectionIdleTime = maxConnectionIdleTime;
    }

    public long getMaxConnectionLifeTime() {
        return maxConnectionLifeTime;
    }

    public void setMaxConnectionLifeTime(long maxConnectionLifeTime) {
        this.maxConnectionLifeTime = maxConnectionLifeTime;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getMaxWaitQueueSize() {
        return maxWaitQueueSize;
    }

    public void setMaxWaitQueueSize(int maxWaitQueueSize) {
        this.maxWaitQueueSize = maxWaitQueueSize;
    }

    public long getMaxWaitTime() {
        return maxWaitTime;
    }

    public void setMaxWaitTime(long maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
    }

    public int getMinSize() {
        return minSize;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public ConnectionPoolSettings getConnectionPoolSettings() {
        return connectionPoolSettings;
    }

    public void setConnectionPoolSettings(ConnectionPoolSettings connectionPoolSettings) {
        this.connectionPoolSettings = connectionPoolSettings;
    }
}
