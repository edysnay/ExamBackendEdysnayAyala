package com.chattigo.spring.api.services;

import com.chattigo.spring.api.entity.Gateway;
import com.chattigo.spring.api.exceptions.NoDataFoundException;
import com.chattigo.spring.api.exceptions.ValidateServiceException;
import com.chattigo.spring.api.repository.GatewayRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class GatewayService {

  @Value("${devices.size}")
  private Long deviceSize;

  @Autowired
  private GatewayRepository repo;

  public List<Gateway> findAll(Pageable page) {
    return repo.findAll(page).toList();
  }

  public Gateway findById(String id) {
    return repo.findById(id)
        .orElseThrow(() -> new NoDataFoundException("The gateway don't exist."));
  }


  @Transactional
  public Gateway save(Gateway gateway) {
    if (repo.existsById(gateway.getSerialNumber())) {
      throw new ValidateServiceException("Exist a gateway whit this serialNumber: " + gateway.getSerialNumber());
    }
    if (gateway.getDevices().size() > deviceSize) {
      throw new ValidateServiceException("Only " + deviceSize + " devices per gateway allowed");
    }
    gateway.getDevices().forEach(device -> device.setGateway(gateway));

    return repo.save(gateway);
  }
}
