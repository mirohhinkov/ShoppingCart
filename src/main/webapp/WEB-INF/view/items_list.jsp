<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="java.util.ArrayList"%>
<%@page import="solent.oodev.model.TodoItem"%>
<%@page import="solent.oodev.model.TodoData"%>
<%@ page import="solent.oodev.utils.Mappings"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Todo Items</title>
</head>
<body>
    <div align="center">
        <c:url var="addItem" value="${Mappings.ADD_ITEM}" />
        <a href="addItem" >Add Item</a>
        <table border="1" cellpadding="5">
            <caption>
                <h2>Todo Items</h2>
            </caption>
            <tr>
                <th> Delete </th>
                <th>   Title    </th>
                <th>   Deadline </th>
                <th> Delete </th>
            </tr>
<!--
            <%
                TodoData todoData = (TodoData) request.getAttribute("todoData");
                for (TodoItem item : todoData.getItems()) {
                    out.print("<tr><td>" + item.getTitle() + " </td><td>" + item.getDeadline() + "</td></tr>");
                    }
            %>
-->
        <c:forEach var="item" items="${todoData.items}">
            <c:url var="editItem" value="${Mappings.ADD_ITEM}">
                <c:param name="id" value="${item.id}" />
            </c:url>
            <c:url var="deleteItem" value="${Mappings.DELETE_ITEM}">
                <c:param name="id" value="${item.id}" />
            </c:url>
            <tr>
                <td><a href="${editItem}">Edit</a></td>
                <td><c:out value="${item.title}" />
                <td><c:out value="${item.deadline}" />
                <td><a href="${deleteItem}">Delete</a></td>
            </tr>
        </c:forEach>

        </table>

    </div>

</body>
</html>