package com.rodrigotroy.commons.model;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gather-commons
 * User: rodrigotroy
 * Date: 8/8/17
 * Time: 11:56
 */
public interface IEnum {
    /**
     * @return un Integer unico para el grupo al cual pertenece el enum
     */
    Integer getID();

    /**
     * @return un String para desplegar en pantalla
     */
    String getLabel();
}
