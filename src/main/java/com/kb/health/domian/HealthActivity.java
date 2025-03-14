package com.kb.health.domian;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class HealthActivity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String recordkey;

  private Integer steps;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "unit", column = @Column(name = "distanceUnit")),
      @AttributeOverride(name = "value", column = @Column(name = "distanceValue"))
  })
  private ActivityRecord distance;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "unit", column = @Column(name = "caloriesUnit")),
      @AttributeOverride(name = "value", column = @Column(name = "caloriesValue"))
  })
  private ActivityRecord calories;

  private LocalDateTime startTime;

  private LocalDateTime endTime;

  @Embedded
  private HealthDevice device;
}
