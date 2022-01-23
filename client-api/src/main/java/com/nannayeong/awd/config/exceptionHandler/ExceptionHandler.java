package com.nannayeong.awd.config.exceptionHandler;

import com.nannayeong.awd.common.dto.response.ResponseEntityBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class ExceptionHandler {

  @org.springframework.web.bind.annotation.ExceptionHandler(value = HttpClientErrorException.class)
  public ResponseEntity<ResponseEntityBody> ConflictException(HttpClientErrorException httpClientErrorException) {

    return ResponseEntity.status(httpClientErrorException.getStatusCode())
        .body(ResponseEntityBody.builder()
            .success(false)
            .errMsg(httpClientErrorException.getMessage())
            .build()
        );
  }
}
