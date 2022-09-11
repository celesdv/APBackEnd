package com.app.APFinal.aplication.util;

import com.app.APFinal.infrastructure.database.repository.IUserRepository;
import com.app.APFinal.infrastructure.spring.config.security.ERole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

  @Autowired
  private IUserRepository userRepository;

  public Authentication getAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

  public UserDetails getUserAuthenticated() {
    Object principal = getAuthentication().getPrincipal();
    String username = (principal instanceof UserDetails)
        ? ((UserDetails) principal).getUsername() : principal.toString();

    return userRepository.findByEmail(username);
  }

  public boolean hasAdminRole() {
    return getAuthentication().getAuthorities().stream()
        .anyMatch(grantedAuthority -> ERole.ADMIN.getFullRoleName()
            .equals(grantedAuthority.getAuthority()));
  }

}
