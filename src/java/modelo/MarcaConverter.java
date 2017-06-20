/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import persistencia.MarcaDAO;

/**
 *
 * @author Sergio
 */

@FacesConverter("marcaConverter")
public class MarcaConverter implements Converter {
    
    /**
     * Recebe o código da marca como String, realiza uma busca por código e retorna
     * a marca
     * @param context
     * @param component
     * @param value
     * @return 
     */
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        return new MarcaDAO().buscarPorCod(Integer.valueOf(value));
    }

    /**
     * Recupera o objeto Cidade e retorna o código da cidade como String
     * @param context
     * @param component
     * @param value
     * @return 
     */
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        Marca m = (Marca) value;

        return String.valueOf(m.getCod());
    }
    
}
