package com.mwaiauth.springjwt.repo;

import com.mwaiauth.springjwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long>{
    User findByUsername(String username);
}
