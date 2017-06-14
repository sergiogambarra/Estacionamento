/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.ArrayList;
import modelo.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UsuarioDAO {

    private Session sessao;

    public UsuarioDAO() {
        sessao = HibernateUtil.getSessionFactory().openSession();

    }

    public void incluir(Usuario usr) {
        Transaction t = sessao.beginTransaction();
        sessao.save(usr);
        t.commit();
    }

    public void alterar(Usuario usr) {
        Transaction t = sessao.beginTransaction();
        sessao.clear();
        sessao.update(usr);
        t.commit();
        sessao.flush();

    }

    public void excluir(int id_usuario) {
        Transaction t = sessao.beginTransaction();
        sessao.delete(carregar(id_usuario));
        t.commit();

    }
    
    
    public ArrayList<Usuario> listar() {
        return (ArrayList<Usuario>) sessao.createCriteria(Usuario.class).list();
    }

    public Usuario carregar(int id) {
        return (Usuario) sessao.get(Usuario.class, id);
    }
    
    
    public Usuario buscarUsuario(String email) {
        //sessao = HibernateUtil.getSessionFactory().openSession();
        Query query = sessao.createQuery("from Usuario u where u.email = :email");
        Usuario u = (Usuario) query.setString("email", email).uniqueResult();

        return u;
    }
}
    
    