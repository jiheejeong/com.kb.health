package com.kb.health.domian.repository;


import com.kb.health.domian.HealthActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HealthActivityRepository extends JpaRepository<HealthActivity, Long> {
  List<HealthActivity> findAllByOrderByStartTimeAsc();
}
