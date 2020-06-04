package com.mark.bootwebpractice2.repository;

import com.mark.bootwebpractice2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail(String email);
}
