package com.chattigo.spring.api.repository;

import com.chattigo.spring.api.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
  Optional<Device> findByIdAndGatewaySerialNumber(Long id, String fk_gateway);
}
