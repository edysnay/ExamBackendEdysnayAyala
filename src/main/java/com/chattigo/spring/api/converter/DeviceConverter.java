package com.chattigo.spring.api.converter;

import com.chattigo.spring.api.dtos.DeviceDTO;
import com.chattigo.spring.api.entity.Device;
import com.chattigo.spring.api.exceptions.ValidateServiceException;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@AllArgsConstructor
public class DeviceConverter extends AbstractConverter<Device, DeviceDTO> {

  private DateTimeFormatter dateTimeFormat;

  @Override
  public DeviceDTO fromEntity(Device entity) {

    if (entity == null) return null;

    return DeviceDTO.builder()
        .id(entity.getId())
        .status(entity.getStatus())
        .vendor(entity.getVendor())
        .createdDate(entity.getCreatedDate().format(dateTimeFormat))
        .build();
  }

  @Override
  public Device fromDTO(DeviceDTO dto) {

    if (dto == null) return null;

    try {
      return Device.builder()
          .vendor(dto.getVendor())
          .createdDate(LocalDate.parse(dto.getCreatedDate(), dateTimeFormat))
          .status(dto.getStatus())
          .build();
    } catch (DateTimeParseException e) {
      throw new ValidateServiceException("Date invalid (dd/MM/yyy).", e);
    }

  }
}
