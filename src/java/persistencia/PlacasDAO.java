/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.ArrayList;
import modelo.Placas;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Sergio
 */
public class PlacasDAO {
    
    private Session sessao;
    
    public PlacasDAO() {
        sessao = HibernateUtil.getSessionFactory().openSession();
    }
    
    public void incluir(Placas plac) {
        Transaction t = sessao.beginTransaction();
        sessao.save(plac);
        t.commit();
        
    }
    
    public void alterar(Placas plac) {
        Transaction t = sessao.beginTransaction();
        sessao.update(plac);
        t.commit();
        sessao.flush();
        sessao.clear();
    }
    
    public ArrayList<Placas> listar() {
        sessao = HibernateUtil.getSessionFactory().openSession();
        return (ArrayList<Placas>) sessao.createCriteria(Placas.class).list();
    }
    
}
