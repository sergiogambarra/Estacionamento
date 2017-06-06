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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Alunos;
import modelo.Placas;
import modelo.Usuario;
import modelo.Veiculo;
import persistencia.AlunosDAO;
import persistencia.PlacasDAO;
import persistencia.UsuarioDAO;
import persistencia.VeiculoDAO;

/**
 *
 * @author Sergio
 */


@ManagedBean
@SessionScoped

public class SisEstacionamentoBean1 {

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
    private List<Alunos> listaAlunos;
    
    private final UsuarioDAO usuarioDao = new UsuarioDAO();
    private final VeiculoDAO veiculoDao = new VeiculoDAO();
    private final PlacasDAO placasDao = new PlacasDAO();
    private final AlunosDAO alunosDao = new AlunosDAO();
    
    public SisEstacionamentoBean1() {
        listaUsuarios = usuarioDao.listar();
        listaPlacas = placasDao.listar();
        listaAlunos = alunosDao.listar();
        
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
            listaUsuarios = usuarioDao.listar();
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
    
    public List<Alunos>lerCSVAlunos(String CSV) throws Exception
    {
        //listaAlunos = alunosDao.listar();
        CsvToBean csv = new CsvToBean();

        String csvFilename = "listagem.csv";
        CSVReader csvReader = new CSVReader(new FileReader(csvFilename),';', '\'', 1);
        //CSVReader reader=new CSVReader(new InputStreamReader(new FileInputStream("d:\\a.csv"), "UTF-8"), ',', '\'', 1);

        //Set column mapping strategy
        List list = csv.parse(setColumMapping(), csvReader);


        for (Object object : list) {
            listaAlunos.addAll(list);
            Alunos alunos = (Alunos) object;
            System.out.println(alunos.getNome());
        }
        return listaAlunos;
    }
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    private static ColumnPositionMappingStrategy setColumMapping()
    {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(Alunos.class);
        String[] columns = new String[] {"matricula", "nome", "matriz_curricular", "situacao", "ingresso"};
        strategy.setColumnMapping(columns);
        return strategy;
    }
    
}
