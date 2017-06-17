/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import modelo.Placas;
import backingbeans.SisEstacionamentoBean;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author sergio
 */
@WebServlet(urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private Placas placa = new Placas();
    private SisEstacionamentoBean sis = new SisEstacionamentoBean();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Servlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        JSONParser parser = new JSONParser();
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
                BufferedReader reader = request.getReader();
                while ((line = reader.readLine()) != null)
                  jb.append(line);
              } catch (Exception e) { /*report an error*/ }

        try {
               Object obj = parser.parse(jb.toString());

                JSONObject jsonObject = (JSONObject) obj;
                //System.out.println(jsonObject);

                //String plate = (String) jsonObject.get("results");
                //System.out.println(plate);

                //long age = (Long) jsonObject.get("age");
                //System.out.println(age);

                // loop array
                org.json.simple.JSONArray results = (org.json.simple.JSONArray) jsonObject.get("results");
                String uuid = (String) jsonObject.get("uuid");
                System.out.println(uuid);
                this.placa.setFotoEntrada(uuid);
                //iterator = results.iterator();

                Iterator<Object> iterator = results.iterator();
                while (iterator.hasNext()) {
                        if (results.iterator().next() != null){
                            JSONObject jsonObjectnovo = (JSONObject) results.iterator().next();
                            String plate = (String) jsonObjectnovo.get("plate");
                            String[] pp = plate.split("");
                        
                        if (pp.length == 7){
                            for(int i=0;i<pp.length;i++){
                                if (i<3){
                                    if (Character.isLetter(pp[i].charAt(0))){                                        
                                    } else if (pp[i].equals("1")){
                                        pp[i] = "I";                                        
                                    } else if (pp[i].equals("0")){
                                        pp[i] = "O";                                        
                                    }else if (pp[i].equals("5")){
                                        pp[i] = "S";                                        
                                    }else if (pp[i].equals("8")){
                                        pp[i] = "B";                                        
                                    }
                                } else if (!Character.isLetter(pp[i].charAt(0))){                                        
                                    } else if (pp[i].equals("I")){
                                        pp[i] = "1";                                      
                                    } else if (pp[i].equals("O")){
                                        pp[i] = "0";                                        
                                    } else if (pp[i].equals("S")){
                                        pp[i] = "5";                                        
                                    }else if (pp[i].equals("B")){
                                        pp[i] = "8";
                                    }
                            plate = String.join("",pp);
                            }
                        } else {
                            break;
                        }
                        System.out.println("placa: "+plate);
                        this.placa.setPlaca(plate);
                        this.sis.incluirPlaca(this.placa);   
                        break;
                    }
 
                }
                    
              } catch (ParseException e) {
            e.printStackTrace();
        }

        // Work with the data using methods like...
        // int someInt = jsonObject.getInt("intParamName");
        // String someString = jsonObject.getString("stringParamName");
        // JSONObject nestedObj = jsonObject.getJSONObject("nestedObjName");
        // JSONArray arr = jsonObject.getJSONArray("arrayParamName");
        // etc...
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
