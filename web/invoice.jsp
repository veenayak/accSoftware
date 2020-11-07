<%-- 
    Document   : invoice
    Created on : 7 May, 2019, 11:53:11 PM
    Author     : winayak
--%>

<%@page import="pojo.Sales_pojo"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="configuration.App"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>JSP Page</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

        <!-- Popper JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        
        <script type="text/javascript" src="js/jquery.classyqr.js"></script>
        
        <script src="https://rawgit.com/sitepoint-editors/jsqrcode/master/src/qr_packed.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <!-- font awesome icons -->
        <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
        
        <link rel="stylesheet" href="css/index.css">
        <style>
            .table tr{
                width : 20%;
            }
            #invoice{
                width: 900px;
                padding: 20px 0;
                margin: auto;
                border-top: 10px solid #75a0c3; 
                overflow: hidden;
                background: #fff;
            }
            #invoice p{
                border: 0px;
                margin: 0px;
                border: 0px;
                background: #fff;
            }
            #invoice .info p{
                border-radius: 5px;
                padding: 10px;
                background: #ffffff;
                border: 1px solid #dedede;
            }
            #invoice div{
                float: left;
                padding: 10px;
            }
            .head{
                text-align: center;
                width: 100%;
                margin-bottom: 10px;
            }
            #invoice>div:nth-child(7),#invoice div:nth-child(9){                
                float: right!important;
            }
            #invoice .info,#invoice .last{
                padding :10px;
                width: 50%;
            }
            .info_table{
                margin-top: 20px;
                width: 100%;;
            }
            #invoice div:nth-child(9){
                text-align: left;
                width: 100%;
            }
            #invoice div:nth-child(10){
                text-align: right;
                width: 100%;
            }
            #invoice div:nth-child(4){
                width: 100%;
            }
/*            #myModal3 .modal-dialog.modal-dialog-centered{
                min-width: 800px;
            }*/
            .last p{
                padding-bottom: 0px;
            }
            #invoice h5{
                margin-bottom: 15px;
            }
            .head p{
                padding-top: 0px;
            }
            .table thead th{
                white-space: nowrap;
            }
            @media only screen and (max-width:1200px){
                #invoice{
                    width: 100%;
                }
                #invoice div{
                    width: 100%!important;
                }
            }
        </style>
    </head>
    <body>
        <% if(request.getSession(false)!=null){
        %>
        <div id="invoice">
            <div class="head">
                <h5>TAX INVOICE</h5>
                <h3>KATHPAL FANCY DRESS</h3>
                <p>Sirsla Road,Mohan Nagar,Kurukshetra(Haryana)</p>
                <p>Deals in : All kinds of dresses for rent and hire for cultural programs</p>
            </div>
            <div class="info">
                <% 
                    int id = Integer.parseInt(request.getParameter("id"));
                    App obj = new App();
                    List l = obj.fetch_sales(session.getAttribute("gstin").toString(),"one",id);;
                    Sales_pojo obj2 = (Sales_pojo) l.get(0);
                %>
                <p>Date <%out.println(obj2.getinvoicedate().toString().substring(0, 10));%></p>
                <p>Invoice No <%out.println(obj2.getinvoiceno());%></p>
                <p>State Code <%out.println(obj2.getbillstatecode());%></p>                        
            </div>
            <div class="info">
                <p>Transportation Mode</p>
                <p>Date of supply <%out.println(obj2.getinvoicedate().toString().substring(0, 10));%></p>
                <p>Place of supply <%out.println(obj2.getpos());%></p>
            </div>
            <div class="info">
                <h5>Details of receiver(billed to)</h5>
                <p>Name <%out.println(obj2.getcname());%></p>
                <p>Gstin <%out.println(obj2.getcustomergstin());%></p>
                <p>Address<%out.println(obj2.getbilladdress());%></p>
                <p>City <%out.println(obj2.getbillcity());%></p>
                <p>State<%out.println(obj2.getbillstate());%></p>
                <p>Pin code<%out.println(obj2.getbillpincode());%></p>
                <p>State code<%out.println(obj2.getbillstatecode());%></p>                
            </div>
            <% 
                String[] name = obj2.getproductsname().split(",");
                String[] cgst = obj2.getcgst().split(",");
                String[] sgst = obj2.getcgst().split(",");
                String[] igst = obj2.getigst().split(",");
                String[] cess = obj2.getcess().split(",");
                String[] price = obj2.getproductsamount().split(",");
                String[] qty = obj2.getproductsqty().split(",");
                
            %>
            <div class="info_table">
                <table class="table">
                    <thead>
                      <tr>
                        <th>Name</th>
                        <th>Qty</th>
                        <th>CGST</th>
                        <th>SGST</th>
                        <th>IGST</th>
                        <th>CESS</th>
                        <th>Price</th>  
                      </tr>
                    </thead>
                    <tbody>
                        <%for(int i=0;i<name.length;i++){%>
                        <tr>
                            <td><%=name[i]%></td>
                            <td><%=qty[i]%></td>
                            <td><%=cgst[i]%></td>
                            <td><%=sgst[i]%></td>
                            <td><%=igst[i]%></td>
                            <td><%=cess[i]%></td>
                            <td><%=price[i]%></td>
                        </tr>        
                        <%}%>
                    </tbody>
                </table>
            </div>

            <div class="info">

            </div>
            <div class="info">
                <p>Transport charges : <%out.println(obj2.gettranscharge());%></p>
                <p id="total2"></p>
                <p>Total Amount : <span id="total3"><%out.println(obj2.getamount());%></span></p>
                <p id="total1"></p>
            </div>
            <div class="info">
<!--                <p>Total invoice amount in words : </p>-->
            </div>
            <div class="last">
                <h5>Term and Conditions:</h5>
                <p>Goods once sold will not be taken back</p>
                <p>Interests @ 18% will be charged if payments is not within 30 days</p>
                <p>All disputes subject to kurukshetra jurisdiction</p>
            </div>
            <div class="last">
                <p>Certified that the particulars above are true and correct</p>
                <p>For:KATHPAL FANCY DRESS</p>
                <br><br><br><br>
                <i>Auth. Sign</i>
            </div>
        </div>
        <%}%>
    </body>
</html>
