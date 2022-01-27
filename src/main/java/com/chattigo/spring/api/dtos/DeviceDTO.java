package com.chattigo.spring.api.dtos;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDTO {

  private Long id;
  private String vendor;
  private String createdDate;
  private String status;
}
