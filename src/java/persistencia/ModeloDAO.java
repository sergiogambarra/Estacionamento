/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.ArrayList;
import java.util.List;
import modelo.Marca;
import modelo.Modelo;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Sergio
 */
public class ModeloDAO {
    private List<Marca> marcas = new ArrayList<>();
    private Session sessao;
    
 
    public ModeloDAO(){
        
        sessao = HibernateUtil.getSessionFactory().openSession();
 
    }
    
    public void incluir(Modelo mod) {
        Transaction t = sessao.beginTransaction();
        sessao.save(mod);
        t.commit();
    }
 
    public Modelo buscarPorId(Integer id){

        return null;
    }
 
    public List<Marca> buscar(String query){
 
        List<Marca> resultados = new ArrayList<>();
 
        for(Marca c : marcas){
 
            if(c.getNome().startsWith(query)){
 
                resultados.add(c);
            }
        }
 
        return resultados;
    }
    
}
