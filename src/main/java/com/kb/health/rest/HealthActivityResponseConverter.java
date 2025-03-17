package com.kb.health.rest;

import com.kb.health.rest.payload.SearchHealthActivityResponse;
import com.kb.health.service.search.SearchHealthActivity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface HealthActivityResponseConverter {
  HealthActivityResponseConverter INSTANCE = Mappers.getMapper(HealthActivityResponseConverter.class);

  List<SearchHealthActivityResponse> toResponse(List<SearchHealthActivity> source);
}
