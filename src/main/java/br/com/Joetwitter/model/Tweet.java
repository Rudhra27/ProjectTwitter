package br.com.Joetwitter.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;



@Entity
public class Tweet {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @ManyToOne
  private User poster;
  
  @Type(type="text")
  private String content;
  
  @Temporal(TemporalType.TIMESTAMP)
  private Calendar timeday;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getPoster() {
    return poster;
  }

  public void setPoster(User poster) {
    this.poster = poster;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Calendar getTimeday() {
    return timeday;
  }

  public void setTimeday(Calendar timeday) {
    this.timeday = timeday;
  }

}
