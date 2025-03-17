package com.kb.health.service.search;

import com.kb.health.domian.HealthActivity;
import com.kb.health.domian.repository.HealthActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchHealthActivityService {

  private final HealthActivityRepository healthActivityRepository;

  public List<SearchHealthActivity> searchByMonth() {
    final Map<String, List<HealthActivity>> searchByDaily = healthActivityRepository.findAllByOrderByStartTimeAsc().stream()
        .collect(Collectors.groupingBy(HealthActivity::getRecordKey, Collectors.mapping(Function.identity(), Collectors.toList())));
    final List<SearchHealthActivity> result = new ArrayList<>();
    searchByDaily.forEach((key, value) -> {
      Map<Integer, List<HealthActivity>> summation = value.stream().collect(Collectors.groupingBy(activity -> activity.getStartTime().getYear() + activity.getStartTime().getMonthValue(), Collectors.mapping(Function.identity(), Collectors.toList())));
      result.add(SearchHealthActivity.builder()
          .recordKey(key)
          .summations(getHealthActivitySummation("yyyy-MM", summation))
          .build());
    });
    return result;
  }

  public List<SearchHealthActivity> searchByDaily() {
    final Map<String, List<HealthActivity>> searchByDaily = healthActivityRepository.findAllByOrderByStartTimeAsc().stream()
        .collect(Collectors.groupingBy(HealthActivity::getRecordKey, Collectors.mapping(Function.identity(), Collectors.toList())));
    final List<SearchHealthActivity> result = new ArrayList<>();
    searchByDaily.forEach((key, value) -> {
      Map<Integer, List<HealthActivity>> summation = value.stream().collect(Collectors.groupingBy(activity -> activity.getStartTime().getDayOfYear(), Collectors.mapping(Function.identity(), Collectors.toList())));
      result.add(SearchHealthActivity.builder()
          .recordKey(key)
          .summations(getHealthActivitySummation("yyyy-MM.dd", summation))
          .build());
    });
    return result;
  }

  private List<SearchHealthActivity.Summation> getHealthActivitySummation(final String DateTimeFormat, final Map<Integer, List<HealthActivity>> summationMap) {
    final List<SearchHealthActivity.Summation> summations = new ArrayList<>();
    summationMap.forEach((key, value) -> summations.add(SearchHealthActivity.Summation.builder()
        .date(value.get(0).getStartTime().format(DateTimeFormatter.ofPattern(DateTimeFormat)))
        .steps(value.stream().map(HealthActivity::getSteps).reduce(BigDecimal.ZERO, BigDecimal::add))
        .distance(value.stream().map(activity -> activity.getDistance().getValue()).reduce(BigDecimal.ZERO, BigDecimal::add))
        .calories(value.stream().map(activity -> activity.getCalories().getValue()).reduce(BigDecimal.ZERO, BigDecimal::add))
        .build()));
    return summations;
  }
}
