/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingbeans;

/**
 *
 * @author Sergio
 */
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import java.io.FileReader;

import java.util.List;
import modelo.Alunos;
 

 
public class CSVMappedToJavaBeanExample
{
   @SuppressWarnings({"rawtypes", "unchecked"})
   public static void main(String[] args) throws Exception
   {
      CsvToBean csv = new CsvToBean();
       
      String csvFilename = "listagem.csv";
      CSVReader csvReader = new CSVReader(new FileReader(csvFilename));
      
       
      //Set column mapping strategy
      List list = csv.parse(setColumMapping(), csvReader);
      
       
      for (Object object : list) {
          Alunos employee = (Alunos) object;
          System.out.println(employee);
      }
   }
    
   @SuppressWarnings({"rawtypes", "unchecked"})
   private static ColumnPositionMappingStrategy setColumMapping()
   {
      ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
      strategy.setType(Alunos.class);
      String[] columns = new String[] {"id", "matricula", "nome", "country", "age"};
    //      curso;
//        private String matriz_curricular;
//        private String situacao;
//        private String ingresso;
      strategy.setColumnMapping(columns);
      return strategy;
   }
}
