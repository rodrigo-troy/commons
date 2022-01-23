package com.rodrigotroy.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class DateHandler {

    public static String getDateUS(Date fecha) {
        if (fecha != null) {
            Calendar cal = Calendar.getInstance(Locale.US);
            cal.setTime(fecha);

            String mes;
            String dia;

            if ((cal.get(Calendar.MONTH) + 1) < 10) {
                mes = "0"
                      + (cal.get(Calendar.MONTH) + 1);
            } else {
                mes = String.valueOf((cal.get(Calendar.MONTH) + 1));
            }

            if (cal.get(Calendar.DATE) < 10) {
                dia = "0"
                      + cal.get(Calendar.DATE);
            } else {
                dia = String.valueOf(cal.get(Calendar.DATE));
            }

            return mes
                   + "/"
                   + dia
                   + "/"
                   + cal.get(Calendar.YEAR);
        } else {
            return "";
        }
    }

    public static String getDateCL(Date fecha) {
        if (fecha != null) {
            Calendar cal = Calendar.getInstance(Locale.US);
            cal.setTime(fecha);

            String mes;
            String dia = "";

            if ((cal.get(Calendar.MONTH) + 1) < 10) {
                mes = "0"
                      + (cal.get(Calendar.MONTH) + 1);
            } else {
                mes = String.valueOf((cal.get(Calendar.MONTH) + 1));
            }

            if (cal.get(Calendar.DATE) < 10) {
                dia = "0"
                      + cal.get(Calendar.DATE);
            } else {
                dia = String.valueOf(cal.get(Calendar.DATE));
            }

            return dia
                   + "/"
                   + mes
                   + "/"
                   + cal.get(Calendar.YEAR);
        } else {
            return "";
        }
    }

    public static int calculateDifference(Date a,
                                          Date b) {
        int difference = 0;
        Calendar earlier = Calendar.getInstance();
        Calendar later = Calendar.getInstance();

        earlier.setTime(a);
        later.setTime(b);

        int tempDifference;
        while (earlier.get(Calendar.YEAR) != later.get(Calendar.YEAR)) {
            tempDifference = 365 * (later.get(Calendar.YEAR) - earlier.get(Calendar.YEAR));
            difference += tempDifference;

            earlier.add(Calendar.DAY_OF_YEAR,
                        tempDifference);
        }

        if (earlier.get(Calendar.DAY_OF_YEAR) != later.get(Calendar.DAY_OF_YEAR)) {
            tempDifference = later.get(Calendar.DAY_OF_YEAR)
                             - earlier.get(Calendar.DAY_OF_YEAR);
            difference += tempDifference;

            earlier.add(Calendar.DAY_OF_YEAR,
                        tempDifference);
        }

        return difference;
    }

    public static Date agregarDias(Integer dias,
                                   Date date) {
        if (date != null
            && dias != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            cal.add(Calendar.DAY_OF_MONTH,
                    dias);

            return cal.getTime();
        }

        return null;
    }

    public static Date agregarMeses(int meses,
                                    Date date) {
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            cal.add(Calendar.MONTH,
                    meses);

            return cal.getTime();
        }

        return null;
    }

    public static Integer obtenerDia(Date date) {
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            return cal.get(Calendar.DAY_OF_MONTH);
        }

        return null;
    }

    public static Integer obtenerMes(Date date) {
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            return cal.get(Calendar.MONTH);
        }

        return null;
    }

    public static Integer obtenerAno(Date date) {
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            return cal.get(Calendar.YEAR);
        }

        return null;
    }

    public static Date construirFecha(Integer dia,
                                      Integer mes,
                                      Integer ano) {
        if (dia != null
            && dia < 32
            && mes != null
            && mes >= 0
            && mes < 13
            && ano != null
            && ano > 0) {
            Calendar cal = Calendar.getInstance();
            if (dia < 0) {
                cal.set(1,
                        mes,
                        dia);
            } else {
                cal.set(ano,
                        mes,
                        dia);
            }

            return cal.getTime();
        }

        return null;
    }

    public static String mesATexto(Integer mes) {
        if (mes != null) {
            switch (mes) {
                case 1:
                    return "Enero";
                case 2:
                    return "Febrero";
                case 3:
                    return "Marzo";
                case 4:
                    return "Abril";
                case 5:
                    return "Mayo";
                case 6:
                    return "Junio";
                case 7:
                    return "Julio";
                case 8:
                    return "Agosto";
                case 9:
                    return "Septiembre";
                case 10:
                    return "Octubre";
                case 11:
                    return "Noviembre";
                case 12:
                    return "Diciembre";
            }
        }

        return null;
    }
}
