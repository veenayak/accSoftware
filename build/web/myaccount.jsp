<%-- 
    Document   : dashboard
    Created on : 15 Apr, 2019, 10:22:40 PM
    Author     : winayak
--%>

<%@page import="pojo.Warehouse_pojo"%>
<%@page import="pojo.Login_pojo"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
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
            #pwd{
                width: 85%;
                display: inline-block;
            }
            .modal input+button{
                background: #f1f1f1;
                border-radius: 0px .25rem .25rem 0px;
                border: 1px solid #dedede;
                padding: 8.5px;
                width: 15%;
                float: right;
            }
            input[type="checkbox"]{
                width: 40px;
                margin: auto;
            }
            .priv div{
                width: 25%;
                float: left;
            }
            .priv{
                overflow: hidden;
                text-align: center;
            }
            .table_div>div>div>span,.table_div>div>span{
                float: right;
                margin-left: 10px;
            }
            .table_div h5{
                display: inline-block;
            }
            .table_div select{
                -webkit-appearance: none;
                border: 0;
                background: transparent;
            }
            #myModal3 input{
                width: 50%;
                float: left;
            }
            #myModal3 .form-group{
                float: left;
            }
            #myModal3 label{
                width: 100%;
            }
            #myModal3 span{
                float: left;
                padding: 7px 10px;
                background: #e8e8e8;
                border-radius: 5px 0 0 5px;
            }
            .table_div+.table_div h4{
                margin-bottom: 15px;
            }
            .table_div+.table_div>div{
                overflow: hidden;
            }
            .table_div+.table_div>div>div{
                overflow: hidden;
                padding: 10px 0;
            }
            @media only screen and (min-width:992px){
                #myModal3 .form-group{
                    float: left;
                    width: 50%;
                }
            }
        </style>
        <script>
            $(document).ready(function(){
                $("#generatepwd").click(function(){
                    $("#pwd").val(Math.random().toString(36).substring(7));
                });
            });
        </script>
        <script src="js/index.js"></script>
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
        <div id="sidebar" class="collapse show col-md-4 col-xl-2">
            <a href="dashboard.jsp">Dashboard</a>
            <a href="inventory.jsp">Inventory</a>
            <a href="sales.jsp">Sales</a>
            <a href="payments.jsp">Paid</a>
