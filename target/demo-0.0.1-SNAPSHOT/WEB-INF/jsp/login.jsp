<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
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

<font color="#9acd32">
    <c:if test="${not empty param.logout}">
        <c:out value="  You have been logged out."/>
    </c:if>
</font>

<form:form modelAttribute="user" method="post" action="/login">
    <table>
        <caption style="font-size: large; font-weight: bold; font-family:'Times New Roman'">
            LOGIN FORM
        </caption>
        <tr>
            <td>User Name</td>
            <td>
                <form:input path="username"/>
                <form:errors path="username" cssStyle="color: red"/>
            </td>
        </tr>
        <tr>
            <td>Pass Word</td>
            <td>
                <form:password path="password"/>
                <form:errors path="password" cssStyle="color: red"/>
            </td>
        </tr>
        <tr>
            <td>Remember Me</td>
            <td>
                <input type="checkbox" name="remember-me"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <form:button name="Submit"> Login</form:button>
            </td>
        </tr>
    </table>
</form:form>
<font color="red">
    <c:if test="${not empty param.loginFailed}">
        <c:out value="Login Failed, Incorrect Username or Password"/>
    </c:if>
</font>
<h4>
    <a href="/registration">Goto registration page!</a>
</h4>
</body>
</html>