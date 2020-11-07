/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import configuration.App;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import pojo.Transaction_pojo;

/**
 *
 * @author winayak
 */
@WebServlet(name = "Transaction", urlPatterns = {"/Transaction"})
public class Transaction extends HttpServlet {

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
            String date = request.getParameter("date");
            String mode = request.getParameter("mode");
            String amount = request.getParameter("amount");
            float dueamount = Float.parseFloat(request.getParameter("dueamount"));
            float tamount = Float.parseFloat(request.getParameter("totalamount"));
            int transinvoiceid = Integer.parseInt(request.getParameter("transinvoiceid"));
            int cid = Integer.parseInt(request.getParameter("custid"));
            int wid = Integer.parseInt(request.getParameter("wareid"));
            String type = request.getParameter("type");
            App obj = new App();
            String res = obj.update_transaction(transinvoiceid, amount, mode, date, dueamount,tamount,cid,wid,type);
            if((int)(dueamount)==0){
                if(type.equals("sale"))
                    obj.update_paystatus(transinvoiceid, "paid");
                else
                    obj.update_paystatus2(transinvoiceid, "paid");
            }
            else{
                if(type.equals("sale"))
                    obj.update_paystatus(transinvoiceid, "pending");
                else
                    obj.update_paystatus2(transinvoiceid, "pending");
            }
            out.println(res);
        }
        catch(Exception e){
            response.getWriter().println(e);

        }
    }
    protected void processRequest2(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int id = Integer.parseInt(request.getParameter("id"));
            String type = request.getParameter("type");
            HttpSession session = request.getSession();
            String gstin =  session.getAttribute("gstin").toString();
            App obj = new App();
            Transaction_pojo obj2 = new Transaction_pojo();
            List l = obj.fetch_transactions(id, gstin,type);
            String[] res = {"","","","",""};
            if(!l.get(0).equals("0")){
                obj2 = (Transaction_pojo)l.get(0);
                res[0] = obj2.getamount();
                res[1] = obj2.gettransdate();
                res[2] = obj2.getmode();
                res[3] = Float.toString(obj2.getdueamount());
                res[4] = Float.toString(obj2.gettotalamount());
            }       
            else{
                res[0] = "error";
            }
            JSONArray array = new JSONArray(Arrays.asList(res));
            out.println(array);
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
        processRequest2(request, response);
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
