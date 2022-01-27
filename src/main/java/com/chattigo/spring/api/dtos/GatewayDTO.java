package com.chattigo.spring.api.dtos;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GatewayDTO {

  private String serialNumber;
  private String name;
  private String ipAddress;
  private List<DeviceDTO> devices = new java.util.ArrayList<>();
}
