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
@WebServlet(name = "Saved", urlPatterns = {"/Saved"})
public class Saved extends HttpServlet {

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
                String type = request.getParameter("type");
                String gstin = session.getAttribute("gstin").toString();
                String res = "";
                if(submit.equals("category")){
                    if(type.equals("insert")){
                        String cat = request.getParameter("cat");
                        App obj = new App();
                        res = obj.insert_saved(gstin,null,submit,null,null,0,null,null,null,null,null,cat,null,null);
                    }
                    else if(type.equals("update")){
                        String cat = request.getParameter("cat");
                        App obj = new App();
                        int id = Integer.parseInt(request.getParameter("id"));
                        res = obj.update_saved(gstin,null,submit,id,null,null,0,null,null,null,null,null,cat,null,null);
                    }
                }
                else if(submit.equals("size")){
                    if(type.equals("insert")){
                        String size = request.getParameter("size");
                        App obj = new App();
                        res = obj.insert_saved(gstin,null,submit,null,null,0,null,null,null,null,size,null,null,null);
                    }
                    else if(type.equals("update")){
                        String size = request.getParameter("size");
                        App obj = new App();
                        int id = Integer.parseInt(request.getParameter("id"));
                        res = obj.update_saved(gstin,null,submit,id,null,null,0,null,null,null,null,size,null,null,null);
                    }
                }
                else if(submit.equals("subcategory")){
                    if(type.equals("insert")){
                        String scat = request.getParameter("scat");
                        App obj = new App();
                        res = obj.insert_saved(gstin,null,submit,null,null,0,null,null,null,null,null,null,scat,null);
                    }
                    else if(type.equals("update")){
                        String scat = request.getParameter("scat");
                        App obj = new App();
                        int id = Integer.parseInt(request.getParameter("id"));
                        res = obj.update_saved(gstin,null,submit,id,null,null,0,null,null,null,null,null,null,scat,null);
                    }

                }
    //            else if(submit.equals("tax")){
    //                if(type.equals("insert")){
    //                    String tax = request.getParameter("tax");
    //                    Float taxrate = Float.parseFloat(request.getParameter("taxrate"));
    //                    App obj = new App();
    //                    obj.insert_saved(gstin,null,submit,null,null,0,null,null,null,tax,taxrate,null,null,null,null,null);               
    //                }
    //                else if(type.equals("update")){
    //                    String tax = request.getParameter("tax");
    //                    Float taxrate = Float.parseFloat(request.getParameter("taxrate"));
    //                    App obj = new App();
    //                    int id = Integer.parseInt(request.getParameter("id"));
    //                    obj.update_saved(null,submit,id,null,null,0,null,null,null,tax,taxrate,null,null,null,null,null);
    //                }
    //            }
                else if(submit.equals("unit")){
                    if(type.equals("insert")){
                        String unit = request.getParameter("unit");
                        App obj = new App();
                        res = obj.insert_saved(gstin,null,submit,null,null,0,null,null,null,unit,null,null,null,null);                
                    }
                    else if(type.equals("update")){
                        String unit = request.getParameter("unit");
                        App obj = new App();
                        int id = Integer.parseInt(request.getParameter("id"));
                        res = obj.update_saved(gstin,null,submit,id,null,null,0,null,null,null,unit,null,null,null,null);
                    }
                }
                else if(submit.equals("collections")){
                    if(type.equals("insert")){
                        String collections = request.getParameter("collections");
                        String items = request.getParameter("items");
                        App obj = new App();
                        res = obj.insert_saved(gstin,null,submit,null,collections,0,null,null,null,null,null,null,null,items);                
                    }
                    else if(type.equals("update")){
                        String collections = request.getParameter("collections");
                        String items = request.getParameter("items");
                        App obj = new App();
                        int id = Integer.parseInt(request.getParameter("id"));
                        res = obj.update_saved(gstin,null,submit,id,null,null,0,null,collections,null,null,null,null,null,items);
                    }
                }
                else if(submit.equals("warehouse")){
                    if(session.getAttribute("type").equals("multi")){
                        if(type.equals("insert")){
                            String warehouse = request.getParameter("warehouse");
                            int pincode = Integer.parseInt(request.getParameter("pincode"));
                            String address = request.getParameter("address");
                            String state = request.getParameter("state");
                            String city = request.getParameter("city");
                            String statecode = request.getParameter("statecode");
                            App obj = new App();
                            res = obj.insert_saved(gstin,statecode,submit,warehouse,null,pincode,address,state,city,null,null,null,null,null);                
                        }
                        else if(type.equals("update")){
                            String warehouse = request.getParameter("warehouse");
                            int pincode = Integer.parseInt(request.getParameter("pincode"));
                            String address = request.getParameter("address");
                            String state = request.getParameter("state");
                            String city = request.getParameter("city");
                            String statecode = request.getParameter("statecode");
                            App obj = new App();
                            int id = Integer.parseInt(request.getParameter("id"));
                            res = obj.update_saved(gstin,statecode,submit,id,warehouse,null,pincode,address,state,city,null,null,null,null,null);
                        }
                    }
                    else{
                        if(type.equals("update")){
                            String warehouse = request.getParameter("warehouse");
                            int pincode = Integer.parseInt(request.getParameter("pincode"));
                            String address = request.getParameter("address");
                            String state = request.getParameter("state");
                            String city = request.getParameter("city");
                            String statecode = request.getParameter("statecode");
                            App obj = new App();
                            int id = Integer.parseInt(request.getParameter("id"));
                            res = obj.update_saved(gstin,statecode,submit,id,warehouse,null,pincode,address,state,city,null,null,null,null,null);
                        }
                        else{
                            out.println("You can not add more warehouse.");
                        }
                    }
                }
               out.println(res);
            }
            else{
                out.println("session expred.");
            }
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
