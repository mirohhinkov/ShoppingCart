<%@page import="solent.oodev.utils.html.INav"%>
<%@page import="solent.oodev.model.classes.OrderDetails"%>
<%@page import="java.util.List"%>

<%@include file="default.jsp" %>
<%@include file="header.jsp" %>

<%
            List<OrderDetails> orders = (List<OrderDetails>) request.getAttribute("orders");

            out.print(INav.WELCOME);
            out.print(INav.MAIN);
            for (OrderDetails ord : orders) {
                out.print("Order ID: " + ord.getOrderId() + ", Date: " + ord.getOrderDate() +
                    ", Product: " + ord.getProdDescription() + ", Quantity: " + ord.getQuantity() + "</br>");
            }

            out.print(INav.END_MAIN);
            out.print(INav.BODY_END);
%>