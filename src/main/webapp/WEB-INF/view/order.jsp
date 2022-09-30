<%@page import="solent.oodev.utils.html.INav"%>

<%@include file="default.jsp" %>
<%@include file="header.jsp" %>

<%
            long orderId = (long) request.getAttribute("orderNumber");
            out.print(INav.WELCOME);
            out.print(INav.MAIN);
            out.print("<h2>Your Order Number is: " + orderId + "</h2>");
            out.print(INav.END_MAIN);
            out.print(INav.BODY_END);
%>