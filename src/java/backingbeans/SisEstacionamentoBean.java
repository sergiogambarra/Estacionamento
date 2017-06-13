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
import javax.faces.bean.ViewScoped;
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
@ViewScoped

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
   private Integer progress;
          
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
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date d2 = new Date();
        String data2Formatada = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(d2);
        listaPlacas = placasDao.listar();
        Date d1 = listaPlacas.get(listaPlacas.size()-1).getEntrada();
	String data1Formatada = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(d1);
        try {
            d1 = dateFormat.parse(data1Formatada);
            d2 = dateFormat.parse(data2Formatada);
            long diferenca = d2.getTime() - d1.getTime();
            System.out.println(diferenca);
            long diffSeconds = diferenca / 1000 % 60;
            System.out.print(diffSeconds + " segundos, ");
            long diffMinutes = diferenca / (60 * 1000) % 60;
            System.out.print(diffMinutes + " minutos, ");
            if (((d2.getTime() - d1.getTime())/ 1000 % 60) + (((d2.getTime() - d1.getTime())/ (60 * 1000) % 60)*60) > 20){
                PlacasDAO placasDao = new PlacasDAO();
                pla.setEntrada(d2);
                placasDao.incluir(pla);
                d1 = d2;
            } 
            
        } catch (Exception e) {
			e.printStackTrace();
		}
        
   
        
        d2 = new Date();
        
        
        placas = new Placas();
        return this.placas;
    }
    
    //Upload CSV de Alunos
    
    public void lerCSVAlunos() throws Exception
    {
        listaAlunos.removeAll(listaAlunos);
        List<Alunos> listaNovaAlunos = listaAlunos;
        listaAlunos = alunosDao.listar();
        //listaAlunos = alunosDao.listar();
        CsvToBean csv = new CsvToBean();

        //String csvFilename = "C:\\Users\\Sergio\\Documents\\NetBeansProjects\\Git\\Estacionamento\\alunos.csv";
        String csvFilename = "/home/sergio/NetBeansProjects/Estacionamento/alunos.csv";
        CSVReader csvReader = new CSVReader(new FileReader(csvFilename),';', '\'', 1);
        //CSVReader reader=new CSVReader(new InputStreamReader(new FileInputStream("d:\\a.csv"), "UTF-8"), ',', '\'', 1);

        //Set column mapping strategy
        List list = csv.parse(mapearColunasCSVAlunos(), csvReader);
        
        int i=0;

        for (Object object : list) {
            i++;
            alunos = (Alunos) object;
            listaNovaAlunos.add(alunos);
            alunosDao.incluir(alunos);
            progress =  i * 100 / list.size();
            System.out.println(progress);
            this.setProgress(progress);
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
        List<Servidores> listaNovaServidores = listaServidores;
        listaServidores = servidoresDao.listar();
        CsvToBean csv = new CsvToBean();

        //String csvFilename = "C:\\Users\\Sergio\\Documents\\NetBeansProjects\\Git\\Estacionamento\\servidores.csv";
        String csvFilename = "/home/sergio/NetBeansProjects/Estacionamento/servidores.csv";
        CSVReader csvReader = new CSVReader(new FileReader(csvFilename));

        //Set column mapping strategy
        List list = csv.parse(mapearColunasCSVServidores(), csvReader);
        
        int i=0;
        
        for (Object object : list) {
            i++;
            servidores = (Servidores) object;
            listaNovaServidores.add(servidores);
            servidoresDao.incluir(servidores);
            progress =  i * 100 / list.size();
            System.out.println(progress);
            this.setProgress(progress);
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
    
    
 
    public Integer getProgress() {
        if(progress == null) {
            progress = 0;
        }
        
         
        return progress;
    }
 
    public void setProgress(Integer progress) {
        this.progress = progress;
    }
     
    public void onComplete() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Lista de Servidores Atualizada"));
    }
     
    public void cancel() {
        progress = null;
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