<!--            <a href="banking.jsp">Banking</a>
            <a href="reports.jsp">Reports</a>-->
            <a href="contact.jsp">Contacts</a>
            <a href="myaccount.jsp" class="active">Settings</a>
        </div>
        <% if(request.getSession(false)!=null && session.getAttribute("email") != null){%>
            <div class="col-12 col-lg-12 col-xl-10" id="main"> 
                <div>
                    <h4>Setting</h4>
                    <%if(!session.getAttribute("type").toString().equals("sub")){%>
                    <div class="dropdown" style="display: inline-block;">
                        <button type="button" data-toggle="dropdown"><i class="fas fa-caret-down"></i></button>
                        <div class="dropdown-menu">
                            <a data-toggle="modal" data-target="#myModal2" class="dropdown-item">Add user</a>
                            <a data-toggle="modal" data-target="#myModal3" class="dropdown-item">Edit invoice serial</a>
                        </div>
                    </div>
                    <%}%>
                </div>                  
            
                <div class="table_div">
                    <div>
                        <%  App obj = new App();
                            List l = obj.fetch_login(session.getAttribute("email").toString(),null,"admin");
                            Login_pojo obj2 = (Login_pojo)l.get(0);
                            %>
                            <p style="display: none;"><span id="uid"><%out.println(obj2.getid());%></span></p>
                            <h5><span id="uname"><%out.println(obj2.getname());%></span></h5>
                            <p><span id="uemail"><%out.println(obj2.getemail());%></span></p>
                            <p>Contact-<span id="ucontact"><%out.println(obj2.getcontact());%></span></p>
                            <p>Domain-<span id="udomain"><%out.println(obj2.getdomain());%></span></p>
                            <p>Address-<span id="uaddress"><%out.println(obj2.getaddress());%></span></p>
                            <p>State-<span id="ustate"><%out.println(obj2.getstate());%></span></p>
                            <p>City-<span id="ucity"><%out.println(obj2.getcity());%></span></p>

                            <span id="edituser"><i class="fas fa-edit"></i></span>
                    </div>
                </div>
                <%if(!session.getAttribute("type").toString().equals("sub")){%>
                <div class="table_div">
                    <div>
                        <h4>Users</h4>
                        <%  List l2 = obj.fetch_login(null,session.getAttribute("gstin").toString(),"sub");
                            if(!l2.get(0).equals("0")){
                                Iterator i = l2.iterator();
                                while(i.hasNext()){
                                    Login_pojo obj3 = (Login_pojo)i.next();                                
                            %>
                            <div>
                                <p style="display: none;"><span class="id"><%out.println(obj3.getid());%></span></p>
                                <h5><span class="name"><%out.println(obj3.getname());%></span></h5>
                                <span data-toggle="collapse" class="showuser"><i class="fas fa-caret-down"></i></span>
                                <p><span class="email"><%out.println(obj3.getemail());%></span></p>
                                <div class="userdiv collapse">
                                    <p>Contact-<span class="contact"><%out.println(obj3.getcontact());%></span></p>
                                    <p>Address-<span class="address"><%out.println(obj3.getaddress());%></span></p>
                                    <p>State-<span class="state"><%out.println(obj3.getstate());%></span></p>
                                    <p>City-<span class="city"><%out.println(obj3.getcity());%></span></p>
                                    <p>Password-<span class="pwd"><%out.println(obj3.getpassword());%></span></p>
                                    <p>Privileges create-<span class="pc"><%out.println(obj3.getpriv_c());%></span></p>
                                    <p>Privileges view-<span class="pv"><%out.println(obj3.getpriv_v());%></span></p>
                                    <p>Privileges delete-<span class="pd"><%out.println(obj3.getpriv_d());%></span></p>
                                    <% 
                                        List l4 = obj.fetch_saved("warehouse", session.getAttribute("gstin").toString(),obj3.getwarehouse());
                                        if(!l4.get(0).equals("0")){
                                            Warehouse_pojo obj4 = (Warehouse_pojo)l4.get(0);
                                    %>
                                    <p>Warehouse-<select class="warehouse"><option value="<%out.println(obj4.getid());%>"><%out.println(obj4.getwarename());%></option></select></p>
                                    <%}%>
                                </div>
                                
                                <span class="edituser"><i class="fas fa-edit"></i></span>
                                <span class="delete"><i class="fas fa-trash"></i></span>
                            </div>
                        <%}}
                            else{%>
                                <div>
                                    <h5>No users found.</h5>
                                </div>
                            <%}%>
                    </div>
                </div>
                <%}%>
            </div>
            <div class="modal fade small_modal" id="myModal1">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">

                        <!-- Modal Header -->
                        <div class="modal-header">
                          <h4 class="modal-title">Edit Info</h4>
                          <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>

                        <div class="modal-body">
                            <div class="form-group">
                                <input type="number" id="uid2" disabled  style="display:none;"> 
                                <label for="uname2">Name</label>
                                <input type="text" id="uname2" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label for="uaddress2">Address</label>
                                <input type="text" id="uaddress2" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="ustate2">State</label>
                                <input type="text" id="ustate2" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="ucity2">City</label>
                                <input type="text" id="ucity2" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="ucontact2">Contact</label>
                                <input type="tel" id="ucontact2" class="form-control" required>
                            </div>
    <!--                        <div class="form-group">
                                <label for="upincode2">Pincode</label>
                                <input type="tel" id="upincode2" class="form-control" required>
                            </div>-->
    <!--                        <div class="form-group">
                                <label for="ustatecode2">Contact</label>
                                <input type="tel" id="ustatecode2" class="form-control" required>
                            </div>-->
                        </div>

                        <!-- Modal footer -->
                        <div class="modal-footer">
                          <button type="button" id="addetails">Save</button>
                        </div>

                    </div>
                </div>
            </div>  
        <%if(!session.getAttribute("type").toString().equals("sub")){%>

        <div class="modal fade small_modal" id="myModal2">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                      <h4 class="modal-title">Add user</h4>
                      <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <div class="modal-body">
                        <div class="form-group">
                            <input type="number" id="id" disabled  style="display:none;"> 
                            <label for="name">Name</label>
                            <input type="text" id="name" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" id="email" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="text" id="pwd" class="form-control" required>
                            <button id="generatepwd"><i class="fas fa-unlock-alt"></i></button>
                        </div>
                        <div class="form-group">
                            <label for="address">Address</label>
                            <input type="text" id="address" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="state">State</label>
                            <input type="text" id="state" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="city">City</label>
                            <input type="text" id="city" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="contact">Contact</label>
                            <input type="tel" id="contact" class="form-control" required>
                        </div>
                        <div class="form-group">
                                <label for="warhouse">warehouse</label>
                                <select id="warehouse" class="form-control">
                                    <% List l3 = obj.fetch_saved("warehouse", session.getAttribute("gstin").toString(), 0);
                                        Iterator i2 = l3.iterator();
                                        if(!l3.get(0).equals("0")){                                        
                                            while(i2.hasNext()){
                                            Warehouse_pojo  obj5 = (Warehouse_pojo)i2.next();
                                    %>
                                    <option value="<%out.println(obj5.getid());%>"><%out.println(obj5.getwarename());%></option>
                                    <%}}%>
                                </select>
                        </div>
                        <div class="priv">
                            <h6>Create privileges</h6>
                            <div class="form-group">
                                <label for="sc">sales</label>
                                <input type="checkbox" id="sc" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="pc">Purchase</label>
                                <input type="checkbox" id="pc" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="ic">Inventory</label>
                                <input type="checkbox" id="ic" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="ic">Contact</label>
                                <input type="checkbox" id="cc" class="form-control">
                            </div>
                        </div>
                        <div class="priv">
                            <h6>View privileges</h6>
                            <div class="form-group">
                                <label for="sv">sales</label>
                                <input type="checkbox" id="sv" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="pv">Purchase</label>
                                <input type="checkbox" id="pv" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="iv">Inventory</label>
                                <input type="checkbox" id="iv" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="iv">Contact</label>
                                <input type="checkbox" id="cv" class="form-control">
                            </div>
                        </div>
                        <div class="priv">
                            <h6>Delete privileges</h6>
                            <div class="form-group">
                                <label for="sd">sales</label>
                                <input type="checkbox" id="sd" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="pd">Purchase</label>
                                <input type="checkbox" id="pd" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="id">Inventory</label>
                                <input type="checkbox" id="idel" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="cd">Contact</label>
                                <input type="checkbox" id="cd" class="form-control">
                            </div>
                        </div>
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="button" id="adduser" value="insert">Save</button>
                    </div>

                </div>
            </div>
        </div> 
        <div class="modal fade small_modal" id="myModal3">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                      <h4 class="modal-title">Edit Invoice serial</h4>
                      <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <div class="modal-body">
                        <div class="form-group">
                            <label for="sis">Sales Invoice serial</label>
                            <span id="sis2">SI</span>
                            <input type="number" id="sis2" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="pis2">Purchase Invoice serial</label>
                            <span id="pis">PI</span>
                            <input type="number" id="pis2" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="prs2">Proforma serial</label>
                            <span id="prs">PR</span>
                            <input type="number" id="prs2" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="ess2">Estimate serial</label>
                            <span id="ess">ES</span>
                            <input type="number" id="ess2" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="rvs2">Refund voucher serial</label>
                            <span id="rvs">REFV</span>
                            <input type="number" id="rvs2" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="pvs">Purchase voucher serial</label>
                            <span id="pvs">PV</span>
                            <input type="number" id="pvs2" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="bos2">Bills of supply serial</label>
                            <span id="bos">BOS</span>
                            <input type="number" id="bos2" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="exs2">Export serial</label>
                            <span id="exs">EX</span>
                            <input type="number" id="exs2" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="ars2">Advance receipt serial</label>
                            <span id="ars">AR</span>
                            <input type="number" id="ars2" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="ars2">Advance payment serial</label>
                            <span id="aps">AP</span>
                            <input type="number" id="aps2" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="pos2">Purchase order serial</label>
                            <span id="pos">PO</span>
                            <input type="number" id="pos2" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="dcs2">Delivery challan serial</label>
                            <span id="dcs">DC</span>
                            <input type="number" id="dcs2" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="cns">Credit note serial</label>
                            <span id="cns">CN</span>
                            <input type="number" id="cns2" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="dns2">Debit note serial</label>
                            <span id="dns">DN</span>
                            <input type="number" id="dns2" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="oos2">Online orders serial</label>
                            <span id="oos">OO</span>
                            <input type="number" id="oos2" class="form-control" required>
                        </div>
                                               
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                      <button type="button" id="addserial">Save</button>
                    </div>

                </div>
            </div>
        </div>  
        <div id="loading">
            <img src="img/loader.svg">
        </div>
        <%}}
        else{
            out.println("<script>alert(\"Session expired.\");location.assign(\"index.html\");</script>");
        }
        %>
    </body>
</html>
