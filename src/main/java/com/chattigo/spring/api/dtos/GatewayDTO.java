package com.chattigo.spring.api.dtos;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GatewayDTO implements Serializable {

  private String serialNumber;
  private String name;
  private String ipAddress;
  private List<DeviceDTO> devices = new java.util.ArrayList<>();
}
