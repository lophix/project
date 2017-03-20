package com.flag.project.consumer;

import com.flag.project.consumer.enums.TopicEnum;
import com.flag.project.consumer.proxy.KafkaConsumerProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;

/**
 * kafka consumer main access
 *
 * @author xuj
 * @version V0.0.1-SNAPSHOT
 * @since 2017-03-16-15:16
 */
public class KafkaConsumerMain {

    private static final Logger LOG = LogManager.getLogger(KafkaConsumerMain.class);

    public static void main(String[] args) {
        KafkaConsumerProxy<String, String> consumerProxy = new KafkaConsumerProxy<>();
        consumerProxy.startConsumer(Collections.singleton(TopicEnum.TOPIC_TE.getValue()),
                records -> records.forEach(record -> LOG.info("offset = {}, key = {}, value = {}", record.offset(), record.key(), record.value())));
    }
}
