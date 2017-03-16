package com.flag.project.consumer.visitor;

import org.apache.kafka.clients.consumer.ConsumerRecords;

/**
 * abstract visitor
 *
 * @author xuj
 * @version V0.0.1-SNAPSHOT
 * @since 2017-03-16-15:07
 */
public interface IKafkaVisitor<K, V> {
    void consume(ConsumerRecords<K, V> records);
}
