package com.nannayeong.awd.user.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;

@ToString
@Builder
@Entity
@Getter
@Table(name = "USER")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name = "key")
  private Integer key;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "nickname")
  private String nickname;

  @Column(name = "join_date")
  @Builder.Default
  private LocalDateTime joinDate = LocalDateTime.now();
}
