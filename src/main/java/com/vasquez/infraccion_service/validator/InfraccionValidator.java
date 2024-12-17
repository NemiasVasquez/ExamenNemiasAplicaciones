package com.vasquez.infraccion_service.validator;

import com.vasquez.infraccion_service.entity.Infraccion;
import com.vasquez.infraccion_service.exception.ValidateServiceException;

import java.util.regex.Pattern;

public class InfraccionValidator {

    public static void save(Infraccion obj) {
        if (obj.getDni() == null || obj.getDni().trim().isEmpty()) {
            throw new ValidateServiceException("El DNI es requerido");
        }
        if (obj.getDni().length() != 8) {
            throw new ValidateServiceException("El DNI debe tener exactamente 8 caracteres");
        }
        if (!Pattern.matches("\\d{8}", obj.getDni())) {
            throw new ValidateServiceException("El DNI debe contener solo números");
        }

        if (obj.getFecha() == null) {
            throw new ValidateServiceException("La fecha es requerida");
        }

        if (obj.getTipoInfraccion() == null || obj.getTipoInfraccion().trim().isEmpty()) {
            throw new ValidateServiceException("El tipo de infracción es requerido");
        }
        if (obj.getTipoInfraccion().length() > 20) {
            throw new ValidateServiceException("El tipo de infracción no debe tener más de 20 caracteres");
        }

        if (obj.getUbicacion() == null || obj.getUbicacion().trim().isEmpty()) {
            throw new ValidateServiceException("La ubicación es requerida");
        }
        if (obj.getUbicacion().length() > 200) {
            throw new ValidateServiceException("La ubicación no debe tener más de 200 caracteres");
        }

        if (obj.getMontoMulta() == null) {
            throw new ValidateServiceException("El monto de la multa es requerido");
        }
        if (obj.getMontoMulta().compareTo(new java.math.BigDecimal("0")) <= 0) {
            throw new ValidateServiceException("El monto de la multa debe ser mayor que 0");
        }

        if (obj.getEstado() == null || obj.getEstado().trim().isEmpty()) {
            throw new ValidateServiceException("El estado es requerido");
        }
        if (obj.getEstado().length() > 20) {
            throw new ValidateServiceException("El estado no debe tener más de 20 caracteres");
        }
        if (!obj.getEstado().equalsIgnoreCase("pagado") && !obj.getEstado().equalsIgnoreCase("no pagado")) {
            throw new ValidateServiceException("El estado debe ser 'pagado' o 'no pagado'");
        }
    }
}
