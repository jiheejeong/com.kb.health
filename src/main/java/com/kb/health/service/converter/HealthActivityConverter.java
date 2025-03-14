package com.kb.health.service.converter;

import com.kb.health.domian.HealthActivity;
import com.kb.health.domian.HealthDevice;
import com.kb.health.service.HealthActivityRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HealthActivityConverter {
  HealthActivityConverter INSTANCE = Mappers.getMapper(HealthActivityConverter.class);

  @Mapping(target = "startTime", source = "period.from")
  @Mapping(target = "endTime", source = "period.to")
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "device", ignore = true)
  HealthActivity toHealthActivity(String recordKey, HealthActivityRecord.HealActivity activity);

  @Mapping(target = "terminalName", source = "product.name")
  @Mapping(target = "terminalVender", source = "product.vender")
  HealthDevice toHealthDevice(HealthActivityRecord.HealthDevice device);
}
