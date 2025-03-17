package com.kb.health.domian;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
public class HealthActivityLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String recordKey;

  private LocalDateTime lastUpdate;

  private String type;

  private String memo;

  @CreatedDate
  private LocalDateTime registeredAt;

  public HealthActivityLog(String recordKey, LocalDateTime lastUpdate, String type) {
    this.recordKey = recordKey;
    this.lastUpdate = lastUpdate;
    this.type = type;
  }

  public void saveMemo(String memo){
    this.memo = memo;
  }
}
