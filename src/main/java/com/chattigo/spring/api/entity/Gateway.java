package com.chattigo.spring.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "GATEWAYS")
public class Gateway {

  @Id
  @Column(name = "SERIAL_NUMBER", nullable = false)
  @NotNull(message = "The serial number is required")
  @NotEmpty(message = "The serial number not be empty")

  private String serialNumber;


  @Column(name = "NAME", nullable = false)
  @NotNull(message = "The name is required")
  private String name;

  @Pattern(
      message = "The ip  address is invalid",
      regexp = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$"
  )
  @NotNull(message = "The ip address is required")
  @Column(name = "IP_ADDRESS", nullable = false)
  private String ipAddress;


  @OneToMany(mappedBy = "gateway", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JsonIgnoreProperties("gateway")
  private List<Device> devices;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Gateway gateway = (Gateway) o;
    return serialNumber.equals(gateway.serialNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serialNumber);
  }
}
