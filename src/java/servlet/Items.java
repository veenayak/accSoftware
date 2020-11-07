package servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
@WebServlet(urlPatterns = {"/Items"})
public class Items extends HttpServlet {

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
                String submit = request.getParameter("submit");             
                String name = request.getParameter("name");
                float sp = Float.parseFloat(request.getParameter("sp"));
                float cp = Float.parseFloat(request.getParameter("cp"));
                String code = request.getParameter("code");
                int hsncode = Integer.parseInt(request.getParameter("hsncode"));
                int stock = Integer.parseInt(request.getParameter("stock"));
                String desc = request.getParameter("desc");
                int cat = Integer.parseInt(request.getParameter("cat"));
                int scat = Integer.parseInt(request.getParameter("scat"));
                int al_lim = Integer.parseInt(request.getParameter("limit"));
                int size = Integer.parseInt(request.getParameter("size"));
                int unit = Integer.parseInt(request.getParameter("unit"));

                float cgst = Float.parseFloat(request.getParameter("cgst"));
                float sgst = Float.parseFloat(request.getParameter("sgst"));
                float igst = Float.parseFloat(request.getParameter("igst"));
                float cess = Float.parseFloat(request.getParameter("cess"));
                float igstamount = Float.parseFloat(request.getParameter("igstamount"));
                float cgstamount = Float.parseFloat(request.getParameter("cgstamount"));
                float sgstamount = Float.parseFloat(request.getParameter("sgstamount"));
                float cessamount = Float.parseFloat(request.getParameter("cessamount"));
                int discount = Integer.parseInt(request.getParameter("discount"));
                String discounttype = request.getParameter("discounttype");

                String type = request.getParameter("type");
                int warehouse = Integer.parseInt(request.getParameter("warehouse"));

                int isold = Integer.parseInt(request.getParameter("isold"));

                String gstin = session.getAttribute("gstin").toString();
                App obj = new App();
                String res = "";

                try{
                    if(submit.equals("insert")){
                        res = obj.insert_items(gstin,al_lim,name,cp,sp,code,hsncode,stock,warehouse,desc,
                                cat,scat,size,unit,type,cgst,sgst,igst,cess,cgstamount,sgstamount,igstamount,cessamount
                                ,discount,discounttype);
                        out.println(res);
                    }
                    else if(submit.equals("update")){
                        int id = Integer.parseInt(request.getParameter("id"));
                        res = obj.update_items(gstin,id,name,cp,sp,code,hsncode,stock,warehouse,desc,
                                cat,scat,isold,al_lim,size,unit,type,cgst,sgst,igst,cess,cgstamount,
                                sgstamount,igstamount,cessamount,discount,discounttype);
                        out.println(res);
                    }
                }
                catch(Exception e){
                    out.println(e);
                }
            }
            else{
                out.println("session expired.");
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
