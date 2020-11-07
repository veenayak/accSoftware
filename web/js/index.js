/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//insert item
$(document).ready(function(){
    $("#ware").parent("div").css("width","49%");
    $("#ware").parent("div").css("float","left");
    $("#stock").parent("div").css("width","49%");
    $("#stock").parent("div").css("float","right");
    $(".stock2").parent("div").css("float","right");
    var items = $(".collitem").eq(0);
    var delbut = $(".delcollitem").eq(0);
    var transdiv = $(".transactionsdiv").eq(0).prop('outerHTML');
    var transrow = $("#transmodalform>div").html();
    $("#itempost").click(function(e){
        e.preventDefault();
        var name = $("#name").val().replace(/\n/g,"");
        var id = $("#itemid").val().replace(/\n/g,"");
        var desc = $("#desc").val();
        var code = Date.now;
        var hsncode = parseInt($("#hsncode").val());
        var cp = parseInt($("#cp").val());
        var sp = parseInt($("#sp").val());
        var cat = $("#cat").val().replace(/\n/g,"");
        var size = $("#size").val().replace(/\n/g,"");
        var warehouse = $("#ware").val().replace(/\n/g,"");
        var limit = $("#limit").val();
        var scat = $("#scat").val().replace(/\n/g,"");
        var unit = $("#unit").val().replace(/\n/g,"");
        var stock = $("#stock").val();
        var type = $("#type").val();
        var submit = $(this).val();
        var cgst =  parseInt($("#taxrate").val());
        var sgst =  parseInt($("#taxrate").val());
        var igst =  2*parseInt($("#taxrate").val());
        var cess =  parseInt($("#cess").val());
        var discount = $("#discount").val();
        var discounttype = $("#discounttype").val();
        var cgstamount = sp*cgst/100;
        var sgstamount = sp*sgst/100;
        var igstamount = sp*igst/100;
        var cessamount = sp*cess/100;
        var flag = 0; 
        var stockcount = 0;        
        $("#myModal1 select").each(function(){
            if($(this).val() == "" && $(this).parent().css("display")!="none" && $(this).attr("required")){
                $(this).attr("placeholder","Required Field!!");
                $(this).addClass("inp");
                flag = 1;
            }
        });
        $("#myModal1 input").each(function(){
            if($(this).val() == "" && $(this).parent().css("display")!="none" && $(this).attr("required")){
                $(this).attr("placeholder","Required Field!!");
                $(this).addClass("inp");
                flag = 1;
            }
        });
        console.log(stockcount+igstamount);
        console.log(flag);
        if(flag==0){
            $("#loading").show();
            $.ajax({
                type: "post",
                url: "Items",
                data: {id:id,isold:0,submit:submit,name:name,hsncode:hsncode,warehouse:warehouse,limit:limit
                    ,size:size,unit:unit,cgst:cgst,sgst:sgst,igst:igst,cess:cess,cgstamount:cgstamount,igstamount:igstamount,
                    sgstamount:sgstamount,cessamount:cessamount,desc:desc,sp:sp,cp:cp,stock:stock,code:code
                    ,cat:cat,scat:scat,type:type,stockcount:stockcount,discount:discount,discounttype:discounttype},
                success:function(res){
                    $("#loading").hide(function(){
                        alert(res);  
                    });                      
                },
                error:function(res){
                    $("#loading").hide(function(){
                        alert(res);  
                    });  
                }
            });
        }
        setTimeout(function(){
            $("#myModal1 input").attr("placeholder","");
            $("#myModal1 input").removeClass("inp");
            $("#myModal1 select").removeClass("inp");
            $("#submit1").prop("disabled",false);
        },2000);
    });
    //edit item
    $(".edititem").click(function(e){
        e.preventDefault();        
        var id = parseInt($(this).parent("div").children(".eid").val());
        var name = $(this).parent("div").find(".iname").html();
        var desc = $(this).parent("div").find(".idesc").html();
        var hsncode = parseInt($(this).parent("div").find(".ihsncode").html());
        var cp = parseInt($(this).parent("div").find(".icp").html().replace(/\n/g,""));
        var sp = parseInt($(this).parent("div").find(".isp").html().replace(/\n/g,""));
        var cat = $(this).parent("div").find(".icat").val();
        var size = $(this).parent("div").find(".isize").val();
        var warehouse = $(this).parent("div").find(".iware").val();
        var limit = $(this).parent("div").find(".ilimit").html();
        var scat = $(this).parent("div").find(".iscat").val();
        var stock = $(this).parent("div").find(".istock").val();
        var unit = $(this).parent("div").find(".iunit").val();
        var cgst = parseInt($(this).parent("div").find(".icgst").html().replace(/\n/g,""));
        var cess = parseInt($(this).parent("div").find(".icess").html().replace(/\n/g,""));
        var type = $(this).parent("div").find(".itype").html();   
        var discount = parseInt($(this).parent("div").find(".idiscount").html().replace(/\n/g,""));
        var discounttype = $(this).parent("div").find(".idiscounttype").html().replace(/\n/g,"");
        
        $("#itemid").val(id);
        $("#discount").val(discount);
        $("#discounttype").val(discounttype);
        $("#name").val(name);
        $("#desc").val(desc);
        $("#hsncode").val(hsncode);
        $("#cp").val(cp);
        $("#sp").val(sp);
        $("#limit").val(limit.replace(/\n/g,""));        
        $("#type").val();
        $("#itempost").val("update");
        $("#taxrate").val(cgst);     
        $("#cess").val(cess);   
        $("#scat").val(scat.replace(/\n/g,"")+"\n");
        $("#cat").val(cat.replace(/\n/g,"")+"\n");
        $("#size").val(size.replace(/\n/g,"")+"\n");
        $("#ware").val(warehouse.replace(/\n/g,"")+"\n");
        $("#stock").val(parseInt(stock));
        $("#unit").val(unit.replace(/\n/g,"")+"\n");
        $("#type").val(type.replace(/\n/g,""));
        
        $("#myModal1").modal('show');
    });
    //add collection
    $("#addcollitem").click(function(){
        $(this).before(items.prop("outerHTML")); 
        $(this).before(delbut.prop("outerHTML")); 
        console.log("asd");
     });
     //delete collectionitem
     $(document).on("click",".delcollitem",function(){
        $(this).prev("select").remove();
        $(this).remove();
        console.log("asd");
     });
    //insert category
    $("#addcat").click(function(e){
        e.preventDefault();
        var id = $("#catid").val();
        var cat = $("#cat2").val();
        var type = $(this).val();
        var flag = 0; 
        if($("#cat2").val() == "" ){
            $("#cat2").attr("placeholder","Required Field!!");
            $("#cat2").addClass("inp");
            flag = 1;
        }
        console.log(cat);
        if(flag==0){
            $("#loading").show();
            $.ajax({
                type: "post",
                url: "Saved",
                data: {id:id,cat:cat,submit:"category",type:type},
                success:function(res){
                    $("#loading").hide(function(){
                        alert(res);  
                    });  
                },
                error:function(res){
                    $("#loading").hide(function(){
                        alert(res);  
                    });  
                }
            });
        }
        setTimeout(function(){
            $("#cat2").removeClass("inp");
            $("#cat2").attr("placeholder","");
            $("#submit2").prop("disabled",false);
        },2000);

    });
    //edit category
    $(".editcat").click(function(e){
        console.log("asdasdasd");
        e.preventDefault();        
        var id = $(this).parent("p").children(".eid").val();
        var cat = $(this).parent("p").children(".category").val();
        console.log(id);
        $("#catid").val(id);
        $("#cat2").val(cat);      
        $("#addcat").val("update");      
    });
    //insert sub-category
    $("#addscat").click(function(e){
        e.preventDefault();
        var scat = $("#scat2").val();
        var id = $("#scatid").val();
        var type = $(this).val();
        var flag = 0; 
        if($("#scat2").val() == "" ){
            $("#scat2").attr("placeholder","Required Field!!");
            $("#scat2").addClass("inp");
            flag = 1;
        }
        console.log(cat);
        if(flag==0){
            $("#loading").show();
            $.ajax({
                type: "post",
                url: "Saved",
                data: {id:id,scat:scat,submit:"subcategory",type:type},
                success:function(res){
                    $("#loading").hide(function(){
                        alert(res);  
                    });  
                },
                error:function(res){
                    $("#loading").hide(function(){
                        alert(res);  
                    });  
                }
            });
        }
        setTimeout(function(){
            $("#scat2").removeClass("inp");
            $("#scat2").attr("placeholder","");
        },2000);

    });
    //edit subcategory
    $(".editscat").click(function(e){
        e.preventDefault();        
        var id = $(this).parent("p").children(".eid").val();
        var scat = $(this).parent("p").children(".scategory").val();
        console.log(id);
        $("#scatid").val(id);
        $("#scat2").val(scat);      
        $("#addscat").val("update");      
    });
    //insert size
    $("#addsize").click(function(e){
        e.preventDefault();
        var id = $("#sizeid").val();
        var type = $(this).val();
        var size = $("#size2").val();
        var flag = 0; 
        if($("#size2").val() == ""){
            $("#size2").attr("placeholder","Required Field!!");
            $("#size2").addClass("inp");
            flag = 1;
        }
        console.log(cat);
        if(flag==0){
            $("#loading").show();
            $.ajax({
                type: "post",
                url: "Saved",
                data: {size:size,submit:"size",type:type,id:id},
                success:function(res){
                    $("#loading").hide(function(){
                        alert(res);  
                    });                     
                },
                error:function(res){
                    $("#loading").hide(function(){
                        alert(res);  
                    });  
                }
            });
        }
        setTimeout(function(){
            $("#size2").removeClass("inp");
            $("#size2").attr("placeholder","");
        },2000);

    });
    //edit size
    $(".editsize").click(function(e){
        console.log("asdasdasd");
        e.preventDefault();        
        var id = $(this).parent("p").children(".eid").val();
        var size = $(this).parent("p").children(".size").val();
        console.log(id);
        $("#sizeid").val(id);
        $("#size2").val(size);      
        $("#addsize").val("update");      
    });
    //insert unit
    $("#addunit").click(function(e){
        e.preventDefault();
        var id = $("#unitid").val();
        var type = $(this).val();
        var unit = $("#unit2").val();
        var flag = 0; 
        if($("#unit2").val() == ""){
            $("#unit2").attr("placeholder","Required Field!!");
            $("#unit2").addClass("inp");
            flag = 1;
        }
        console.log(cat);
        if(flag==0){
            $("#loading").show();
            $.ajax({
                type: "post",
                url: "Saved",
                data: {unit:unit,submit:"unit",type:type,id:id},
                success:function(res){
                    $("#loading").hide(function(){
                        alert(res);  
                    });  
                },
                error:function(res){
                    $("#loading").hide(function(){
                        alert(res);  
                    });  
                }
            });
        }
        setTimeout(function(){
            $("#unit2").removeClass("inp");
            $("#unit2").attr("placeholder","");
        },2000);

    });
    //edit unit
    $(".editunit").click(function(e){
        console.log("asdasdasd");
        e.preventDefault();        
        var id = $(this).parent("p").children(".eid").val();
        var unit = $(this).parent("p").children(".unit").val();
        console.log(id);
        $("#unitid").val(id);
        $("#unit2").val(unit);      
        $("#addunit").val("update");      
    });
//    //insert tax
//    $("#addtax").click(function(e){
//        e.preventDefault();
//        var tax = $("#tax2").val();
//        var taxrate = $("#taxrate2").val();
//        var id = $("#taxid").val();
//        var type = $(this).val();
//        var flag = 0; 
//        $("#taxmodal input").each(function(){
//            if($(this).val() == "" && $(this).parent().css("display")!="none" && $(this).attr("required")){
//                $(this).attr("placeholder","Required Field!!");
//                $(this).addClass("inp");
//                flag = 1;
//            }
//        });
//        console.log(cat);
//        if(flag==0){
//            $.ajax({
//                type: "post",
//                url: "Saved",
//                data: {tax:tax,taxrate:taxrate,submit:"tax",type:type,id:id},
//                success:function(res){
//                    alert(res);   
//                },
//                error:function(res){
//                    alert("Error!!");
//                }
//            });
//        }
//        setTimeout(function(){
//            $("#myModal2 input").removeClass("inp");
//            $("#myModal2 input").attr("placeholder","");
//            $("#submit2").prop("disabled",false);
//        },2000);
//
//    });
//    //edit tax
//    $(".edittax").click(function(e){
//        console.log("asdasdasd");
//        e.preventDefault();        
//        var id = $(this).parent("p").children(".eid").val();
//        var taxname = $(this).parent("p").children(".taxname").val();
//        var taxrate = $(this).parent("p").children(".taxrate").val();
//        console.log(id);
//        $("#taxid").val(id);
//        $("#tax2").val(taxname);
//        $("#taxrate2").val(taxrate);
//        $("#addtax").val("update");      
//    });
    //insert collection
    $(document).on("click","#addcoll",function(e){
        e.preventDefault();
        var collections = $("#collections2").val();
        var item = "";
        var id = $("#collid").val();
        var type = $(this).val();
        var flag = 0; 
        if($("#collections2").val() == ""){
            $("#collections2").attr("placeholder","Required Field!!");
            $("#collections2").addClass("inp");
            flag = 1;
        }
        $(".collitem").each(function(){
            if($(this).val==""){
                flag=1;
            }
            else{
                if(item=="")
                    item = $(this).val();
                else
                    item = item+","+$(this).val();
            }   
        });
        if(flag==0){
            $("#loading").show();
            $.ajax({
                type: "post",
                url: "Saved",
                data: {collections:collections,submit:"collections",type:type,items:item,id:id},
                success:function(res){
                    $("#loading").hide(function(){
                        alert(res);  
                    });                    
                },
                error:function(res){
                    $("#loading").hide(function(){
                        alert(res);  
                    });  
                }
            });
        }
        setTimeout(function(){
            $("#collections2").removeClass("inp");
            $("#collections2").attr("placeholder","");
            $("#submit2").prop("disabled",false);
        },2000);

    });
    //edit collection
    $(".editcoll").click(function(e){
        e.preventDefault();        
        var id = $(this).parent("p").children(".eid").val();
        var collections = $(this).parent("p").children(".collections").html();
        var item = $(this).parent("p").find(".collitem2");
        var i = 0;
        $("#collectionsmodal .modal-body .form-control").remove("select");
        $("#collectionsmodal .delcollitem").remove();
        $(item).each(function(){
            $("#addcollitem").before(items.prop("outerHTML")); 
            $("#addcollitem").before(delbut.prop("outerHTML")); 
            $(".collitem").eq(i).val($(this).val().replace(/\n/g,"")+"\n");
            i++;
        });
        
        
        $("#collections2").val(collections);
        $("#collid").val(id);

    });
    //insert warehouse
    $("#addware").click(function(e){
        e.preventDefault();
        var warehouse = $("#warehouse2").val();
        var pincode = $("#pincode2").val();
        var city = $("#city2").val();
        var address = $("#address2").val();
        var state = $("#state2").val();
        var statecode = $("#statecode2").val();
        var id = $("#wareid").val();
        var type = $(this).val();
        var flag = 0; 
        $("#warehousemodal input").each(function(){
            if($(this).val() == "" && $(this).parent().css("display")!="none" && $(this).attr("required")){
                $(this).attr("placeholder","Required Field!!");
                $(this).addClass("inp");
                flag = 1;
            }
        });
        console.log(cat);
        if(flag==0){
            $("#loading").show();
            $.ajax({
                type: "post",
                url: "Saved",
                data: {id:id,warehouse:warehouse,pincode:pincode,city:city,address:address,state:state,statecode:statecode,submit:"warehouse",type:type},
                success:function(res){
                    $("#loading").hide(function(){
                        alert(res);  
                    });  
                },
                error:function(res){
                    $("#loading").hide(function(){
                        alert(res);  
                    });  
                }
            });
        }
        setTimeout(function(){
            $("#myModal2 input").removeClass("inp");
            $("#myModal2 input").attr("placeholder","");
            $("#submit2").prop("disabled",false);
        },2000);

    });
    //edit warehouse
    $(".editware").click(function(e){
        e.preventDefault();        
        var id = $(this).parent("p").children(".eid").val();
        var warehouse = $(this).parent("p").children(".warehouse").val();
        var pincode = $(this).parent("p").children(".pincode").val();
        var city = $(this).parent("p").children(".city").val();
        var address = $(this).parent("p").children(".address").val();
        var state = $(this).parent("p").children(".state").val();
        var statecode = $(this).parent("p").children(".statecode").val();
        
        $("#warehouse2").val(warehouse);
        $("#pincode2").val(pincode);
        $("#city2").val(city);
        $("#address2").val(address);
        $("#state2").val(state);
        $("#statecode2").val(statecode);
        $("#wareid").val(id);
        $("#addware").val("update");      
    });
    
    //generate qrcode
    $("#generatei").click(function(){
        var text = $("#myModal3 input").val();
        if(text == ""){
            $("#myModal3 input").attr("placeholder","Required Field!!");
            $("#myModal3 input").addClass("inp");
            setTimeout(function(){
                $("#myModal3 input").removeClass("inp");
                $("#myModal3 input").attr("placeholder","");
                $("#generatei").prop("disabled",false);
            },2000);
        }
        else{
            console.log(text);
            $("#qr").empty();
            $("#qr").append("<img>");
            var src = "https://chart.googleapis.com/chart?cht=qr&chs=250x250&chl="+text;
            $("#qr img").attr("src",src);
        }
    });
    $("#main .dropdown a").click(function(){
        $(".modal .modal-body div>input").val("");
        $(".modal div>input[type=checkbox]").prop("checked",false);
        $("#bill").prop('checked',true);
        $(".modal input[type=number]").val(0);
        $(".modal button").val("insert");               
    });
    //add contact
    $("#contactpost").click(function(e){
        e.preventDefault();
        var email = $("#email").val();
        var phone = $("#tel").val();
        var comp = $("#comp").val();
        var state = $("#state").val();
        var scode = $("#scode").val();
        var pcode = $("#pcode").val();
        var address = $("#add").val();
        var pno = $("#pno").val();
        var ifsc = $("#bifsc").val();
        var city = $("#city").val();
        var name = $("#name").val();        
        var accno = $("#accno").val();
        var type = $("#type").val();
        var salary = $("#salary").val();
        var gstin = $("#gstin").val();
        var submit = $(this).val();
        var id = $("#id").val();
        var flag = 0;
        var re = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/igm;
        $("#myModal1 input").each(function(){
            if($(this).val() == "" && $(this).parent().css("display")!="none" && $(this).attr("required")){
                $(this).attr("placeholder","Required Field!!");
                $(this).addClass("inp");
                flag = 1;
            }
        });
        if(!re.test(email)){
            $("#email").attr("placeholder","Required Field!!");
            $("#email").addClass("inp");
            flag = 1;
        }      
        console.log(pcode);
        if(flag==0){
            $("#loading").show();
            $.ajax({
                type: "post",
                url: "Contact",
                data: {id:id,submit:submit,salary:salary,name:name,type:type,email:email,comp:comp
                    ,phone:phone,address:address,scode:scode,state:state,city:city,pno:pno,
                    ifsc:ifsc,accno:accno,pincode:pcode,gstin:gstin},
                success:function(res){
                    $("#loading").hide(function(){
                        alert(res);  
                    });  
                },
                error:function(res){
                    $("#loading").hide(function(){
                        alert(res);  
                    });  
                }
            });
        }
        setTimeout(function(){
            $("#myModal1 input").attr("placeholder","");
            $("#myModal1 input").removeClass("inp");
            $("#submit1").prop("disabled",false);
        },2000);
    });
    //edit contact
    $(".editcontact").click(function(e){
        e.preventDefault();        
        var id = parseInt($(this).parent("div").children(".eid").val());        
        var email = $(this).parent("div").find(".email").html();
        var phone = parseInt($(this).parent("div").find(".phone").html().replace(/\n/g,""));
        var comp = $(this).parent("div").find(".comp").html();
        var state = $(this).parent("div").find(".state").html();
        var scode = $(this).parent("div").find(".scode").html().replace(/\n/g,"");
        var pcode = parseInt($(this).parent("div").find(".pcode").html().replace(/\n/g,""));
        var address = $(this).parent("div").find(".address").html();
        var pno = parseInt($(this).parent("div").find(".pno").html().replace(/\n/g,""));
        var ifsc = parseInt($(this).parent("div").find(".bifsc").html().replace(/\n/g,""));
        var city = $(this).parent("div").find(".city").html();
        var name = $(this).parent("div").find(".name").html();        
        var accno = parseInt($(this).parent("div").find(".accno").html().replace(/\n/g,""));
        var type = $(this).parent("div").find(".type").html();
        var salary = parseInt($(this).parent("div").find(".salary").html().replace(/\n/g,""));
        var gstin = $(this).parent("div").find(".gstin").html();
        
        $("#email").val(email);
        $("#tel").val(phone);
        $("#comp").val(comp);
        $("#state").val(state);
        $("#scode").val(scode);
        $("#pcode").val(pcode);
        $("#add").val(address);
        $("#pno").val(pno);
        $("#bifsc").val(ifsc);
        $("#city").val(city);
        $("#name").val(name);        
        $("#accno").val(accno);
        $("#gstin").val(gstin);
        $("#type").val(type.replace(/\n/g,""));
        $("#salary").val(salary);
        $("#contactpost").val("update");
        $("#id").val(id);
        
        console.log(type);
        $("#myModal1").modal('show');
    });
    //add user
    $("#adduser").click(function(){
        var name = $("#name").val();
        var email = $("#email").val();
        var state = $("#state").val();
        var city = $("#city").val();
        var pwd = $("#pwd").val();
        var address = $("#address").val();
        var warehouse = $("#warehouse").val();
        var contact = $("#contact").val();
        var submit = $(this).val();
        var id = $("#id").val();
        var type = "sub";
        var privc = "";
        var privv = "";
        var privd = "";
        var re = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/igm;
        var flag = 0;
        if($("#sc").prop("checked")==true){
            if(privc=="")
                 privc = "sale";
             else
                 privc = ","+"sale";
        }
        if($("#pc").prop("checked")==true){
            if(privc=="")
                 privc = "purchase";
             else
                 privc = privc+","+"purchase";
        }
        if($("#cc").prop("checked")==true){
            if(privc=="")
                 privc = "contact";
             else
                 privc = privc+","+"contact";
        }
        if($("#ic").prop("checked")==true){
            if(privc=="")
                 privc = "inventory";
             else
                 privc = privc+","+"inventory";
        }
        
        if($("#sv").prop("checked")==true){
            if(privv=="")
                 privv = "sale";
             else
                 privv = ","+"sale";
        }
        if($("#pv").prop("checked")==true){
            if(privv=="")
                 privv = "purchase";
             else
                 privv = privv+","+"purchase";
        }
        if($("#cv").prop("checked")==true){
            if(privv=="")
                 privv = "contact";
             else
                 privv = privv+","+"contact";
        }
        if($("#iv").prop("checked")==true){
            if(privv=="")
                 privv = "inventory";
             else
                 privv = privv+","+"inventory";
        }
        
        if($("#sd").prop("checked")==true){
            if(privd=="")
                 privd = "sales";
             else
                 privd = ","+"sale";
        }
        if($("#pd").prop("checked")==true){
            if(privd=="")
                 privd = "purchases";
             else
                 privd = privd+","+"purchase";
        }
        if($("#cd").prop("checked")==true){
            if(privd=="")
                 privd = "contact";
             else
                 privd = privd+","+"contact";
        }
        if($("#idel").prop("checked")==true){
            if(privd=="")
                 privd = "inventory";
             else
                 privd = privd+","+"inventory";
        }
        console.log(privc+privd+privv);
        $("#myModal2 input").each(function(){
            if($(this).val() == "" && $(this).parent().css("display")!="none" && $(this).attr("required")){
                $(this).attr("placeholder","Required Field!!");
                $(this).addClass("inp");
                flag = 1;
            }
        });
        if(!re.test(email)){
            $("#email").attr("placeholder","Required Field!!");
            $("#email").addClass("inp");
            flag = 1;
        }       
        if(flag==0){
            $("#loading").show();
            $.ajax({
                type: "post",
                url: "User",
                data: {id:id,submit:submit,name:name,email:email,pwd:pwd
                    ,address:address,state:state,city:city,contact:contact,privc:privc,
                    privv:privv,privd:privd,type:type,warehouse:warehouse},
                success:function(res){
                    $("#loading").hide(function(){
                        alert(res);  
                    });                    
                },
                error:function(res){
                    $("#loading").hide(function(){
                        alert(res);  
                    });  
                }
            });
        }
        setTimeout(function(){
            $("#myModal1 input").attr("placeholder","");
            $("#myModal1 input").removeClass("inp");
            $("#submit1").prop("disabled",false);
        },2000);
    });
    $(".edituser").click(function(){
        var email = $(this).parent("div").find(".email").html();
        var name = $(this).parent("div").find(".name").html();
        var warehouse = $(this).parent("div").find(".warehouse").val().replace(/\n/g,"");
        var contact = $(this).parent("div").find(".contact").html();
        var state = $(this).parent("div").find(".state").html();
        var city = $(this).parent("div").find(".city").html();
        var address = $(this).parent("div").find(".address").html();
        var id = $(this).parent("div").find(".id").html().replace(/\n/g,"");
        var pwd = $(this).parent("div").find(".pwd").html();
        var pc = $(this).parent("div").find(".pc").html();
        var pv = $(this).parent("div").find(".pv").html();
        var pd = $(this).parent("div").find(".pd").html();
        if(pc.includes("sale")){
            $("#sc").prop("checked",true);
        }
        if(pc.includes("purchase")){
            $("#pc").prop("checked",true);
        }
        if(pc.includes("contact")){
            $("#cc").prop("checked",true);
        }
        if(pc.includes("inventory")){
            $("#ic").prop("checked",true);
        }
        if(pv.includes("sale")){
            $("#sv").prop("checked",true);
        }
        if(pv.includes("purchase")){
            $("#pv").prop("checked",true);
        }
        if(pv.includes("contact")){
            $("#cv").prop("checked",true);
        }
        if(pv.includes("inventory")){
            $("#iv").prop("checked",true);
        }
        if(pd.includes("sale")){
            $("#sd").prop("checked",true);
        }
        if(pd.includes("purchase")){
            $("#pd").prop("checked",true);
        }
        if(pd.includes("contact")){
            $("#cd").prop("checked",true);
        }
        if(pd.includes("inventory")){
            $("#idel").prop("checked",true);
        }
        $("#adduse").val("update");
        $("#name").val(name);
        $("#warehouse").val(warehouse+"\n");
        $("#pwd").val(pwd);
        $("#email").val(email);
        $("#state").val(state);
        $("#city").val(city);
        $("#address").val(address);
        $("#contact").val(contact);
        $("#adduser").val("update");
        $("#id").val(id);
        $("#myModal2").modal('show');
    });
    $("#edituser").click(function(){
        var name = $("#uname").html();
        var contact = $("#ucontact").html();
        var state = $("#ustate").html();
        var city = $("#ucity").html();
        var address = $("#uaddress").html();
        var statecode = $("#ustatecode").html();
        var pincode = $("#upincode").html();
        var id = $("#uid").html().replace(/\n/g,"");

 
        $("#uname2").val(name);
//        $("#upwd").val(pwd);
//        $("#uemail").val(email);
        $("#ustate2").val(state);
        $("#ucity2").val(city);
        $("#uaddress2").val(address);
        $("#ucontact2").val(contact);
        $("#ustatecode2").val(statecode);
        $("#upincode2").val(pincode);
        $("#uid2").val(id);
        $("#myModal1").modal('show');
         
    });
    $("#addetails").click(function(){
        var name = $("#uname2").val();
        var contact = $("#ucontact2").val();
        var state = $("#ustate2").val();
        var city = $("#ucity2").val();
        var address = $("#uaddress2").val();
        var id = $("#uid2").val();
        var flag = 0;
        $("#myModal1 input").each(function(){
            if($(this).val() == "" && $(this).parent().css("display")!="none" && $(this).attr("required")){
                $(this).attr("placeholder","Required Field!!");
                $(this).addClass("inp");
                flag = 1;
            }
        });    
        if(flag==0){
            $("#loading").show();
            $.ajax({
                type: "post",
                url: "User",
                data: {submit:"update",email:"",pwd:"",warehouse:"",name:name,address:address,state:state,city:city,contact:contact,type:"admin",id:id},
                success:function(res){
                    $("#loading").hide(function(){
                        alert(res);  
                    });  
                },
                error:function(res){
                    $("#loading").hide(function(){
                        alert(res);  
                    });  
                }
            });
        }
        setTimeout(function(){
            $("#myModal1 input").attr("placeholder","");
            $("#myModal1 input").removeClass("inp");
            $("#submit1").prop("disabled",false);
        },2000);
    });
    $("#addserial").click(function(){
        var sis = $("#sis").val();
        var sis2 = sis+","+$("#sis2").val();
        var pis = $("#pis").val();
        var pis2 = pis+","+$("#pis2").val();
        var prs = $("prs").val();
        var prs2 = prs+","+$("#prs2").val();
        var ess = $("#ess").val();
        var ess2 = ess+","+$("#ess2").val();
        var bos = $("#bos").val();
        var bos2 = bos+","+$("#bos2").val();
        var exs = $("#exs").val();
        var exs2 = exs+","+$("#exs2").val();
        var ars = $("#ars").val();
        var ars2 = ars+","+$("#ars2").val();
        var pos = $("#pos").val();
        var pos2 = pos+","+$("#pos2").val();
        var dcs = $("#dcs").val();
        var dcs2 = dcs+","+$("#dcs2").val();
        var cns = $("#cns").val();
        var cns2 = cns+","+$("#cns2").val();
        var dns = $("#dns").val();
        var dns2 = dns+","+$("#dns2").val();
        var rvs = $("#rvs").val();
        var rvs2 = rvs+","+$("#rvs2").val();
        var pvs = $("#pvs").val();
        var pvs2 = pvs+","+$("#pvs2").val();
        var aps = $("#aps").val();
        var aps2 = aps+","+$("#aps2").val();
        var oos = $("#oos").val();
        var oos2 = oos+","+$("#oos2").val();
        var flag = 0;
        $("#myModal3 input").each(function(){
            if($(this).val() == "" && $(this).parent().css("display")!="none" && $(this).attr("required")){
                $(this).attr("placeholder","Required Field!!");
                $(this).addClass("inp");
                flag = 1;
            }
        });    
        if(flag==0){
            $("#loading").show();
            $.ajax({
                type: "post",
                url: "Invoice_serial",
                data: {sis2:sis2,pis2:pis2,prs2:prs2,ess2:ess2,revs2:revs2,bos2:bos2,exs2:exs2,ars2:ars2,pos2:pos2
                    ,dcs2:dcs2,cns2:cns2,dns2:dns2,rvs2:rvs2,pvs2:pvs2,oos2:oos2,id:id},
                success:function(res){
                    $("#loading").hide(function(){
                        alert(res);  
                    });  
                },
                error:function(res){
                    $("#loading").hide(function(){
                        alert(res);  
                    });  
                }
            });
        }
        setTimeout(function(){
            $("#myModal3 input").attr("placeholder","");
            $("#myModal3input").removeClass("inp");
            $("#submit1").prop("disabled",false);
        },2000);
    });
    $("#addsales").click(function(e){
        e.preventDefault();
        var invoiceno = $("#invoiceno").val();
        var name = $("#name").val();
        var cid = $("#cid").val();
        var remonth = $("#remonth").val();
        var requarter = $("#requarter").val();
        var gstin = $("#gstin").val();
        var pos = $("#placeofsupply").val();
        var damount = $("#damount").val();
        var ddate = $("#ddate").val();
        var idate = $("#invoicedate").val();
        var bill = "";
        if($("#bill").prop('checked')){
            bill = true;
        }
        else{
            bill = false;
        }
        
        var billadd = $("#baddr").val();
        var billstate = $("#bstate").val();
        var billcity = $("#bcity").val();
        var billpin = $("#bpin").val();
        var billstatecode = $("#bstatecode").val();
        var shipadd = $("#saddr").val();
        var shipstate = $("#sstate").val();
        var shipcity = $("#scity").val();
        var shippin = $("#spin").val();
        var shipbillno = $("#sbillno").val();
        var shipportcode = $("#spcode").val();
        var shipbilldate = $("#sbilldate").val();
        var shipstatecode = $("#sstatecode").val();
        var roundoff = "";
        if($("#roundoff").prop('checked')){
            roundoff = true;
        }
        else{
            roundoff = false;
        }
        var taxexemp = $("#taxexemp").val();
        var exporttype = $("#exporttype").val();
        var challantype = $("#challantype").val();
        var advanced = $("#advanced").val();
        var transby = $("#transby").val();
        var transcharge = $("#transcharge").val();
        var products = "";
        var productsid = "";
        var productscost = "";
        var productsprice = "";
        var qty = "";
        var taxrate = "";
        var cgst = "";
        var cgstamount = "";
        var igst = "";
        var igstamount = "";
        var cess = "";
        var cessamount = "";
        var discount = "";
        var discounttype = "";
        var productsamount = "";
        var ref = $("#reference").val();
        var tamount = $("#amount").val();
        var submit = $("#addsales").val();
        var type = $("#type").val();
        var id = $("#itemid").val();
        var warehouse = $("#warehouse").val();
        var flag = 0;

        $("#myModal1 input").each(function(){
            console.log($(this).parent().parent().css("display"));
            if($(this).val() == "" && $(this).parent().css("display")!="none" && $(this).parent().parent().css("display")!="none" && $(this).attr("required")){
                $(this).attr("placeholder","Required Field!!");
                $(this).addClass("inp");
                console.log($(this).attr("id"));
                flag = 1;
            }
        });    
        $(".products").each(function(){
           if(products== ""){
               products= $(this).val();
           } 
           else{
               products= products+","+$(this).val();
           }
        });
        $(".pid").each(function(){
           if(productsid== ""){
               productsid= $(this).val();
           } 
           else{
               productsid= productsid+","+$(this).val();
           }
        });
        $(".productscost").each(function(){
           if(productscost== ""){
               productscost= $(this).val();
           } 
           else{
               productscost= productscost+","+$(this).val();
           }
        });
        $(".productsprice").each(function(){
           if(productsprice== ""){
               productsprice= $(this).val();
           } 
           else{
               productsprice= productsprice+","+$(this).val();
           }
        });
        $(".qty").each(function(){
           if(qty== ""){
               qty= $(this).val();
           } 
           else{
               qty= qty+","+$(this).val();
           }
        });
        $(".taxrate").each(function(){
           if(taxrate== ""){
               taxrate= $(this).val();
           } 
           else{
               taxrate= taxrate+","+$(this).val();
           }
        });
        $(".cgst").each(function(){
           if(cgst== ""){
               cgst= $(this).val();
           } 
           else{
               cgst= cgst+","+$(this).val();
           }
        });
        $(".cgstamount").each(function(){
           if(cgstamount== ""){
               cgstamount= $(this).val();
           } 
           else{
               cgstamount= cgstamount+","+$(this).val();
           }
        });        
        $(".igst").each(function(){
           if(igst== ""){
               igst= $(this).val();
           } 
           else{
               igst= igst+","+$(this).val();
           }
        });
        $(".igstamount").each(function(){
           if(igstamount== ""){
               igstamount= $(this).val();
           } 
           else{
               igstamount= igstamount+","+$(this).val();
           }
        });
        $(".cess").each(function(){
           if(cess== ""){
               cess= $(this).val();
           } 
           else{
               cess= cess+","+$(this).val();
           }
        });
        $(".cessamount").each(function(){
           if(cessamount== ""){
               cessamount= $(this).val();
           } 
           else{
               cessamount= cessamount+","+$(this).val();
           }
        });
        $(".discount").each(function(){
           if(discount== ""){
               discount= $(this).val();
           } 
           else{
               discount= discount+","+$(this).val();
           }
        });
        $(".discounttype").each(function(){
           if(discounttype== ""){
               discounttype= $(this).val();
           } 
           else{
               discounttype= discounttype+","+$(this).val();;
           }
        });
        $(".productsamount").each(function(){
           if(productsamount== ""){
               productsamount= $(this).val();
           } 
           else{
               productsamount= productsamount+","+$(this).val();
           }
        });
        console.log(flag);
        if(flag==0){
            $("#loading").show();
            $.ajax({
                type: "post",
                url: "Sales",
                data: {id:id,cid:cid,invoiceno:invoiceno,name:name,remonth:remonth,requarter:requarter,gstin:gstin,pos:pos,
                    ddate:ddate,idate:idate,bill:bill,billadd:billadd,billstate:billstate,billcity:billcity,
                    billpin:billpin,billstatecode:billstatecode,shipstatecode:shipstatecode,shippadd:shipadd,
                    shipcity:shipcity,shipstate:shipstate,shipbillno:shipbillno,
                    shippin:shippin,shipbilldate:shipbilldate,shipportcode:shipportcode,roundoff:roundoff,taxexemp:taxexemp,
                    damount:damount,exporttype:exporttype,tamount:tamount,discounttype:discounttype,
                    products:products,productsid:productsid,productscost:productscost,qty:qty,taxrate:taxrate,
                    sgst:cgst,cgst:cgst,igst:igst,cess:cess,warehouse:warehouse,tamount:tamount,productsprice:productsprice,
                    discount:discount,ref:ref,challantype:challantype,advanced:advanced,productsamount:productsamount,
                    transby:transby,transcharge:transcharge,cgstamount:cgstamount,
                    sgstamount:cgstamount,igstamount:igstamount,cessamount:cessamount,submit:submit,type:type},
                success:function(res){
                    $("#loading").hide(function(){
                        alert(res);  
                    });  
                },
                error:function(res){
                    $("#loading").hide(function(){
                        alert(res);  
                    });  
                }
            });
        }
        setTimeout(function(){
            $("#myModal1 input").removeClass("inp");
            $("#myModal1 input").attr("placeholder","");
            $("#submit1").prop("disabled",false);
        },2000);
    }); 
    $(".editsales").click(function(e){
        e.preventDefault();
        $("#cid").val($(this).parent("div").find(".cid").val().replace(/\n/g,""));
        $("#invoiceno").val($(this).parent("div").find(".iinvoiceno").html().replace(/\n/g,""));
        $("#name").val($(this).parent("div").find(".iname").html().replace(/\n/g,""));
        $("#itemid").val($(this).parent("div").find(".sid").val().replace(/\n/g,""));
        $("#remonth").val($(this).parent("div").find(".iremonth").html().replace(/\n/g,""));
        $("#requarter").val($(this).parent("div").find(".irequart").html().replace(/\n/g,""));
        $("#gstin").val($(this).parent("div").find(".icgstin").html().replace(/\n/g,""));
        $("#pos").val($(this).parent("div").find(".ipos").html().replace(/\n/g,""));
        $("#ddate").val($(this).parent("div").find(".iddate").html().replace(/\n/g,""));
        $("#invoicedate").val($(this).parent("div").find(".iidate").html().replace(/\n/g,""));
        if($(this).parent("div").find(".ibill").html().replace(/\n/g,"")=="true"){
            $("#bill").prop('checked',true);
            $("#salesdiv").hide();
        }
        else{
            $("#bill").prop('checked',false);
            $("#salesdiv").show();
        }
        if($(this).parent("div").find(".iroundoff").html().replace(/\n/g,"")=="true"){
            $("#roundoff").prop('checked',true);
        }
        else{
            $("#roundoff").prop('checked',false);
        }
        
        $("#baddr").val($(this).parent("div").find(".ibaddr").html().replace(/\n/g,""));
        $("#bstate").val($(this).parent("div").find(".ibstate").html().replace(/\n/g,""));
        $("#bcity").val($(this).parent("div").find(".ibcity").html().replace(/\n/g,""));
        $("#bpin").val($(this).parent("div").find(".ibpin").html().replace(/\n/g,""));
        $("#bstatecode").val($(this).parent("div").find(".ibstatecode").html().replace(/\n/g,""));
        $("#saddr").val($(this).parent("div").find(".isaddr").html().replace(/\n/g,""));
        $("#sstate").val($(this).parent("div").find(".isstate").html().replace(/\n/g,""));
        $("#scity").val($(this).parent("div").find(".iscity").html().replace(/\n/g,""));
        $("#spin").val($(this).parent("div").find(".ispin").html().replace(/\n/g,""));
        $("#sbillno").val($(this).parent("div").find(".ibillno").html().replace(/\n/g,""));
        $("#spcode").val($(this).parent("div").find(".iportcode").html().replace(/\n/g,""));
        $("#sbilldate").val($(this).parent("div").find(".ibilldate").html().replace(/\n/g,""));
        $("#sstatecode").val($(this).parent("div").find(".istatecode").html().replace(/\n/g,""));
        $("#exporttype").val($(this).parent("div").find(".iexporttype").html().replace(/\n/g,""));
        $("#challantype").val($(this).parent("div").find(".ichallantype").html().replace(/\n/g,""));
        $("#advanced").val($(this).parent("div").find(".iadvanced").html().replace(/\n/g,""));
        $("#transby").val($(this).parent("div").find(".itransby").html().replace(/\n/g,""));
        $("#transcharge").val($(this).parent("div").find(".itranscharge").html().replace(/\n/g,""));
        $("#reference").val($(this).parent("div").find(".iref").html().replace(/\n/g,""));
        $("#shippedby").val($(this).parent("div").find(".itransby").html().replace(/\n/g,""));
        $("#amount").val($(this).parent("div").find(".itamount").html().replace(/\n/g,""));
        $("#addsales").val("update");
        $("#type").val($(this).parent("div").find(".itype").html().replace(/\n/g,""));
        var transby = $("#transby").val();
        var transcharge = $("#transcharge").val();
        var type = $("#type").val();
        if(type=="dc"){
            $("#transdiv").show();
        }
        else{
            $("#transdiv").hide();
        }
        console.log(transby);        
        console.log(transcharge);        
        if(transby==""||transcharge==0){
            $("#transdiv").hide();
        }
        else{
            $("#transdiv").show();
        }
        $("#warehouse").val(parseInt($(this).parent("div").find(".iwareid").html().replace(/\n/g,"")));
        $("#amount").val(parseInt($(this).parent("div").find(".itamount").html().replace(/\n/g,"")));
        var products = ($(this).parent("div").find(".iproducts").html().replace(/\n/g,"").split(","));
        var productsid = ($(this).parent("div").find(".iproductsid").html().replace(/\n/g,"").split(","));
        var productscost = ($(this).parent("div").find(".iproductscost").html().replace(/\n/g,"").split(","));
        var productsprice = ($(this).parent("div").find(".iproductsprice").html().replace(/\n/g,"").split(","));
        var discounttype = ($(this).parent("div").find(".idiscounttype").html().replace(/\n/g,"").split(","));
        var qty = ($(this).parent("div").find(".iproductsqty").html().replace(/\n/g,"").split(","));
        var taxrate = ($(this).parent("div").find(".itaxrate").html().replace(/\n/g,"").split(","));
        var cgst = ($(this).parent("div").find(".icgst").html().replace(/\n/g,"").split(","));
        var cgstamount = ($(this).parent("div").find(".icgstamount").html().replace(/\n/g,"").split(","));
        var igst = ($(this).parent("div").find(".iigst").html().replace(/\n/g,"").split(","));
        var igstamount = ($(this).parent("div").find(".iigstamount").html().replace(/\n/g,"").split(","));
        var cess = ($(this).parent("div").find(".icess").html().replace(/\n/g,"").split(","));
        var cessamount = ($(this).parent("div").find(".icessamount").html().replace(/\n/g,"").split(","));
        var productsamount = ($(this).parent("div").find(".iamount").html().replace(/\n/g,"").split(","));
        var discount = (parseInt($(this).parent("div").find(".idiscount").html().replace(/\n/g,"").split(",")));
        var length = products.length;
        var i = 0,k=0;
        $("#myModal1 .table tbody").empty();
        for(k=0;k<length;k++){
            $("#add").trigger("click");
        }
        $(".products").each(function(){            
            $(this).val(products[i]);
            i++;
        });
        i=0;
        $(".pid").each(function(){            
            $(this).val(productsid[i]);
            i++;
        });
        i=0;
        $(".productscost").each(function(){            
            $(this).val(productscost[i]);
            i++;
        });
        i=0;
        $(".productsprice").each(function(){            
            $(this).val(productsprice[i]);
            i++;
        });
        i=0;
        $(".productsamount").each(function(){            
            $(this).val(productsamount[i]);
            i++;
        });
        i=0;
        $(".qty").each(function(){
            $(this).val(qty[i]);
            i++;
        }); 
        i=0;
        $(".taxrate").each(function(){
            $(this).val(taxrate[i]);
            i++;
        }); 
        i=0;
        $(".cgst").each(function(){
            $(this).val(cgst[i]);
            i++;
        }); 
        i=0;
        $(".cgstamount").each(function(){
            $(this).val(cgstamount[i]);
            i++;
        }); 
        i=0;
        $(".igst").each(function(){
            $(this).val(igst[i]);
            i++;
        }); 
        i=0;
        $(".igstamount").each(function(){
            $(this).val(igstamount[i]);
            i++;
        }); 
        i=0;
        $(".cess").each(function(){
            $(this).val(cess[i]);
            i++;
        }); 
        i=0;
        $(".cessamount").each(function(){
            $(this).val(cessamount[i]);
            i++;
        }); 
        i=0;
        $(".discount").each(function(){
            $(this).val(discount[i]);
            i++;
        }); 
        i=0;
        $(".discounttype").each(function(){            
            if(discounttype[i]=="percentage"){
                $(this).val("percentage");
            }
            else{
                $(this).val("rs");
            }
            i++;
        });
        $("#myModal1").modal('show');
    });
    $("#addpurchases").click(function(e){
        e.preventDefault();
        var invoiceno = $("#invoiceno").val();
        var name = $("#name").val();
        var cid = $("#cid").val();
        var remonth = $("#remonth").val();
        var requarter = $("#requarter").val();
        var gstin = $("#gstin").val();
        var pos = $("#placeofsupply").val();
        var damount = $("#damount").val();
        var ddate = $("#ddate").val();
        var idate = $("#invoicedate").val();
        
        var roundoff = "";
        if($("#roundoff").prop('checked')){
            roundoff = true;
        }
        else{
            roundoff = false;
        }
        var taxexemp = $("#taxexemp").val();
        var advanced = $("#advanced").val();
        var transcharge = $("#transcharge").val();
        var products = "";
        var productsid = "";
        var productscost = "";
        var productsprice = "";
        var qty = "";
        var taxrate = "";
        var cgst = "";
        var cgstamount = "";
        var igst = "";
        var igstamount = "";
        var cess = "";
        var cessamount = "";
        var discount = "";
        var discounttype = "";
        var productsamount = "";
        var ref = $("#reference").val();
        var tamount = $("#amount").val();
        var submit = $("#addpurchases").val();
        var type = $("#type").val();
        var id = $("#itemid").val();
        var warehouse = $("#warehouse").val();
        var flag = 0;

        $("#myModal1 input").each(function(){
            console.log($(this).parent().parent().css("display"));
            if($(this).val() == "" && $(this).parent().css("display")!="none" && $(this).parent().parent().css("display")!="none" && $(this).attr("required")){
                $(this).attr("placeholder","Required Field!!");
                $(this).addClass("inp");
                console.log($(this).attr("id"));
                flag = 1;
            }
        });    
        $(".products").each(function(){
           if(products== ""){
               products= $(this).val();
           } 
           else{
               products= products+","+$(this).val();
           }
        });
        $(".pid").each(function(){
           if(productsid== ""){
               productsid= $(this).val();
           } 
           else{
               productsid= productsid+","+$(this).val();
           }
        });
        $(".productscost").each(function(){
           if(productscost== ""){
               productscost= $(this).val();
           } 
           else{
               productscost= productscost+","+$(this).val();
           }
        });
        $(".productsprice").each(function(){
           if(productsprice== ""){
               productsprice= $(this).val();
           } 
           else{
               productsprice= productsprice+","+$(this).val();
           }
        });
        $(".qty").each(function(){
           if(qty== ""){
               qty= $(this).val();
           } 
           else{
               qty= qty+","+$(this).val();
           }
        });
        $(".taxrate").each(function(){
           if(taxrate== ""){
               taxrate= $(this).val();
           } 
           else{
               taxrate= taxrate+","+$(this).val();
           }
        });
        $(".cgst").each(function(){
           if(cgst== ""){
               cgst= $(this).val();
           } 
           else{
               cgst= cgst+","+$(this).val();
           }
        });
        $(".cgstamount").each(function(){
           if(cgstamount== ""){
               cgstamount= $(this).val();
           } 
           else{
               cgstamount= cgstamount+","+$(this).val();
           }
        });        
        $(".igst").each(function(){
           if(igst== ""){
               igst= $(this).val();
           } 
           else{
               igst= igst+","+$(this).val();
           }
        });
        $(".igstamount").each(function(){
           if(igstamount== ""){
               igstamount= $(this).val();
           } 
           else{
               igstamount= igstamount+","+$(this).val();
           }
        });
        $(".cess").each(function(){
           if(cess== ""){
               cess= $(this).val();
           } 
           else{
               cess= cess+","+$(this).val();
           }
        });
        $(".cessamount").each(function(){
           if(cessamount== ""){
               cessamount= $(this).val();
           } 
           else{
               cessamount= cessamount+","+$(this).val();
           }
        });
        $(".discount").each(function(){
           if(discount== ""){
               discount= $(this).val();
           } 
           else{
               discount= discount+","+$(this).val();
           }
        });
        $(".discounttype").each(function(){
           if(discounttype== ""){
               discounttype= $(this).val();
           } 
           else{
               discounttype= discounttype+","+$(this).val();;
           }
        });
        $(".productsamount").each(function(){
           if(productsamount== ""){
               productsamount= $(this).val();
           } 
           else{
               productsamount= productsamount+","+$(this).val();
           }
        });
        console.log(flag);
        if(flag==0){
            $("#loading").show();
            $.ajax({
                type: "post",
                url: "Purchases",
                data: {id:id,cid:cid,invoiceno:invoiceno,name:name,remonth:remonth,requarter:requarter,gstin:gstin,pos:pos,
                    ddate:ddate,idate:idate,roundoff:roundoff,taxexemp:taxexemp,
                    damount:damount,tamount:tamount,discounttype:discounttype,
                    products:products,productsid:productsid,productscost:productscost,qty:qty,taxrate:taxrate,
                    sgst:cgst,cgst:cgst,igst:igst,cess:cess,warehouse:warehouse,tamount:tamount,productsprice:productsprice,
                    discount:discount,ref:ref,advanced:advanced,productsamount:productsamount,
                    transcharge:transcharge,cgstamount:cgstamount,sgstamount:cgstamount,
                    igstamount:igstamount,cessamount:cessamount,submit:submit,type:type},
                success:function(res){
                    $("#loading").hide(function(){
                        alert(res);  
                    });  
                },
                error:function(res){
                    $("#loading").hide(function(){
                        alert(res);  
                    });  
                }
            });
        }
        setTimeout(function(){
            $("#myModal1 input").removeClass("inp");
            $("#myModal1 input").attr("placeholder","");
            $("#submit1").prop("disabled",false);
        },2000);
    }); 
    $(".editpurchases").click(function(e){
        e.preventDefault();
        $("#cid").val($(this).parent("div").find(".cid").val().replace(/\n/g,""));
        $("#invoiceno").val($(this).parent("div").find(".iinvoiceno").html().replace(/\n/g,""));
        $("#name").val($(this).parent("div").find(".iname").html().replace(/\n/g,""));
        $("#itemid").val($(this).parent("div").find(".sid").val().replace(/\n/g,""));
        $("#remonth").val($(this).parent("div").find(".iremonth").html().replace(/\n/g,""));
        $("#requarter").val($(this).parent("div").find(".irequart").html().replace(/\n/g,""));
        $("#gstin").val($(this).parent("div").find(".icgstin").html().replace(/\n/g,""));
        $("#pos").val($(this).parent("div").find(".ipos").html().replace(/\n/g,""));
        $("#ddate").val($(this).parent("div").find(".iddate").html().replace(/\n/g,""));
        $("#invoicedate").val($(this).parent("div").find(".iidate").html().replace(/\n/g,""));
        if($(this).parent("div").find(".iroundoff").html().replace(/\n/g,"")=="true"){
            $("#roundoff").prop('checked',true);
        }
        else{
            $("#roundoff").prop('checked',false);
        }
                
        $("#advanced").val($(this).parent("div").find(".iadvanced").html().replace(/\n/g,""));
        $("#transcharge").val($(this).parent("div").find(".itranscharge").html().replace(/\n/g,""));
        $("#reference").val($(this).parent("div").find(".iref").html().replace(/\n/g,""));
        $("#amount").val($(this).parent("div").find(".itamount").html().replace(/\n/g,""));
        $("#addpurchases").val("update");
        $("#type").val($(this).parent("div").find(".itype").html().replace(/\n/g,""));
        $("#warehouse").val(parseInt($(this).parent("div").find(".iwareid").html().replace(/\n/g,"")));
        $("#amount").val(parseInt($(this).parent("div").find(".itamount").html().replace(/\n/g,"")));
        var products = ($(this).parent("div").find(".iproducts").html().replace(/\n/g,"").split(","));
        var productsid = ($(this).parent("div").find(".iproductsid").html().replace(/\n/g,"").split(","));
        var productscost = ($(this).parent("div").find(".iproductscost").html().replace(/\n/g,"").split(","));
        var productsprice = ($(this).parent("div").find(".iproductsprice").html().replace(/\n/g,"").split(","));
        var discounttype = ($(this).parent("div").find(".idiscounttype").html().replace(/\n/g,"").split(","));
        var qty = ($(this).parent("div").find(".iproductsqty").html().replace(/\n/g,"").split(","));
        var taxrate = ($(this).parent("div").find(".itaxrate").html().replace(/\n/g,"").split(","));
        var cgst = ($(this).parent("div").find(".icgst").html().replace(/\n/g,"").split(","));
        var cgstamount = ($(this).parent("div").find(".icgstamount").html().replace(/\n/g,"").split(","));
        var igst = ($(this).parent("div").find(".iigst").html().replace(/\n/g,"").split(","));
        var igstamount = ($(this).parent("div").find(".iigstamount").html().replace(/\n/g,"").split(","));
        var cess = ($(this).parent("div").find(".icess").html().replace(/\n/g,"").split(","));
        var cessamount = ($(this).parent("div").find(".icessamount").html().replace(/\n/g,"").split(","));
        var productsamount = ($(this).parent("div").find(".iamount").html().replace(/\n/g,"").split(","));
        var discount = (parseInt($(this).parent("div").find(".idiscount").html().replace(/\n/g,"").split(",")));
        var length = products.length;
        var i = 0,k=0;
        $("#myModal1 .table tbody").empty();
        for(k=0;k<length;k++){
            $("#add").trigger("click");
        }
        $(".products").each(function(){            
            $(this).val(products[i]);
            i++;
        });
        i=0;
        $(".pid").each(function(){            
            $(this).val(productsid[i]);
            i++;
        });
        i=0;
        $(".productscost").each(function(){            
            $(this).val(productscost[i]);
            i++;
        });
        i=0;
        $(".productsprice").each(function(){            
            $(this).val(productsprice[i]);
            i++;
        });
        i=0;
        $(".productsamount").each(function(){            
            $(this).val(productsamount[i]);
            i++;
        });
        i=0;
        $(".qty").each(function(){
            $(this).val(qty[i]);
            i++;
        }); 
        i=0;
        $(".taxrate").each(function(){
            $(this).val(taxrate[i]);
            i++;
        }); 
        i=0;
        $(".cgst").each(function(){
            $(this).val(cgst[i]);
            i++;
        }); 
        i=0;
        $(".cgstamount").each(function(){
            $(this).val(cgstamount[i]);
            i++;
        }); 
        i=0;
        $(".igst").each(function(){
            $(this).val(igst[i]);
            i++;
        }); 
        i=0;
        $(".igstamount").each(function(){
            $(this).val(igstamount[i]);
            i++;
        }); 
        i=0;
        $(".cess").each(function(){
            $(this).val(cess[i]);
            i++;
        }); 
        i=0;
        $(".cessamount").each(function(){
            $(this).val(cessamount[i]);
            i++;
        }); 
        i=0;
        $(".discount").each(function(){
            $(this).val(discount[i]);
            i++;
        }); 
        i=0;
        $(".discounttype").each(function(){            
            if(discounttype[i]=="percentage"){
                $(this).val("percentage");
            }
            else{
                $(this).val("rs");
            }
            i++;
        });
        $("#myModal1").modal('show');
    });
    $(".viewtrans").click(function(){
        var parent = $(this).parent();
        var id = parent.find(".sid").val();
        var type= $("#transtype").html();
       $("#loading").show();
        $.ajax({
            type: "GET",
            url: "Transaction",
            data: {id:id,type:type},
            success:function(res){
                $("#loading").hide();
                var resjson = JSON.parse(res);  
                console.log(resjson);
                if(resjson[0]=="error"){
                    $("#itransid").val();
                    $(".table_div.col-12.col-lg-4.col-xl-3 h5").after("<div  class=\"transactionsdiv\">Error in retrieving transactions.</div>");
                }
                else{
                    var amount = "";
                    var date = "";
                    var mode = "";
                    amount = resjson[0];
                    $(".transactionsdiv").remove();
                    if(amount!=""){
                        amount = amount.split(",");
                        date = resjson[1].split(",");
                        mode = resjson[2].split(",");
                        var len = amount.length;
                        console.log(len);
                        for(var i = 0;i<len;i++){                                                
                            $("#itransinvoiceid").before(transdiv);
                            $(".transactionsdiv .no").eq(i).html(i+1+".");
                            $(".itransamount").eq(i).html(amount[i]);
                            $(".itransmode").eq(i).html(mode[i]);
                            $(".itransdate").eq(i).html(date[i]);
                            console.log(i);
                        }
                    }
                    else{
                        $("#itransinvoiceid").before(transdiv);
                        $(".transactionsdiv").html("No transactions recorded for this invoice");
                    }
                }
                $("#itransinvoiceid").html(id);
                $("#cooid").val(parent.find(".cid").val());
                $("#wooid").val(parseInt(parent.find(".iwareid").html().replace(/\n/g,"")));
                $("#itransinvoiceno").html(parseInt(parent.find(".iinvoiceno").html().replace(/\n/g,"")));
                $("#transinvoiceid").val(id);
                $("#transinvoiceno").val(parseInt(parent.find(".iinvoiceno").html().replace(/\n/g,"")));
                $("#transdueamount").val(resjson[3].toString());
                $("#transtotalamount").val(resjson[4].toString());
                $("#idueamount").html(resjson[3].toString());
                $("#itotalamount").html(resjson[4].toString());
            },
            error:function(res){
                $("#loading").hide(function(){
                    alert(res);  
                });  
            }
        });
    });
    $("#edittrans").click(function(){        
        $("#myModal3").modal('show');
        $("#transmodalform div").remove();
        var len = $(".itransamount").length;
        for(var i = 0;i<len;i++){
            $("#addtransrow").trigger("click");
            $(".transamount").eq(i).val(parseInt($(".itransamount").eq(i).html().replace(/\n/g,"")));
            $(".transmode").eq(i).val($(".itransmode").eq(i).html().replace(/\n/g,""));
            $(".transdate").eq(i).val($(".itransdate").eq(i).html().replace(/\n/g,""));
        }
    });
    $("#addtransrow").click(function(){        
        $(this).before("<div>"+transrow+"</div>");
    });
    $("#addtrans").click(function(){
        var transinvoiceid = $("#transinvoiceid").val();
        var dueamount = $("#transdueamount").val();
        var totalamount = $("#transtotalamount").val();
        var wareid = $("#wooid").val();
        var custid = $("#cooid").val();
        var type = $(this).val();
        var amount = "";
        var mode = "";
        var date = "";
        var flag = 0;
        $("#myModal3 input").each(function(){
            if($(this).val() == "" && $(this).parent().css("display")!="none" && $(this).parent().parent().css("display")!="none" && $(this).attr("required")){
                $(this).attr("placeholder","Required Field!!");
                $(this).addClass("inp");
                flag = 1;
            }
        });    
        $(".transamount").each(function(){
            console.log(amount);
           if(amount==""){
               amount = $(this).val();
           } 
           else{
               amount = amount+","+$(this).val();
           }
        });
        $(".transmode").each(function(){
           if(mode==""){
               mode = $(this).val();
           } 
           else{
               mode = mode+","+$(this).val();
           }
        });
        $(".transdate").each(function(){
           if(date==""){
               date = $(this).val();
           } 
           else{
               date = date+","+$(this).val();
           }
        });
        if(flag==0){
            $("#loading").show();
            $.ajax({
                type: "post",
                url: "Transaction",
                data: {transinvoiceid:transinvoiceid,amount:amount,mode:mode,date:date,dueamount:dueamount,wareid:wareid,custid:custid,
                    totalamount:totalamount,type:type},
                success:function(res){
                    $("#loading").hide();
                    alert(res);
                },
                error: function(res){
                    $("#loading").hide();
                    alert(res);
                }
            });
        }
        setTimeout(function(){
            $("#myModal3 input").removeClass("inp");
            $("#myModal3 input").attr("placeholder","");
            $("#submit1").prop("disabled",false);
        },2000);
    });
    $(document).on("click",".deltransrow",function(){
        $(this).parent().remove();
    });
    $(".showuser").click(function(){
       $(this).next().next("div").collapse("toggle");
    });
    $("#main select").focus(function(e){
        e.preventDefault();
        $(this).children("option").css("border","0")
        console.log("asd");
    });
    $(".div_toggle").click(function(){
        var len = $(this).parent("div").find(".collapse").length;
        console.log(len);
        if(len>0){
            $(this).parent("div").find(".itemdiv").removeClass("collapse");
        }
        else{
            $(this).parent("div").find(".itemdiv").addClass("collapse");
        }
    });
    $(document).on("focus",".dropsearch",function(){
        $(this).parents(".form-group").css("overflow","visible");
        $(this).next().css("display","inline-block");
    });
    $(document).on("focusout",".dropsearch",function(){
        $(this).parents(".form-group").css("overflow","hidden");
        $(this).next().css("display","none");
    });
    $(document).on("keyup",".dropsearch",function(){
        var search = $(this).val();
        $(this).next().find(".two").each(function(){
            var result = $(this).html();
            console.log(result);
            console.log(search);
            if(result.includes(search)){
                $(this).parent().css("display","block");
            } 
            else{
                $(this).parent().hide();
            } 
        });
    });
    $(document).on("mouseenter",".filter>span",function(){
       $(this).parent().prev().val($(this).find(".two").html()); 
       $(this).parent().prev().prev().val($(this).find(".one").html().replace(/\n/g,"")); 
    });
    $(".saleinvoice").click(function(){
        var id = $(this).parent().find(".sid").val();
        var myWindow = window.open("invoice.jsp?id="+id, "myWindow", "width=1000, height=1000");
        myWindow.print();
    });
    
});
$(document).on("click",function(event){
    if(!$(event.target).closest(".dropdown").length && !$(event.target).closest(".dropdown-div").length){
        $(".dropdown-div").hide();
    }
 });
