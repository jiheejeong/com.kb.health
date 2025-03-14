package com.kb.health.rest;

import com.kb.health.service.SaveHealthActivityService;
import com.kb.health.support.ResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/healty-activity")
@RequiredArgsConstructor
public class HealthActivityRecordController {

  private final SaveHealthActivityService saveHealthActivityService;

  /**
   * @title 고객건강활동 정보 DB저장하는 API (INPUT.josn 데이터 DB 저장)
   */
  @PostMapping
  public ResponseWrapper<Void> saveHealthActivity() {
    saveHealthActivityService.saveHealthActivity();
    return ResponseWrapper.ok();
  }
}
