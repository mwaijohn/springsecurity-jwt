/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mwaiauth.springjwt.service;

import com.mwaiauth.springjwt.model.Role;
import com.mwaiauth.springjwt.model.User;
import com.mwaiauth.springjwt.repo.RoleRepo;
import com.mwaiauth.springjwt.repo.UserRepo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author HP
 */
@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService,UserDetailsService{
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    @Override
    public User saveUser(User user) {
        log.info("Adding user {} to the database",user.getName());
        return userRepo.save(user);
    }

    @Override
    public Role SaveRole(Role role) {
        log.info("Adding role {} to the database",role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        User user = userRepo.findByUsername(userName);
        Role role = roleRepo.findByName(roleName);
        log.info("Addin role {} to user {}",role.getName(),user.getName());
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String userName) {
        log.info("Getting user by user name {}",userName);
        return userRepo.findByUsername(userName);
    }

    @Override
    public List<User> getUsers() {
        log.info("Retreaving all users");
        return userRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null){
            log.error("User not found");
            throw new UsernameNotFoundException("User not found");
        }else{
         log.info("User {} found in the database",username);
        }
        
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }
    
}
