/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.ArrayList;
import java.util.List;
import modelo.Servidores;
import org.hibernate.Query;
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
    
//     public List<Servidores> listarUsuariosCadastrados() {
//        String jpql = "SELECT usuario * from usuario inner join veiculo ON usuario.idUsuario = veiculo.id";
//        Query query = sessao.createQuery(jpql);
//        
//        return query.list();
//        
//    }
    
     public Servidores buscaPorMatricula(String matricula) {
        //Servidores usuario = new Servidores();
        Servidores ser = null;
//        String sql = "select * from Servidores j where j.matricula = ?";
//        Query query = sessao.createQuery(sql);
        String hql = "FROM Servidores WHERE matricula = '" + matricula + "'";
			Query query = sessao.createQuery(hql);
    
			if (!query.list().isEmpty()) {
				ser = (Servidores) query.list().get(0);
			}
        return ser;
    }
    
}
