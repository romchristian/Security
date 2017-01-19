/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.security.web;



import com.ideaspymes.security.cuenta.Cuenta;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author christian
 */
@FacesConverter(forClass = Cuenta.class,value = "cuentaConverter")
public class CuentaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }

        return getController(facesContext).find(getKey(value));
    }

    public CuentaBean getController(FacesContext facesContext) {
        return (CuentaBean) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "cuentaBean");
    }

    java.lang.Long getKey(String value) {
        java.lang.Long key;
        try {
            key = Long.valueOf(value);
        } catch (Exception e) {
            key = 0L;
        }
        return key;
    }

    String getStringKey(java.lang.Long value) {
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }

        try {

            Cuenta o = (Cuenta) object;
            return getStringKey(o.getId());
        } catch (Exception e) {
            return null;
        }

    }
}
