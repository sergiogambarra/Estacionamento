/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */

@Entity
@Table(name = "placas")
public class Placas {

    /**
     * @return the fotoEntrda
     */
    public String getFotoEntrada() {
        return fotoEntrada;
    }

    /**
     * @param fotoEntrda the fotoEntrda to set
     */
    public void setFotoEntrada(String fotoEntrda) {
        this.fotoEntrada = fotoEntrda;
    }

    /**
     * @return the fotoSaida
     */
    public String getFotoSaida() {
        return fotoSaida;
    }

    /**
     * @param fotoSaida the fotoSaida to set
     */
    public void setFotoSaida(String fotoSaida) {
        this.fotoSaida = fotoSaida;
    }
    @Id
    @GeneratedValue
    private int id;
    private String placa;
    private String fotoEntrada;
    private String fotoSaida;
    private Date entrada;
    private Date saida;
    

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }


    /**
     * @return the entrada
     */
    public Date getEntrada() {
        return entrada;
    }

    /**
     * @param entrada the entrada to set
     */
    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    /**
     * @return the saida
     */
    public Date getSaida() {
        return saida;
    }

    /**
     * @param saida the saida to set
     */
    public void setSaida(Date saida) {
        this.saida = saida;
    }
    
}
