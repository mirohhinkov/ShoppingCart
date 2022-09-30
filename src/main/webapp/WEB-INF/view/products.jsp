<%@page import="solent.oodev.utils.html.INav"%>
<%@page import="solent.oodev.utils.html.ISearchBar"%>
<%@page import="solent.oodev.utils.html.IProductHTML"%>
<%@page import="solent.oodev.model.classes.product.Product"%>
<%@page import="solent.oodev.model.classes.Cart"%>
<%@page import="solent.oodev.model.classes.ProductOrder"%>
<%@page import="solent.oodev.utils.AttributeNames"%>
<%@page import="java.util.List"%>

<%@include file="default.jsp" %>
<%@include file="header.jsp" %>
<%
    List<Product> products = (List<Product>) request.getAttribute(AttributeNames.PRODUCTS);
    Cart cart = (Cart) request.getAttribute(AttributeNames.CART);
    String filter = (String) request.getAttribute(AttributeNames.FILTER);
    filter = (filter==null) ? "" : filter;

    out.print(INav.WELCOME_START);
    out.print(ISearchBar.SEARCH_BAR.replace("%1%", filter));
    out.print(INav.WELCOME_END);
    out.print(INav.MAIN);

    for (Product product : products) {
        int availableQty = product.getStock();
        ProductOrder order = cart.getProductOrders().get(product.getProductId());
        if (order != null) availableQty -= order.getQuantity();
        String btnTxt = (availableQty > 0) ? "ADD TO CART" : "OUT OF STOCK";
        String url = (availableQty > 0) ? "/add?productId=" + product.getProductId() + "&filter=" + filter : "#";
        out.print(IProductHTML.CARD
         .replace("%1", product.getProductUrl())
         .replaceAll("%2", product.getDescription())
         .replace("%4", product.getCategoryDescription())
         .replace("%5", url)
         .replace("%6", btnTxt)
        );
    }
    out.print(INav.END_MAIN);
%>
</body>