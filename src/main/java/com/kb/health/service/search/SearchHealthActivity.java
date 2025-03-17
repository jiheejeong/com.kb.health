package com.kb.health.service.search;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
@Builder
public class SearchHealthActivity {
  String recordKey;
  List<Summation> summations;

  @Value
  @Builder
  public static class Summation {
    String date;
    BigDecimal steps;
    BigDecimal distance;
    BigDecimal calories;
  }
}
