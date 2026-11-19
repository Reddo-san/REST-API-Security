package com.rest_api.rest_api_demo.service;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rest_api.rest_api_demo.entity.Auth_Role;
import com.rest_api.rest_api_demo.entity.Auth_User;
import com.rest_api.rest_api_demo.repository.AuthUserRepository;


@Service 
public class CustomUserDetailsService implements UserDetailsService {
  
  private final AuthUserRepository authUserRepo;

    public CustomUserDetailsService(AuthUserRepository authUserRepo) {
        this.authUserRepo = authUserRepo;
    }

    public UserDetails loadUserByUsername(String theUsername){
      Auth_User user = authUserRepo.findByUsername(theUsername)
            .orElseThrow(() -> new UsernameNotFoundException("Username " 
                                  + theUsername + " does not exist"));

      return new User(user.getUsername(),
                        user.getPassword(),
                        mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Auth_Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }


}
