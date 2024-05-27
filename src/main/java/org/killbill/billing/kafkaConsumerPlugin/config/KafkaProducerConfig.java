package org.killbill.billing.kafkaConsumerPlugin.config;

import java.util.Properties;

import javax.inject.Inject;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.killbill.billing.kafkaConsumerPlugin.json.KafkaProp;

import lombok.extern.slf4j.Slf4j;

public class KafkaProducerConfig {
    Properties props = new Properties();

    @Inject
    public KafkaProducerConfig(final KafkaProp kafkaProp){
        addProperties(kafkaProp);
    }

    public Properties getKafkaProperties(){
        return this.props;
    }

    private void addProperties(final KafkaProp kafkaProp){
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProp.getServer());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        setSSLConfigProperties(props,kafkaProp);
    }
    private void setSSLConfigProperties(Properties props, final KafkaProp kafkaProp) {
        if (kafkaProp.isSslEnabled()) {
            props.put("security.protocol", "SSL");
            props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, kafkaProp.getTrustStoreLocation());
            props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, kafkaProp.getTrustStorePassword());
            props.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, kafkaProp.getKeyPassword());
            props.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, kafkaProp.getKeyStorePassword());
            props.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, kafkaProp.getKeyStoreLocation());
            props.put("ssl.client.auth", "required");
            props.put(SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG, "");
            props.put("security.inter.broker.protocol", "SSL");
        }
    }
}
