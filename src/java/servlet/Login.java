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
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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
            String email = request.getParameter("email");
            String pwd = request.getParameter("password");
            String[] res = {"","","","","","","","",""};
            HttpSession session = request.getSession();
            try {
                App validate = new App();
                res = validate.login_validate(email, pwd);
                if(res[0].equals(email)){                   
                    session.setAttribute("email", res[0]);
                    session.setAttribute("llogin", res[1]);
                    session.setAttribute("gstin", res[2]);
                    session.setAttribute("warehouse", res[3]);
                    session.setAttribute("type", res[4]);
                    session.setAttribute("privc", res[5]);
                    session.setAttribute("privd", res[6]);
                    session.setAttribute("privv", res[7]);
                    session.setAttribute("id", res[8]);
                    session.setAttribute("cgstin", res[2].split(",")[0]);
                    validate.setllogin(Integer.parseInt(res[8]),"lastlogin");
                    out.println("1");
                }
                else{
                    out.println(res[0]);
                }
            } catch (Exception e) {
                out.println(e);
                e.printStackTrace();
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