$(document).on("click",".dropdown",function(){
    $(".dropdown-div").hide();
    $(this).find(".dropdown-div").show();
});
$(document).on("click",".switch",function(){
    if($(this).find("input").prop("checked") == true){
        
        
        $("h1").css("color","var(--darkText)");
        $("h2").css("color","var(--darkText)");
        $("h3").css("color","var(--darkText)");
        $("h4").css("color","var(--darkText)");
        $("h6").css("color","var(--darkText)");
        $("a").css("color","var(--darkText)");

        $("label").css("color","var(--darkTextSpan)");
        $("input").css("color","var(--darkText)");
        $("p").css("color","var(--darkText)");
        $("nav").css("background-color","var(--lightTextSpan)");
        $("#main").css("background-color","var(--lightTextSpan)");
        $(".table").css("color","var(--darkText)");

        $(".dropdown>span i").css("color","var(--darkTextSpan)");
        $("nav").css("border-bottom","var(--darkBorder)");
        $(".dropdown-div").css("box-shadow","var(--darkShadow)");
        $(".dropdown-div span").css("color","var(--darkTextSpan)");
        $(".dropdown-div").css("border-top","var(--darkBorder)");
        $(".dropdown-div").css("background-color","var(--darkSidebar)");
        $("#sidebar").css("background-color","var(--darkSidebar)");

        $(".active").css("background-color","var(--darkActive)")
        $("#sidebar a").css("color","var(--darkTextSpan)");
        $(".slider.round").css("border","var(--darkBorder)");

        $("#sidebar a").hover(function(){
          $(this).css("background-color","var(--darkActive)");  
        },function(){
            if(!$(this).hasClass("active"))
                $(this).css("background-color","transparent");  

        });



    }
    else{
        $("h1").css("color","var(--lightText)");
        $("h2").css("color","var(--lightText)");
        $("h3").css("color","var(--lightText)");
        $("h4").css("color","var(--lightText)");
        $("h6").css("color","var(--lightText)");
        $("a").css("color","var(--lightText)");

        $("label").css("color","var(--lightText)");
        $("input").css("color","var(--lightText)");
        $("p").css("color","var(--lightText)");
       
        $("nav").css("background-color","#ffffff");
        $(".table").css("color","var(--lightText)");

        $("#main").css("background-color","#ffffff");
        $(".dropdown>span i").css("color","var(--lightTextSpan)");
        $(".dropdown-div span").css("color","var(--lightTextSpan)");
        $(".dropdown-div").css("border-top","var(--lightBorder)");
        $(".slider.round").css("border","var(--lightBorder)");

        $("nav").css("border-bottom","var(--lightBorder)");

        $(".dropdown-div").css("box-shadow","var(--lightShadow)");
        $(".dropdown-div").css("background-color","#ffffff");
        $("#sidebar").css("background-color","var(--lightSidebar)");

        $(".active").css("background-color","var(--lightActive)");
        $("#sidebar a").css("color","var(--lightTextSpan)");
        $("#sidebar a").hover(function(){
          $(this).css("background-color","var(--lightActive)");  
        },function(){
            if(!$(this).hasClass("active"))
                $(this).css("background-color","transparent");  
        
        });

    }
});
