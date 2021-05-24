package br.com.Joetwitter.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.Joetwitter.model.Tweet;



public interface TweetRepository extends CrudRepository<Tweet, Long> {

  @Query("SELECT t from Tweet t ORDER BY t.timeday DESC")
  Iterable<Tweet> findAllOrdenedByDate();

}
