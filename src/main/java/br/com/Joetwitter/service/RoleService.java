package br.com.Joetwitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.Joetwitter.model.Role;
import br.com.Joetwitter.repository.RoleRepository;


@Repository
public class RoleService {
  
  private RoleRepository roleRepository;

  @Autowired
  public void setRoleRepository(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }
  
  public void save(Role role) {
    roleRepository.save(role);
  }
  
  public boolean hasRole(String role) {
    return roleRepository.exists(role);
  }

}
