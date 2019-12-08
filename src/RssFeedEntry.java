package ch.basler.playground.kafka;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class RssFeedEntry implements Serializable {
  private static final long serialVersionUID = -5918402619099183965L;

  private String uri;
  private String title;
  private String contents;
  private List<String> authors;
  private Date updated;
  private Date published;

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContents() {
    return contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }

  public List<String> getAuthors() {
    return authors;
  }

  public void setAuthors(List<String> authors) {
    this.authors = authors;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  public Date getPublished() {
    return published;
  }

  public void setPublished(Date published) {
    this.published = published;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((authors == null) ? 0 : authors.hashCode());
    result = prime * result + ((contents == null) ? 0 : contents.hashCode());
    result = prime * result + ((published == null) ? 0 : published.hashCode());
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    result = prime * result + ((updated == null) ? 0 : updated.hashCode());
    result = prime * result + ((uri == null) ? 0 : uri.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    RssFeedEntry other = (RssFeedEntry) obj;
    if (authors == null) {
      if (other.authors != null)
        return false;
    } else if (!authors.equals(other.authors))
      return false;
    if (contents == null) {
      if (other.contents != null)
        return false;
    } else if (!contents.equals(other.contents))
      return false;
    if (published == null) {
      if (other.published != null)
        return false;
    } else if (!published.equals(other.published))
      return false;
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals(other.title))
      return false;
    if (updated == null) {
      if (other.updated != null)
        return false;
    } else if (!updated.equals(other.updated))
      return false;
    if (uri == null) {
      if (other.uri != null)
        return false;
    } else if (!uri.equals(other.uri))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "RssFeedEntry [uri=" + uri + ", title=" + title + ", contents=" + contents + ", authors=" + authors
        + ", updated=" + updated + ", published=" + published + "]";
  }

}
