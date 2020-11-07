/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import configuration.App;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author winayak
 */
@WebServlet(name = "User", urlPatterns = {"/User"})
public class User extends HttpServlet {

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
            HttpSession session = request.getSession(false);
            if(session!=null){
                String email = request.getParameter("email");
                String pwd = request.getParameter("pwd");
                int contact = Integer.parseInt(request.getParameter("contact"));
                String name = request.getParameter("name");
                String state = request.getParameter("state");
                String city = request.getParameter("city");
                String address = request.getParameter("address");
                String submit = request.getParameter("submit");
                String type = request.getParameter("type");
                String gstin = session.getAttribute("gstin").toString();
                if(submit.equals("insert")){
                    String privc = request.getParameter("privc");
                    String privv = request.getParameter("privv");
                    String privd = request.getParameter("privd");
                    int warehouse = Integer.parseInt(request.getParameter("warehouse").replaceAll("\n",""));
                    App obj = new App();   
                    String res = obj.insert_login(name,"sub",pwd,email, contact, "", gstin, type, city, address, warehouse, privv, privc, privd);
                    out.println(res);
                }
                else if(submit.equals("update")){
                    App obj = new App();  
                    if(type.equals("sub")){
                        String privc = request.getParameter("privc");
                        String privv = request.getParameter("privv");
                        String privd = request.getParameter("privd");
                        int warehouse = Integer.parseInt(request.getParameter("warehouse").replaceAll("\n",""));
                        int id = Integer.parseInt(request.getParameter("id"));
                        String res = obj.update_login(id,email, name, pwd, contact, "", gstin, "sub", address, city, state ,warehouse, privc, privv, privd, gstin);
                        out.println(res);
                    }
                    else if(type.equals("multi")||type.equals("admin")){
                        int id = Integer.parseInt(request.getParameter("id"));
                        String res = obj.update_login(id,session.getAttribute("email").toString(), name, "", contact, "", "",type, address, city,state ,0, "all", "all", "all", "all");
                        out.println(res);
                    }
                }
            }
            else{
                out.println("session expired.");
            }
        }
        catch(Exception e){
            PrintWriter out = response.getWriter();
            out.println(e);
            
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
