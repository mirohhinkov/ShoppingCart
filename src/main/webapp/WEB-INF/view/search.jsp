<%--
    Author     : Miroslav Hinkov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String filter = request.getParameter("songsSearch");
    response.sendRedirect("/filtered?filter=" + filter);
%>
