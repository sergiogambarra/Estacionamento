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
import persistencia.ServidoresDAO;
import backingbeans.SisEstacionamentoBean;
import persistencia.ModeloDAO;

/**
 *
 * @author Sergio
 */

@FacesConverter(value="modeloConverter")
public class ModeloConverter implements Converter {
   
    
//    @Override
//    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
//        ServidoresDAO servidoresDAO = new ServidoresDAO();
//        Servidores servidores = servidoresDAO.buscaPorMatricula(string);
//        
//        return servidores;
//    }
    
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        return new ModeloDAO().buscarPorId(Integer.valueOf(value));
    }
    
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        Modelo modelo = new Modelo();
        modelo = (Modelo) value;
        return modelo.getNome();

//        Modelo m = (Modelo) value;
//
//        return String.valueOf(m.getId());
    }

//    @Override
//    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
//        Servidores servidores = new Servidores();
//        servidores = (Servidores) o;
//        return servidores.getMatricula();
//    }

}
