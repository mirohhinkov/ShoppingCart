<%@page import="solent.oodev.utils.html.INav"%>
<%@page import="solent.oodev.utils.html.ISearchBar"%>
<%@page import="solent.oodev.model.classes.product.Product"%>
<%@page import="java.util.List"%>

<%@include file="default.jsp" %>
<%@include file="header.jsp" %>
<%
    List<Product> products = (List<Product>) request.getAttribute("products");
    String filter = (String) request.getAttribute("filter");
    filter = (filter==null) ? "" : filter;

    out.print(INav.WELCOME_START);
    out.print(ISearchBar.SEARCH_BAR.replace("%1%", filter));
    out.print(INav.WELCOME_END);
    out.print(INav.MAIN);

    for (Product product : products) out.print("<p>" + product + "</p>");
    out.print(INav.END_MAIN);
%>
</body>