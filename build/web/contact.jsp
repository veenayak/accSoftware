<%-- 
    Document   : dashboard
    Created on : 15 Apr, 2019, 10:22:40 PM
    Author     : winayak
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.LinkedHashSet"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="pojo.Sales_pojo"%>
<%@page import="pojo.Contact_pojo"%>
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

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

        <!-- font awesome icons -->
        <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>        
        
        <link rel="stylesheet" href="css/index.css">
        <style>
            #main .table th{
                width: 9.1%;
            }
            .table_div .editcontact, .table_div .delete,.div_toggle{
                float: right;
                margin-left: 10px;
            }
            .table_div h5{
                display: inline-block;
            }
            #contactdiv div{
                width: 50%;
                float: left;
            }
            .table_div h5{
                display: inline-block;
            }
            .table_div select,.collitem2{
                -webkit-appearance: none;
                border: 0;
                background: transparent;
            }
            .table_div, .alert_div{
                padding: 0;
                float: left;
            }
            .itemdiv+p{
                text-align: right;
                clear: both;
                font-weight: bold;
            }
            .phone{
                padding-left: 40px;
            }
            .itemdiv{
                padding-left: 45px;
            }
            @media only screen and (min-width:992px){
                .table_div{
                    padding-right: 15px;
                }
                .itemdiv div{
                    float: left;
                    width: 50%;
                }
            }
        </style>
        <script src="js/index.js"></script>

        <script>
            $(document).ready(function(){
                $("#salary").parent("div").hide(); 
                $("#gstin").parent("div").hide();
                $("#comp").parent("div").hide();
                $("#type").change(function(){
                    if($(this).val()=="employee"){
                         $("#salary").parent("div").show();
                    } 
                    else{
                         $("#salary").parent("div").hide();
                    }
                    if($(this).val()=="vendor"){
                        $("#gstin").parent("div").show();
                        $("#comp").parent("div").show(); 
                        $("#gstin").parent("div").show();
                    } 
                    else{
                        $("#gstin").parent("div").hide();
                        $("#comp").parent("div").hide();
                        $("#gstin").parent("div").hide();
                    }
                    console.log($(this).val());
                });
            });
        </script>
    </head>
    <body>
	<nav>
            <h3>PersAcc</h3>
        <% if(request.getSession(false)!=null && session.getAttribute("email") != null){%>
            <span data-toggle="collapse" data-target="#sidebar"><i class="fas fa-list"></i></span>
            <div class="dropdown dropleft">
                <span data-toggle="dropdown">
                    <i class="fas fa-user"></i>
                </span>
                <div class="dropdown-menu">
                    <a href="#" class="dropdown-item"><% out.println(session.getAttribute("email")); %></a>
                    <span><% out.println(session.getAttribute("llogin")); %></span>
                    <a class="dropdown-item" href="Logout">Logout</a>
                </div>
            </div>
            <% if(session.getAttribute("type").equals("multi")){%>            
            <div class="dropdown dropleft" id="optsel">
                <%  String[] gstin = session.getAttribute("gstin").toString().split(",");%>
                <h4 data-toggle="dropdown">
                    <% 
                        if(session.getAttribute("cgstin")==null){
                            out.println(gstin[0]);
                        }
                        else{
                            out.println(session.getAttribute("cgstin"));
                        }
                    %>
                
                </h4>
                <div class="dropdown-menu">
                    <% for(int i=0;i<gstin.length;i++){%>
                        <a href="#" class="dropdown-item"><%out.println(gstin[i]);%></a>
                    <%}%>
                </div>
            </div>
            
        <%}}%>
	</nav>
        <div id="sidebar" class="collapse show col-xl-2">
            <a href="dashboard.jsp">Dashboard</a>
            <a href="inventory.jsp">Inventory</a>
            <a href="sales.jsp">Sales</a>
            <a href="payments.jsp">Paid</a>
