package com.chattigo.spring.api.services;

import com.chattigo.spring.api.entity.Device;
import com.chattigo.spring.api.exceptions.NoDataFoundException;
import com.chattigo.spring.api.exceptions.ValidateServiceException;
import com.chattigo.spring.api.repository.DeviceRepository;
import com.chattigo.spring.api.repository.GatewayRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class DeviceService {

  @Value("${devices.size}")
  private Long deviceSize;

  @Autowired
  private DeviceRepository repo;

  @Autowired
  private GatewayRepository gatewayRepo;

  @Transactional
  public Device save(Device device, String gatewayId) {

    return gatewayRepo.findById(gatewayId)
        .map(gateway -> {
          device.setGateway(gateway);
          if (gateway.getDevices().size() > deviceSize) {
            throw new ValidateServiceException("Only " + deviceSize + " devices per gateway allowed");
          }
          return repo.save(device);
        })
        .orElseThrow(() -> new NoDataFoundException("The gatewayId don't exist."));
  }

  public void delete(Long id, String gatewayId) {
    Device device = repo.findByIdAndGatewaySerialNumber(id, gatewayId)
        .orElseThrow(() -> new NoDataFoundException("The gatewayId(gateway) and the id (device) don't exist."));
    repo.delete(device);
  }
}
