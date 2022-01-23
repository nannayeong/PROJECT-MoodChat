package com.nannayeong.awd.user;

import com.google.gson.Gson;
import com.nannayeong.awd.CommonTests;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 유저 컨트롤러 테스트
 * @since 2022-01-23
 */
public class UserControllerTests extends CommonTests {

  /**
   * 회원가입 테스트
   * @throws Exception
   */
  @Test
  public void join() throws Exception {

    Map<String, String> map = new HashMap<String, String>() {{
      put("type", "default");
      put("password", "1234");
      put("nickname", "admin");
      put("email", "admin@ard.com");
    }};

    mockMvc.perform(patch("/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new Gson().toJson(map, Map.class))
        )
        .andExpect(status().isCreated())
        .andDo(print());
  }

  /**
   * 로그인 테스트
   * @throws Exception
   */
  @Test
  public void login() throws Exception {

    Map<String, String> map = new HashMap<String, String>() {{
      put("email", "");
      put("id", "admin");
    }};

    mockMvc.perform(patch("/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new Gson().toJson(map, Map.class))
        )
        .andExpect(status().isCreated())
        .andDo(print());
  }
}
