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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Sergio
 */
public class ModeloDAO {
    private List<Modelo> modelos = new ArrayList<>();
    private Session sessao;
    
 
    public ModeloDAO(){
        
        sessao = HibernateUtil.getSessionFactory().openSession();
 
    }
    
    public void incluir(Modelo mod) {
        Transaction t = sessao.beginTransaction();
        sessao.save(mod);
        t.commit();
    }
    
//    public Alunos buscaPorMatricula(String matricula) {
//        //Servidores usuario = new Servidores();
//        Alunos alu = null;
////        String sql = "select * from Servidores j where j.matricula = ?";
////        Query query = sessao.createQuery(sql);
//        String hql = "FROM Alunos WHERE matricula = '" + matricula + "'";
//			Query query = sessao.createQuery(hql);
//    
//			if (!query.list().isEmpty()) {
//				alu = (Alunos) query.list().get(0);
//			}
//        return alu;
//    }
 
    public Modelo buscarPorId(Integer id){
        Modelo mod = null;
        String hql = "FROM Modelo WHERE id = '" + id + "'";
			Query query = sessao.createQuery(hql);
    
			if (!query.list().isEmpty()) {
				mod = (Modelo) query.list().get(0);
			}
        return mod;
        
        //return (Modelo) sessao.createQuery("select * from Modelo m where m.id =" + id);
    }
 
    public List<Modelo> buscar(String query){
 
        List<Modelo> resultados = new ArrayList<>();
 
        for(Modelo m : modelos){
 
            if(m.getNome().startsWith(query)){
 
                resultados.add(m);
            }
        }
 
        return resultados;
    }
    
    public ArrayList<Modelo> listar() {
        return (ArrayList<Modelo>) sessao.createCriteria(Modelo.class).list();
    }
    
}
