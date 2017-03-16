package com.flag.project.consumer.proxy;

import com.flag.project.consumer.visitor.IKafkaVisitor;
import com.flag.project.core.utils.PathUtil;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Properties;

/**
 * kafka consumer proxy
 *
 * @author xuj
 * @version V0.0.1-SNAPSHOT
 * @since 2017-03-16-14:58
 */
public class KafkaConsumerProxy<K, V> {
    private static final Logger LOG = LogManager.getLogger(KafkaConsumerProxy.class);

    private Consumer<K, V> consumer;

    private boolean isClose = false;
    private int milliForPoll = 1000;

    public KafkaConsumerProxy() {
        this.consumer = getKafkaConsumer();
    }

    public void close() {
        consumer.close();
        isClose = true;
    }

    public void startConsumer(Collection<String> topics, IKafkaVisitor<K, V> visitor) {
        consumer.subscribe(topics);
        while (!isClose) {
            ConsumerRecords<K, V> records = consumer.poll(milliForPoll);
            if (!records.isEmpty()) {
                visitor.consume(records);
            }
        }
    }

    public int getMilliForPoll() {
        return milliForPoll;
    }

    public void setMilliForPoll(int milliForPoll) {
        this.milliForPoll = milliForPoll;
    }

    private Consumer<K, V> getKafkaConsumer() {
        if (consumer == null) {
            Properties prop = new Properties();
            try {
                prop.load(Files.newInputStream(PathUtil.getPath(KafkaConsumerProxy.class, "kafka.properties")));
            } catch (IOException e) {
                LOG.error("load kafka properties had thrown an exception, {}", e.getMessage());
            }
            consumer = new KafkaConsumer<>(prop);
        }
        return consumer;
    }
}
