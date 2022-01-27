package com.chattigo.spring.api;

import com.chattigo.spring.api.controller.DeviceController;
import com.chattigo.spring.api.controller.GatewayController;
import com.chattigo.spring.api.repository.DeviceRepository;
import com.chattigo.spring.api.repository.GatewayRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApiManagementTests {

  @Autowired
  private GatewayController gatewayController;

  @Autowired
  private GatewayRepository gatewayRepository;

  @Autowired
  private DeviceController deviceController;

  @Autowired
  private DeviceRepository deviceRepository;

  @Test
  void contextLoads() {
    assertThat(gatewayController).isNotNull();
    assertThat(gatewayRepository).isNotNull();
    assertThat(deviceController).isNotNull();
    assertThat(deviceRepository).isNotNull();
  }

}
