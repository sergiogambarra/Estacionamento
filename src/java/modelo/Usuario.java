/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author sergio
 */

@Entity
@Table(name = "usuario")
public class Usuario {

    /**
     * @return the instituicao
     */
    public String getInstituicao() {
        return instituicao;
    }

    /**
     * @param instituicao the instituicao to set
     */
    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }
    
    @Id
    @GeneratedValue
    private int id_user;
    private String nome;
    private String vinculo;
    private String matriculaSIAPE;
    private String matricula;
    private String curso;
    private String empresa;
    private String instituicao;
    private String RG;
    
   @OneToMany(mappedBy="usuario")
    private List<Veiculo> cadastraVeic;

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the vinculo
     */
    public String getVinculo() {
        return vinculo;
    }

    /**
     * @param vinculo the vinculo to set
     */
    public void setVinculo(String vinculo) {
        this.vinculo = vinculo;
    }

    /**
     * @return the matricula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * @return the RG
     */
    public String getRG() {
        return RG;
    }

    /**
     * @param RG the RG to set
     */
    public void setRG(String RG) {
        this.RG = RG;
    }

    /**
     * @return the id_user
     */
    public int getId_user() {
        return id_user;
    }

    /**
     * @param id_user the id_user to set
     */
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    /**
     * @return the matriculaSIAPE
     */
    public String getMatriculaSIAPE() {
        return matriculaSIAPE;
    }

    /**
     * @param matriculaSIAPE the matriculaSIAPE to set
     */
    public void setMatriculaSIAPE(String matriculaSIAPE) {
        this.matriculaSIAPE = matriculaSIAPE;
    }

    /**
     * @return the curso
     */
    public String getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(String curso) {
        this.curso = curso;
    }

    /**
     * @return the empresa
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /**
     * @return the cadastraVeic
     */
    public List<Veiculo> getCadastraVeic() {
        return cadastraVeic;
    }

    /**
     * @param cadastraVeic the cadastraVeic to set
     */
    public void setCadastraVeic(List<Veiculo> cadastraVeic) {
        this.cadastraVeic = cadastraVeic;
    }
    
    
}
