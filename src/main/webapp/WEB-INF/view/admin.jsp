<%@page import="solent.oodev.utils.html.INav"%>

<%@include file="default.jsp" %>
<%@include file="header.jsp" %>

<%
        out.print(INav.BODY);
        out.print(INav.WELCOME);
        out.print(INav.MAIN);
%>
        <div>
            <h2>Admin Page</h2>
            <a href="add-product"><button>ADD PRODUCT</button></a>
            <a href="update"><button>Change Stock</button></a>
        </div>
<%
        out.print(INav.END_MAIN);
        out.print(INav.BODY_END);
%>