<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title> Todo Managament Page </title>
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
    <script>
        function filterTodoName() {
            var input, filter, table, tr, td, i, txtValue;
            input = document.getElementById("inputTodoName");
            filter = input.value.toUpperCase();
            table = document.getElementById("myTable");
            tr = table.getElementsByTagName("tr");
            for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[0];
                if (td) {
                    txtValue = td.textContent || td.innerText;
                    if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                }
            }
        }

        function filterTodoStatus() {
            var input, filter, table, tr, td, i, txtValue, sel;
            sel = document.getElementById("inputTodoStatus");
            input = sel.options[sel.selectedIndex];
            filter = input.value.toUpperCase();
            table = document.getElementById("myTable");
            tr = table.getElementsByTagName("tr");
            for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[3];
                if (td) {
                    txtValue = td.textContent || td.innerText;
                    if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                }
            }
        }

        function sortTable(n) {
            var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
            table = document.getElementById("myTable");
            switching = true;
            //Set the sorting direction to ascending:
            dir = "asc";
            /*Make a loop that will continue until
            no switching has been done:*/
            while (switching) {
                //start by saying: no switching is done:
                switching = false;
                rows = table.rows;
                /*Loop through all table rows (except the
                first, which contains table headers):*/
                for (i = 1; i < (rows.length - 1); i++) {
                    //start by saying there should be no switching:
                    shouldSwitch = false;
                    /*Get the two elements you want to compare,
                    one from current row and one from the next:*/
                    x = rows[i].getElementsByTagName("TD")[n];
                    y = rows[i + 1].getElementsByTagName("TD")[n];
                    /*check if the two rows should switch place,
                    based on the direction, asc or desc:*/
                    if (dir == "asc") {
                        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                            //if so, mark as a switch and break the loop:
                            shouldSwitch = true;
                            break;
                        }
                    } else if (dir == "desc") {
                        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                            //if so, mark as a switch and break the loop:
                            shouldSwitch = true;
                            break;
                        }
                    }
                }
                if (shouldSwitch) {
                    /*If a switch has been marked, make the switch
                    and mark that a switch has been done:*/
                    rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                    switching = true;
                    //Each time a switch is done, increase this count by 1:
                    switchcount++;
                } else {
                    /*If no switching has been done AND the direction is "asc",
                    set the direction to "desc" and run the while loop again.*/
                    if (switchcount == 0 && dir == "asc") {
                        dir = "desc";
                        switching = true;
                    }
                }
            }
        }
    </script>
</head>
<body>
<a href="/todo/select/${todo.id}/item/add">Add New Item</a> <br/>
<form class="form-right" action="/logout" method="post">
    <input type="submit" value="Logout"> <input type="hidden"
                                                name="${_csrf.parameterName}" value="${_csrf.token}">
</form>
<form:form method="post" modelAttribute="todo">
    <form:hidden path="id"/>
    Todo Name :
    <form:input path="name"/>
    <form:button name="Submit">Update</form:button>
    <font color="#add8e6">
        <c:if test="${not empty updated}">
            <c:out value="Todo Name Updated!"/>
        </c:if>
    </font>
    <font color="red">
        <c:if test="${not empty message}">
            <c:out value="Name alanı boş olamaz!"/>
        </c:if>
    </font>
</form:form>
<br/>
<c:choose>
    <c:when test="${empty itemList}">
        <font color="red"> <c:out value="Hiç bir item eklenmedi!"/>
        </font>
    </c:when>
    <c:otherwise>
        Filter by Item Name: <input type="text" id="inputTodoName" onkeyup="filterTodoName()" placeholder="Search for Item Name.."
               title="Type in a name">
        Filter by Item Status : <select onchange="filterTodoStatus()" id="inputTodoStatus">
            <option value="" selected>Choose here</option>
            <option value="Completed">Completed</option>
            <option value="Incomplete">Incomplete</option>
        </select>
        <form:form method="post">
            <table id="myTable">
                <thread>
                    <tr style="font-weight: bold" bgcolor="#add8e6">
                        <!--<td onclick="sortTable(0)">Item ID</td> -->
                        <td onclick="sortTable(0)">Item Name</td>
                        <td onclick="sortTable(1)">Item Description</td>
                        <td onclick="sortTable(2)">Item Deadline</td>
                        <td onclick="sortTable(3)">Item Status</td>
                    </tr>
                </thread>
                <c:forEach items="${itemList}" var="item" varStatus="status">
                    <tr bgcolor="${status.index % 2 == 0 ? 'white':'lightgray'}">
                        <!--<td>${item.id}</td>-->
                        <td>${item.name}</td>
                        <td>${item.description}</td>
                        <td>${item.deadline}</td>
                        <td>
                            <c:out value="${item.status==true?'Completed':'Incomplete'}"/>
                        </td>
                        <td><a href="/todo/select/${item.todo.id}/item/delete/${item.id}">Delete</a></td>
                        <td><a href="/todo/select/${item.todo.id}/item/${item.id}">See Details</a></td>
                    </tr>
                </c:forEach>
            </table>
        </form:form>
        <font color="#add8e6">
            <c:if test="${not empty updatedItem}">
               <!-- <c:out value="Item Updated!"/> -->
            </c:if>
        </font>
    </c:otherwise>
</c:choose>

</body>
</html>