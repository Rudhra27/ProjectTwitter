package br.com.Joetwitter.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;


@Entity
public class Role implements GrantedAuthority {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  @Id
  private String role;

  @Override
  public String getAuthority() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

}
