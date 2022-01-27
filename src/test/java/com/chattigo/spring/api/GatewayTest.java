package com.chattigo.spring.api;

import com.chattigo.spring.api.dtos.DeviceDTO;
import com.chattigo.spring.api.dtos.GatewayDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GatewayTest {
  @Autowired
  private MockMvc mockMvc;

  private final ObjectMapper objectMapper = new ObjectMapper();
  private GatewayDTO gateway;
  private List<DeviceDTO> devices;

  @BeforeEach
  public void setUp() {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    gateway = GatewayDTO.builder()
        .name("Gateway")
        .ipAddress("10.0.0.333")
        .serialNumber("52665669")
        .build();

    devices = List.of(
        DeviceDTO.builder()
            .id(1L)
            .vendor("Google")
            .createdDate("21/01/2021")
            .status("offline")
            .build()
    );

    gateway.setDevices(devices);
  }

  @Test
  public void invalidIpv4Address() throws Exception {
    mockMvc.perform(post("/gateways")
            .content(toJson(gateway))
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("The ip  address is invalid;"));
  }

  private String toJson(Object obj) throws JsonProcessingException {
    return objectMapper.writeValueAsString(obj);
  }
}
