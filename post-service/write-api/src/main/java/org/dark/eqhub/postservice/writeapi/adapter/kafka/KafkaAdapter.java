package org.dark.eqhub.postservice.writeapi.adapter.kafka;


import org.dark.eqhub.postservice.writeapi.domain.port.output.KafkaPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class KafkaAdapter implements KafkaPort {

    private static final Logger logger = LoggerFactory.getLogger(KafkaAdapter.class);


}
