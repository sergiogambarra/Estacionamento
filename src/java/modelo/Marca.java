/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */

@Entity
@Table(name = "marca")
public class Marca {


    /**
     * @return the codprivate int id;
    private String nome;
     */
    public Integer getCod() {
        return cod;
    }

    /**
     * @param cod the cod to set
     */
    public void setCod(Integer cod) {
        this.cod = cod;
    }

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
    
    @Id
    private int cod;
    private String nome;
    
    
//    @OneToMany
//    @JoinColumn(name="id_mod")
//    private List<Modelo> modelo;
    
 
    public Marca(){
 
    }
 
    public Marca(Integer cod, String nome){
 
        this.cod = cod;
        this.nome = nome;
    }

    
    
}