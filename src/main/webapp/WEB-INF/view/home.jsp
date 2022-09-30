<%@page import="solent.oodev.utils.html.INav"%>

<%@include file="default.jsp" %>
<%@include file="header.jsp" %>

<%
            out.print(INav.WELCOME);
            out.print(INav.MAIN);
            out.print("<h2>Shopping Cart System</h2>");
            out.print(INav.END_MAIN);
            out.print(INav.BODY_END);
%>