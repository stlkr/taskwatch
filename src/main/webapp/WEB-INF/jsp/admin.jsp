<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<html lang="ru">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="${pageContext.request.getContextPath()}/static/site.js"></script>
    <title>Список пользователей</title>

</head>

<body>
    <jsp:include page="navbar.jsp" />

    <table class="table table-sm">
        <c:if test="${not empty users}">
            <thead>
                <th scope="col">Логин</th>
                <th scope="col">Пароль</th>
                <th scope="col">e-Mail</th>
                <th scope="col">Ативен</th>
                <th scope="col">Доступ</th>
            </thead>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                    <td>${user.email}</td>
                    <td>${user.enabled}</td>
                    <td>${user.userRoles}</td>
                    <td>
                        <a href="${pageContext.request.getContextPath()}/admin/edit?id=${user.id}">Править</a>
                    </td>
                    <td>
                        <c:if test="${user.username != 'admin'}">
                            <a href="${pageContext.request.getContextPath()}/admin/delete?id=${user.id}">Удалить</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty users}">
            Нет зарегистрированных пользователей
        </c:if>
    </table>

</body>

</html>