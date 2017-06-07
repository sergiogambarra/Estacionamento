/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingbeans;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import java.io.FileReader;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.spi.Context;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Alunos;
import modelo.Placas;
import modelo.Usuario;
import modelo.Veiculo;
import modelo.Servidores;
import persistencia.AlunosDAO;
import persistencia.PlacasDAO;
import persistencia.UsuarioDAO;
import persistencia.VeiculoDAO;
import persistencia.ServidoresDAO;

/**
 *
 * @author Sergio
 */


@ManagedBean
@SessionScoped

public class SisEstacionamentoBean implements Serializable{

    /**
     * @return the servidores
     */
    public Servidores getServidores() {
        return servidores;
    }

    /**
     * @param servidores the servidores to set
     */
    public void setServidores(Servidores servidores) {
        this.servidores = servidores;
    }

    /**
     * @param listaUsuarios the listaUsuarios to set
     */
    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    /**
     * @return the alunos
     */
    public Alunos getAlunos() {
        return alunos;
    }

    /**
     * @param alunos the alunos to set
     */
    public void setAlunos(Alunos alunos) {
        this.alunos = alunos;
    }


    
    private Usuario usuario = new Usuario();
    private Veiculo veiculo = new Veiculo();
    private Placas placas = new Placas();
    private Servidores servidores = new Servidores();
    private Alunos alunos = new Alunos();
    
    
    private List<Usuario> listaUsuarios;
    private List<Placas> listaPlacas;
    private List<Alunos> listaAlunos;
    private List<Servidores> listaServidores;
    
    private final UsuarioDAO usuarioDao = new UsuarioDAO();
    private final VeiculoDAO veiculoDao = new VeiculoDAO();
    private final PlacasDAO placasDao = new PlacasDAO();
    private final AlunosDAO alunosDao = new AlunosDAO();
    private final ServidoresDAO servidoresDao = new ServidoresDAO();
    
    
    public SisEstacionamentoBean() {
        listaUsuarios = usuarioDao.listar();
        listaPlacas = placasDao.listar();
        listaAlunos = alunosDao.listar();
        listaServidores = servidoresDao.listar();
        
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
        if (veiculo.getPlaca() != null){
            usuarioDao = new UsuarioDAO();
            setListaUsuarios(usuarioDao.listar());
            ArrayList<Integer> ids_users = new ArrayList<Integer>();
            for (int i = 0; i< this.listaUsuarios.size();i++){
                ids_users.add(this.listaUsuarios.get(i).getId_user());
            }
            int lastIDuser = Collections.max(ids_users);
            this.usuario.setId_user(lastIDuser);
            this.veiculo.setUsuario(usuario);
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
    
    //Upload CSV de Alunos
    
    public void lerCSVAlunos() throws Exception
    {
        listaAlunos.removeAll(listaAlunos);
        listaAlunos = alunosDao.listar();
        //listaAlunos = alunosDao.listar();
        CsvToBean csv = new CsvToBean();

        //String csvFilename = "C:\\Users\\Sergio\\Documents\\NetBeansProjects\\Git\\Estacionamento\\listagem.csv";
        String csvFilename = "/home/sergio/NetBeansProjects/Estacionamento/alunos.csv";
        CSVReader csvReader = new CSVReader(new FileReader(csvFilename),';', '\'', 1);
        //CSVReader reader=new CSVReader(new InputStreamReader(new FileInputStream("d:\\a.csv"), "UTF-8"), ',', '\'', 1);

        //Set column mapping strategy
        List list = csv.parse(mapearColunasCSVAlunos(), csvReader);
        
        

        for (Object object : list) {
            alunos = (Alunos) object;
            listaAlunos.add(alunos);
            alunosDao.incluir(alunos);
        }
    }
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    private static ColumnPositionMappingStrategy mapearColunasCSVAlunos()
    {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(Alunos.class);
        String[] columns = new String[] {"matricula", "nome", "curso"};
        strategy.setColumnMapping(columns);
        return strategy;
    }
    
    //Upload CSV de Servidores
    
    public void lerCSVServidores() throws Exception
    {
        listaServidores.removeAll(listaServidores);
        listaServidores = servidoresDao.listar();
        //listaAlunos = alunosDao.listar();
        CsvToBean csv = new CsvToBean();

        //String csvFilename = "C:\\Users\\Sergio\\Documents\\NetBeansProjects\\Git\\Estacionamento\\listagem.csv";
        String csvFilename = "/home/sergio/NetBeansProjects/Estacionamento/servidores.csv";
        CSVReader csvReader = new CSVReader(new FileReader(csvFilename));
        //CSVReader reader=new CSVReader(new InputStreamReader(new FileInputStream("d:\\a.csv"), "UTF-8"), ',', '\'', 1);

        //Set column mapping strategy
        List list = csv.parse(mapearColunasCSVServidores(), csvReader);
        
        

        for (Object object : list) {
            servidores = (Servidores) object;
            listaServidores.add(servidores);
            servidoresDao.incluir(servidores);
        }
    }
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    private static ColumnPositionMappingStrategy mapearColunasCSVServidores()
    {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(Servidores.class);
        String[] columns = new String[] {"matricula", "nome"};
        strategy.setColumnMapping(columns);
        return strategy;
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

}
