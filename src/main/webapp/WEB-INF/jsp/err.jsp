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
    <title>Ошибка</title>

</head>
<body>
    <div style="height: 100vh">
        <div class="container  h-100">
            <div class="row h-100">
                <div class="col-sm-12 my-auto">
                    <div class="card card-block w-50 mx-auto">
                        <c:if test="${not empty errCode}">
                            <div class="d-flex justify-content-center">
                                <h1><c:out value="${errCode}" /></h1>
                            </div>
                        </c:if>
                        <c:if test="${not empty errMsg}">
                            <div class="d-flex justify-content-center">
                                <c:out value="${errMsg}" />
                            </div>
                        </c:if>
                        <hr />
                        <div class="d-flex justify-content-center">
                            <a href="${pageContext.request.getContextPath()}/list">На главную</a>
                        </div>
                    </div>
                </div>

            </div>

        </div>
    </div>
</body>
</html>