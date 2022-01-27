package com.chattigo.spring.api.converter;

import com.chattigo.spring.api.dtos.DeviceDTO;
import com.chattigo.spring.api.dtos.GatewayDTO;
import com.chattigo.spring.api.entity.Device;
import com.chattigo.spring.api.entity.Gateway;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class GatewayConverter extends AbstractConverter<Gateway, GatewayDTO> {

  private DeviceConverter deviceConverter;

  @Override
  public GatewayDTO fromEntity(Gateway entity) {
    if (entity == null)
      return null;

    List<DeviceDTO> devices = fromDeviceEntity(entity.getDevices());

    return GatewayDTO.builder()
        .serialNumber(entity.getSerialNumber())
        .name(entity.getName())
        .ipAddress(entity.getIpAddress())
        .devices(devices)
        .build();
  }

  @Override
  public Gateway fromDTO(GatewayDTO dto) {
    if (dto == null) return null;

    List<Device> devices = fromDeviceDTO(dto.getDevices());

    return Gateway.builder()
        .serialNumber(dto.getSerialNumber())
        .name(dto.getName())
        .ipAddress(dto.getIpAddress())
        .devices(devices)
        .build();
  }

  private List<DeviceDTO> fromDeviceEntity(List<Device> devices) {
    if (devices == null) return null;

    return devices.stream().map(deviceConverter::fromEntity)
        .collect(Collectors.toList());
  }

  private List<Device> fromDeviceDTO(List<DeviceDTO> devices) {
    if (devices == null) return null;

    return devices.stream().map(deviceConverter::fromDTO)
        .collect(Collectors.toList());
  }
}
