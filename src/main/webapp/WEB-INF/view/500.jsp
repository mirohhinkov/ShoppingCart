<%@page import="solent.oodev.utils.html.INav"%>

<%@include file="default.jsp" %>
<%@include file="header.jsp" %>
<%
        out.print(INav.BODY);
        out.print(INav.WELCOME);
        out.print(INav.MAIN);
        String errorMessage = (String) request.getAttribute("errorMessage");
%>
        <div>
            <h1 class="red_txt">401</h1>
            <h2 class="red_txt">Unauthorized - <%= errorMessage%></h2>
        </div>
<%
        out.print(INav.END_MAIN);
        out.print(INav.BODY_END);
%>