<!--            <a href="banking.jsp">Banking</a>
            <a href="reports.jsp">Reports</a>-->
            <a href="contact.jsp" class="active">Contacts</a>
            <a href="myaccount.jsp">Settings</a>
        </div>
        <% if(request.getSession(false)!=null && session.getAttribute("email") != null){
            if(session.getAttribute("privv").toString().contains("contact") || session.getAttribute("type").equals("admin") || session.getAttribute("type").equals("multi")){
        %>
        <div class="col-12 col-lg-12 col-xl-10" id="main"> 
            <div>
                <h4>Contact</h4>
                <div class="dropdown" style="display: inline-block;">
                    <button type="button" data-toggle="dropdown"><i class="fas fa-caret-down"></i></button>
                    <div class="dropdown-menu">
                        <a data-toggle="modal" data-target="#myModal1" class="dropdown-item">Add Contact</a>
                    </div>
                </div>  
            </div>
            <div class="table_div col-12 col-lg-8 col-xl-9"">
                    <% 
                         App obj = new App();
                         List l = obj.fetch_contact(session.getAttribute("gstin").toString());
                         Contact_pojo obj2 = new Contact_pojo();
                         Iterator i = l.iterator();
                         int z = 0;
                         if(!l.get(0).equals("0")){
                             while(i.hasNext()){
                                obj2 = (Contact_pojo) i.next();
                                z++;
                     %>
                     <div>
                    <span class="no"><%=z%>.</span>
                    <h5 class="name"><%out.println(obj2.getname());%></h5>
                    <span   class="div_toggle"><i class="fas fa-caret-down"></i></span>
                    <p><span class="phone"><%out.println(obj2.getphone());%></span></p>
                    <input class="eid" disabled style="display: none;" value="<%out.println(obj2.getid());%>">
                    <div class="collapse itemdiv">
                        <div>
                            <h5>Personal info</h5>
                            <p><span class="email"><%out.println(obj2.getemail());%></span></p>
                            <p>Company-<span class="comp"><%out.println(obj2.getcomp());%></span></p>
                            <p>Gstin-<span class="gstin"><%out.println(obj2.getcgstin());%></span></p>
                            <p>Salary-<span class="salary"><%out.println(obj2.getsalary());%></span></p>                            
                        </div>
                        <div>
                            <h5>Address</h5>
                            <p><span class="address"><%out.println(obj2.getaddress());%></span></p>
                            <p>State-<span class="city"><%out.println(obj2.getcity());%></span></p>
                            <p>City-<span class="state"><%out.println(obj2.getstate());%></span></p>
                            <p>State code-<span class="scode"><%out.println(obj2.getstatecode());%></span></p>
                            <p>Pin code-<span class="pcode"><%out.println(obj2.getpincode());%></span></p>                            
                        </div>
                        <div>
                            <h5>Account</h5>
                            <p>PAN no-<span class="pno"><%out.println(obj2.getpanno());%></span></p>
                            <p>IFSC-<span class="bifsc"><%out.println(obj2.getbifsc());%></span></p>
                            <p>Account No-<span class="accno"><%out.println(obj2.getacc_no());%></span></p>
                        </div>
                    </div>
                    <p><span class="type"><%out.println(obj2.gettype());%></span></p>
                    <span class="editcontact"><i class="fas fa-edit"></i></span>
                    <span class="delete"><i class="fas fa-trash"></i></span>
                </div>
                <%}}
                    else{%>
                        <div>
                            <h5>No information.</h5>
                        </div>
                    <%}%>
            </div>
                
            <div class="alert_div col-12 col-lg-4 col-xl-3"">
                <div>
                    <h5>
                        Unpaid consumers
                    </h5>
                </div>
                <div>
                    <h5>
                        Unpaid vendors
                    </h5>
                </div>
                <div>
                    <h5>
                        Unpaid employees
                    </h5>
                </div>
            </div>
                
        </div>                      
        
        <div class="modal fade" id="myModal1">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                      <h4 class="modal-title">Add Contacts</h4>
                      <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        <div class="form-group" style="display: none">
                            <label for="id">Id</label>
                            <input type="text" class="form-control" id="id" disabled>
                        </div>
                        <div class="form-group">
                            <label for="name">Name</label>
                            <input type="text" class="form-control" id="name" required>
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" class="form-control" id="email" required>
                        </div>
                        <div class="form-group">
                            <label for="tel">Phone Number</label>
                            <input type="number" class="form-control" id="tel" required>
                        </div>
                        <div class="form-group">
                            <label for="comp">Company Name</label>
                            <input type="text" class="form-control" id="comp">
                        </div>
                        <div class="form-group">
                            <label for="add">Address</label>
                            <input type="text" class="form-control" id="add" required>
                        </div>
                        <div class="form-group">
                            <label for="city">City</label>
                            <input type="text" class="form-control" id="city" required>
                        </div>
                        <div class="form-group">
                            <label for="pno">Pan No</label>
                            <input type="number" class="form-control" id="pno">
                        </div>
                        <div class="form-group">
                            <label for="state">State</label>
                            <input type="text" class="form-control" id="state" required>
                        </div>
                        <div class="form-group">
                            <label for="scode">State code</label>
                            <input type="text" class="form-control" id="scode">
                        </div>
                        <div class="form-group">
                            <label for="pcode">Pin code</label>
                            <input type="number" class="form-control" id="pcode">
                        </div>
                        <div class="form-group">
                            <label for="accno">Account No</label>
                            <input type="number" class="form-control" id="accno">
                        </div>
                        <div class="form-group">
                            <label for="bifsc">IFSC</label>
                            <input type="number" class="form-control" id="bifsc">
                        </div>
                        <div class="form-group">
                            <label for="salary">Salary</label>
                            <input type="number" class="form-control" id="salary" value="0">
                        </div>
                        <div class="form-group">
                            <label for="gstin">Gstin</label>
                            <input type="text" class="form-control" id="gstin" value="0">
                        </div>
                        <div class="form-group">
                            <label for="type">Type</label>
                            <select id="type" class="form-control">
                                <option value="consumer">
                                    Consumer
                                </option>
                                <option value="vendor">
                                    Vendor
                                </option>
                                <option value="employee">
                                    Employee
                                </option>
                            </select>
                        </div>
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                      <button type="button" class="btn btn-secondary" id="contactpost" value="insert">Save</button>
                    </div>

                </div>
            </div>
        </div>
        <div id="loading">
            <img src="img/loader.svg">
        </div>
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

