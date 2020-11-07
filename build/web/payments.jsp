<%-- 
    Document   : dashboard
    Created on : 15 Apr, 2019, 10:22:40 PM
    Author     : winayak
--%>

<%@page import="pojo.Warehouse_pojo"%>
<%@page import="pojo.Contact_pojo"%>
<%@page import="pojo.Items_pojo"%>
<%@page import="pojo.Purchases_pojo"%>
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
            #main .table th{
                width: 10%;
            }
            #main h4 a{
                color: black;
            }
            .pro_div{
                width: 100%;
                display: inline-block;
            }
            #myModal1 .form-group{
                overflow: hidden;
            }
            #myModal1 label{
                width: 100%;
            }
            #pro_but button{
                background: #f1f1f1;
                border-radius: 5px;
                border: 0;
                border: 1px solid #dedede;
                margin-top: 15px;
                padding: 8.5px;
                width: 50%;
                float: left;
            }
            #pro_o{
                white-space: nowrap;
                display: inline-block;
                width: 100%;
                margin-top: 15px;
            }
            .pro_div{
                position: relative;
            }
/*            .pro_div button{
                background: #f1f1f1;
                position: absolute;
                top:50%;
                left: 50%;
                transform: translate(-50%,-50%);
                color: #333;
                padding: 10px 15px;
                border-radius: 5px;
                display: none;
            }    */
            .table tr{
                position: relative;
            }
            .table tbody tr .tabin{
                width: 100px;
                position: absolute;
                text-align: center;
                transform: translate(-50%,25%);
                left: 50%;
                background: rgb(255,255,255,0.9);
                border: 0px;
                padding: 10px;
                display: none;
                cursor: pointer;
            }
            .table tbody tr .tabout{
                width: 50%;
                position: absolute;
                text-align: center;
                transform: translate(-50%,25%);
                left: 50%;
                background: rgb(255,255,255,0.9);
                border: 0px;
                padding: 10px;
                display: none;
                cursor: pointer;
            }
            .sales_div{
                width: 50%;
                float: left;
            }
            .trash{
                padding: 5px 8px;
                background: #945050;
                border: 0px;
                outline: 0px;
                font-size: 13px;
                width: 30px;
                color: #fff;
                border-radius: 5px;
                text-align: center;
            }
            .table tbody tr td:first-child{
                vertical-align: middle;
            }
            .modal .table{
                background: transparent;
            }
            .modal .table th{
                border-top: 0;
            }
            #submit01{
                background: #e8e8e8;
                color: #666;
            }
            .modal input[type="checkbox"]{
                width: 30px;
            }
            .taxbox{
                float: left;
            }
            #myModal1 th,#myModal1 td{
                width: 10%;
            }
            .discount,.discounttype{
                width: 50%;
                float: left;
            }
            #myModal1 .form-group{
                overflow: hidden;
                width: 50%;
                padding: 10px;
                float: left;
            }
            #myModal1 .modal-footer{
                clear: both;
            }
            .table_div{
                padding: 0;
                padding-right: 15px;
                float: left;
            }
            .table_div h5{
                display: inline-block;
            }
            .div_toggle,.delete,.editpurchases,.viewtrans{
                float: right;
                margin-left: 10px;
            }
            .iname+p{                
                padding-left: 45px;
                font-size: 18px;
            }
            .iname+p+p{
                padding-left: 45px;
            }
            .itemdiv{
                padding-left: 45px;
            }
            .itemdiv>div{
                overflow: hidden;
                width: 100%;
                margin-top: 10px;
            }
            .itemdiv>div div{
                width: 50%;
                float: left;
            }
            .alert_div{
                padding: 0px;
                float: left;
            }
            .table_div{
                padding: 0;
                padding-right: 15px;
                float: left;
                min-height: 82vh;
            }
            .viewtrans{
                width: auto!important;
            }
            #itransinvoiceno{
                font-weight: bold;
                float: right;
            }
            .itransmode{
                float: left;
                margin : 0;
            }
            .itransamount,.itransdate{
                float: right;
                margin: 0;
            }
            .itransmode,.itransdate{
                color: #666;
            }
            .transactionsdiv{
                width: 100%;
                margin: 15px 0;
                overflow: hidden;
            }
            .transactionsdiv .no{
                float: left;
                margin: 0;
                vertical-align: top;
                padding: 0!important;
                line-height: 1;
            }
            #transmodalform>div>div{
                width: 30%;
                float: left;
                padding-right: 10px;
            }
             #transmodalform>div:last-child{
                padding-right: 0;
            }
            #addtransrow{
                width: 100%;
                margin-bottom: 10px;
                text-align: center;
                display: inline-block;
            }
            .deltransrow{
                background: #945050;
                color: #fff;
                padding: 5px 8px;
                border: 0px;
                outline: 0px;
                font-size: 13px;
                width: 30px;
                border-radius: 5px;
                text-align: center;
                float: right;
                margin-top: 35px;
            }
            #amountdiv{
                margin-bottom: 10px;
            }
            #amountdiv p{
                display: inline;
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
            @media only screen and (min-width:992px){
                #myModal1 .modal-dialog{
                    min-width: 990px;
                    width: 80%;
                }
            }
        </style>
        <script src="js/index.js"></script>
        <script>
            $(document).ready(function(){
                var prodiv = $(".pro_div").eq(0).find("tbody tr").html();                
                $(document).on("click",".trash",function(){
                    $(this).parents("tr").remove();
                });                                                
                
                $("#add").click(function(){
                    $("#pro_o tbody").append("<tr>"+prodiv+"</tr>");
                });   
                $("#pur").click(function(){
                    $("#myModal1 #type").val("pi");
                    $("#myModal1 h4").html("Purchase Invoice");
                    
                });
                $("#advpa").click(function(){
                    $("#myModal1 #type").val("ap");
                    $("#myModal1 h4").html("Advance Payment");
                });               
                $("#puror").click(function(){
                    $("#myModal1 #type").val("po");
                    $("#myModal1 h4").html("Purchase Order");
                });
//                $("#refvo").click(function(){
//                    $("#myModal1 #type").val("refundvoucher");
//                });
                $("#billos").click(function(){
                    $("#myModal1 #type").val("bos");
                    $("#myModal1 h4").html("Bills of Supply");
                });
                $("#cnote").click(function(){
                    $("#myModal1 #type").val("cn");
                    $("#myModal1 h4").html("Credit Note");
                });
                $("#dnote").click(function(){
                    $("#myModal1 #type").val("dn");
                    $("#myModal1 h4").html("Debit Note");
                });
                $("#pamend").click(function(){
                    $("#myModal1 #type").val("pi");
                    $("#myModal1 h4").html("Sales Invoice amendment");
                });
                $("#camend").click(function(){
                    $("#myModal1 #type").val("cn");
                    $("#myModal1 h4").html("Credit Note amendment");
                });
                $("#damend").click(function(){
                    $("#myModal1 #type").val("dn");
                    $("#myModal1 h4").html("Dredit Note amendment");
                });
                $(document).on("mouseenter",".table .filter>span",function(){
                    $(this).parents("tr").find(".productscost").val($(this).find(".three").html().replace(/\n/g,"")); 
                    $(this).parents("tr").find(".productsprice").val($(this).find(".four").html().replace(/\n/g,""));
                    $(this).parents("tr").find(".taxrate").val($(this).find(".five").html().replace(/\n/g,""));
                    $(this).parents("tr").find(".cgst").val($(this).find(".five").html().replace(/\n/g,""));
                    $(this).parents("tr").find(".sgst").val($(this).find(".five").html().replace(/\n/g,""));
                    $(this).parents("tr").find(".igst").val(0);
                    $(this).parents("tr").find(".cess").val($(this).find(".six").html().replace(/\n/g,""));
                    $(this).parents("tr").find(".discount").val($(this).find(".seven").html().replace(/\n/g,""));
                    $(this).parents("tr").find(".discounttype").val($(this).find(".eight").html().replace(/\n/g,""));
                    var sp = parseInt($(this).parents("tr").find(".productsprice").val());
                    var discounttype = $(this).parents("tr").find(".discounttype").val();
                    var discount = parseInt($(this).parents("tr").find(".discount").val());
                    var transportationcharge = parseInt($("#transcharge").val());
                    sp = sp + transportationcharge;
                    if(discounttype=="percentage"){
                        sp = sp - (discount/100)*sp;
                    }
                    else{
                        sp = sp - discount;
                    }                    
                    var qty = parseInt($(this).parents("tr").find(".qty").val());
                    var taxrate = 2*parseInt($(this).parents("tr").find(".taxrate").val());
                    var taxamount = sp*(taxrate/100);
                    var cgst = parseInt($(this).parents("tr").find(".cgst").val());
                    var cess = parseInt($(this).parents("tr").find(".cess").val());
                    var cgstamount = sp*(cgst/100);
                    var cessamount = sp*(cess/100);
                    var amount = (taxamount+cessamount+sp)*qty;     
                    $(this).parents("tr").find(".cgstamount").val(cgstamount);
                    $(this).parents("tr").find(".igstamount").val(0);
                    $(this).parents("tr").find(".cessamount").val(cessamount);
                    $(this).parents("tr").find(".productsamount").val(amount);
                    var sum = 0;
                    $(".productsamount").each(function(){
                        sum = sum + parseInt($(this).val());
                    });
                    $("#amount").val(sum);
                });
                $(document).on("change",".qty",function(){
                    var sp = parseInt($(this).parents("tr").find(".productsprice").val());
                    var discounttype = $(this).parents("tr").find(".discounttype").val();
                    var discount = parseInt($(this).parents("tr").find(".discount").val());
                    var transportationcharge = parseInt($("#transcharge").val());
                    sp = sp + transportationcharge;
                    if(discounttype=="percentage"){
                        sp = sp - (discount/100)*sp;
                    }
                    else{
                        sp = sp - discount;
                    }                    
                    var qty = parseInt($(this).parents("tr").find(".qty").val());
                    var taxrate = 2*parseInt($(this).parents("tr").find(".taxrate").val());
                    var taxamount = sp*(taxrate/100);
                    var cgst = parseInt($(this).parents("tr").find(".cgst").val());
                    var igst = parseInt($(this).parents("tr").find(".igst").val());
                    var cess = parseInt($(this).parents("tr").find(".cess").val());
                    var cgstamount = sp*(cgst/100);
                    var igstamount = sp*(igst/100);
                    var cessamount = sp*(cess/100);
                    var amount = (taxamount+cessamount+sp)*qty;     
                    $(this).parents("tr").find(".cgstamount").val(cgstamount);
                    $(this).parents("tr").find(".igstamount").val(igstamount);
                    $(this).parents("tr").find(".cessamount").val(cessamount);
                    $(this).parents("tr").find(".productsamount").val(amount);
                    var sum = 0;
                    $(".productsamount").each(function(){
                        sum = sum + parseInt($(this).val());
                    });
                    $("#amount").val(sum);
                });
                $(document).on("change",".cgst",function(){
                    var sp = parseInt($(this).parents("tr").find(".productsprice").val());
                    var discounttype = $(this).parents("tr").find(".discounttype").val();
                    var discount = parseInt($(this).parents("tr").find(".discount").val());
                    var transportationcharge = parseInt($("#transcharge").val());
                    $(this).parents("tr").find(".taxrate").val(parseInt($(this).val()));
                    sp = sp + transportationcharge;
                    if(discounttype=="percentage"){
                        sp = sp - (discount/100)*sp;
                    }
                    else{
                        sp = sp - discount;
                    }                    
                    var qty = parseInt($(this).parents("tr").find(".qty").val());
                    var taxrate = 2*parseInt($(this).parents("tr").find(".taxrate").val());
                    var taxamount = sp*(taxrate/100);
                    var cgst = parseInt($(this).parents("tr").find(".cgst").val());
                    var cess = parseInt($(this).parents("tr").find(".cess").val());
                    var cgstamount = sp*(cgst/100);
                    var cessamount = sp*(cess/100);
                    var amount = (taxamount+cessamount+sp)*qty;     
                    $(this).parents("tr").find(".igst").val(0);
                    $(this).parents("tr").find(".cgstamount").val(cgstamount);
                    $(this).parents("tr").find(".igstamount").val(0);
                    $(this).parents("tr").find(".cessamount").val(cessamount);
                    $(this).parents("tr").find(".productsamount").val(amount);
                    var sum = 0;
                    $(".productsamount").each(function(){
                        sum = sum + parseInt($(this).val());
                    });
                    $("#amount").val(sum);
                });
                $(document).on("change",".igst",function(){
                    var sp = parseInt($(this).parents("tr").find(".productsprice").val());
                    var discounttype = $(this).parents("tr").find(".discounttype").val();
                    var discount = parseInt($(this).parents("tr").find(".discount").val());
                    var transportationcharge = parseInt($("#transcharge").val());
                    sp = sp + transportationcharge;
                    if(discounttype=="percentage"){
                        sp = sp - (discount/100)*sp;
                    }
                    else{
                        sp = sp - discount;
                    }                    
                    var qty = parseInt($(this).parents("tr").find(".qty").val());                    
                    $(this).parents("tr").find(".taxrate").val(parseInt($(this).val()));
                    var taxrate = 2*parseInt($(this).parents("tr").find(".taxrate").val());
                    var taxamount = sp*(taxrate/100);
                    var igst = parseInt($(this).parents("tr").find(".igst").val());
                    var cess = parseInt($(this).parents("tr").find(".cess").val());
                    var igstamount = sp*(igst/100);
                    var cessamount = sp*(cess/100);
                    var amount = (taxamount+cessamount+sp)*qty;     
                    $(this).parents("tr").find(".cgstamount").val(0);
                    $(this).parents("tr").find(".cgst").val(0);
                    $(this).parents("tr").find(".igstamount").val(igstamount);
                    $(this).parents("tr").find(".cessamount").val(cessamount);
                    $(this).parents("tr").find(".productsamount").val(amount);
                    var sum = 0;
                    $(".productsamount").each(function(){
                        sum = sum + parseInt($(this).val());
                    });
                    $("#amount").val(sum);
                });
                $(document).on("change",".taxrate",function(){
                    var sp = parseInt($(this).parents("tr").find(".productsprice").val());
                    var discounttype = $(this).parents("tr").find(".discounttype").val();
                    var discount = parseInt($(this).parents("tr").find(".discount").val());
                    var transportationcharge = parseInt($("#transcharge").val());
                    sp = sp + transportationcharge;
                    if(discounttype=="percentage"){
                        sp = sp - (discount/100)*sp;
                    }
                    else{
                        sp = sp - discount;
                    }                    
                    var qty = parseInt($(this).parents("tr").find(".qty").val());
                    var taxrate = 2*parseInt($(this).parents("tr").find(".taxrate").val());
                    var taxamount = sp*(taxrate/100);
                    var cgst = parseInt($(this).parents("tr").find(".cgst").val());
                    var igst = parseInt($(this).parents("tr").find(".igst").val());
                    var cess = parseInt($(this).parents("tr").find(".cess").val());
                    var cgstamount = sp*(cgst/100);
                    var igstamount = sp*(igst/100);
                    var cessamount = sp*(cess/100);
                    var amount = (taxamount+cessamount+sp)*qty;     
                    $(this).parents("tr").find(".cgstamount").val(cgstamount);
                    $(this).parents("tr").find(".igstamount").val(igstamount);
                    $(this).parents("tr").find(".cessamount").val(cessamount);
                    $(this).parents("tr").find(".productsamount").val(amount);
                    $(this).parents("tr").find(".cgst").val(parseInt($(this).val()));
                    var sum = 0;
                    $(".productsamount").each(function(){
                        sum = sum + parseInt($(this).val());
                    });
                    $("#amount").val(sum);
                });
                $(document).on("change",".cess",function(){
                    var sp = parseInt($(this).parents("tr").find(".productsprice").val());
                    var discounttype = $(this).parents("tr").find(".discounttype").val();
                    var discount = parseInt($(this).parents("tr").find(".discount").val());
                    var transportationcharge = parseInt($("#transcharge").val());
                    sp = sp + transportationcharge;
                    if(discounttype=="percentage"){
                        sp = sp - (discount/100)*sp;
                    }
                    else{
                        sp = sp - discount;
                    }                    
                    var qty = parseInt($(this).parents("tr").find(".qty").val());
                    var taxrate = 2*parseInt($(this).parents("tr").find(".taxrate").val());
                    var taxamount = sp*(taxrate/100);
                    var cgst = parseInt($(this).parents("tr").find(".cgst").val());
                    var igst = parseInt($(this).parents("tr").find(".igst").val());
                    var cess = parseInt($(this).parents("tr").find(".cess").val());
                    var cgstamount = sp*(cgst/100);
                    var igstamount = sp*(igst/100);
                    var cessamount = sp*(cess/100);
                    var amount = (taxamount+cessamount+sp)*qty;     
                    $(this).parents("tr").find(".cgstamount").val(cgstamount);
                    $(this).parents("tr").find(".igstamount").val(igstamount);
                    $(this).parents("tr").find(".cessamount").val(cessamount);
                    $(this).parents("tr").find(".productsamount").val(amount);
                    var sum = 0;
                    $(".productsamount").each(function(){
                        sum = sum + parseInt($(this).val());
                    });
                    $("#amount").val(sum);
                });
                $(document).on("change",".discount",function(){
                    var sp = parseInt($(this).parents("tr").find(".productsprice").val());
                    var discounttype = $(this).parents("tr").find(".discounttype").val();
                    var discount = parseInt($(this).parents("tr").find(".discount").val());
                    var transportationcharge = parseInt($("#transcharge").val());
                    sp = sp + transportationcharge;
                    if(discounttype=="percentage"){
                        sp = sp - (discount/100)*sp;
                    }
                    else{
                        sp = sp - discount;
                    }                    
                    var qty = parseInt($(this).parents("tr").find(".qty").val());
                    var taxrate = 2*parseInt($(this).parents("tr").find(".taxrate").val());
                    var taxamount = sp*(taxrate/100);
                    var cgst = parseInt($(this).parents("tr").find(".cgst").val());
                    var igst = parseInt($(this).parents("tr").find(".igst").val());
                    var cess = parseInt($(this).parents("tr").find(".cess").val());
                    var cgstamount = sp*(cgst/100);
                    var igstamount = sp*(igst/100);
                    var cessamount = sp*(cess/100);
                    var amount = (taxamount+cessamount+sp)*qty;     
                    $(this).parents("tr").find(".cgstamount").val(cgstamount);
                    $(this).parents("tr").find(".igstamount").val(igstamount);
                    $(this).parents("tr").find(".cessamount").val(cessamount);
                    $(this).parents("tr").find(".productsamount").val(amount);
                    var sum = 0;
                    $(".productsamount").each(function(){
                        sum = sum + parseInt($(this).val());
                    });
                    $("#amount").val(sum);
                });
                $(document).on("change",".discounttype",function(){
                    var sp = parseInt($(this).parents("tr").find(".productsprice").val());
                    var discounttype = $(this).parents("tr").find(".discounttype").val();
                    var discount = parseInt($(this).parents("tr").find(".discount").val());
                    var transportationcharge = parseInt($("#transcharge").val());
                    sp = sp + transportationcharge;
                    if(discounttype=="percentage"){
                        sp = sp - (discount/100)*sp;
                    }
                    else{
                        sp = sp - discount;
                    }                    
                    var qty = parseInt($(this).parents("tr").find(".qty").val());
                    var taxrate = 2*parseInt($(this).parents("tr").find(".taxrate").val());
                    var taxamount = sp*(taxrate/100);
                    var cgst = parseInt($(this).parents("tr").find(".cgst").val());
                    var igst = parseInt($(this).parents("tr").find(".igst").val());
                    var cess = parseInt($(this).parents("tr").find(".cess").val());
                    var cgstamount = sp*(cgst/100);
                    var igstamount = sp*(igst/100);
                    var cessamount = sp*(cess/100);
                    var amount = (taxamount+cessamount+sp)*qty;     
                    $(this).parents("tr").find(".cgstamount").val(cgstamount);
                    $(this).parents("tr").find(".igstamount").val(igstamount);
                    $(this).parents("tr").find(".cessamount").val(cessamount);
                    $(this).parents("tr").find(".productsamount").val(amount);
                    var sum = 0;
                    $(".productsamount").each(function(){
                        sum = sum + parseInt($(this).val());
                    });
                    $("#amount").val(sum);
                });
                $(document).on("change",".qty",function(){
                    var sp = parseInt($(this).parents("tr").find(".productsprice").val());
                    var discounttype = $(this).parents("tr").find(".discounttype").val();
                    var discount = parseInt($(this).parents("tr").find(".discount").val());
                    var transportationcharge = parseInt($("#transcharge").val());
                    sp = sp + transportationcharge;
                    if(discounttype=="percentage"){
                        sp = sp - (discount/100)*sp;
                    }
                    else{
                        sp = sp - discount;
                    }                    
                    var qty = parseInt($(this).parents("tr").find(".qty").val());
                    var taxrate = 2*parseInt($(this).parents("tr").find(".taxrate").val());
                    var taxamount = sp*(taxrate/100);
                    var cgst = parseInt($(this).parents("tr").find(".cgst").val());
                    var igst = parseInt($(this).parents("tr").find(".igst").val());
                    var cess = parseInt($(this).parents("tr").find(".cess").val());
                    var cgstamount = sp*(cgst/100);
                    var igstamount = sp*(igst/100);
                    var cessamount = sp*(cess/100);
                    var amount = (taxamount+cessamount+sp)*qty;     
                    $(this).parents("tr").find(".cgstamount").val(cgstamount);
                    $(this).parents("tr").find(".igstamount").val(igstamount);
                    $(this).parents("tr").find(".cessamount").val(cessamount);
                    $(this).parents("tr").find(".productsamount").val(amount);
                    var sum = 0;
                    $(".productsamount").each(function(){
                        sum = sum + parseInt($(this).val());
                    });
                    $("#amount").val(sum);
                });
                $(document).on("change","#transcharge",function(){
                    var sp = parseInt($(this).parents("tr").find(".productsprice").val());
                    var discounttype = $(this).parents("tr").find(".discounttype").val();
                    var discount = parseInt($(this).parents("tr").find(".discount").val());
                    var transportationcharge = parseInt($("#transcharge").val());
                    sp = sp + transportationcharge;
                    if(discounttype=="percentage"){
                        sp = sp - (discount/100)*sp;
                    }
                    else{
                        sp = sp - discount;
                    }                    
                    var qty = parseInt($(this).parents("tr").find(".qty").val());
                    var taxrate = 2*parseInt($(this).parents("tr").find(".taxrate").val());
                    var taxamount = sp*(taxrate/100);
                    var cgst = parseInt($(this).parents("tr").find(".cgst").val());
                    var cess = parseInt($(this).parents("tr").find(".cess").val());
                    var cgstamount = sp*(cgst/100);
                    var cessamount = sp*(cess/100);
                    var amount = (taxamount+cessamount+sp)*qty;     
                    $(this).parents("tr").find(".igst").val(0);
                    $(this).parents("tr").find(".cgstamount").val(cgstamount);
                    $(this).parents("tr").find(".igstamount").val(0);
                    $(this).parents("tr").find(".cessamount").val(cessamount);
                    $(this).parents("tr").find(".productsamount").val(amount);
                    var sum = 0;
                    $(".productsamount").each(function(){
                        sum = sum + parseInt($(this).val());
                    });
                    $("#amount").val(sum);
                });
                $("#main .dropdown a").click(function(){
                    $("#myModal1 .table tbody").empty();  
                    $("#add").trigger("click");
                    $("#salesdiv").hide();
                    $("#bill").prop('checked',true);
                });
                $(document).on("change",".transamount",function(){
                    var amount = 0;
                    $(".transamount").each(function(){
                       amount = amount + parseInt($(this).val()); 
                    });
                    var damount = $("#transtotalamount").val()-amount;
                    if(damount<0){
                        alert("Transaction can not be greater than net amount.");
                        $("#transdueamount").val(amount-parseInt($(this).val()));
                        $(this).val("");
                    }
                    else{
                        $("#transdueamount").val(damount);
                    }
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
        <div id="sidebar" class="col-xl-2 collapse show">
            <a href="dashboard.jsp">Dashboard</a>
            <a href="inventory.jsp">Inventory</a>
            <a href="sales.jsp">Sales</a>
            <a href="payments.jsp" class="active">Paid</a>
<!--            <a href="banking.jsp">Banking</a>
            <a href="reports.jsp">Reports</a>-->
            <a href="contact.jsp">Contacts</a>
            <a href="myaccount.jsp">Settings</a>
        </div>
        <% if(request.getSession(false)!=null && session.getAttribute("email") != null){
            if(session.getAttribute("privv").toString().contains("purchase") || session.getAttribute("type").equals("admin") || session.getAttribute("type").equals("multi")){
        %>
        <div class="col-12 col-lg-12 col-xl-10" id="main"> 
            <div>
                <h4>Purchases</h4>
                <div class="dropdown" style="display: inline-block">
                    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown"></button>
                    <div class="dropdown-menu">
                      <a id="pur" class="dropdown-item" href="#" data-toggle="modal" data-target="#myModal1">Purchase Invoice</a>
                      <a id="advpay" class="dropdown-item" href="#" data-toggle="modal" data-target="#myModal1">Advance Payments</a>
                      <a id="delch" class="dropdown-item" href="#" data-toggle="modal" data-target="#myModal1">Delivery Challan</a>
                      <a id="puror" class="dropdown-item" href="#" data-toggle="modal" data-target="#myModal1">Purchase Order</a>
                      <a id="billos" class="dropdown-item" href="#" data-toggle="modal" data-target="#myModal1">Bills of Supply</a>
                      <a id="cnote" class="dropdown-item" href="#" data-toggle="modal" data-target="#myModal1">Credit note</a>
                      <a id="dnote" class="dropdown-item" href="#" data-toggle="modal" data-target="#myModal1">Debit note</a>
                      <a id="pamend" class="dropdown-item" href="#" data-toggle="modal" data-target="#myModal1">Purchase Amendment</a>
                      <a id="camend" class="dropdown-item" href="#" data-toggle="modal" data-target="#myModal1">Credit Note Amendment</a>
                      <a id="damend" class="dropdown-item" href="#" data-toggle="modal" data-target="#myModal1">Debit Note Amendment</a>
                    </div>
                </div>
            </div>   
            <div class="table_div col-12 col-lg-8 col-xl-9">                
                <% 
                    App obj = new App();                           
                    List l = obj.fetch_purchases(session.getAttribute("gstin").toString(),"all",0);
                    Purchases_pojo obj2 = new Purchases_pojo();
                    Iterator i = l.iterator();
                    int z= 0;
                    int pdel = 0;
                    int ppay = 0;
                    int total = 0;     
                    float ballimp = 0;
                    float oosp = 0;
                    if(!l.get(0).equals("0")){
                            while(i.hasNext()){                            
                                obj2 = (Purchases_pojo) i.next();   
                                if(!obj2.getpstatus().equals("paid")){
                                    ppay++;
                                }
                                if(!obj2.getdelstatus().equals("delivered")){
                                    pdel++;
                                }
                                total++;
                                z++;
                %>
                    <div> 
                        <%out.println("<input type=\"number\" class=\"sid\" disabled value=\""+obj2.getid()+"\"hidden>");%>
                        <span class="no"><%=z%>.</span>
                        <%out.println("<input type=\"number\" class=\"cid\" disabled value=\""+obj2.getcid()+"\"hidden>");%>
                        <h5 class="iname"><%out.println(obj2.getcname());%></h5>
                        <p><span class="itype"><%out.println(obj2.gettype());%></span>
                            <span class="iinvoiceno"><%out.println(obj2.getinvoiceno());%></span>
                        </p>
                        <%if(obj2.getreference()==null){%>
                            <p><span class="iref"></span></p>
                            
                        <%}else{%>
                            <p><span class="iref"><%out.println(obj2.getreference());%></span></p>
                        <%}%>
                        <div class="collapse itemdiv">  
                            <div>
                                <div>
                                    <h5>Invoice details</h5>
                                    <p>Customer Gstin-<span class="icgstin"><%out.println(obj2.getcustomergstin());%></p>
                                    <p>Warehouse-<span class="iwareid"><%out.println(obj2.getwarehouseid());%></p>
                                    <p>Return Month-<%out.println("<span class=\"iremonth\">"+obj2.getreturnmonth()+"</span>");%></p>
                                    <p>Return Quarter-<span class="irequart"><%out.println(obj2.getreturnquarter());%></p>                                
                                    <p>Place of Supply-<span class="ipos"><%out.println(obj2.getpos());%></p>                                
                                    <p>Invoice date-
                                        <%out.println("<span class=\"iidate\">"+obj2.getinvoicedate().toString().substring(0, 10)+"</span>");%>
                                    </p>                                
                                    <p>Due date-<%out.println("<span class=\"iddate\">"+obj2.getinvoicedate().toString().substring(0, 10)+"</span>");%></p>                                
                                </div>
                            </div>
                            <div>
                                <div>
                                    <h5>Additional info</h5>
                                    <p>Advanced-<%out.println("<span class=\"iadvanced\">"+obj2.getinvoicesetting()+"</span>");%></p>
                                    <p>Roundoff-<span class="iroundoff"><%out.println(obj2.getroundoff());%></p>
                                    <p>Transportation Charge-<span class="itranscharge"><%out.println(obj2.gettranscharge());%></p>
                                </div>
                            </div>
                            <div style="display: none;">
                                <h5>Products</h5>
                                <table style="width:100%;">
                                    <thead>
                                        <th>Sr</th>
                                        <th>Products</th>
                                        <th>Quantity</th>
                                        <th>Tax rate</th>
                                        <th>CGST/SGST</th>
                                        <th>IGST</th>
                                        <th>CESS</th>
                                        <th>Disount</th>
                                        <th>Amount</th>                                        
                                    </thead>
                                    </tbody>
                                        <td><%=z%></td>
                                        <td><span class="iproducts"><%=obj2.getproductsname()%></span>
                                            <span class="iproductsid" ><%=obj2.getproductsid()%></span>
                                            <span class="iproductscost" ><%=obj2.getproductscost()%></span>
                                            <span class="iproductsprice"><%=obj2.getproductsprice()%></span>
                                        </td>
                                        <%="<td><span class=\"iproductsqty\">"%><%=obj2.getproductsqty()%><%="</span></td>"%>
                                        <td class="itaxrate"><%=obj2.gettaxrate()%></td>
                                        <td>
                                            <span class="icgst"><%=obj2.getcgst()%></span>
                                            <span class="icgstamount" style="display: none;"><%=obj2.getcgstamount()%></span>
                                        </td>
                                        <td>
                                            <span class="iigst"><%=obj2.getigst()%></span>
                                            <span class="iigstamount" style="display: none;"><%=obj2.getigstamount()%></span>
                                        </td>
                                       <td>
                                            <span class="icess"><%=obj2.getcess()%></span>
                                            <span class="icessamount" style="display: none;"><%=obj2.getcessamount()%></span>
                                        </td>
                                        <td>
                                            <span class="idiscount"><%=obj2.getdiscount()%></span>
                                            <span class="idiscounttype" ><%=obj2.getdiscounttype()%></span>
                                        </td>
                                        <td class="iamount"><%=obj2.getproductsamount()%></td>                                        
                                    </tbody>
                                </table>
                            </div>
                        </div>     
                        <p style="clear:both;padding-left:45px;">Payment <span><%=obj2.getpstatus()%></span></p>
                        <p style="clear:both;padding-left:45px;">Products <span"><%=obj2.getdelstatus()%></span></p>
                        <p style="text-align:right;clear:both;font-weight: bold;"><span class="itamount"><%=obj2.getamount()%></span></p>
                        <span class="div_toggle"><i class="fas fa-caret-down"></i></span> 
                        <span class="editpurchases"><i class="fas fa-edit"></i></span>
                        <span class="viewtrans">Transactions</span>
<!--                        <span class="purchaseinvoice">Transactions</span>-->
                        <span class="delete"><i class="fas fa-trash"></i></span>
                    </div> 
                <%}}
                    else{%>
                    <div>
                        <h5>No invoice recorded.</h5>
                    </div>
                <%}%>
            </div>
            <div class="alert_div col-12 col-lg-4 col-xl-3">
                <div>
                    <h5>
                        Pending payments - <%=ppay%>
                    </h5>
                        <% if(total!=0){
                            oosp = Float.valueOf(ppay*100/total);
                            ballimp = Float.valueOf(pdel*100/total);
                        %>
                        <hr id="oos" style="width:<%out.println(oosp+"%");%>">
                        <%}%>
                </div>
                <div>
                    <h5>
                        Pending Delivery - <%=pdel%>
                    </h5>
                        <% if(total!=0){%>                            
                        <hr id="allim" style="width:<%out.println(ballimp+"%");%>">
                        <%}%>
                </div>
            </div>
            <div class="table_div col-12 col-lg-4 col-xl-3" style="padding:0;">
                <span style="display: none" id="transtype">purchase</span>
                <div>
                    <h5>
                        Transactions
                        <p id="itransid" style="display: none;"></p>
                    </h5>
                    <p id="itransinvoiceno"></p>
                    <div class="transactionsdiv">
                        <p class="no"></p> 
                        <p class="itransamount"></p>
                        <div style="clear: both;">
                            <p class="itransmode"></p>
                            <p class="itransdate"></p>
                        </div>
                    </div>
                    <p id="itransinvoiceid" style="display: none;"></p>
                    <div id="amountdiv">
                        <p>Due</p>
                        <p id="idueamount"></p>
                        <br>
                        <p>Amount</p>
                        <p id="itotalamount"></p>
                    </div>
                    <span id="edittrans"><i class="fas fa-edit"></i></span>
                    <span class="delete"><i class="fas fa-trash"></i></span>
                </div>
            </div>
        </div>        
        <div class="modal fade" id="myModal1">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                      <h4 class="modal-title">Add Purchases</h4>
                      <input type="text" id="type" value="" disabled hidden>
                      <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        <input type="number" class="form-control" id="itemid" style="display: none;" disabled>
                        <div>
                            <div class="sales_div">
                                <h5>Invoice details</h5>
                                <div class="form-group">
                                    <label for="invoiceno">Serial no</label>
                                    <input type="text" class="form-control" id="invoiceno" required>
                                </div>
                                <div class="form-group" style="position: relative;">
                                    <label for="name">Customer</label>
                                    <input type="number" class="form-control cid" id="cid" hidden>
                                    <input type="text" class="form-control dropsearch" id="name" autocomplete="off" required>
                                    <div type="text" class="filter">
                                        <%
                                            List l3 = obj.fetch_contact(session.getAttribute("gstin").toString());
                                            Contact_pojo obj4 = new Contact_pojo();
                                            Iterator i3 = l3.iterator();
                                            if(!l3.get(0).equals("0")){
                                                while(i3.hasNext()){
                                                   obj4 = (Contact_pojo) i3.next();
                                        %>
                                        <span><span style="display: none" class="one"><%=obj4.getid()%></span><span class="two"><%=obj4.getname()%></span></span>
                                        <%}}%>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="name">Customer Gstin</label>
                                    <input type="text" class="form-control" id="gstin">
                                </div>
                                <div class="form-group">
                                    <label for="warehouse">Warehouse</label>
                                    <select type="text" class="form-control" id="warehouse">
                                        <%                    
                                            Warehouse_pojo obj5 = new Warehouse_pojo();
                                            List l4 = obj.fetch_saved("warehouse",session.getAttribute("gstin").
                                                toString(),0);
                                            Iterator i4 = l4.iterator();
                                            if(!l4.get(0).equals("0")){
                                                while(i4.hasNext()){
                                                   obj5 = (Warehouse_pojo) i4.next();                                                   
                                        %>
                                        <option value="<%=obj5.getid()%>"><%=obj5.getwarename()%></option>
                                        <%}}%>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="remonth">Return Month</label>
                                    <input type="month" class="form-control" id="remonth" required>
                                </div>
                                <div class="form-group">
                                    <label for="requarter">Return Quarter</label>
                                    <select type="text" class="form-control" id="requarter" required>
                                        <option value="jan-mar">Jan-Mar</option>
                                        <option value="apr-jun">Apr-Jun</option>
                                        <option value="jul-sep">Jul-Sep</option>
                                        <option value="oct-dec">Oct-Dec</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="placeofsupply">Place of Supply</label>
                                    <select id="placeofsupply" class="form-control" required>
                                        <option value="a">a</option>
                                        <option value="b">b</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="invoicedate">Date</label>
                                    <input type="date" class="form-control" id="invoicedate" required>
                                </div>
                                <div class="form-group">
                                    <label for="ddate">Due Date</label>
                                    <input type="date" class="form-control" id="ddate" required>
                                </div>
                            </div>
                            <div class="sales_div" id="transdiv"> 
                                <h5>Transportation info</h5>                                                                                    
                                <div class="form-group">
                                    <label for="transcharge">Transportation charge</label>
                                    <input type="number" class="form-control" id="transcharge" required>
                                </div>

                            </div>
                            <div class="sales_div">
                                <h5>Additional info</h5>                               
                                <div class="form-group">
                                    <label for="advanced">Advanced</label>
                                    <select id="advanced" class="form-control">
                                        <option value="none">None</option>
                                        <option value="deemed export">Deemed Export</option>
                                        <option value="reverse charge">Reverse Charge</option>
                                    </select>
                                </div>  
                            </div>
                        </div>
                        <div class="form-group" style="width: 100%;clear:both;padding: 10px;border-radius: 10px;margin:0;">
                            <div id="pro_o">
                                <div class="pro_div">
                                    <table class="table">
                                        <thead>
                                          <tr>
                                            <th>Sr</th>
                                            <th style="min-width:150px;">Product</th>
                                            <th>Quantity</th>
                                            <th>Tax Rate</th>
                                            <th>CGST/SGST</th>
                                            <th>IGST</th>
                                            <th>CESS</th>
                                            <th style="min-width:150px;">Discount</th>
                                            <th>Amount</th>
                                            <th>Delete</th>
                                          </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>1</td>
                                                <td style="position:relative;">
                                                    <input type="number" class="form-control productsprice" hidden>
                                                    <input type="number" class="form-control productscost" hidden>
                                                    <input type="number" class="form-control pid" hidden>
                                                    <input type="text" class="form-control dropsearch products" autocomplete="off" required>
                                                    <div type="text" class="filter">
                                                       <% 
                                                        Items_pojo obj3 = new Items_pojo();
                                                        
                                                        List l2 = obj.fetch_items(session.getAttribute("gstin").toString(),0);
                                                        Iterator i2 = l2.iterator();
                                                        if(!l2.get(0).equals("0")){  
                                                            while(i2.hasNext()){                                                                                                  
                                                              obj3 = (Items_pojo) i2.next();
                                                        %>
                                                        <span>
                                                            <%="<span style=\"display:none;\" class=\"one\">"%><%out.println(obj3.getid());%><%="</span>"%>
                                                            <%="<span class=\"two\">"%><%out.println(obj3.getname());%><%="</span>"%>
                                                            <%="<span style=\"display:none;\" class=\"three\">"%><%out.println(obj3.getcp());%><%="</span>"%>
                                                            <%="<span style=\"display:none;\" class=\"four\">"%><%out.println(obj3.getsp());%><%="</span>"%>
                                                            <%="<span style=\"display:none;\" class=\"five\">"%><%out.println(obj3.getcgst());%><%="</span>"%>
                                                            <%="<span style=\"display:none;\" class=\"six\">"%><%out.println(obj3.getcess());%><%="</span>"%>
                                                            <%="<span style=\"display:none;\" class=\"seven\">"%><%out.println(obj3.getdiscount());%><%="</span>"%>
                                                            <%="<span style=\"display:none;\" class=\"eight\">"%><%out.println(obj3.getdiscounttype());%><%="</span>"%>
                                                        </span>
                                                        <%}}
                                                        else{
                                                            out.println("<option>add+</option>");
                                                        }%>
                                                    </div>                                                    
                                                </td>
                                                <td><input type="number" class="form-control qty" value="0" required></td>
                                                <td><input type="number" class="form-control taxrate" value="0" required></td>
                                                <td><input type="number" class="form-control cgst" value="0" required>
                                                    <input type="number" class="form-control cgstamount" value="0" hidden>
                                                </td>
                                                <td><input type="number" class="form-control igst" value="0" required>
                                                    <input type="number" class="form-control igstamount" value="0" hidden></td>
                                                <td><input type="number" class="form-control cess" value="0" required>
                                                    <input type="number" class="form-control cessamount" value="0" hidden></td>                                                
                                                <td><input type="number" class="form-control discount" value="0" required>
                                                    <select type="text" class="form-control discounttype" value="0">
                                                        <option value="percentage">%</option>
                                                        <option value="rs">rs</option>                                                    
                                                    </select>
                                                </td>
                                                <td><input type="number" class="form-control productsamount" value="0" required></td>
                                                <td><button class="trash"><i class="fas fa-trash"></i></button></td>
                                            </tr>
                                        </tbody>
                                        <tfoot>
                                            <tr>
                                                <td colspan="11"><a href="#" id="add"Add a row><i class="fas fa-plus"></i></button></td>
                                            </tr>
                                        </tfoot>
                                    </table>
                                </div>
                            </div>
                        </div>                                                                            
                        <div class="sales_div">
                            <div class="form-group" style="width:100%;">
                                <label for="reference">Reference</label>
                                <textarea type="text" class="form-control" id="reference"></textarea>
                            </div>
                        </div>  
                        <div class="sales_div">
                            <div class="form-group" style="float: right;">
                                <label for="amount">Total Amount</label>
                                <input type="number" class="form-control" id="amount">
                            </div>
                            <div class="taxbox form-group" style="float: right;">
                                <label for="roundoff">Roundoff total</label>
                                <input type="checkbox" class="form-control" id="roundoff">
                            </div>
                        </div>                        
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                      <button type="button" id="submit01">Draft</button>
                      <button type="button" id="addpurchases" value="insert">Save</button>
                    </div>

                </div>
            </div>
        </div>
                                             
        <div class="modal fade" id="myModal3">
            <div class="modal-dialog modal-dialog-centered">
              <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Record Transactions</h4>
                    <input type="text" id="transinvoiceid" class="form-control" hidden required>
                  <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <div class="form-group">
                        <label for="transno">Invoice no</label>
                        <input type="number" id="transinvoiceno" class="form-control" disabled required>
                        <input type="number" id="cooid" class="form-control" hidden disabled required>
                        <input type="number" id="wooid" class="form-control" hidden disabled required>
                    </div> 
                    <div id="transmodalform">
                        <div>
                            <div class="form-group">
                                <label for="transamount">Amount</label>
                                <input type="number" class="transamount form-control" required>
                            </div> 
                            <div class="form-group">
                                <label for="date">Date</label>
                                <input type="date" class="transdate form-control" required>
                            </div> 
                            <div class="form-group">
                                <label for="transmode">Mode</label>
                                <input type="text" class="transmode form-control" required>
                            </div> 
                            <span class="deltransrow"><i class="fas fa-trash"></i></span>
                        </div>
                        <a href="#" id="addtransrow"><i class="fas fa-plus"></i></a>
                    </div>
                    <div class="form-group">
                        <label for="transdueamount">Due amount</label>
                        <input type="text" id="transdueamount" class="form-control" required>
                    </div> 
                    <div class="form-group">
                        <label for="transtotalamount">Total amount</label>
                        <input type="text" id="transtotalamount" class="form-control" required disabled>
                    </div>                     
                </div>

                <div class="modal-footer">
                    <button type="button" id="addtrans" value="purchase">Save</button>
                </div>
              </div>
            </div>
        </div>    
        <div id="loading">
            <img src="img/loader.svg">
        </div>
        <script>
            function showQRIntro() {
                return confirm("Use your camera to take a picture of a QR code.");
              }
            function openQRCamera(node) {
                var reader = new FileReader();
                reader.onload = function() {
                  node.value = "";
                  qrcode.callback = function(res) {
                    if(res instanceof Error) {
                      alert("No QR code found. Please make sure the QR code is within the camera's frame and try again.");
                    } else {
                      alert(res);
                    }
                  };
                  qrcode.decode(reader.result);
                };
                reader.readAsDataURL(node.files[0]);
            }
        </script>
        <%}
            else{
                out.println("You are not authorised to view this page.");
            }
        }
        else{
            out.println("<script>alert(\"Session expired.\");location.assign(\"index.html\")</script>");
        }
        %>
    </body>
</html>
