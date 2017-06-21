/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.ArrayList;
import java.util.List;
import modelo.Marca;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Sergio
 */
public class MarcaDAO {
    private List<Marca> marcas = new ArrayList<>();
    private Session sessao;
    
 
    public MarcaDAO(){
        
        sessao = HibernateUtil.getSessionFactory().openSession();
 
        marcas.add(new Marca(1, "Acura"));
        marcas.add(new Marca(2, "Agrale"));
        marcas.add(new Marca(3, "Alfa Romeo"));
        marcas.add(new Marca(4, "AM Gen"));
        marcas.add(new Marca(5, "Asia Motors"));
        marcas.add(new Marca(6, "ASTON MARTIN"));
        marcas.add(new Marca(7, "Audi"));
        marcas.add(new Marca(8, "BMW"));
        marcas.add(new Marca(9, "BRM"));
        marcas.add(new Marca(10, "Buggy"));
        marcas.add(new Marca(11, "Bugre"));
        marcas.add(new Marca(12, "Cadillac"));
        marcas.add(new Marca(13, "CBT Jipe"));
        marcas.add(new Marca(14, "CHANA"));
        marcas.add(new Marca(15, "CHANGAN"));
        marcas.add(new Marca(16, "CHERY"));
        marcas.add(new Marca(17, "Chrysler"));
        marcas.add(new Marca(18, "Citroen"));
        marcas.add(new Marca(19, "Cross Lander"));
        marcas.add(new Marca(20, "Daewoo"));
        marcas.add(new Marca(21, "Daihatsu"));
        marcas.add(new Marca(22, "Dodge"));
        marcas.add(new Marca(23, "EFFA"));
        marcas.add(new Marca(24, "Engesa"));
        marcas.add(new Marca(25, "Envemo"));
        marcas.add(new Marca(26, "Ferrari"));
        marcas.add(new Marca(27, "Fiat"));
        marcas.add(new Marca(28, "Fibravan"));
        marcas.add(new Marca(29, "Ford"));
        marcas.add(new Marca(30, "FOTON"));
        marcas.add(new Marca(31, "Fyber"));
        marcas.add(new Marca(32, "GEELY"));
        marcas.add(new Marca(33, "GM - Chevrolet"));
        marcas.add(new Marca(34, "GREAT WALL"));
        marcas.add(new Marca(35, "Gurgel"));
        marcas.add(new Marca(36, "HAFEI"));
        marcas.add(new Marca(37, "Honda"));
        marcas.add(new Marca(38, "Hyundai"));
        marcas.add(new Marca(39, "Isuzu"));
        marcas.add(new Marca(40, "JAC"));
        marcas.add(new Marca(41, "Jaguar"));
        marcas.add(new Marca(42, "Jeep"));
        marcas.add(new Marca(43, "JINBEI"));
        marcas.add(new Marca(44, "JPX"));
        marcas.add(new Marca(45, "Kia Motors"));
        marcas.add(new Marca(46, "Lada"));
        marcas.add(new Marca(47, "LAMBORGHINI"));
        marcas.add(new Marca(48, "Land Rover"));
        marcas.add(new Marca(49, "Lexus"));
        marcas.add(new Marca(50, "LIFAN"));
        marcas.add(new Marca(51, "LOBINI"));
        marcas.add(new Marca(52, "Lotus"));
        marcas.add(new Marca(53, "Mahindra"));
        marcas.add(new Marca(54, "Maserati"));
        marcas.add(new Marca(55, "Matra"));
        marcas.add(new Marca(56, "Mazda"));
        marcas.add(new Marca(57, "Mercedes-Benz"));
        marcas.add(new Marca(58, "Mercury"));
        marcas.add(new Marca(59, "MG"));
        marcas.add(new Marca(60, "MINI"));
        marcas.add(new Marca(61, "Mitsubishi"));
        marcas.add(new Marca(62, "Miura"));
        marcas.add(new Marca(63, "Nissan"));
        marcas.add(new Marca(64, "Peugeot"));
        marcas.add(new Marca(65, "Plymouth"));
        marcas.add(new Marca(66, "Pontiac"));
        marcas.add(new Marca(67, "Porsche"));
        marcas.add(new Marca(68, "RAM"));
        marcas.add(new Marca(69, "RELY"));
        marcas.add(new Marca(70, "Renault"));
        marcas.add(new Marca(71, "Rolls-Royce"));
        marcas.add(new Marca(72, "Rover"));
        marcas.add(new Marca(73, "Saab"));
        marcas.add(new Marca(74, "Saturn"));
        marcas.add(new Marca(75, "Seat"));
        marcas.add(new Marca(76, "SHINERAY"));
        marcas.add(new Marca(77, "smart"));
        marcas.add(new Marca(78, "SSANGYONG"));
        marcas.add(new Marca(79, "Subaru"));
        marcas.add(new Marca(80, "Suzuki"));
        marcas.add(new Marca(81, "TAC"));
        marcas.add(new Marca(82, "Toyota"));
        marcas.add(new Marca(83, "Troller"));
        marcas.add(new Marca(84, "Volvo"));
        marcas.add(new Marca(85, "VW - VolksWagen"));
        marcas.add(new Marca(86, "Wake"));
        marcas.add(new Marca(87, "Walk"));
    }
    
    public void incluir(Marca mar) {
        Transaction t = sessao.beginTransaction();
        sessao.save(mar);
        t.commit();
    }
 
    public Marca buscarPorCod(Integer cod){
 
        for(Marca m : marcas){
 
            if(m.getCod().equals(cod)){
 
                return m;
            }
        } 
 
        return null;
    }
 
    public List<Marca> buscar(String query){
 
        List<Marca> resultados = new ArrayList<>();
 
        for(Marca c : marcas){
 
            if(c.getNome().startsWith(query)){
 
                resultados.add(c);
            }
        }
 
        return resultados;
    }
    
}
