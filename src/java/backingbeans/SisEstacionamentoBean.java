/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Usuario;
import modelo.Veiculo;

/**
 *
 * @author Sergio
 */


@ManagedBean
@SessionScoped

public class SisEstacionamentoBean {
    private Usuario usuario = new Usuario();
    private Veiculo veiculo = new Veiculo();
    
    public SisEstacionamentoBean() {
        //listaEventos = evtDao.listar();
        
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the veiculo
     */
    public Veiculo getVeiculo() {
        return veiculo;
    }

    /**
     * @param veiculo the veiculo to set
     */
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    
}
