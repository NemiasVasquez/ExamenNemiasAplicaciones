package com.vasquez.infraccion_service.service;

import java.util.List;

import com.vasquez.infraccion_service.entity.Infraccion;

public interface InfraccionService {

    public List<Infraccion> findAll(int offset, int limit);
    public Infraccion findById(int id);
    public Infraccion save(Infraccion obj);
    public Boolean delete(int id);
    public List<Infraccion> findByNombre(String dni, int offset, int limit);
    public Infraccion annulInfraccion(int id);
}
