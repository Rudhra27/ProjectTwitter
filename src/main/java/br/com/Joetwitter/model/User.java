package br.com.Joetwitter.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table( name = "systemuser" )
public class User implements UserDetails {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Id
  @Size(min = 3, max = 12, message = "Your username has to have 3-12 digits")
  private String username;

  @Size(min = 8, max = 16, message = "Password has to have 8-16 digits")
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  private List<Role> roles = new ArrayList<>();

  @ManyToMany
  private List<User> following = new ArrayList<>();

  @OneToMany(mappedBy = "poster")
  private List<Tweet> tweets = new ArrayList<>();

  @ManyToMany(mappedBy = "following")
  private List<User> followers = new ArrayList<>();

  @OneToMany(mappedBy = "user")
  private List<Notification> notifications = new ArrayList<>();

  private String profilePhotoPath;

  public List<Notification> getNotifications() {
    return Collections.unmodifiableList(notifications);
  }

  public List<User> getFollowers() {
    return Collections.unmodifiableList(followers);
  }

  public List<Tweet> getTweets() {
    return tweets;
  }

  public List<User> getFollowing() {
    return Collections.unmodifiableList(following);
  }

  public void addFollowing(User user) {
    following.add(user);
  }

  @Override
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((username == null) ? 0 : username.hashCode());
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
    User other = (User) obj;
    if (username == null) {
      if (other.username != null)
        return false;
    } else if (!username.toLowerCase().equals(other.username.toLowerCase()))
      return false;
    return true;
  }

  public String getProfilePhotoPath() {
    return profilePhotoPath;
  }

  public void setProfilePhotoPath(String profilePhotoPath) {
    this.profilePhotoPath = profilePhotoPath;
  }

  @Override
  public Collection<Role> getAuthorities() {
    return roles;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public void addRole(Role role) {
    roles.add(role);
  }

  public boolean isFollowing(User user) {
    return getFollowing().contains(user);
  }

  public void removeFollowing(User user) {
    following.remove(user);
  }

}
