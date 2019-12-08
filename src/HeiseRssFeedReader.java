package ch.basler.playground.kafka;

import static java.util.stream.Collectors.toList;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import ch.basler.playground.kafka.model.RssFeedEntry;

@Component
public class HeiseRssFeedReader {
  private static final Logger LOG = LoggerFactory.getLogger(HeiseRssFeedReader.class);

  private final KafkaProducer producer;

  @Autowired
  HeiseRssFeedReader(KafkaProducer producer) {
    this.producer = producer;
  }

  public void readerAndProduce() {
    try {
      URL feedSource = new URL("https://www.heise.de/ct/rss/artikel-atom.xml");
      SyndFeedInput input = new SyndFeedInput();
      SyndFeed feed = input.build(new XmlReader(feedSource));

      feed.getEntries().forEach(e -> processEntry(e));
    } catch (Exception ex) {
      LOG.error("Unerwarteter Fehler beim Lesen von Heise und Schreiben nach Kafka", ex);
    }
  }

  private void processEntry(SyndEntry syndEntry) {
    RssFeedEntry rssFeedEntry = new RssFeedEntry();
    rssFeedEntry.setTitle(syndEntry.getTitle());
    rssFeedEntry.setUpdated(syndEntry.getUpdatedDate());
    rssFeedEntry.setPublished(syndEntry.getPublishedDate());
    rssFeedEntry.setAuthors(syndEntry.getAuthors().stream().map(p -> p.getName()).collect(toList()));
    rssFeedEntry.setContents(syndEntry.getContents().get(0).getValue());
    rssFeedEntry.setUri(syndEntry.getUri());

    producer.sendMessage(rssFeedEntry);
  }

}
