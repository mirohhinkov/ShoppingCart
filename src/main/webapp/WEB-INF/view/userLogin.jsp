<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page import="solent.oodev.utils.html.INav"%>
<%@include file="default.jsp" %>
<%@include file="header.jsp" %>

<%
    out.print(INav.BODY);
    out.print(INav.WELCOME);
    out.print(INav.MAIN);
    String message = (String) request.getAttribute("message");
    if (message == null || message.isEmpty()) message = "Login";
%>
    <div class="login-form">
      <form:form class="form" method="POST" modelAttribute="user">
<%
        out.print("<h3>" + message + "</h3><br />");
%>
        <div class="form__group">
          <label class="form__label" for="email">
            Email address
          </label>
          <br />
          <form:input
            path="userEmail"
            class="form__input"
            placeholder="you@example.com"
            type="email"
            name="email"
            required="true"
          />
        </div>
        <div class="form__group.ma-bt-md">
          <label class="form__label" for="password">
            Password
          </label>
          <br />
          <form:input
            path="password"
            class="form__input"
            type="password"
            name="password"
            required="true"
            minLength="5"
          />
        </div>
        <p>Do not have an account. <a href="/signup">Sign-up</a></p>
        <div class="form__group">
          <input type="submit" class="btn btn--blue" value="Login" />
        </div>
      </form:form>
    </div>
<%
    out.print(INav.END_MAIN);
    out.print(INav.BODY_END);
%>