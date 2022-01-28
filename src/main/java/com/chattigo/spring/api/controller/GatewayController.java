package com.chattigo.spring.api.controller;

import com.chattigo.spring.api.converter.GatewayConverter;
import com.chattigo.spring.api.dtos.GatewayDTO;
import com.chattigo.spring.api.repository.GatewayRepository;
import com.chattigo.spring.api.entity.Gateway;
import com.chattigo.spring.api.services.GatewayService;
import com.chattigo.spring.api.utils.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class GatewayController {

  @Autowired
  private GatewayService service;

  @Autowired
  private GatewayConverter converter;

  @GetMapping(value = "/gateways")
  public ResponseEntity<List<GatewayDTO>> findAll(
      @RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
      @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize) {

    Pageable page = PageRequest.of(pageNumber, pageSize);
    List<Gateway> gateways = service.findAll(page);
    List<GatewayDTO> dtoGateways = converter.fromEntity(gateways);

    return new WrapperResponse("success", dtoGateways)
        .createResponse(HttpStatus.OK);
  }

  @GetMapping(value = "/gateways/{gatewayId}")
  public ResponseEntity<WrapperResponse<GatewayDTO>> findById(@PathVariable("gatewayId") String gatewayId) {

    Gateway gateway = service.findById(gatewayId);
    GatewayDTO gatewayDTO = converter.fromEntity(gateway);
    return new WrapperResponse<GatewayDTO>("success", gatewayDTO)
        .createResponse(HttpStatus.OK);
  }

  @PostMapping(value = "/gateways")
  public ResponseEntity<GatewayDTO> create(@Valid @RequestBody GatewayDTO gateway) {
    Gateway newGateway = service.save(converter.fromDTO(gateway));
    GatewayDTO gatewayDTO = converter.fromEntity(newGateway);

    return new WrapperResponse("success", gatewayDTO)
        .createResponse(HttpStatus.CREATED);
  }
}
