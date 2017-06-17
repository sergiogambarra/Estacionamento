/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.ArrayList;
import modelo.Outros;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Sergio
 */
public class OutrosDAO {
    private Session sessao;

    public OutrosDAO() {
        sessao = HibernateUtil.getSessionFactory().openSession();
    }

    public void incluir(Outros alu) {
        
                Transaction t = sessao.beginTransaction();
                sessao.save(alu);
                t.commit();
    }

    public ArrayList<Outros> listar() {
        return (ArrayList<Outros>) sessao.createCriteria(Outros.class).list();
    }
}
