<%@page import="solent.oodev.utils.html.INav"%>

<%@include file="default.jsp" %>
<%@include file="header.jsp" %>

<%
            out.print(INav.WELCOME);
            out.print(INav.MAIN);
            out.print("<p>The project is built as a part of Solent University QHO543 course</p>");
            out.print(INav.END_MAIN);
            out.print(INav.BODY_END);
%>