package com.nannayeong.awd.user.controller;

import com.nannayeong.awd.common.dto.response.ResponseEntityBody;
import com.nannayeong.awd.user.dto.RqJoin;
import com.nannayeong.awd.user.service.IUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 유저 컨트롤러
 * @since 2022-01-23
 */
@Slf4j
@RestController
@RequestMapping(value = "/user")
@AllArgsConstructor
public class UserController {

  @Qualifier(value = "user")
  private final IUserService userService;

  /**
   * 회원가입
   * @param rqJoin 회원가입시 필요한 정보
   * @return ResponseEntityBody success
   */
  @PatchMapping
  public ResponseEntity<ResponseEntityBody> join(@RequestBody @Valid RqJoin rqJoin) {

    log.info("[userController - join] rqJoin: " + rqJoin);

    userService.join(rqJoin.getUser());

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ResponseEntityBody.builder().build());
  }
}
