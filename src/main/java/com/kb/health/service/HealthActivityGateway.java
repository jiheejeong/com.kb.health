package com.kb.health.service;

import com.kb.health.domian.HealthActivity;
import com.kb.health.domian.HealthActivityLog;
import com.kb.health.domian.repository.HealthActivityLogRepository;
import com.kb.health.domian.repository.HealthActivityRepository;
import com.kb.health.support.Gateway;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Gateway
@RequiredArgsConstructor
public class HealthActivityGateway {

  private final HealthActivityRepository healthActivityRepository;
  private final HealthActivityLogRepository healthActivityLogRepository;

  public void save(final HealthActivityLog healthActivityLog, final List<HealthActivity> healthActivities) {
    healthActivityLogRepository.save(healthActivityLog);
    healthActivityRepository.saveAll(healthActivities);
  }

  public List<HealthActivity> findAll() {
    return healthActivityRepository.findAll();
  }
}
