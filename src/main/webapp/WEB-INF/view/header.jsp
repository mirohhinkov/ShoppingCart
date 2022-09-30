<%@ page import="solent.oodev.controller.UserController"%>
<%
    String loginText = (session.getAttribute("loggedUser") == null) ? "Login" : "Logout";
%>

    <c:url var="productsLink" value="${Mappings.PRODUCTS}" />
    <c:url var="cartLink" value="${Mappings.CART}" />
    <c:url var="ordersLink" value="${Mappings.ORDERS}" />
    <c:url var="loginLink" value="${Mappings.LOGIN}" />
    <c:url var="contactLink" value="${Mappings.CONTACT}" />
    <c:url var="aboutLink" value="${Mappings.ABOUT}" />
<header id="main-navigation">
    <nav>
        <a href="${productsLink}">Products</a>
        <a href="${cartLink}">Cart</a>
        <a href="${ordersLink}">Orders</a>
        <a href="${loginLink}"><%= loginText %></a>
        <a href="${contactLink}">Contact</a>
        <a href="${aboutLink}">About</a>
    </nav>
</header>