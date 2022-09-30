<%@page import="solent.oodev.utils.html.INav"%>

<%@include file="default.jsp" %>
<%@include file="header.jsp" %>

<%
            out.print(INav.WELCOME);
            out.print(INav.MAIN);
            out.print("<p>You can contact the author of the Cart System on: miroh1@gmail.com</p>");
            out.print(INav.END_MAIN);
            out.print(INav.BODY_END);
%>