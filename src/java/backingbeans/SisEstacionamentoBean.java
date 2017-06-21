/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingbeans;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import modelo.Alunos;
import modelo.Marca;
import modelo.Modelo;
import modelo.Outros;
import modelo.Placas;
import modelo.Usuario;
import modelo.Veiculo;
import modelo.Servidores;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.primefaces.event.SelectEvent;

import org.primefaces.model.UploadedFile;
import persistencia.AlunosDAO;
import persistencia.MarcaDAO;
import persistencia.ModeloDAO;
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
     * @return the listaModelos
     */
    public List<Modelo> getListaModelos() {
        return listaModelos;
    }

    /**
     * @param listaModelos the listaModelos to set
     */
    public void setListaModelos(List<Modelo> listaModelos) {
        this.listaModelos = listaModelos;
    }

    /**
     * @return the modelo
     */
    public Modelo getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the file
     */
    public UploadedFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(UploadedFile file) {
        this.file = file;
    }

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

    private UploadedFile file;
    private Servidores servidorSelecionado;
    private Marca marca = new Marca();
    private Modelo modelo = new Modelo();
    
    private Usuario usuario = new Usuario();
    private Veiculo veiculo = new Veiculo();
    private Placas placas = new Placas();
    private Servidores servidores;
    private Alunos alunos = new Alunos();
    private Outros outros = new Outros();
    
    
    private List<Usuario> listaUsuarios;
    private List<Usuario> listaUsuariosCadastrados;
    private List<Placas> listaPlacas;
    private List<Alunos> listaAlunos;
    private List<Veiculo> listaVeiculos;
    private List<Servidores> listaServidores;
    private List<Modelo> listaModelos;
    
    private final UsuarioDAO usuarioDao = new UsuarioDAO();
    private final VeiculoDAO veiculoDao = new VeiculoDAO();
    private final PlacasDAO placasDao = new PlacasDAO();
    private final AlunosDAO alunosDao = new AlunosDAO();
    private final ServidoresDAO servidoresDao = new ServidoresDAO();
    private MarcaDAO marcaDao;
    private ModeloDAO modeloDao = new ModeloDAO();
    
    @PostConstruct
    private void init(){
        marcaDao = new MarcaDAO();
    }
    
    public SisEstacionamentoBean() {
        listaUsuarios = usuarioDao.listar();
        listaUsuariosCadastrados = usuarioDao.listarUsuariosCadastrados();
        listaPlacas = placasDao.listar();
        listaVeiculos = veiculoDao.listar();
        listaAlunos = alunosDao.listar();
        listaModelos = modeloDao.listar();
        listaServidores = servidoresDao.listar();
        
    }
   private Integer progress;
          
    //usuario
    public String incluirUsuario() throws Exception {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg;
        this.outros.setVinculo(this.usuario.getVinculo());
        this.alunos.setVinculo(this.usuario.getVinculo());
        this.servidores.setVinculo(this.usuario.getVinculo());
        if (this.usuario.getVinculo().equals("Servidor")){
            usuarioDao.incluir(this.servidores);
        } else if(this.usuario.getVinculo().equals("Aluno")){
            usuarioDao.incluir(this.alunos);
        } else {
            usuarioDao.incluir(this.outros);
        }
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Usuario cadastrado com Sucesso!", "");
        context.addMessage(null, msg);
        if (veiculo.getPlaca() != null){
            usuarioDao = new UsuarioDAO();
            setListaUsuarios(usuarioDao.listar());
            ArrayList<Integer> ids_users = new ArrayList<Integer>();
            for (int i = 0; i< this.listaUsuarios.size();i++){
                ids_users.add(this.listaUsuarios.get(i).getIdUsuario());
            }
            int lastIdUsuario = Collections.max(ids_users);
            this.usuario.setIdUsuario(lastIdUsuario);
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
    
    public List<Servidores> completaNomeServidores(String query) {
        this.listaServidores = servidoresDao.listar();
        List<Servidores> sugestoes = new ArrayList<Servidores>();
        for (Servidores s : this.listaServidores) {
            if (s.getMatricula().startsWith(query)) {
                sugestoes.add(s);
            }
        }
        return sugestoes;
    }
    
    public List<Alunos> completaNomeAlunos(String query) {
        this.listaAlunos = alunosDao.listar();
        List<Alunos> sugestoes = new ArrayList<Alunos>();
        for (Alunos a : this.listaAlunos) {
            if (a.getMatricula().startsWith(query)) {
                sugestoes.add(a);
            }
        }
        return sugestoes;
    }
     
    //veiculo
    
    
    public String incluirVeiculo() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg;
        this.modelo = new Modelo();
        
        if (veiculo.getPlaca() != null){
            
        }
        marcaDao.incluir(marca);
        this.modelo.setMarca(marca);
        modeloDao.incluir(modelo);
        
        veiculo.setModelo(modelo);
        if (usuario.getVinculo().equals("Aluno")){
            veiculo.setUsuario(alunos);
        } else if (usuario.getVinculo().equals("Servidor")){
            veiculo.setUsuario(servidores);
        } else{
            veiculo.setUsuario(outros);
        }
        veiculoDao.incluir(veiculo);
        
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Veiculo incluído com Sucesso!", "");
        context.addMessage(null, msg);
        
        return "index";
    }
    
    public String incluirVeiculo2() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg;
        int id = 0;
        
        listaAlunos = alunosDao.listar();
        listaServidores = servidoresDao.listar();
        
        if (veiculo.getPlaca() != null){
            if (servidores != null){
                for (int k =0; k<listaServidores.size(); k++){
                    if (listaServidores.get(k).getMatricula().equals(servidores.getMatricula())){
                        id = listaServidores.get(k).getIdUsuario();
                    }
                }
            }
            if (alunos != null){
                for (int k =0; k < listaAlunos.size(); k++){
                    if (listaAlunos.get(k).getMatricula().equals(alunos.getMatricula())){
                        id = listaAlunos.get(k).getIdUsuario();
                    }
                }
            }
            
            if (id > 0 ){
                this.usuario.setIdUsuario(id);
            } else {
                this.outros.setVinculo(this.usuario.getVinculo());
                this.alunos.setVinculo(this.usuario.getVinculo());
                this.servidores.setVinculo(this.usuario.getVinculo());
                if (this.usuario.getVinculo().equals("Servidor")){
                    usuarioDao.incluir(this.servidores);
                } else if(this.usuario.getVinculo().equals("Aluno")){
                    usuarioDao.incluir(this.alunos);
                } else {
                    usuarioDao.incluir(this.outros);
                }
                
                setListaUsuarios(usuarioDao.listar());
                ArrayList<Integer> ids_users = new ArrayList<Integer>();
                for (int i = 0; i< this.listaUsuarios.size();i++){
                    ids_users.add(this.listaUsuarios.get(i).getIdUsuario());
                }
                int lastIdUsuario = Collections.max(ids_users);
                this.usuario.setIdUsuario(lastIdUsuario);
            }
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Usuario cadastrado com Sucesso!", "");
                context.addMessage(null, msg);

            this.veiculo.setUsuario(usuario);
            VeiculoDAO veiculoDao = new VeiculoDAO();
            veiculoDao.incluir(veiculo);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Veiculo incluído com Sucesso!", "");
        context.addMessage(null, msg);
        }
        return "index";
    }
    
    //placas capturadas
    public Placas incluirPlaca(Placas pla) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date d2 = new Date();
        String data2Formatada = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(d2);
        String data2FormatadaDia = new SimpleDateFormat("MM/dd/yyyy").format(d2);
        
        Date d1;
        String data1Formatada;
        String fotoSaida = "";
        
        setListaPlacas(placasDao.listar());
