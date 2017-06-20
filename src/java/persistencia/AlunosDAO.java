/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.ArrayList;
import modelo.Alunos;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author sergio
 */
public class AlunosDAO {
    
    private Session sessao;

    public AlunosDAO() {
        sessao = HibernateUtil.getSessionFactory().openSession();
    }

    public void incluir(Alunos alu) {
        
        Transaction t = sessao.beginTransaction();
        sessao.save(alu);
        t.commit();
        sessao.flush();
        sessao.clear();
  
    }

    public ArrayList<Alunos> listar() {
        return (ArrayList<Alunos>) sessao.createCriteria(Alunos.class).list();
    }
    
    public Alunos buscaPorMatricula(String matricula) {
        //Servidores usuario = new Servidores();
        Alunos alu = null;
//        String sql = "select * from Servidores j where j.matricula = ?";
//        Query query = sessao.createQuery(sql);
        String hql = "FROM Alunos WHERE matricula = '" + matricula + "'";
			Query query = sessao.createQuery(hql);
    
			if (!query.list().isEmpty()) {
				alu = (Alunos) query.list().get(0);
			}
        return alu;
    }
}

