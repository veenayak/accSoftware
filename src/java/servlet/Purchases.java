/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import configuration.App;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@WebServlet(name = "Purchases", urlPatterns = {"/Purchases"})
public class Purchases extends HttpServlet {

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
            String invoiceno = request.getParameter("invoiceno");
            
            String invoicesetting = request.getParameter("advanced");
            //customer
            int cid = Integer.parseInt(request.getParameter("cid"));
            String name = request.getParameter("name");

            String cgstin = request.getParameter("gstin");
            String pos = request.getParameter("pos");

            //products
            String productsid = request.getParameter("productsid");
            String productsname = request.getParameter("products");
            String productsqty = request.getParameter("qty");
            String productsprice = request.getParameter("productsprice");
            String productsamount = request.getParameter("productsamount");
            String productscost = request.getParameter("productscost");
            //date
            SimpleDateFormat availDate = new SimpleDateFormat("dd-MM-yyyy");
            Date orderdate = null; 
            Date invoicedate = availDate.parse(request.getParameter("idate")); 
            Date paydate = null; 
            Date deldate = null;            
            //amount
            Float amount = Float.parseFloat(request.getParameter("tamount"));           
            String discount = request.getParameter("discount");
            String discounttype = request.getParameter("discounttype");
            String type = request.getParameter("type");
            //tax
            String  cessamount = null;
            String cess = null;
            String cgstamount = null;
            String cgst = null;
            String sgstamount = null;
            String sgst = null;
            String igstamount = null;
            String igst = null;
            String taxrate = null;
            boolean roundoff = Boolean.parseBoolean(request.getParameter("roundoff"));
            //accounts
//            int accountid = Integer.parseInt(request.getParameter("accountid"));
            //          
            String linkedinvoice = "";
            String returnmonth = "";
            String returnquarter = "";
            int transcharge = 0;
            String paystatus = "";
            String delstatus = "";
            Date payduedate= null; 
            Date delduedate = null;
            if(!type.equals("bos")){
                cessamount = request.getParameter("cessamount");
                cess = request.getParameter("cess");
                cgstamount = request.getParameter("cgstamount");
                cgst = request.getParameter("cgst");
                sgstamount = request.getParameter("sgstamount");
                sgst = request.getParameter("sgst");
                igstamount = request.getParameter("igstamount");
                igst = request.getParameter("igst");
                taxrate = request.getParameter("taxrate");
                paystatus = "pending";
                delstatus = "delivered";
                returnmonth = request.getParameter("remonth");
                returnquarter = request.getParameter("requarter");
                payduedate = availDate.parse(request.getParameter("ddate"));
            }            
            if(type.equals("amendment")){
                linkedinvoice = request.getParameter("linkedinvoice");
            }
            if(type.equals("pi")){
                paystatus = "pending";
                delstatus = "delivered";
                returnmonth = request.getParameter("remonth");
                returnquarter = request.getParameter("requarter");
                payduedate = availDate.parse(request.getParameter("ddate"));
            }
            if(type.equals("ap")){
                paystatus = "pending";
                delstatus = "undelivered";
                returnmonth = request.getParameter("remonth");
                returnquarter = request.getParameter("requarter");
                delduedate = availDate.parse(request.getParameter("ddate"));
            }
            int warehouseid = Integer.parseInt(request.getParameter("warehouse"));
            String ref = request.getParameter("ref");               
            HttpSession session = request.getSession();
            String gstin = session.getAttribute("gstin").toString();
            
            App obj = new App();
            String submit = request.getParameter("submit");        
            if(submit.equals("insert")){
                String res = obj.insert_purchases(gstin,invoiceno,invoicedate,cgstin,deldate,delduedate,paydate,
                        payduedate,orderdate,amount,paystatus,delstatus,
                    cid,name,transcharge,type, invoicesetting, linkedinvoice,
                pos ,productsid, productsqty,productsname,productscost,
                returnmonth, returnquarter,taxrate, cess, cessamount, cgst,cgstamount, sgst, sgstamount, igst,
                igstamount,warehouseid,productsprice,productsamount,ref,discounttype,discount,roundoff);

                out.println(res);   
            }
            else if(submit.equals("update")){
                boolean cancelled = Boolean.parseBoolean(request.getParameter("cancelled"));
                int id = Integer.parseInt(request.getParameter("id"));
                String res = obj.update_purchases(gstin,id,invoiceno,invoicedate,deldate,delduedate,paydate,payduedate,
                orderdate,amount,paystatus,delstatus,cid,name,
                transcharge,type,invoicesetting,linkedinvoice,
                pos , cgstin,productsid, productsqty,productsname,productscost,
                returnmonth, returnquarter,taxrate, cess, cessamount, cgst,
                cgstamount, sgst, sgstamount, igst, igstamount, warehouseid,productsprice,productsamount,
                cancelled,ref,discounttype,discount,roundoff);
                
                out.println(res);   
            }
            else{
                out.println("don't be sneaky!!");
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
