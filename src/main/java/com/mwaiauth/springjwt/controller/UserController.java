/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mwaiauth.springjwt.controller;

import com.mwaiauth.springjwt.model.Role;
import com.mwaiauth.springjwt.model.User;
import com.mwaiauth.springjwt.service.UserService;
import java.net.URI;
import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author HP
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }
    
    @GetMapping("/user/save")
    public ResponseEntity<User> savUSer(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }
    
    @GetMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toString());
        return ResponseEntity.created(uri).body(userService.SaveRole(role));
    }
    
    @GetMapping("/role/adduser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
        userService.addRoleToUser(form.getUserName(), form.getRoleName());
        return ResponseEntity.ok().build();
    }
}

@Data
class RoleToUserForm{
    private String userName;
    private String roleName;
}