//        if (getListaPlacas().size() > 0 ){
//            d1 = getListaPlacas().get(getListaPlacas().size()-1).getEntrada();
//        } else {
//            
//        }
        
        //String data1Formatada = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(d1);
        
        
        //Verifica se a data de entrada vinda do banco nao vem vazia
        //Caso o banco esteja limpo
        if (getListaPlacas().size() == 0 || getListaPlacas().get(getListaPlacas().size()-1).getEntrada() == null){
            data1Formatada = "01/01/2000 23:59:59";
            
        } else if (getListaPlacas().get(getListaPlacas().size()-1).getSaida() == null){
            d1 = getListaPlacas().get(getListaPlacas().size()-1).getEntrada();
            data1Formatada = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(d1);
        } else {
            d1 = getListaPlacas().get(getListaPlacas().size()-1).getSaida();
            data1Formatada = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(d1);
        }
        
        try {
            d1 = dateFormat.parse(data1Formatada);
            d2 = dateFormat.parse(data2Formatada);
            long diferenca = d2.getTime() - d1.getTime();
            System.out.println(diferenca);
            long diffSeconds = diferenca / 1000 % 60;
            System.out.print(diffSeconds + " segundos, ");
            long diffMinutes = diferenca / (60 * 1000) % 60;
            System.out.print(diffMinutes + " minutos, ");
            long diffHours = diferenca / (60 * 60 * 1000); 
            System.out.print(diffHours + " horas, ");
            
            // && listaPlacas.get(i).getSaida().equals(null)
            
            if (diffSeconds + (diffMinutes * 60) + (diffHours * 60 *60) > 20){
                boolean entrada = true;
                
                for (int i =0;i<getListaPlacas().size();i++){
                    if (getListaPlacas().get(i).getPlaca().equals(pla.getPlaca()) && getListaPlacas().get(i).getSaida() == null){
                        fotoSaida = pla.getFotoEntrada();
                        pla = getListaPlacas().get(i);
                        entrada = false;
                    } 
                }
                if (entrada == false){
                    PlacasDAO placasDao = new PlacasDAO();
                    pla.setSaida(d2);
                    pla.setFotoSaida(fotoSaida);
                    placasDao.alterar(pla);
                    d1 = new Date();
                    
                } else {
                    PlacasDAO placasDao = new PlacasDAO();
                    pla.setEntrada(d2);
                    placasDao.incluir(pla);
                    d1 = new Date();
                    
                }
                
            } 
            
        } catch (Exception e) {
			e.printStackTrace();
		}
        
   
        
        d2 = new Date();
        
        
        placas = new Placas();
        return this.placas;
    }
    
    //Upload CSV de Alunos
    //Upload CSV de Servidores
    public void upload() throws IOException {
        listaServidores = servidoresDao.listar();
        this.servidores = new Servidores();
        AlunosDAO alunosDao = new AlunosDAO();
        listaAlunos = alunosDao.listar();
        InputStream input = getFile().getInputstream();
        InputStream input2 = input;
        
//        BufferedReader in = new BufferedReader(new InputStreamReader(input2));
//        String line = null;
//        progress = 0;
//        StringBuilder responseData = new StringBuilder();
//        while((line = in.readLine()) != null) {
//            responseData.append(line);
//            progress++;
//        }
//        System.out.println(progress);
        
        if (file != null) {
            int i=0;
            CSVParser parser = new CSVParser(new InputStreamReader(input, StandardCharsets.ISO_8859_1),CSVFormat.EXCEL.withHeader().withDelimiter(';'));
            int gravados = 0;
            boolean gravar = true;
            for (CSVRecord record : parser) {
                i++;
                if (record.isMapped("MATRICULA")){
                    gravar = true;
                    for (int k =0 ; k<listaServidores.size();k++){
                        if (listaServidores.get(k).getMatricula().equals(record.get("MATRICULA"))){
                            gravar = false;
                        }
                    }
                    this.servidores.setMatricula(record.get("MATRICULA"));
                    this.servidores.setNome(record.get("SERVIDOR"));
                    this.servidores.setCargo(record.get("CARGO EMPREGO"));
                    this.servidores.setVinculo("Servidor");
                    if (gravar){
                        servidoresDao.incluir(servidores);
                        gravados++;
                    }
                } else {
                    gravar = true;
                    for (int j =0 ; j<listaAlunos.size();j++){
                        if (listaAlunos.get(j).getMatricula().equals(record.get("Matrícula"))){
                            gravar = false;
                        }
                    }
                    this.alunos.setMatricula(record.get("Matrícula"));
                    this.alunos.setNome(record.get("Nome"));
                    this.alunos.setCurso(record.get("Curso"));
                    this.alunos.setVinculo("Aluno");
                    if (gravar){
                        alunosDao.incluir(alunos);
                        gravados++;
                    }
                }
//                progress =  i * 100 / record.size();
//                System.out.println(progress);
                System.out.println(gravados);
//                this.setProgress(progress);
            }
            parser.close();
            FacesMessage message = new FacesMessage("Sucesso! ", file.getFileName()+" enviado e " + gravados +" registros foram inserido!");
            
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
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

    /**
     * @return the outros
     */
    public Outros getOutros() {
        return outros;
    }

    /**
     * @param outros the outros to set
     */
    public void setOutros(Outros outros) {
        this.outros = outros;
    }

    /**
     * @return the listaPlacas
     */
    public List<Placas> getListaPlacas() {
        return listaPlacas;
    }

    /**
     * @param listaPlacas the listaPlacas to set
     */
    public void setListaPlacas(List<Placas> listaPlacas) {
        this.listaPlacas = listaPlacas;
    }

    /**
     * @return the listaAlunos
     */
    public List<Alunos> getListaAlunos() {
        return listaAlunos;
    }

    /**
     * @param listaAlunos the listaAlunos to set
     */
    public void setListaAlunos(List<Alunos> listaAlunos) {
        this.listaAlunos = listaAlunos;
    }

    /**
     * @return the listaVeiculos
     */
    public List<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    /**
     * @param listaVeiculos the listaVeiculos to set
     */
    public void setListaVeiculos(List<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    /**
     * @return the listaUsuariosCadastrados
     */
    public List<Usuario> getListaUsuariosCadastrados() {
//        for (int i=0;i<listaVeiculos.size();i++){
//            listaUsuariosCadastrados.add(listaVeiculos.get(i).getUsuario());
//        }
        return listaUsuariosCadastrados;
    }

    /**
     * @param listaUsuariosCadastrados the listaUsuariosCadastrados to set
     */
    public void setListaUsuariosCadastrados(List<Usuario> listaUsuariosCadastrados) {
        this.listaUsuariosCadastrados = listaUsuariosCadastrados;
    }


    /**
     * @return the servidorSelecionado
     */
    public Servidores getServidorSelecionado() {
        return servidorSelecionado;
    }

    /**
     * @param servidorSelecionado the servidorSelecionado to set
     */
    public void setServidorSelecionado(Servidores servidorSelecionado) {
        this.servidorSelecionado = servidorSelecionado;
    }
    
    public List<Marca> completeMetodo(String query){
 
        return marcaDao.buscar(query);
    }
    public List<Modelo> completeModelo(String query){
        this.listaModelos = modeloDao.listar();
        List<Modelo> sugestoes = new ArrayList<Modelo>();
        for (Modelo m : this.listaModelos) {
            if (m.getNome().startsWith(query)) {
                sugestoes.add(m);
            }
        }
        return sugestoes;
    }
    
    
 
    public void submit(ActionEvent event){
 
        System.out.println(marca.getCod() + " - " + marca.getNome());
 
    }
    
    public void handleSelectServidor(SelectEvent event) {
        this.servidores = (Servidores) event.getObject();
    }
    
    public void handleSelectAluno(SelectEvent event) {
        this.alunos = (Alunos) event.getObject();
    }
    
    /**
     * @return the marca
     */
    public Marca getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(Marca marca) {
        this.marca = marca;
    }

}
