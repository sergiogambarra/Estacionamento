/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingbeans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

    /**
     * @return the placas
     */
    public Placas getPlacas() {
        return placas;
    }

    /**
     * @param placas the placas to set
     */
    public void setPlacas(Placas placas) {
        this.placas = placas;
    }
    
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    
    private Usuario usuario = new Usuario();
    private Veiculo veiculo = new Veiculo();
    private Placas placas = new Placas();
    
    private List<Usuario> listaUsuarios;
    private List<Placas> listaPlacas;
    
    private final UsuarioDAO usuarioDao = new UsuarioDAO();
    private final VeiculoDAO veiculoDao = new VeiculoDAO();
    private final PlacasDAO placasDao = new PlacasDAO();
    
    public SisEstacionamentoBean() {
        listaUsuarios = usuarioDao.listar();
        listaPlacas = placasDao.listar();
    }
   
          
    //usuario
    public String incluirUsuario() throws Exception {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg;
        usuarioDao.incluir(usuario);
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Usuario cadastrado com Sucesso!", "");
        context.addMessage(null, msg);
        if (veiculo != null){
            VeiculoDAO veiculoDao = new VeiculoDAO();
            veiculoDao.incluir(veiculo);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Veiculo incluído com Sucesso!", "");
        context.addMessage(null, msg);
        }
        usuario = new Usuario();
        veiculo = new Veiculo();
        return "incluirUsuario";
    }
    
    public String consultarUsuario(int id) {
        usuario = usuarioDao.carregar(id);//idUsuario
        return "consultaUsuario";
    }
    
    public String iniciaAlteracaoUsuario(int id) {
        usuario = usuarioDao.carregar(id);
        return "alterarUsuario";
    }
    
    
    //veiculo
    public String incluirVeiculo() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg;
        VeiculoDAO veiDAO = new VeiculoDAO();
        return null;
    }
    
    //placas capturadas
    public Placas incluirPlaca(Placas pla) {
        listaPlacas = placasDao.listar();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        for (int i =0;i<listaPlacas.size();i++){
            if (pla.getPlaca() == listaPlacas.get(i).getPlaca() && listaPlacas.get(i).getEntrada().before(date)){
                
                
            } 
        }
        PlacasDAO placasDao = new PlacasDAO();
        pla.setEntrada(date);
        
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg;
        placasDao.incluir(pla);
        placas = new Placas();
        return this.placas;
    }
    
}
