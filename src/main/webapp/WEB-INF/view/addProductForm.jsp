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
          <label class="form__label" for="category">
            Category ID
          </label>
          <br />
          <form:input
            path="categoryId"
            class="form__input"
            type="number"
            min="1"
            max="6"
            name="category"
            required="true"
          />
        </div>

        <div class="form__group">
          <label class="form__label" for="description">
            Product Description
          </label>
          <br />
          <form:input
            path="description"
            class="form__input"
            type="text"
            name="description"
            required="true"
          />
        </div>

        <div class="form__group">
                  <label class="form__label" for="price">
            Price
          </label>
          <br />
          <form:input
            path="price"
            class="form__input"
            type="number"
            min="0.01"
            step="0.01"
            name="price"
            required="true"
          />
        </div>

        <div class="form__group">
          <label class="form__label" for="stock">
            Stock
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
          <label class="form__label" for="img">
            Product Image
          </label>
          <br />
          <form:input
            path="productUrl"
            class="form__input"
            type="text"
            name="img"
            required="true"
          />
        </div>

        <div class="form__group">
          <input type="submit" class="btn btn--blue" value="Add" />
        </div>
      </form:form>
    </div>
<%
    out.print(INav.END_MAIN);
    out.print(INav.BODY_END);
%>