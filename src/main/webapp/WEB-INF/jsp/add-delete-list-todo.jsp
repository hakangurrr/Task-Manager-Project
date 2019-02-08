<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title> Todo List Page </title>
    <style>
        .form-right {
            display: flex;
            flex-direction: column;
            align-items: flex-end;
        }
        table {
            border-spacing: 0;
            width: 100%;
            border: 1px solid #ddd;
        }

        th {
            cursor: pointer;
        }

        th, td {
            text-align: left;
            padding: 16px;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2
        }
    </style>
</head>
<body>
<form class="form-right" action="/logout" method="post">
    <input type="submit" value="Logout"> <input type="hidden"
                                                name="${_csrf.parameterName}" value="${_csrf.token}">
</form>
    <form:form method="post" modelAttribute="todo" action="/todo/list">
        Todo Name : <form:input path="name" placeholder="Add new to do"/>
    <form:button name="Submit">Add todo</form:button> <br/>
    <font color="red">
        <c:if test="${not empty message}">
            <c:out value="Name alanı boş olamaz!"/>
        </c:if>
    </font>
    </form:form>
<c:choose>
    <c:when test="${empty todoList}">
        <font color="red"> <c:out value="Hiç bir todo eklenmedi!"/>
        </font>
    </c:when>
    <c:otherwise>
        <table>
            <thread>
                <tr style="font-weight: bold" bgcolor="#add8e6">
                    <td>Todo ID</td>
                    <td>Todo Name</td>
                </tr>
            </thread>
            <c:forEach items="${todoList}" var="todo" varStatus="status">
                <tr bgcolor="${status.index % 2 == 0 ? 'white':'lightgray'}">
                    <td>${todo.id}</td>
                    <td>${todo.name}</td>
                    <td><a href="/todo/delete/${todo.id}">Delete</a></td>
                    <td><a href="/todo/select/${todo.id}">See Details</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
</body>
</html>