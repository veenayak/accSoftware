<%-- 
    Document   : dashboard
    Created on : 15 Apr, 2019, 10:22:40 PM
    Author     : winayak
--%>

<%@page import="pojo.Purchases_pojo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="pojo.Subcategory_pojo"%>
<%@page import="pojo.Collections_pojo"%>
<%--<%@page import="pojo.Tax_pojo"%>--%>
<%@page import="pojo.Size_pojo"%>
<%@page import="pojo.Unit_pojo"%>
<%@page import="pojo.Warehouse_pojo"%>
<%@page import="pojo.Category_pojo"%>
<%@page import="pojo.Login_pojo"%>
<%@page import="java.util.Iterator"%>
<%@page import="pojo.Items_pojo"%>
<%@page import="java.util.List"%>
<%@page import="configuration.App"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>JSP Page</title>
        <%@ include file="imports.jsp" %>  

        <link rel="stylesheet" href="css/index.css">
        <style>
            #main .table th{
                width: 6.66%;
            }
            .form-group{
                overflow: hidden;
                
            }
            label{
                width: 100%;
            }
            #taxmodal .modal-body>div input,#taxmodal .modal-body>div label{
                width: 42.5%;
                float: left;
            }
            #taxmodal .modal-body input{
                width: 40%;
            }
            #collections2,.collitem,#warehousemodal .modal-body>div{
                margin-bottom: 20px!important;
            }
            #addcollitem{
                background: transparent;
                border: 0;
                font-size: 1.8rem;
                font-weight: bold;
                display: block;
                clear: both;
                text-align: center;
                line-height: 1;
                margin-bottom: 20px;

            }
            .delcollitem{
                width: 10%;
                float: left;
                max-width: 30px!important;
            }
            #warehousemodal .modal-body>div input:nth-of-type(odd),#warehousemodal .modal-body>div label:nth-of-type(odd){
                float: left;
                width: 45%;
            }
            #warehousemodal .modal-body>div input:nth-of-type(even),#warehousemodal .modal-body>div label:nth-of-type(even){
                float: right;
                width: 45%;
            }
            
            .qrcode{
                text-align: center;
            }
/*            #main h4 a{
                color: black;
            }*/
            .table_div h5{
                display: inline-block;
            }
            .table_div select,.collitem2{
                -webkit-appearance: none;
                border: 0;
                background: transparent;
            }
            .iscat,.icat,.iscat+i,.iunit+span,.table_div .edititem,.table_div .delete{
                float: right;
                margin-left: 10px;
            }
            .itemdiv{
                padding-left: 45px;
            }
            .icat+p{
                text-align: right;
                clear: both;
                font-weight: bold;
            }
            .table_div,.alert_div{
                padding: 0px;
                float: left;
            }
            .idesc{
                padding-left: 40px;
            }
            #oos{
                border: 0;
                height: 5px;
                background: #bd6f6f;
                float: left;
            }
            #allim{
                border: 0;
                height: 5px;
                background: #e6c088;
                float: left;
            }
            #warehousemodal p{
                float: left;
                padding-bottom: 15px;
            }
            #warehousemodal input{
                margin-bottom: 15px;
            }
            #warehousemodal p input,#warehousemodal p label{
                width: 50%;
            }
            .itemimage{
                width: 50px;
                height: 50px;
                float: right;
                border-radius: 5px;
            }
            .div_toggle{
                float: right;
                margin-left: 10px;
            }
            .collitem2{
                margin-right: 10px;
            }
            .delcollitem{
                color: #fff;
                background: #945050!important;
                border: 0!important;
            }
            #myModal1 input[type="checkbox"]{
                width: 30px;
            }
            @media only screen and (min-width:992px){
                .table_div{
                    padding-right: 15px;
                }
                .itemdiv div{
                    float: left;
                    width: 50%;
                }
                #taxmodal .modal-body button,#collectionsmodal select+button,#collectionsmodal input+button,
                #sizemodal input+button,#unitmodal input+button,#myModal2 input+button,#myModal3 input+button{
                    background: #f1f1f1;
                    border-radius: 5px;
                    border: 1px solid #dedede;
                    padding: 8.5px;
                    width: 15%;
                    max-width: 60px;
                    float: right;
                }
                #warehousemodal .modal-body button{
                    background: #f1f1f1;
                    border: 1px solid #dedede;
                    padding: 8.5px;
                    border-radius: 5px;
                    width: 15%;
                    float: right;
                    margin: 20px 0;
                }
                #collectionsmodal input,#collectionsmodal .modal-body>div select,#sizemodal label+input,
                #unitmodal label+input,#myModal2 label+input,#myModal3 label+input{
                    width: 80%; 
                    float: left;
                    border-radius: 5px;
                }
            }
            @media only screen and (max-width:992px){
                #taxmodal .modal-body button,#collectionsmodal select+button,#collectionsmodal input+button,
                #sizemodal input+button,#unitmodal input+button,#myModal2 input+button,#myModal3 input+button{
                    background: #f1f1f1;
                    border-radius: 5px;
                    border: 1px solid #dedede;
                    padding: 8.5px;
                    width: 60px;
                    float: right;
                }
                #warehousemodal .modal-body button{
                    background: #f1f1f1;
                    border: 1px solid #dedede;
                    padding: 8.5px;
                    border-radius: 5px;
                    width: 15%;
                    width: 60px;
                    float: right;
                    margin: 20px 0;
                }
                #collectionsmodal input,#collectionsmodal .modal-body>div select,#sizemodal label+input,
                #unitmodal label+input,#myModal2 label+input,#myModal3 label+input{
                    width: 75%; 
                    float: left;
                    border-radius: 5px;
                }
            }
             
