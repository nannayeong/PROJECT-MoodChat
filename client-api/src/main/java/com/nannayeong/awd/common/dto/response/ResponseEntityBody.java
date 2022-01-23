package com.nannayeong.awd.common.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class ResponseEntityBody {

  @Builder.Default
  private Boolean success = true;

  private String errMsg;

  private IResponseEntityBodyData data;
}
