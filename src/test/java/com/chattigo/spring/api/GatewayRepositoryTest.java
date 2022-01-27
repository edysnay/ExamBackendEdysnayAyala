package com.chattigo.spring.api;

import com.chattigo.spring.api.entity.Device;
import com.chattigo.spring.api.entity.Gateway;
import com.chattigo.spring.api.repository.GatewayRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GatewayRepositoryTest {

  @Autowired
  private GatewayRepository repo;

  @Value("${config.dateFormat}")
  private String dateFormat;
  private Gateway gateway;
  private List<Device> devices;

  @BeforeEach
  public void setUp() {
    DateTimeFormatter format = DateTimeFormatter.ofPattern(dateFormat);

    gateway = Gateway.builder()
        .name("Gateway")
        .ipAddress("10.0.0.1")
        .serialNumber("52665669")
        .build();

    devices = List.of(
        Device.builder()
            .id(1L)
            .vendor("Google")
            .createdDate(LocalDate.parse("21/01/2021", format))
            .status("offline")
            .gateway(gateway)
            .build()
    );

    gateway.setDevices(devices);
  }

  @AfterEach
  public void tearDown() {
    repo.deleteAll();
    gateway = null;
    devices = null;
  }

  @Test
  public void givenGatewayToAddShouldReturnAddedGateway() {
    repo.save(gateway);
    repo.findById(gateway.getSerialNumber())
        .stream()
        .map(Gateway::getSerialNumber)
        .forEach(sr -> assertEquals("52665669", sr));
  }

  @Test
  public void GivenGetAllDevicesShouldReturnListOfAllDevices() {
    repo.save(gateway);
    repo.findById(gateway.getSerialNumber())
        .ifPresent(sr ->
            assertEquals(1, sr.getDevices().size()));
  }

  @Test
  public void givenIdTODeleteThenShouldDeleteTheGateway() {
    repo.save(gateway);
    repo.deleteById(gateway.getSerialNumber());
    Optional<Gateway> optional = repo.findById(gateway.getSerialNumber());
    assertEquals(Optional.empty(), optional);
  }


}
