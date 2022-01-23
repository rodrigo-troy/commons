package com.rodrigotroy.commons.domain;

import com.rodrigotroy.commons.util.DigitoVerificador;
import com.rodrigotroy.commons.util.Validator;
import org.apache.commons.lang3.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gathercommons
 * User: rodrigotroy
 * Date: 11/14/17
 * Time: 12:49
 * Clase para manipular campos RUT que vienen desde el origen con DV.
 */
public class Rut {
    private String rutSinDV;
    private String rutConDV;
    private String digitoVerificador;

    public Rut(String rutConDV) {
        if (Validator.validateRUT(rutConDV)) {
            this.rutConDV = rutConDV;
            this.rutSinDV = rutConDV.substring(0,
                                               rutConDV.indexOf("-")).replaceAll("\\.",
                                                                                 "");
            this.digitoVerificador = rutConDV.substring(rutConDV.indexOf("-") + 1,
                                                        rutConDV.indexOf("-") + 2).toUpperCase();
        }
    }

    public String getDigitoVerificadorCalculado() {
        return DigitoVerificador.calcular(Integer.parseInt(this.getRutSinDV()));
    }

    public Boolean esValido() {
        boolean rutEsValido;

        if (Validator.validateRUT(this.rutConDV)) {
            final Boolean rutSinDVEsNumerico = StringUtils.isNumeric(rutSinDV);
            final Boolean dvEsValido = DigitoVerificador.calcular(Integer.parseInt(rutSinDV)).equals(digitoVerificador);

            rutEsValido = rutSinDVEsNumerico && dvEsValido;
        } else {
            rutEsValido = false;
        }

        return rutEsValido;
    }

    public String getRutSinDV() {
        if (rutSinDV == null) {
            rutSinDV = "";
        }

        return rutSinDV;
    }

    public String getRutConDV() {
        if (rutConDV == null) {
            rutConDV = "";
        }

        return rutConDV;
    }

    public String getDigitoVerificador() {
        if (digitoVerificador == null) {
            digitoVerificador = "";
        }

        return digitoVerificador;
    }
}
