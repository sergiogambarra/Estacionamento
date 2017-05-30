/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

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
    
}
