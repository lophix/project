package com.flag.project.analysis;

import com.flag.project.analysis.entity.enums.TopicEnum;
import com.flag.project.analysis.kafka.KafkaConsumerLaunch;
import com.flag.project.analysis.server.AnalysisServer;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;

/**
 * analysis class server
 *
 * @author xuj
 * @version 0.0.1-SNAPSHOT
 * @since 2017-03-01 10:37
 */
public class MainServer {

    private static final Logger LOG = LogManager.getLogger(MainServer.class);

    public static void main(String[] args) {
        int port = 8087;
        new Thread(MainServer::startKafkaConsumer).start();
        new AnalysisServer(port).bind();
    }

    private static void startKafkaConsumer(){
        KafkaConsumerLaunch launch = new KafkaConsumerLaunch();
        Consumer<String, String> consumer = launch.getKafkaConsumer();
        consumer.subscribe(Collections.singleton(TopicEnum.TOPIC_TE.getValue()));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)
                LOG.info("offset = {}, key = {}, value = {}", record.offset(), record.key(), record.value());
        }
    }
}
