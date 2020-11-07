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
@WebServlet(name = "Invoice_serial", urlPatterns = {"/Invoice_serial"})
public class Invoice_serial extends HttpServlet {

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
                String sis2 = request.getParameter("sis2");
                String pis2 = request.getParameter("pis2");
                String ess2 = request.getParameter("ess2");
                String prs2 = request.getParameter("prs2");
                String bos2 = request.getParameter("bos2");
                String exs2 = request.getParameter("exs2");
                String ars2 = request.getParameter("ars2");
                String pos2 = request.getParameter("pos2");
                String dcs2 = request.getParameter("dcs2");
                String cns2 = request.getParameter("cns2");
                String dns2 = request.getParameter("dns2");
                String rvs2 = request.getParameter("rvs2");
                String pvs2 = request.getParameter("pvs2");
                String aps2 = request.getParameter("aps2");
                String oos2 = request.getParameter("oos2");
                String res = "";
                int id = (Integer)(session.getAttribute("id"));
                try {
                    App validate = new App();
                    res = validate.update_invoice_serial(id,sis2,pis2,ess2,prs2,bos2,exs2,ars2,pos2,dcs2,cns2,dns2,rvs2,pvs2,aps2,oos2);
                    out.println(res);

                }
                catch (Exception e) {
                    out.println(e);
                    e.printStackTrace();
                }
            }
            else{
                out.println("session expired");
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
