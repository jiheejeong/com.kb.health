package com.kb.health.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kb.health.domian.HealthActivity;
import com.kb.health.domian.HealthActivityLog;
import com.kb.health.domian.HealthDevice;
import com.kb.health.service.converter.HealthActivityConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SaveHealthActivityService {

  private final ObjectMapper objectMapper;
  private final HealthActivityGateway healthActivityGateway;

  @Transactional
  public void saveHealthActivity() {
    try {
      final File file = ResourceUtils.getFile("classpath:INPUT_DATA1.json");
      final Map<String, Object> customerHealthActivity = objectMapper.readValue(file, new TypeReference<Map<String, Object>>() {
      });
      final HealthActivityRecord healthActivity = objectMapper.convertValue(customerHealthActivity, HealthActivityRecord.class);
      save(healthActivity);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void save(final HealthActivityRecord record) {
    final String recordKey = record.getRecordkey();

    final String memo = record.getData().getMemo();
    final HealthActivityLog healthActivityLog = new HealthActivityLog(recordKey, record.getLastUpdate(), record.getType());
    if (StringUtils.hasText(memo)) {
      healthActivityLog.saveMemo(memo);
    }

    final HealthDevice device = HealthActivityConverter.INSTANCE.toHealthDevice(record.getData().getSource());
    final List<HealthActivity> healthActivities = record.getData().getEntries().stream()
        .map(activity -> HealthActivityConverter.INSTANCE.toHealthActivity(recordKey, activity).toBuilder().device(device).build())
        .toList();

    healthActivityGateway.save(healthActivityLog, healthActivities);
  }
}
