package com.chattigo.spring.api.repository;

import com.chattigo.spring.api.entity.Gateway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GatewayRepository extends JpaRepository<Gateway, String> {
}
