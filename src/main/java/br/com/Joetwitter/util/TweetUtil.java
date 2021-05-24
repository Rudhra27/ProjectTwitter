package br.com.Joetwitter.util;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.Joetwitter.model.Notification;
import br.com.Joetwitter.model.Tweet;
import br.com.Joetwitter.model.User;
import br.com.Joetwitter.service.NotificationService;
import br.com.Joetwitter.service.UserService;

/**
 * A class to help use tweets
 * 
 * @author arthur
 *
 */

public class TweetUtil {
  /**
   * 
   * @param content a valid content tweet
   * @return a list of users mentioneds with lowercase
   */
  public static Set<String> getMentionedUsers(String content) {
    String pattern = "(^|[^A-Za-z0-9_-])@([A-Za-z0-9_-]+)";
    Pattern r = Pattern.compile(pattern);

    String text = content.toLowerCase();
    Matcher matcher = r.matcher(text);
    HashSet<String> mentionedUsers = new HashSet<>();
    while (matcher.find()) {
      String mentioned = matcher.group(2);
      mentionedUsers.add(mentioned);
    }
    return mentionedUsers;
  }

  /**
   * Send notifications to users that exist in the system
   * @param mentionedUsers Users in String
   * @param userService injected by string
   * @param notificationService injected by string
   * @param tweet the current tweet that will be posted
   */
  public static void sendNotification(Set<String> mentionedUsers,
      UserService userService, NotificationService notificationService, Tweet tweet) {
    for (String username : mentionedUsers) {
      User user = new User();
      user.setUsername(username);
      if(!userService.contains(user))
        continue;
      user = userService.loadUserByUsername(user.getUsername());
      Notification notification = new Notification();
      notification.setDatetime(tweet.getTimeday());
      notification.setReaded(false);
      notification.setUser(user);
      notification.setContent("You was target in one tweet");
      notification.setLink("/tweet/" + tweet.getId());
      notificationService.save(notification);
    }
  }
}
