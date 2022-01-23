package com.nannayeong.awd.user.dto;

import com.nannayeong.awd.user.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RqJoin {

  @NotBlank(message = "로그인 타입을 지정해주세요.")
  private String type;

  @NotBlank(message = "이메일을 입력해주세요.")
  private String email;

  @NotBlank(message = "비밀번호를 입력해주세요.")
  private String password;

  @NotBlank(message = "닉네임을 입력해주세요.")
  private String nickname;

  public User getUser() {

    return User.builder()
        .email(this.getEmail())
        .password(this.getPassword())
        .nickname(this.getNickname())
        .build();
  }

}
