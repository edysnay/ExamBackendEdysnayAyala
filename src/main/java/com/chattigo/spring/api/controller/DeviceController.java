package com.chattigo.spring.api.controller;

import com.chattigo.spring.api.converter.DeviceConverter;
import com.chattigo.spring.api.dtos.DeviceDTO;
import com.chattigo.spring.api.dtos.GatewayDTO;
import com.chattigo.spring.api.entity.Device;
import com.chattigo.spring.api.entity.Gateway;
import com.chattigo.spring.api.exceptions.NoDataFoundException;
import com.chattigo.spring.api.repository.DeviceRepository;
import com.chattigo.spring.api.repository.GatewayRepository;
import com.chattigo.spring.api.services.DeviceService;
import com.chattigo.spring.api.utils.WrapperResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class DeviceController {

  @Autowired
  private DeviceConverter converter;

  @Autowired
  private DeviceService service;

  @PostMapping(value = "/gateways/{gatewayId}/device")
  public ResponseEntity<DeviceDTO> create(@PathVariable("gatewayId") String gatewayId,
                                          @Valid @RequestBody DeviceDTO device) {
    Device newDevice = service.save(converter.fromDTO(device), gatewayId);
    DeviceDTO deviceDTO = converter.fromEntity(newDevice);

    return new WrapperResponse("success", deviceDTO)
        .createResponse(HttpStatus.CREATED);
  }

  @DeleteMapping("/gateways/{gatewayId}/device/{deviceId}")
  public ResponseEntity<?> deleteBook(
      @PathVariable(value = "gatewayId") String gatewayId,
      @PathVariable(value = "deviceId") Long deviceId
  ) {
    service.delete(deviceId, gatewayId);
    return new WrapperResponse("success", "Device deleted")
        .createResponse(HttpStatus.CREATED);
  }

}
