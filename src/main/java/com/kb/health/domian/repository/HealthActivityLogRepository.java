package com.kb.health.domian.repository;


import com.kb.health.domian.HealthActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthActivityLogRepository extends JpaRepository<HealthActivityLog, Long> {
}
