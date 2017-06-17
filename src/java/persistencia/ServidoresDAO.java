/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.ArrayList;
import modelo.Servidores;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author sergio
 */
public class ServidoresDAO {
    private Session sessao;

    public ServidoresDAO() {
        sessao = HibernateUtil.getSessionFactory().openSession();
    }

    public void incluir(Servidores ser) {

        Transaction t = sessao.beginTransaction();
        sessao.save(ser);
        t.commit();
        sessao.flush();
        sessao.clear();
            
    }

    public ArrayList<Servidores> listar() {
        return (ArrayList<Servidores>) sessao.createCriteria(Servidores.class).list();
    }
    
}
