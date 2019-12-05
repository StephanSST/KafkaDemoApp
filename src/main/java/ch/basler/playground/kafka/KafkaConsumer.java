package ch.basler.playground.kafka;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

  private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumer.class);

  @KafkaListener(topics = "sst", groupId = "sst-kafka-demo-app-consumer")
  public void consume(String message) throws IOException {
    LOG.info(String.format("#### -> Consumed message -> %s", message));
  }

}
