package com.kb.health.service;

import com.kb.health.domian.HealthActivity;
import com.kb.health.domian.HealthDevice;
import com.kb.health.service.save.HealthActivityRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@Mapper
public interface HealthActivityConverter {
  HealthActivityConverter INSTANCE = Mappers.getMapper(HealthActivityConverter.class);

  @Mapping(target = "startTime", source = "activity.period.from", qualifiedByName = "convertPeriod")
  @Mapping(target = "endTime", source = "activity.period.to", qualifiedByName = "convertPeriod")
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "device", ignore = true)
  HealthActivity toHealthActivity(String recordKey, HealthActivityRecord.HealActivity activity);

  @Mapping(target = "terminalName", source = "product.name")
  @Mapping(target = "terminalVender", source = "product.vender")
  HealthDevice toHealthDevice(HealthActivityRecord.HealthDevice source);

  @Named("convertPeriod")
  default LocalDateTime convertPeriod(String date) {
    final String defaultPattern = "yyyy-MM-dd HH:mm:ss";
    if (isDateFormat(date, defaultPattern)) {
      return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(defaultPattern));
    }
    final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
        .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
        .appendOffset("+HHmm", "Z")
        .toFormatter();
    return LocalDateTime.parse(date, formatter);
  }

  default boolean isDateFormat(String date, String pattern) {
    try {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
      simpleDateFormat.setLenient(false);
      simpleDateFormat.parse(date);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
