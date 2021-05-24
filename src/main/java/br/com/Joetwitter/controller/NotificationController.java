package br.com.Joetwitter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.Joetwitter.model.Notification;
import br.com.Joetwitter.model.User;
import br.com.Joetwitter.service.NotificationService;
import br.com.Joetwitter.service.UserService;



@Controller
public class NotificationController {

  private UserService userService;
  private NotificationService notificationService;

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @Autowired
  public void setNotificationService(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  /**
   * Get the notification of the current logged user
   * 
   * @param model inject by string
   * @return model of notification list's path
   */
  @RequestMapping("notification")
  public String getNotifications(Model model) {

    Authentication authenticated = SecurityContextHolder.getContext()
        .getAuthentication();
    String username = authenticated.getName().toLowerCase();

    User user = userService.loadUserByUsername(username);
    List<Notification> notifications = user.getNotifications();

    model.addAttribute("notifications", notifications);

    for (Notification notification : notifications) {
      notification.setReaded(true);
      notificationService.save(notification);
    }

    return "notifications/list";
  }

}
