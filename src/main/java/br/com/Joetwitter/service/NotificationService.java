package br.com.Joetwitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.Joetwitter.model.Notification;
import br.com.Joetwitter.repository.NotificationRepository;



@Repository
public class NotificationService {
  
  private NotificationRepository notificationRepository;
  
  @Autowired
  public void setNotificationRepository (NotificationRepository notificationRepository) {
    this.notificationRepository = notificationRepository;
  }

  public void save(Notification notification) {
    notificationRepository.save(notification);
  }
  

}
