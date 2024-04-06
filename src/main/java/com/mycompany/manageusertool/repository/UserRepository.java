package com.mycompany.manageusertool.repository;

import com.mycompany.manageusertool.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
