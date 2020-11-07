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
@WebServlet(name = "Contact", urlPatterns = {"/Contact"})
public class Contact extends HttpServlet {

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
                String comp = request.getParameter("comp");
                int phone = Integer.parseInt(request.getParameter("phone"));
                String address = request.getParameter("address");
                String scode = request.getParameter("scode");
                String state = request.getParameter("state");
                String city = request.getParameter("city");
                int pincode = Integer.parseInt(request.getParameter("pincode"));

                String accno = request.getParameter("accno");
                String ifsc = request.getParameter("ifsc");
                String pno = request.getParameter("pno");

                String name = request.getParameter("name");

                String type = request.getParameter("type");
                String cgstin = request.getParameter("gstin");

                String submit = request.getParameter("submit");

                int salary = 0;
                if(type.equals("employee")){
                    salary = Integer.parseInt(request.getParameter("salary"));
                }
                else{
                    salary = 0;
                }
                email  = email.replaceAll("\\s","");
                if(submit.equals("insert")){
                    App obj = new App();
                    String gstin = session.getAttribute("gstin").toString();
                    String res = obj.insert_contact(gstin,name,email,comp,phone,address,scode,state,city,accno,
                            ifsc,pno,type,salary,pincode,cgstin);
                    out.println(res);
                }
                else if(submit.equals("update")){
                    App obj = new App();                            
                    int id = Integer.parseInt(request.getParameter("id"));
                    String gstin = session.getAttribute("gstin").toString();        

                    String res = obj.update_contact(id,gstin,name,email,comp,phone,address,scode,state,city,accno,
                            ifsc,pno,type,salary,pincode,cgstin);
                    out.println(res);
                }
            }
            else{
                out.println("session expired");
            }
        }
        catch(Exception e){
            response.getWriter().println(e);
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
