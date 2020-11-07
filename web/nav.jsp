<nav>
    <h1>PersAcc</h1>
    <label class="switch">
        <input type="checkbox">
        <span class="slider round"></span>
    </label>
    <% if(request.getSession(false)!=null && session.getAttribute("email") != null){%>
        <span><i class="fas fa-list"></i></span>
        <div class="dropdown">
            <span>
                <i class="fas fa-user"></i>
            </span>
            <div class="dropdown-div">
                <span class="title">User</span>
                <p><% out.println(session.getAttribute("email")); %></p>
                <span class="title">Last Login</span>
                <p><% out.println(session.getAttribute("llogin")); %></p>
                <a href="Logout">Logout</a>
            </div>
        </div>
        <% if(session.getAttribute("type").equals("multi")){%>            
        <div id="optsel">
            <%  String[] gstin = session.getAttribute("gstin").toString().split(",");%>
            <h4>
                <% 
                    if(session.getAttribute("cgstin")==null){
                        out.println(gstin[0]);
                    }
                    else{
                        out.println(session.getAttribute("cgstin"));
                    }
                %>

            </h4>
            <div>
                <% for(int i=0;i<gstin.length;i++){%>
                    <a href="#" class="dropdown-item"><%out.println(gstin[i]);%></a>
                <%}%>
            </div>
        </div>

    <%}}%>
</nav>