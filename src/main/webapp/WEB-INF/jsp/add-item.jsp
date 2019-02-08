<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title> Add Item Page </title>
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

<form:form method="post" modelAttribute="newItem">
    <table>
        <tr>
            <td>Item Name</td>
            <td>
                <form:input path="name"/>
                <form:errors path="name" cssStyle="color: red"/>
            </td>
        </tr>
        <tr>
            <td>Item Description</td>
            <td>
                <form:input path="description"/>
                <form:errors path="description" cssStyle="color: red"/>
            </td>
        </tr>
        <tr>
            <td>Item Deadline</td>
            <td>
                <form:input type="date" path="deadline"/>
                <form:errors path="deadline" cssStyle="color: red"/>
            </td>
        </tr>
        <tr>
            <td>Item Status</td>
            <td>
                <form:checkbox path="status"/>Complete
                <form:errors path="status" cssStyle="color: red"/>
            </td>
        </tr>
        <tr>
            <td colspan="2"><form:button name="Submit">Add Item</form:button></td>
        </tr>
    </table>
</form:form>

</body>
</html>