package br.com.Joetwitter.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
public class Notification {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @ManyToOne
  private User user;
  
  private String content;
  
  private String link;
  
  private boolean readed;
  
  @Temporal(TemporalType.TIMESTAMP)
  private Calendar datetime;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public void setUser(User user) {
    this.user = user;
  }

  public Calendar getDatetime() {
    return datetime;
  }

  public void setDatetime(Calendar datetime) {
    this.datetime = datetime;
  }

  public boolean isReaded() {
    return readed;
  }

  public void setReaded(boolean readed) {
    this.readed = readed;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

}
