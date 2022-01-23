package com.nannayeong.awd.user.dao.jpa;

import com.nannayeong.awd.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmailAndPassword(String email, String password);

  Optional<User> findByEmail(String email);
}
