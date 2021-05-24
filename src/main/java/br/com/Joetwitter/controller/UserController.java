package br.com.Joetwitter.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.Joetwitter.model.Role;
import br.com.Joetwitter.model.Tweet;
import br.com.Joetwitter.model.User;
import br.com.Joetwitter.service.RoleService;
import br.com.Joetwitter.service.UserService;



@Controller
public class UserController {
  
  private UserService userService;
  private RoleService roleService;
  
  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }
  
  @Autowired
  public void setRoleService(RoleService roleService) {
    this.roleService = roleService;
  }

  /**
   * a post method to create a new user
   * 
   * @param user valid
   * @param br injected
   * @return a redirect to the index page
   */
  @PostMapping("/user")
  public String createUser(@Valid @ModelAttribute User user, BindingResult br) {
    if (userService.contains(user))
      br.rejectValue("username", "error.user", "There are a user with this username.");
    if(br.hasErrors())
      return "users/form";
    Role role = new Role();
    
    role.setRole("ROLE_NORMAL");
    
    if(!roleService.hasRole(role.getAuthority())){
      roleService.save(role);
    }
    
    user.addRole(role);
    
    user.setUsername(user.getUsername().toLowerCase());
    
    userService.save(user);
    
    
    return "redirect:/";
  }
  
  /**
   * get method to create a new user from a form
   * 
   * @param model injected
   * @return form's model path
   */
  @GetMapping("/user")
  public String userForm(Model model) {
    User user = new User();
    model.addAttribute("user", user);
    
    return "users/form";
    
  }
  
  /**
   * get method to the login page
   * 
   * @param model injected
   * @return login's model path
   */
  @GetMapping("/login")
  public String loginForm(Model model) {
    User user = new User();
    model.addAttribute("user", user);
    
    
    return "users/login";
  }
  
  /**
   * a get method to see the details of a user
   * 
   * @param username of a user that contains in the system
   * @param model injeted
   * @return details' model path
   */
  @GetMapping("/{username}")
  public String profileDetails(@PathVariable("username") String username, Model model) {
    Authentication auth = SecurityContextHolder.getContext()
        .getAuthentication();
    String currentUsername = auth.getName().toLowerCase();
    username = username.toLowerCase();
    User currentUser = new User();
    currentUser.setUsername(currentUsername);
    boolean containsUser = userService.contains(currentUser);
    if (!containsUser)
      return "redirect:/";
    if(currentUsername.equals(username))
      model.addAttribute("isOwner", true);
    else
      model.addAttribute("isOwner", false);
    
    User user = userService.loadUserByUsername(username.toLowerCase());
    
    if(containsUser){
      currentUser = userService.loadUserByUsername(currentUsername);
      boolean isFollowing = currentUser.isFollowing(user);
      model.addAttribute("isFollowing", isFollowing);
      
    } else {
      model.addAttribute("isFollowing", false);
    }
    
    List<Tweet> tweets = user.getTweets();
    
    model.addAttribute("tweets", tweets);
    
    model.addAttribute("user", user);
    
    model.addAttribute("following", user.getFollowing().size());
    
    model.addAttribute("followers", user.getFollowers().size());
    
    return "users/details";
  }
  
  /**
   * A post method to the current user follow other user
   * 
   * @param username of a user that contains in the system
   * @return a redirect to the index page
   */
  @PostMapping("/follow")
  public String follow(@RequestParam("username") String username) {
    Authentication auth = SecurityContextHolder.getContext()
        .getAuthentication();
    String currentUsername = auth.getName().toLowerCase();
    if(currentUsername.equals(username))
      return "redirect:/" + username;
    
    User user = userService.loadUserByUsername(username);
    User currentUser = userService.loadUserByUsername(currentUsername);
    
    if(!currentUser.isFollowing(user))
      currentUser.addFollowing(user);
    userService.save(currentUser);
    
    
    return "redirect:/" + username;
  }

  /**
   * A post method to the current user unfollow other user
   * 
   * @param username of a user that contains in the system
   * @return a redirect to the index page
   */
  @PostMapping("/unfollow")
  public String unfollow(@RequestParam("username") String username) {
    
    Authentication auth = SecurityContextHolder.getContext()
        .getAuthentication();
    String currentUsername = auth.getName().toLowerCase();
    if(currentUsername.equals(username))
      return "redirect:/" + username;
    
    User user = userService.loadUserByUsername(username);
    User currentUser = userService.loadUserByUsername(currentUsername);
    
    if(currentUser.isFollowing(user))
      currentUser.removeFollowing(user);
    
    userService.save(currentUser);
    
    return "redirect:/" + username;
  }
}
