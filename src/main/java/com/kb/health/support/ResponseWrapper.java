package com.kb.health.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWrapper<T> {
  /** HTTP 상태 코드 */
  private int status;
  /** 데이터 */
  private T data;

  public static ResponseWrapper<Void> ok() {
    return ok(null);
  }

  public static <T> ResponseWrapper<T> ok(@Nullable final T data) {
    return new ResponseWrapper<>(HttpStatus.OK.value(), data);
  }
}
