package com.dsp.repo;

import com.dsp.entity.ClientLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientLocationRepository extends JpaRepository<ClientLocation, Long> {
}
