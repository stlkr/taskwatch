<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html lang="en">

<head>
    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Правка задачи ${name}</title>


    <!-- Bootstrap 4 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    <link id="bsdp-css" href="https://unpkg.com/bootstrap-datepicker@1.9.0/dist/css/bootstrap-datepicker3.min.css" rel="stylesheet">
    <script src="https://unpkg.com/bootstrap-datepicker@1.9.0/dist/js/bootstrap-datepicker.min.js"></script>
    <script src="https://unpkg.com/bootstrap-datepicker@1.9.0/dist/locales/bootstrap-datepicker.ru.min.js" charset="UTF-8"></script>

    <script>
        $(function () {
            $("#datepicker").datepicker();
        });
    </script>
</head>

<body>
    <jsp:include page="navbar.jsp" />
    <div class="container">
        <c:if test="${not empty name}">
            <form action="/edit/update" method="post" class="form-group">
        </c:if>
        <c:if test="${empty name}">
            <form action="/edit/add" method="post" class="form-group">
        </c:if>

        <input type="hidden" name="id" value="${id}" />

        <div class="form-group">
            <label for="name">Имя:</label>
            <input type="text" id="name" class="form-control" value="${name}" name="name" />
        </div>
        <div class="form-group">
            <label for="descr">Описание:</label>
            <textarea id="descr" class="form-control" name="descr">${descr}</textarea>
        </div>


        <div class="form-group">
            <label for="datepicker">Дата окончания:</label>
            <input type="text" class="form-control" name="selDate" id="datepicker" value="${selDate}" />
        </div>
        <c:if test="${not empty name}">
            <input type="submit" class="btn btn-primary form-control" value="Применить" />
        </c:if>

        <c:if test="${empty name}">
            <input type="submit" class="btn btn-primary form-control" value="Добавить" />
        </c:if>
        </form>
    </div>
</body>

</html>