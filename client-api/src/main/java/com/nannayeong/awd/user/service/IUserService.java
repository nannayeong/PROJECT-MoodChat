package com.nannayeong.awd.user.service;

import com.nannayeong.awd.user.entity.User;

/**
 * 유저 서비스 인터페이스
 * @author namayeong
 * @since 2022-01-23
 */
public interface IUserService {

    void join(User user);

    User login(User user);
}
