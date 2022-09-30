<%@page import="solent.oodev.utils.html.INav"%>
<%@page import="solent.oodev.utils.html.ICart"%>
<%@page import="solent.oodev.model.classes.product.Product"%>
<%@page import="solent.oodev.model.classes.Cart"%>
<%@page import="solent.oodev.model.classes.user.User"%>
<%@page import="solent.oodev.model.classes.ProductOrder"%>
<%@page import="solent.oodev.utils.AttributeNames"%>
<%@page import="java.util.List"%>

<%@include file="default.jsp" %>
<%@include file="header.jsp" %>

<%
    Cart cart = (Cart) request.getAttribute(AttributeNames.CART);
    double cartPrice = (double) request.getAttribute(AttributeNames.CART_PRICE);
    out.print(INav.WELCOME);
    out.print(INav.MAIN);
    out.print(ICart.TITLE);
    out.print(ICart.HEAD);
    for (ProductOrder order : cart.getProductOrders().values()) {
         out.print(ICart.ROW
             .replace("%1%", order.getProduct().getDescription())
             .replace("%2%", order.getProduct().getCategoryDescription())
             .replace("%3%", "" + order.getQuantity())
             .replace("%4%", "" + order.getProduct().getPrice())
             .replace("#1", "cart-action?id=" + order.getProduct().getProductId() + "&action=inc")
             .replace("#2", "cart-action?id=" + order.getProduct().getProductId() + "&action=dec")
             .replace("#3", "cart-action?id=" + order.getProduct().getProductId() + "&action=del"));
    }
    out.print(ICart.PRICE_ROW.replace("%1", String.format( "%.2f", cartPrice)));
    out.print(ICart.END_TABLE);
    boolean isUserLog = session.getAttribute("loggedUser") != null;
    User user = (isUserLog) ? (User) session.getAttribute("loggedUser") : null;
    String buttonTxt = (!isUserLog || user.getBalance() >= cartPrice) ? ICart.BUY_LINK : ICart.BUY_TOPUP;
    out.print(ICart.BUY.replace("%1%", buttonTxt));
    out.print(INav.END_MAIN);
    out.print(INav.BODY_END);
%>