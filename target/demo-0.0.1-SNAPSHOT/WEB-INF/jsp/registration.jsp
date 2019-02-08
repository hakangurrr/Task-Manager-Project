<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Page</title>
    <style>
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
<form:form method="POST" modelAttribute="user">
    <table>
        <caption style="font-size: large; font-weight: bold; font-family:'Times New Roman'">
            REGISTRATION FORM
        </caption>
        <tr>
            <td>First Name</td>
            <td>
                <form:input path="firstName"/>
            </td>
            <td>
                <form:errors path="firstName" cssStyle="color: red"/>
            </td>
        </tr>
        <tr>
            <td>Last Name</td>
            <td>
                <form:input path="lastName"/>
            </td>
            <td>
                <form:errors path="lastName" cssStyle="color: red"/>
            </td>
        </tr>
        <tr>
            <td>User Name</td>
            <td>
                <form:input path="username"/>
            </td>
            <td>
                <form:errors path="username" cssStyle="color: red"/>
            </td>
        </tr>
        <tr>
            <td>Pass Word</td>
            <td>
                <form:password path="password"/>
            </td>
            <td>
                <form:errors path="password" cssStyle="color: red"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <form:button name="Submit"> Register User</form:button>
            </td>
        </tr>
    </table>
</form:form>
<h4>
    <a href="/login">Goto login page!</a>
</h4>
</body>
</html>