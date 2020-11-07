<%-- 
    Document   : dashboard
    Created on : 15 Apr, 2019, 10:22:40 PM
    Author     : winayak
--%>

<%@page import="pojo.Sales_pojo"%>
<%@page import="java.util.Iterator"%>
<%@page import="pojo.Purchases_pojo"%>
<%@page import="pojo.Items_pojo"%>
<%@page import="configuration.App"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>JSP Page</title>
     	<%@ include file="imports.jsp" %>  

        
        <style>
            
            #skill1,#skill2{
                width: 100%;
                float: left;
                padding: 0px;
                margin-bottom: 10px;
            }
            #skill1 span,#skill2 span{
                display: inline-block;
                height: 10px;
                float: left;
                padding: 7px 0px;
                border: 0px!important;
            }
            #skill1 span:nth-child(1),#skill2 span:nth-child(1){
                background: var(--success);
            }
            #skill1 span:nth-child(2),#skill2 span:nth-child(2){
                background: var(--warning);
            }
            #main h4 span a{
                padding: 14.5px 20px;
                display: inline-block;
                text-decoration: none;
            }
            #alerts h4{
                margin-bottom: 0;
            }
            #alerts .dropdown-div{
                left: 0;
                right: auto;
                margin-left: 15px;
                max-width: 150px;
            }
 
            #trans h4{
                margin-bottom: 0;
            }
        </style>
        <script>
            $(document).ready(function(){
                var total1 = parseInt($("#val1").html())+parseInt($("#val2").html());
                var total2 = parseInt($("#val3").html())+parseInt($("#val4").html());
                console.log(total1);
                console.log(total2);
                var val1 = ($("#val1").html()/total1)*100;
                var val2 = ($("#val2").html()/total1)*100;
                var val3 = ($("#val3").html()/total2)*100;
                var val4 = ($("#val4").html()/total2)*100;
                console.log(val3);
                console.log(val4);
                $("#skill1 span").eq(0).css("width",val1+"%");
                $("#skill1 span").eq(1).css("width",val2+"%");
                $("#skill2 span").eq(0).css("width",val3+"%");
                $("#skill2 span").eq(1).css("width",val4+"%");      
                $("#sidebar a:first-child").addClass("active");
            });
        </script>
    </head>
    <body>
	<%@ include file="nav.jsp" %>  
	<%@ include file="sidebar.jsp" %>  

       
        <% if(request.getSession(false)!=null && session.getAttribute("email") != null){
            if(session.getAttribute("type").toString().equals("multi") || session.getAttribute("type").toString().equals("admin")){%>
        <div id="main"> 
            <div>
                <h1>Dashboard       
    <!--                <span  data-toggle="tooltip" title="GST"><a href="" data-toggle="modal" data-target="#myModal1">GST</a></span>-->
                </h1>
            </div>
            <div id="rec">
                <div>
                    <h4>Total Receivables</h4>
                    <% 
                        App obj = new App();
                        List l = obj.fetch_sales(session.getAttribute("gstin").toString(),"all",0);
                        Sales_pojo obj2 = new Sales_pojo();
                        Iterator i = l.iterator();
                        float sum = 0,sum2 = 0;
                        if(!l.get(0).equals("0")){
                            while(i.hasNext()){
                            obj2 = (Sales_pojo) i.next();
                            if(obj2.getpstatus().equals("paid")){
                                sum = sum + obj2.getamount();
                                
                            }
                            else if(obj2.getpstatus().equals("pending")){
                                sum2 = sum2 + obj2.getamount();
                                
                            }
                        }}
                    %>
                    <aside id="skill1"><span></span><span></span></aside>
                    <p><span>Received</span><span id="val1"><%=sum%></span></p>
                    <p style="float: right"><span>Due</span><span id="val2"><%=sum2%></span></p>
                </div>
            </div>
            <div id="pay">
                <div>
                    <h4>Total Payables</h4>
                    <% 
                        List l2 = obj.fetch_purchases(session.getAttribute("gstin").toString(),"all",0);
                        Purchases_pojo obj3 = new Purchases_pojo();
                        Iterator i2 = l2.iterator();
                        float sum3 = 0,sum4 = 0;
                        if(!l2.get(0).equals("0")){
                            while(i2.hasNext()){
                                obj3 = (Purchases_pojo) i2.next();
                                if(obj3.getpstatus().equals("paid")){
                                    sum3 = sum3 + obj3.getamount();

                                }
                                else if(obj3.getpstatus().equals("pending")){
                                    sum4 = sum4 + obj3.getamount();

                                }
                            }
                        }
                    %>
                    <aside id="skill2"><span></span><span></span></aside>
                    <p><span>Paid</span><span id="val3"><%=sum3%></span></p>
                    <p style="float: right"><span>Due</span><span id="val4"><%=sum4%></span></p>
                </div>
            </div>
            <div id="trans">
                <div>
                    <h4>Pending Transactions</h4>
                    <table class="table">
                        <thead>
                          <tr>
                            <th><i class="fas fa-user"></i>User</th>
                            <th><i class="fas fa-calendar"></i>Date</th>
                            <th><i class="fas fa-money-bill"></i>Amount</th>
                            <th><i class="fas fa-calendar"></i>Type</th>
                          </tr>
                        </thead>
                        <tbody>
                            <% 
                                Purchases_pojo obj4 = new Purchases_pojo();
                                Iterator i3 = l2.iterator();
                                
                                if(!l2.get(0).equals("0")){
                                    while(i3.hasNext()){
                                    obj4 = (Purchases_pojo) i3.next();
                                    if(obj4.getpstatus().equals("pending")){                                
                            %>
                                <tr>
                                    <td><%=obj4.getcname()%></td>
                                  <td><%=obj4.getdate()%></td>
                                  <td><%=obj4.getamount()%>
                                  </td>
                                  <td>Purchases</td>
                                </tr>
                            <%}
                                else{
                                    continue;
                                }
                            }}%>
                            <% 
                                Sales_pojo obj5= new Sales_pojo();
                                Iterator i4 = l.iterator();
                                
                                if(!l.get(0).equals("0")){
                                    while(i4.hasNext()){                                  
                                        obj5 = (Sales_pojo) i4.next(); 
                                        if(obj5.getpstatus().equals("pending")){   
                            %>
                                <tr>
                                    <td><%=obj5.getcname()%></td>
                                  <td><%=obj5.getdate()%></td>
                                  <td><%=obj5.getamount()%></td>
                                  <td>Sales</td>
                                </tr>
                                    <%}
                                       else{
                                           continue;
                                       }
                                   }
                                }%>
                        </tbody>
                      </table>
                </div>
            </div>
            <div id="alerts">
                <div class="dropdown">
                    <h4>Alerts
                        <i class="fas fa-caret-down"></i>
                    </h4>
                    <div class="dropdown-div">
                        <a href="#">Inventory</a>
                        <a href="#">Transaction</a>
                        <a href="#">Order</a>
                        <a href="#">Tax</a>                    
                        <a href="#">??</a>
                    </div>
                </div>
            </div>
        </div>    
<!--        <div class="modal fade" id="myModal1">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">

                     Modal Header 
                    <div class="modal-header">
                      <h4 class="modal-title">GST</h4>
                      <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                     Modal body 
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="sgst">SGST</label>
                            <input type="number" class="form-control" id="sgst">
                        </div>
                        <div class="form-group">
                            <label for="cgst">CGST</label>
                            <input type="number" class="form-control" id="cgst">                            
                        </div>
                        <div class="form-group">
                            <label for="igst">IGST</label>
                            <input type="number" class="form-control" id="igst">
                        </div>
                    </div>

                     Modal footer 
                    <div class="modal-footer">
                      <button type="button" id="submit1">Save</button>
                    </div>

                </div>
            </div>
        </div>-->
        <%}
            else{
                out.println("You are not authorised to view this page.");
            }
        }
        else{
            out.println("<script>alert(\"Session expired.\");location.assign(\"index.html\");</script>");
        }
        %>
    </body>
</html>

