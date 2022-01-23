package com.rodrigotroy.util;

public class DigitoVerificador {
    private DigitoVerificador() {
        throw new IllegalStateException("Utility class");
    }

    public static String calcular(int rut) {
        int aux = rut;
        int digito;
        int factor = 2;
        int suma = 0;
        int dv;

        while (aux != 0) {
            digito = aux % 10;
            suma = suma + factor * digito;
            aux /= 10;
            factor++;

            if (factor == 8) {
                factor = 2;
            }
        }

        dv = 11 - suma % 11;

        if (dv == 11) {
            return "0";
        } else if (dv == 10) {
            return "K";
        } else {
            return String.valueOf(dv);
        }
    }

    public static Boolean validarRUT(String rut) {
        return Validator.validateRUT(rut);
    }
}
