package br.com.Joetwitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.Joetwitter.model.Tweet;
import br.com.Joetwitter.repository.TweetRepository;



@Repository
public class TweetService {

  private TweetRepository tweetRepository;
  
  @Autowired
  public void setTweetRepository(TweetRepository tweetRepository) {
    this.tweetRepository = tweetRepository;
  }

  public void save(Tweet tweet) {
    tweetRepository.save(tweet);
  }

  public Tweet getTweetBy(Long id) {
    return tweetRepository.findOne(id);
  }

  public Iterable<Tweet> allTweets() {
    return tweetRepository.findAllOrdenedByDate();
  }
  
}
