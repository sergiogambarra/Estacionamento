/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.ArrayList;
import java.util.List;
import modelo.Alunos;
import modelo.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import static org.hibernate.criterion.Expression.sql;

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
    
//    public List<Cidade> listaCidadesDoEstado(Integer idEstado) {
//		String jpql = "select c from Cidade c where c.estado.id = :pIdEstado" ;
//		Query query = this.sessao.createQuery(jpql).setParameter("pIdEstado", idEstado) ;
//		return query.getResultList() ;
//	}
    
    
    public List<Usuario> listarUsuariosCadastrados() {
        String jpql = "SELECT usuario * from usuario inner join veiculo ON usuario.idUsuario = veiculo.id";
        Query query = sessao.createQuery(jpql);
        
        return query.list();
        
    }
    
    public Usuario buscaPorMatricula(String matricula) {
        Usuario usuario = new Usuario();
        String sql = "select * from usuario j where j.nome = ?";
        Query query = sessao.createQuery(sql);
        return usuario;
    }
}
    
    