package com.kb.health.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
@Jacksonized
public class HealthActivityRecord{
  String recordkey;
  HealthData data;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss +0000", timezone = "Asia/Seoul")
  LocalDateTime lastUpdate;
  String type;

  @Value
  @Builder
  @Jacksonized
  public static class HealthData {
    List<HealActivity> entries;
    HealthDevice source;
    String memo;
  }

  @Value
  @Builder
  @Jacksonized
  public static class HealthDevice {
    Integer mode;
    Terminal product;
    String name;
    String type;
  }

  @Value
  @Builder
  @Jacksonized
  public static class Terminal {
    String name;
    String vender;
  }

  @Value
  @Builder
  @Jacksonized
  public static class HealActivity {
    BigDecimal steps;
    Period period;
    ActivityRecode distance;
    ActivityRecode calories;
  }

  @Value
  @Builder
  @Jacksonized
  public static class ActivityRecode {
    String unit;
    BigDecimal value;
  }

  @Value
  @Builder
  @Jacksonized
  public static class Period {
    String from;
    String to;
  }
}
