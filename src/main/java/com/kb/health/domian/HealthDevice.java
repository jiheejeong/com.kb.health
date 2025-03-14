package com.kb.health.domian;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Embeddable
public class HealthDevice {

  private String terminalName;

  private String terminalVender;

  private String name;

  private String type;

  private Integer mode;
}
