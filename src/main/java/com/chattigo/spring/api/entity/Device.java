package com.chattigo.spring.api.entity;

import com.chattigo.spring.api.validator.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "DEVICES")
public class Device {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @NotBlank(message = "The vendor not be empty")
  @NotNull(message = "The vendor is required ")
  @Column(name = "VENDOR", nullable = false)
  private String vendor;

  @NotNull(message = "The date is required")
  @Column(name = "CREATED_DATE", nullable = false)
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private LocalDate createdDate;

  @NotEmpty
  @NotNull(message = "The status is required")
  @Status(message = "The status value must be offline/online")
  @Column(name = "STATUS", nullable = false)
  private String status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "FK_GATEWAY", nullable = false)
  private Gateway gateway;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Device device = (Device) o;
    return id.equals(device.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
