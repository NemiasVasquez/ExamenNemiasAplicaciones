package com.vasquez.infraccion_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.vasquez.infraccion_service.entity.Infraccion;

@Repository
public interface InfraccionRepository extends JpaRepository<Infraccion, Integer> {

    public Page<Infraccion> findByDni(String dni, Pageable pageable);
    public Page<Infraccion> findAll(Pageable pageable);

}
