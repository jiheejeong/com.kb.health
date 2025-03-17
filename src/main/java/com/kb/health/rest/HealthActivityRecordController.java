package com.kb.health.rest;

import com.kb.health.rest.payload.SearchHealthActivityResponse;
import com.kb.health.rest.payload.SearchType;
import com.kb.health.service.save.SaveHealthActivityService;
import com.kb.health.service.search.SearchHealthActivity;
import com.kb.health.service.search.SearchHealthActivityService;
import com.kb.health.support.ResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/healty-activity")
@RequiredArgsConstructor
public class HealthActivityRecordController {

  private final SaveHealthActivityService saveHealthActivityService;
  private final SearchHealthActivityService searchHealthActivityService;

  /**
   * @title 고객건강활동 정보 저장 (INPUT.josn 데이터 DB 저장)
   */
  @PostMapping
  public ResponseWrapper<Void> saveHealthActivity() {
    saveHealthActivityService.saveHealthActivity();
    return ResponseWrapper.ok();
  }

  /**
   * @title 고객건강활동 정보 조회
   */
  @GetMapping()
  public ResponseWrapper<List<SearchHealthActivityResponse>> searchHealthActivity(@RequestParam(required = false) SearchType searchType) {
    final List<SearchHealthActivity> result;
    if (SearchType.DAILY.equals(searchType)) {
      result = searchHealthActivityService.searchByDaily();
    }else {
      result = searchHealthActivityService.searchByMonth();
    }
    return ResponseWrapper.ok(HealthActivityResponseConverter.INSTANCE.toResponse(result));
  }
}
