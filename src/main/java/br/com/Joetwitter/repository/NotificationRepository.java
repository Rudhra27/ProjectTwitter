package br.com.Joetwitter.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.Joetwitter.model.Notification;


public interface NotificationRepository extends CrudRepository<Notification, Long> {

}
