/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.ArrayList;
import modelo.Veiculo;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author sergio
 */
public class VeiculoDAO {
    
    private Session sessao;
    
    public VeiculoDAO() {
        sessao = HibernateUtil.getSessionFactory().openSession();
    }
    
    public void incluir(Veiculo vei) {
        Transaction t = sessao.beginTransaction();
        sessao.save(vei);
        t.commit();
    }
    
    public ArrayList<Veiculo> listar() {
        return (ArrayList<Veiculo>) sessao.createCriteria(Veiculo.class).list();
    }
    
}
