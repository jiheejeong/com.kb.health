package com.kb.health.domian;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class HealthActivityLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String recordkey;

  private LocalDateTime lastUpdate;

  private String type;

  private String memo;

  public HealthActivityLog(String recordkey, LocalDateTime lastUpdate, String type) {
    this.recordkey = recordkey;
    this.lastUpdate = lastUpdate;
    this.type = type;
  }

  public void saveMemo(String memo){
    this.memo = memo;
  }
}
