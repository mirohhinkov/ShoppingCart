<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page import="solent.oodev.utils.html.INav"%>
<%@include file="default.jsp" %>
<%@include file="header.jsp" %>

<%
    out.print(INav.BODY);
    out.print(INav.WELCOME);
    out.print(INav.MAIN);
%>
    <div class="login-form">
      <form:form class="form" method="POST" modelAttribute="product">
        <h3>Add Product Form</h3><br />

        <div class="form__group">
          <label class="form__label" for="productId">
            Product ID
          </label>
          <br />
          <form:input
            path="productId"
            class="form__input"
            type="number"
            name="productId"
            required="true"
          />
        </div>

        <div class="form__group">
          <label class="form__label" for="stock">
            New Stock
          </label>
          <br />
          <form:input
            path="stock"
            class="form__input"
            type="number"
            min="0"
            name="stock"
            required="true"
          />
        </div>

        <div class="form__group">
          <input type="submit" class="btn btn--blue" value="Update" />
        </div>
      </form:form>
    </div>
<%
    out.print(INav.END_MAIN);
    out.print(INav.BODY_END);
%>