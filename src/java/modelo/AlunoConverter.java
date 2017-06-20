/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import backingbeans.SisEstacionamentoBean;
import persistencia.AlunosDAO;

/**
 *
 * @author Sergio
 */

@FacesConverter(value="alunoConverter")
public class AlunoConverter implements Converter {
   //private SisEstacionamentoBean sis = new SisEstacionamentoBean();
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        AlunosDAO alunosDAO = new AlunosDAO();
        Alunos alunos = alunosDAO.buscaPorMatricula(string);
        //sis.setAlunos(alunos);
        return alunos;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Alunos alunos = new Alunos();
        alunos = (Alunos) o;
        return alunos.getMatricula();
    }

}
