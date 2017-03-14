package com.flag.project.analysis.kafka;

import com.flag.project.core.utils.PathUtil;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

/**
 * kafka consumer
 *
 * @author xuj
 * @version V1.0-SNAPSHOT
 * @since 2017-03-14-16:04
 */
public class KafkaConsumerLaunch {

    private static final Logger LOG = LogManager.getLogger(KafkaConsumerLaunch.class);

    private Consumer<String, byte[]> consumer;

    public Consumer<String, byte[]> getKafkaConsumer() {
        if (consumer == null) {
            Properties prop = new Properties();
            try {
                prop.load(Files.newInputStream(PathUtil.getPath(KafkaConsumerLaunch.class, "kafka.properties")));
            } catch (IOException e) {
                LOG.error("load kafka properties had thrown an exception, {}", e.getMessage());
            }
            consumer = new KafkaConsumer<>(prop);
        }
        return consumer;
    }

    public void close(){
        consumer.close();
    }
}
