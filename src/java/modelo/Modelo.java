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
@Table(name = "modelo")
public class Modelo {
    @Id
    @GeneratedValue
    private int id;
    private String nome;
    
}
