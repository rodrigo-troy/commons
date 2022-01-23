package com.rodrigotroy.util;

/**
 * Created by rodrigotroy on 11/6/14.
 */
public class MesesUtil {
    private final String[] meses = new String[]{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

    public final String[] getMeses() {
        return meses;
    }

    public final int getNumeroMes(String mes) {
        for (int i = 1; i < 13; i++) {
            if (meses[i - 1].equals(mes)) {
                return i;
            }
        }

        return -1;
    }

    public final String getMes(Integer index) {
        return index < 12 && index > -1 ? this.getMeses()[index] : "";
    }
}
