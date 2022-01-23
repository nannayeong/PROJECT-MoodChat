package com.nannayeong.awd.user.service;

import com.nannayeong.awd.user.dao.jpa.IUserRepository;
import com.nannayeong.awd.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.transaction.Transactional;

/**
 * 유저 서비스 구현
 * @author namayeong
 * @since 2022-01-23
 */
@Slf4j
@Service(value = "user")
@AllArgsConstructor
public class UserService implements IUserService {

  private final IUserRepository userRepository;

  /**
   * 회원가입
   * @param user 회원가입 유저 엔티티
   */
  @Transactional(rollbackOn = {RuntimeException.class})
  public void join(User user) {

    log.info("[userService - join] user: " + user);

    if (userRepository.findByEmail(user.getEmail()).isPresent()) {

      throw new HttpClientErrorException(HttpStatus.CONFLICT, "이미 존재하는 이메일입니다.");
    } else {

      userRepository.save(user);
    }
  }

  /**
   * 로그인
   * @param user 로그인 유저 엔티티
   * @return 로그인 유저 엔티티
   */
  @Transactional(rollbackOn = {RuntimeException.class})
  public User login(User user) {

    log.info("[userService - login] user: " + user);

    return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword())
        .orElseThrow(() -> new HttpClientErrorException(HttpStatus.UNAUTHORIZED,
            "해당하는 계정이 존재하지 않습니다. 아이디와 패스워드를 확인해주세요."));
  }
}
