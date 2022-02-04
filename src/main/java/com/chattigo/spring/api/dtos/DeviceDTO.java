package com.chattigo.spring.api.dtos;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDTO implements Serializable {

  private Long id;
  private String vendor;
  private String createdDate;
  private String status;
}
