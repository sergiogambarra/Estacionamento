/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingbeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Placas;
import modelo.Usuario;
import modelo.Veiculo;
import persistencia.PlacasDAO;
import persistencia.UsuarioDAO;
import persistencia.VeiculoDAO;

/**
 *
 * @author Sergio
 */


@ManagedBean
@SessionScoped

public class SisEstacionamentoBean {
    private Usuario usuario = new Usuario();
    private Veiculo veiculo = new Veiculo();
    private Placas placas = new Placas();
          
    //usuario
    public String incluirUsuario() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg;
        UsuarioDAO usuDAO = new UsuarioDAO();
        return null;
    }
    
    //veiculo
    public String incluirVeiculo() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg;
        VeiculoDAO veiDAO = new VeiculoDAO();
        return null;
    }
    
    //placas capturadas
    public Placas incluirPlaca() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg;
        PlacasDAO placDAO = new PlacasDAO();
        return null;
    }
    
}