/*            @media only screen and (max-width:1220px){
                #myModal3 input+button,#code+button{
                    width: 35%;
                }
                #myModal3 input,#code{
                    width: 65%;
                }
                
            }*/
        </style>
        <script>
            $(document).ready(function(){
                $("#sidebar a:nth-child(2)").addClass("active");
            }); 
        </script>
    </head>
    <body>
	<%@ include file="nav.jsp" %>  
	<%@ include file="sidebar.jsp" %>  
        <% if(request.getSession(false)!=null && session.getAttribute("email") != null){
            if(session.getAttribute("privv").toString().contains("inventory") || session.getAttribute("type").toString().equals("admin") || session.getAttribute("type").toString().equals("multi")){
                
        %>                                       
        <div id="main"> 
            <div>
                <h1>Inventory</h1>                
                <div class="dropdown" style="display: inline-block;">
                    <i class="fas fa-caret-down"></i>
                    <div class="dropdown-div">
                        <a class="dropdown-item">Add items</a>
                        <a>QRcode</a>
                        <a>Category</a>
                        <a>Collections</a>
                        <a>Unit</a>
                        <a>Warehouse</a>
                        <a>Stock</a>
                    </div>
                </div>    
            </div>
            <div class="table_div col-12 col-lg-8 col-xl-9">
                <% 

                    App obj = new App();
                    List l = obj.fetch_items(session.getAttribute("gstin").toString(),0);
                    Items_pojo obj2 = new Items_pojo();
                    int oos = 0;
                    int ballim = 0;
                    float oosp = 0;
                    float ballimp = 0;
                    int total = 0;
                    Category_pojo obj3 = new Category_pojo();
                    Unit_pojo obj4 = new Unit_pojo();
                    Size_pojo obj5 = new Size_pojo();
                    Warehouse_pojo obj8 = new Warehouse_pojo();
                    Subcategory_pojo obj9 = new Subcategory_pojo();
                    Purchases_pojo obj10 = new Purchases_pojo();
                    Iterator i2 = l.iterator();
                    int z = 0;
                    if(!l.get(0).equals("0")){
                            while(i2.hasNext()){                              
                                obj2 = (Items_pojo)i2.next();  
                                if(obj2.getwarestock()==0){
                                    oos++;
                                }
                                else if(obj2.getwarestock()<=obj2.getlimit()){
                                    ballim++;
                                }
                                total++;
                                z++;

                %>         
                    <div> 
                        <input type="text" class="eid" disabled value="<%out.println(obj2.getid());%>" hidden>
                        <span class="no"><%=z%>.</span>
                        <h5 class="iname"><%out.println(obj2.getname());%>
                        <% List l8 = obj.fetch_saved("size",session.getAttribute("gstin").toString(),obj2.getsize());
                            obj5 = (Size_pojo)l8.get(0);
                            List l6 = obj.fetch_saved("category",session.getAttribute("gstin").toString(),obj2.getcat());
                            obj3 = (Category_pojo)l6.get(0);
                            List l7 = obj.fetch_saved("subcategory",session.getAttribute("gstin").toString(),obj2.getscat());
                            obj9 = (Subcategory_pojo)l7.get(0);
                            List l9 = obj.fetch_saved("unit",session.getAttribute("gstin").toString(),obj2.getunit());
                            obj4 = (Unit_pojo)l9.get(0);
                       %>
                        </h5>
                        <select class="isize" disabled>
                           <option value="<%out.println(obj2.getsize());%>"><%out.println(obj5.getsize());%></option>
                        </select> 
                        (<select class="iunit" disabled>
                                   <option value="<%out.println(obj2.getunit());%>"><%out.println(obj4.getunit());%></option>
                        </select>) 
                        <img href="<%out.println(obj2.getimghref());%>" class="itemimage">
                        <p><span class="idesc"><%out.println(obj2.getdesc());%></span></p>
                        <div class="collapse itemdiv">
                            <div>
                                <h5>Product info</h5>
                                <p>code-<span class="icode"><%out.println(obj2.getcode());%></span></p>
                                <p>hsncode-<span class="ihsncode"><%out.println(obj2.gethsncode());%></span></p>
                                <p>Cost-<span class="icp"><%out.println(obj2.getcp());%></span></p>
                                <p>Selling amount-<span class="isp"><%out.println(obj2.getsp());%></span></p>
                                <p>Alert limit-<span class="ilimit"><%out.println(obj2.getlimit());%></span></p>                            
                                <p>Discount-<span class="idiscount"><%out.println(obj2.getdiscount());%></span>
                                    <%if(obj2.getdiscounttype().toString().equals("percentage")){
                                        out.println("<span class=\"idiscounttype\" style=\'display:none;\'>percentage</span><span>%</span>");
                                    %>
                                    <%}else{out.println("<span class=\"idiscounttype\">rs</span>");}%>
                                </p> 
                            </div>
                            <div>
                                <h5>Tax</h5>
                                <p>CGST-<span class="icgst"><%out.println(obj2.getcgst());%></p>
                                <p>SGST-<span class="isgst"><%out.println(obj2.getsgst());%></p>
                                <p>IGST-<span class="iigst"><%out.println(obj2.getigst());%></p>
                                <p>CESS-<span class="icess"><%out.println(obj2.getcess());%></p>                                
                            </div>
                            <div>
                                <h5>Stock</h5>
                                <p>
                                <%  List l10 = obj.fetch_saved("warehouse",session.getAttribute("gstin").
                                                toString(),obj2.getwarehouse());
                                        obj8 = (Warehouse_pojo)l10.get(0);
                                        
                                %>
                                    <select class="iware">
                                       <option value="<%out.println(obj2.getwarehouse());%>"><%out.println(obj8.getwarename());%></option>
                                    </select> 
                                    <select class="istock">
                                       <option value="<%out.println(obj2.getwarestock());%>"><%out.println(obj2.getwarestock());%></option>
                                    </select>                             
                                </p>
                            </div>
                        </div>
                         <select class="iscat" disabled>
                            <option value="<%out.println(obj2.getscat());%>"><%out.println(obj9.getscat());%></option>
                        </select> 
                        <i>&gt;</i>
                        <select class="icat" disabled>
                            <option value="<%out.println(obj2.getcat());%>"><%out.println(obj3.getcatname());%></option>
                        </select>  
                        <p><span class="itype"><%out.println(obj2.gettype());%></p>
                        <span class="div_toggle"><i class="fas fa-caret-down"></i></span> 
                        <span class="edititem"><i class="fas fa-edit"></i></span>
                        <span class="delete"><i class="fas fa-trash"></i></span>
                    </div>
                <%}}
                    else{%>
                        <div>
                            <h5>Your inventory is empty.</h5>
                        </div>
                    <%}%>
            </div>
            <div class="alert_div col-12 col-lg-4 col-xl-3">
                <div>
                    <h5>
                        Out of stock - <%=oos%>
                    </h5>
                        <%if(total!=0){ 
                            oosp = Float.valueOf(oos*100/total);
                            ballimp = Float.valueOf(ballim*100/total);
                        %>
                        <hr id="oos" style="width:<%out.println(oosp+"%");%>">
                        <%}%>
                </div>
                <div>
                    <h5>
                        Below alert limit - <%=ballim%>
                    </h5>
                    <%if(total!=0){%>
                    <hr id="allim" style="width:<%out.println(ballimp+"%");%>">
                    <%}%>
                </div>
            </div>
        </div>
        <!--items modal-->
        <div class="modal fade small_modal" id="myModal1" >
            <input type="number" class="form-control" id="itemid" style="display: none;" disabled>
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                      <h4 class="modal-title">Add Items</h4>
                      <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="name">Name</label>
                            <input type="text" class="form-control" id="name" required>
                        </div>
                        <div class="form-group">
                            <label for="desc">Description</label>
                            <input type="text" class="form-control" id="desc">
                        </div>
                        <div class="form-group" style="display:none">
                            <label for="code">Code</label>
                            <input type="text" class="form-control" id="code" disabled>
                            <button><i class="fas fa-calculator"></i></button>
                        </div>
                        <div class="form-group">
                            <label for="hsncode">HSN Code</label>
                            <input type="number" class="form-control" id="hsncode" required>
                            <a href="">Not sure? Look here-></a>
                        </div>
                        <div class="form-group">
                            <label for="cp">Cost Price</label>
                            <input type="number" class="form-control" id="cp" required>
                        </div>
                        <div class="form-group">
                            <label for="sp">Selling Price</label>
                            <input type="number" class="form-control" id="sp" required>
                        </div>
                        <div class="form-group">
                            <label for="taxrate">Tax rate</label>
                            <input type="number" class="form-control" id="taxrate" required>
                        </div>
                        <div class="form-group">
                            <label for="cess">CESS</label>
                            <input type="number" class="form-control" id="cess" required>
                        </div>
                        <div class="form-group">
                            <label for="cat">Category</label>
                            <select id="cat" class="form-control" required>
                                <% 
                                    List l2 = obj.fetch_saved("category",session.getAttribute("gstin").toString(),0);
                                    Iterator i3 = l2.iterator();
                                    if(!l2.get(0).equals("0")){                                        
                                    while(i3.hasNext()){
                                        obj3 = (Category_pojo)i3.next(); 
                                %>
                                <option value="<%out.println(obj3.getid());%>"><%out.println(obj3.getcatname());%></option>   
                                <%}}%> 
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="scat">Sub-Category</label>
                            <select id="scat" class="form-control" required>
                                <%
                                    List l8 = obj.fetch_saved("subcategory",session.getAttribute("gstin").toString(),0);
                                    Iterator i4 = l8.iterator();
                                        if(!l8.get(0).equals("0")){                                        
                                        while(i4.hasNext()){
                                            obj9 = (Subcategory_pojo)i4.next(); 
                                %>
                                <option value="<%out.println(obj9.getid());%>"><%out.println(obj9.getscat());%></option>   
                                <%}}%>  
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="size">Size</label>
                            <select id="size" class="form-control" required>
                                <% 
                                    List l4 = obj.fetch_saved("size",session.getAttribute("gstin").toString(),0);
                                    Iterator i14 = l4.iterator();
                                    if(!l4.get(0).equals("0")){                                        
                                    while(i14.hasNext()){
                                        obj5 = (Size_pojo)i14.next(); 
                                %>
                                <option value="<%out.println(obj5.getid());%>"><%out.println(obj5.getsize());%></option>   
                                <%}}%> 
                            </select>                                                            
                        </div>
                        <div class="form-group">
                            <label for="limit">Limit</label>
                            <input type="text" class="form-control" id="limit" required>
                        </div>
                        <div class="form-group">
                            <label for="discount">Discount</label>
                            <input type="number" class="form-control" id="discount" required>
                        </div>
                        <div class="form-group">
                            <select class="form-control" id="discounttype">
                                <option value="rs">rs</option>
                                <option value="percentage">%</option>
                            </select>
                        </div>
                        <div class="form-group" required>
                            <label for="warehouse">Warehouse</label>
                            <select id="ware" class="form-control">
                                <%  List l7 = obj.fetch_saved("warehouse",session.getAttribute("gstin").toString(),0);
                                    Iterator i17 = l7.iterator();
                                    if(!l7.get(0).equals("0")){                                        
                                    while(i17.hasNext()){
                                        obj8 = (Warehouse_pojo)i17.next(); 
                                    %>
                                    <option value="<%out.println(obj8.getid());%>"><%out.println(obj8.getwarename());%></option> 
                                <%}}%>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="stock">Stock</label>
                            <input type="number" id="stock" class="form-control" required>
                        </div>
                            <div class="form-group" style="clear: both;">
                            <label for="unit">unit</label>
                            <select id="unit" class="form-control" required>
                                <%  List l3 = obj.fetch_saved("unit",session.getAttribute("gstin").toString(),0);
                                    Iterator i16 = l3.iterator();
                                    if(!l3.get(0).equals("0")){        
                                    while(i16.hasNext()){                                
                                        obj4 = (Unit_pojo)i16.next();                                  
                                %>
                                <option value="<%out.println(obj4.getid());%>"><%out.println(obj4.getunit());%></option>   
                                <%}}%>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="type">Type</label>
                            <select id="type" class="form-control" required>
                                <option value="inventory">Inventory-->will be recorded in accounts</option>   
                                <option value="noninventory">Non-Inventory-->Items you dont want to keep track of</option> 
                            </select>
                        </div>
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                      <button type="button" id="itempost" value="insert">Save</button>
                    </div>

                </div>
            </div>
        </div>     
        <!--category modal-->     
        <div class="modal fade small_modal" id="myModal2">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                      <h4 class="modal-title">Add Category</h4>
                      <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <div class="modal-body">
                        <div class="form-group">
                            <label for="cat2">Category</label>
                            <input type="text" id="cat2" class="form-control" required>
                            <button type="button" id="addcat" value="insert">Save</button>
                            <input type="number" id="catid" disabled  style="display:none;"> 
                        </div>
                            <% Iterator i5 = l2.iterator();
                                if(!l2.get(0).equals("0")){        
                                while(i5.hasNext()){                                
                                    obj3 = (Category_pojo)i5.next();                                  
                            %>
                            <p>
                                <input type="text" class="eid form-control" value="<%out.println(obj3.getid());%>" style="display:none;" disabled>
                                <input type="text" class="category" value="<%out.println(obj3.getcatname());%>" disabled>
                                <span class="editcat"><i class="fas fa-edit"></i></span>
                                <span class="delete"><i class="fas fa-trash"></i></span>
                            </p>
                            <%}}%> 
                        <br> 
                        <div class="form-group">
                            <label for="scat2">Sub-Category</label>
                            <input type="text" class="form-control" id="scat2" required>
                            <button type="button" id="addscat" value="insert">Save</button>
                            <input type="number" id="scatid" disabled  style="display:none;"> 
                        </div>
                            <%
                                Iterator i0 = l8.iterator();
                                if(!l8.get(0).equals("0")){                                        
                                while(i0.hasNext()){
                                    obj9 = (Subcategory_pojo)i0.next(); 
                            %>
                        <p>
                            <input type="text" class="eid form-control" value="<%out.println(obj9.getid());%>" disabled style="display: none">
                            <input type="text" class="scategory" value="<%out.println(obj9.getscat());%>" disabled> 
                            <span class="editscat"><i class="fas fa-edit"></i></span>
                            <span class="delete"><i class="fas fa-trash"></i></span>
                        </p>
                            <%}}%> 
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                      <button type="button" data-dismiss="modal">Close</button>
                    </div>

                </div>
            </div>
        </div>   
                        
        <div class="modal fade small_modal" id="unitmodal">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">

                   <!-- Modal Header -->
                    <div class="modal-header">
                      <h4 class="modal-title">Add Unit/Size</h4>
                      <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <div class="modal-body">                        
                        <div class="form-group">
                            <label for="unit2">Unit</label>
                            <input type="text" class="form-control" id="unit2">
                            <button type="button" id="addunit" value="insert">Save</button>
                            <input type="number" class="form-control" id="unitid" style="display: none;" required disabled>
                        </div>
                            <%  
                                Iterator i6 = l3.iterator();
                                if(!l3.get(0).equals("0")){        
                                while(i6.hasNext()){                                
                                    obj4 = (Unit_pojo)i6.next();                                  
                            %>
                            <p>
                                <input type="text" class="eid form-control" value="<%out.println(obj4.getid());%>" disabled style="display: none">
                                <input type="text" class="unit" value="<%out.println(obj4.getunit());%>" disabled>
                            <span class="editunit"><i class="fas fa-edit"></i></span>
                            <span class="delete"><i class="fas fa-trash"></i></span>
                            </p>
                            <%}}%> 
                        <br>
                        <div class="form-group">
                            <label for="size2">Size</label>
                            <input type="text" class="form-control" id="size2">
                            <button type="button" id="addsize" value="insert">Save</button>
                            <input type="number" id="sizeid" disabled  style="display:none;"> 
                        </div>
                            <%  
                                Iterator i7 = l4.iterator();
                                if(!l4.get(0).equals("0")){        
                                while(i7.hasNext()){                                
                                    obj5 = (Size_pojo)i7.next();                                  
                            %>
                        <p>
                            <input type="text" class="eid form-control" value="<%out.println(obj5.getid());%>" disabled style="display: none">
                            <input type="text" class="size" value="<%out.println(obj5.getsize());%>" disabled>
                            <span class="editsize"><i class="fas fa-edit"></i></span>
                            <span class="delete"><i class="fas fa-trash"></i></span>
                        </p>
                        <%}}%> 
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                      <button type="button" class="submit3" data-dismiss="modal">Close</button>
                    </div>

                </div>
            </div>
        </div>                                 
                        
                        
        <div class="modal fade small_modal" id="collectionsmodal">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                      <h4 class="modal-title">Add Collections</h4>
                      <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <div class="modal-body">

                        <div class="form-group">
                            <label for="collections2">Collections</label>
                            <select class="form-control collitem">
                                <%
                                    Iterator i11 = l.iterator();
                                    if(!l.get(0).equals("0")){
                                            while(i11.hasNext()){                              
                                                obj2 = (Items_pojo)i11.next();                                      

                                %>         
                                    <option value="<%out.println(obj2.getid());%>"><%out.println(obj2.getname());%></option>
                                <%}}%>
                            </select>
                            <button type="button" class="delcollitem"><i class="fas fa-trash"></i></button>
                            <select class="form-control collitem">
                                <%
                                    Iterator i12 = l.iterator();
                                    if(!l.get(0).equals("0")){
                                            while(i12.hasNext()){                              
                                                obj2 = (Items_pojo)i12.next();                                      

                                %>         
                                    <option value="<%out.println(obj2.getid());%>"><%out.println(obj2.getname());%></option>
                                <%}}%>
                            </select>
                            <button type="button" class="delcollitem"><i class="fas fa-trash"></i></button>
                            <a href="#" id="addcollitem">+</a>
                            <input type="text" class="form-control" id="collections2">
                            <button type="button" id="addcoll" value="insert">Save</button>
                            <input type="number" class="form-control" id="collid" style="display: none;" required disabled>
                        </div>
                        <%  List l6 = obj.fetch_saved("collections",session.getAttribute("gstin").toString(),0);
                            Collections_pojo obj7 = new Collections_pojo();
                            Iterator i9 = l6.iterator();
                            List l19 = new ArrayList();
                            List l21 = new ArrayList();
                            if(!l6.get(0).equals("0")){        
                            while(i9.hasNext()){                                
                                obj7 = (Collections_pojo)i9.next();                                                         
                                String item = obj7.getitems().toString();
                                l19.add(item);
                                l21.add(obj7.getcollections());
                            }
                            int i = 0;
                            while(i!=l19.size()){        
                                int j = 0;
                                String item2[] = l19.get(i).toString().split(",");  
                                out.println("<p>");
                                out.println("<span class=\"collections\">"+l21.get(i)+"</span><br>");
                                while(j!=item2.length){
                                    String itemid = item2[j].trim().replaceAll("\n ", "");
                                    List l20 = obj.fetch_items(session.getAttribute("gstin").toString(),Integer.parseInt(itemid));
                                    obj2 = (Items_pojo)l20.get(0);
                            %>
                      
                            <select class="collitem2">
                                <option value="<%out.println(item2[j]);%>"><%out.println(obj2.getname());%></option>
                            </select>
                            <%j++;}%>    
                            <span class="editcoll"><i class="fas fa-edit"></i></span>
                            <span class="delete"><i class="fas fa-trash"></i></span>
                        </p>                        
                        <%i++;}}%> 
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                      <button type="button" class="submit3" data-dismiss="modal">Close</button>
                    </div>

                </div>           

            </div>
        </div>  
        <div class="modal fade small_modal" id="warehousemodal">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                      <h4 class="modal-title">Add Warehouse</h4>
                      <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <div class="modal-body">

                        <div class="form-group">
                            <label for="Warehouse2">Warehouse</label>
                            <label for="city2">City</label>
                            <input type="text" class="form-control" id="warehouse2">
                            <input type="text" class="form-control" id="city2">
                            <label for="state2">State</label>
                            <label for="pincode2">Pincode</label>
                            <input type="text" class="form-control" id="state2">
                            <input type="number" class="form-control" id="pincode2">
                            <label for="statecode2">Statecode</label>
                            <label for="address2">Address</label>
                            <input type="text" class="form-control" id="statecode2">
                            <input type="text" class="form-control" id="address2">
                            <button type="button" id="addware" value="insert">Save</button>
                            <input type="number" class="form-control" id="wareid" style="display: none;" required disabled>
                        </div>
                        <p>
                            <%  
                                Iterator i10 = l7.iterator();
                                if(!l7.get(0).equals("0")){        
                                while(i10.hasNext()){                                
                                    obj8= (Warehouse_pojo)i10.next();                                  
                            %>                            
                            <input type="text" class="eid form-control" value="<%out.println(obj8.getid());%>" disabled style="display: none">
                            <label>Warehouse</label>
                            <input type="text" class="warehouse" value="<%out.println(obj8.getwarename());%>" disabled>
                            <br>
                            <label>State</label>
                            <input type="text" class="state" value="<%out.println(obj8.getstate());%>" disabled>
                            <label>City</label>
                            <input type="text" class="city" value="<%out.println(obj8.getcity());%>" disabled>
                            <label>Pin code</label>
                            <input type="text" class="pincode" value="<%out.println(obj8.getpincode());%>" disabled>
                            <label>State Code</label>
                            <input type="text" class="statecode" value="<%out.println(obj8.getstatecode());%>" disabled>
                            <label>Address</label>
                            <input type="text" class="address" value="<%out.println(obj8.getaddress());%>" disabled>
                            <%}}%> 
                            <span class="editware"><i class="fas fa-edit"></i></span>
                            <span class="delete"><i class="fas fa-trash"></i></span>
                        </p>
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                      <button type="button" class="submit3" data-dismiss="modal">Close</button>
                    </div>

                </div>
            </div>
        </div>  
        <div class="modal fade small_modal" id="stockmodal" style="display:none;">
            <div class="modal-dialog modal-dialog-centered">
              <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                  <h4 class="modal-title">Update stock</h4>
                  <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <div class="form-group" required>
                        <label for="warehouse">Warehouse</label>
                        <select class="ware2 form-control">
                            <% 
                                Iterator i20 = l7.iterator();
                                if(!l7.get(0).equals("0")){                                        
                                while(i20.hasNext()){
                                    obj8 = (Warehouse_pojo)i20.next(); 
                                %>
                                <option value="<%out.println(obj8.getid());%>"><%out.println(obj8.getwarename());%></option> 
                            <%}}%>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="product2">Product</label>
                        <select class="ware2 form-control">
                           <% 
                               Iterator i21 = l.iterator();
                               if(!l.get(0).equals("0")){                                        
                               while(i21.hasNext()){
                                   obj2 = (Items_pojo)i21.next(); 
                               %>
                               <option value="<%out.println(obj2.getid());%>"><%out.println(obj2.getname());%></option> 
                           <%}}%>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="stock2">Stock</label>
                        <input type="number" class="form-control stock2" required>
                    </div>
                        
                </div>

                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" value="insert" id="addstock">save</button>
                </div>
              </div>
            </div>
        </div>  
                            
        <div class="modal fade small_modal" id="myModal3">
            <div class="modal-dialog modal-dialog-centered">
              <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                  <h4 class="modal-title">QR code</h4>
                  <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <div class="form-group">
                        <label for="generatei">Enter to generate QR code</label>
                        <input type="text" class="form-control" required>
                        <button id="generatei"><i class="fas fa-qrcode"></i></button>
                    </div>
                    <div class="qrcode" id="qr"></div>
                    <a href="#" onclick="print2();">print</a>
                </div>

                <div class="modal-footer">
                    <button type="button" data-dismiss="modal">Close</button>
                </div>
              </div>
            </div>
        </div>       
        <div id="loading">
            <img src="img/loader.svg">
        </div>
        <script>
            function print2(){
                var src = document.querySelector("#qr img");
                console.log(src);
                if(src===null){
                    alert("Enter code first");
                }
                else{
                    src = src.getAttribute("src");
                    var myWindow = window.open("", "myWindow", "width=1000, height=1000");
                    myWindow.document.write("<div style=\"text-align:center;\"><img src=\""+src+"\"></div>");     
                    myWindow.print();
                }
            }
        </script>
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
