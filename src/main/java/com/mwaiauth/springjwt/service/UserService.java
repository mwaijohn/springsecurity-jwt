/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mwaiauth.springjwt.service;

import com.mwaiauth.springjwt.model.Role;
import com.mwaiauth.springjwt.model.User;
import java.util.List;

/**
 *
 * @author HP
 */
public interface UserService {
    User saveUser(User user);
    Role SaveRole(Role role);
    void addRoleToUser(String userName,String roleName);
    User getUser(String userName);
    List<User> getUsers();
}
