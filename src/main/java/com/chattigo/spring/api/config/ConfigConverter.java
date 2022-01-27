package com.chattigo.spring.api.config;

import com.chattigo.spring.api.converter.DeviceConverter;
import com.chattigo.spring.api.converter.GatewayConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
public class ConfigConverter {

  @Value("${config.dateFormat}")
  private String dateFormat;

  @Bean
  public DeviceConverter getDeviceConverter() {
    DateTimeFormatter format = DateTimeFormatter.ofPattern(dateFormat);
    return new DeviceConverter(format);
  }

  @Bean
  public GatewayConverter getGatewayConverter() {
    return new GatewayConverter(getDeviceConverter());
  }

}
