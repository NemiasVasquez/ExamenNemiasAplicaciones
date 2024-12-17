package com.vasquez.infraccion_service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vasquez.infraccion_service.entity.Infraccion;
import com.vasquez.infraccion_service.exception.GeneralServiceException;
import com.vasquez.infraccion_service.exception.NoDataFoundException;
import com.vasquez.infraccion_service.exception.ValidateServiceException;
import com.vasquez.infraccion_service.repository.InfraccionRepository;
import com.vasquez.infraccion_service.service.InfraccionService;
import com.vasquez.infraccion_service.validator.InfraccionValidator;

@Service
public class InfraccionServiceImpl implements InfraccionService {

    @Autowired
    private InfraccionRepository repository;

    @Override
    @Transactional
    public Infraccion save(Infraccion obj) {
        try {
            InfraccionValidator.save(obj);  
            return repository.save(obj);    
        } catch (ValidateServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException("Error al registrar la infracción", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Infraccion> findAll(int offset, int limit) {
        try {
            Pageable pageable = PageRequest.of(offset, limit);  
            List<Infraccion> infracciones = repository.findAll(pageable).toList();
            if (infracciones.isEmpty()) {
                throw new NoDataFoundException("No se encontraron infracciones.");
            }
            return infracciones;
        } catch (Exception e) {
            throw new GeneralServiceException("Error al obtener las infracciones", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Infraccion findById(int id) {
        try {
            return repository.findById(id).orElseThrow(
                    () -> new NoDataFoundException("No existe infracción con ese ID"));
        } catch (Exception e) {
            throw new GeneralServiceException("Error al obtener la infracción", e);
        }
    }


    @Override
    @Transactional
    public Infraccion annulInfraccion(int id) {
        try {
            Infraccion infraccion = findById(id);
            infraccion.setEstado("Anulada"); 
            return repository.save(infraccion);  
        } catch (NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException("Error al anular la infracción", e);
        }
    }

	@Override
	public Boolean delete(int id) {
		try {
			Infraccion registro = findById(id);
			repository.delete(registro);
			return true;
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		}catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Infraccion> findByNombre(String dni, int offset, int limit) {
	    try {
	        Pageable pageable = PageRequest.of(offset, limit);

	        Page<Infraccion> infraccionesPage = repository.findByDni(dni, pageable);

	        if (infraccionesPage.isEmpty()) {
	            throw new NoDataFoundException("No se encontraron infracciones para el usuario con DNI: " + dni);
	        }

	        return infraccionesPage.getContent();
	    } catch (Exception e) {
	        throw new GeneralServiceException("Error al obtener las infracciones del usuario", e);
	    }
	}
}
