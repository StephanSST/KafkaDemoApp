package ch.basler.playground.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {
  private static int sCounter = 0;

  private final KafkaProducer producer;

  @Autowired
  KafkaController(KafkaProducer producer) {
    this.producer = producer;
  }

  @RequestMapping("/version")
  public String showVersion() {
    String message = String.format("%s - Hello Computer, this is messag no %s.", System.currentTimeMillis(), sCounter++);
    this.sendMessageToKafkaTopic(message);
    return message;
  }

  @PostMapping(value = "/publish")
  public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
    producer.sendMessage(message);
  }

}
