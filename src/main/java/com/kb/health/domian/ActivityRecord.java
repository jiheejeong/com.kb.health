package com.kb.health.domian;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Embeddable
public class ActivityRecord {
  private String unit;
  private BigDecimal value;
}
