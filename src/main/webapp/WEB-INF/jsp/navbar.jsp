<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<nav class="navbar navbar-light navbar-expand-lg bg-light">
    <a class="navbar-brand" href="${pageContext.request.getContextPath()}/list">TaskWatch</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav nav-pills grey mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.getContextPath()}/list">Список<span class="sr-only">(current)</span></a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.getContextPath()}/edit/new">Добавить<span class="sr-only">(current)</span></a>
            </li>

            <c:if test="${admin == 1}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.getContextPath()}/admin">Пользователи<span class="sr-only">(current)</span></a>
                </li>
            </c:if>

        </ul>
        <div class="nav-item">
            <form class="form-inline my-2 my-sm-2" method="GET" action="${pageContext.request.getContextPath()}/list">
                <div class="input-group">
                    <input class="form-control mr-2 mr-sm-2" type="text" name="query" placeholder="Search" aria-label="Search" value="${query}">
                    <button class="btn btn-outline-success my-0 my-sm-0" type="submit">Поиск</button>
                </div>
            </form>
        </div>
        <div class="nav-item">
            
            <form class="form-inline my-2 my-sm-2" method="GET" action="${pageContext.request.getContextPath()}/logout">
                <div class="input-group">
                    <div class="navbar-text border border-secondary border-top-0 border-bottom-0 border-right-0 h-100 mx-sm-0 mx-md-0 mx-lg-2 mx-0 pl-2">
                        ${session_user}
                    </div>
                    <button class="btn btn-outline-danger my-0 my-sm-0 mx-sm-2 mx-2 mx-lg-0" type="submit">Выход</button>
                </div>
            </form>
        </div>
    </div>
</nav>