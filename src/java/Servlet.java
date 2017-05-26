/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
                BufferedReader reader = request.getReader();
                while ((line = reader.readLine()) != null)
                  jb.append(line);
              } catch (Exception e) { /*report an error*/ }

        try {
                JSONObject jsonObject =  HTTP.toJSONObject(jb.toString());
                String json_str = jb.toString();
                //instancia um novo JSONObject passando a string como entrada
		JSONObject my_obj = new JSONObject(json_str);

		//recupera o array "elenco"  
		JSONArray elenco = my_obj.getJSONArray("plate");
                //imprime cada ator/atriz do elenco com o uso dos métodos length() e get()
                System.out.println("Elenco Original");
		for (int i = 0; i < elenco.length(); i++) {
			System.out.println("(" + i + ") " + elenco.get(i));
		}
		System.out.println();
                //-- insere um novo ator no array 
		elenco.put("Michael Java Fox");
		
		System.out.println("Elenco com o novo ator");
		for (int i = 0; i < elenco.length(); i++) {
			System.out.println("(" + i + ") " + elenco.get(i));
		}
		System.out.println();
                //-- troca o nome da primeira atriz
		elenco.put(0, "Jennifer Json Leigh");
		
		System.out.println("Elenco com o nome da primeira atriz modificado");
		for (int i = 0; i < elenco.length(); i++) {
			System.out.println("(" + i + ") " + elenco.get(i));
		}
		System.out.println();
                //-- remove o terceiro ator
		elenco.remove(2);
		
		System.out.println("Elenco com o ator David Makupovny removido");
		for (int i = 0; i < elenco.length(); i++) {
			System.out.println("(" + i + ") " + elenco.get(i));
		}
		
                
              } catch (JSONException e) {
                //string json: contém array com três elementos
		String json_str = "{\"elenco\":[\"Json Leigh\", \"Mary Stylesheet\", \"David Markupovny\"]}";
		
		
		
		
		
		
		
                throw new IOException("Error parsing JSON request string");
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
