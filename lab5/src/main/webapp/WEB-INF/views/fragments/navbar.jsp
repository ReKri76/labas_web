<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
    <div class="container">
        <a class="navbar-brand" href="<c:url value='/'/>">Доска объявлений</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#mainNav" aria-controls="mainNav" aria-expanded="false" aria-label="Меню">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="mainNav">
            <ul class="navbar-nav ms-auto align-items-md-center gap-2">
                <sec:authorize access="isAuthenticated()">
                    <li class="nav-item text-light">
                        <sec:authentication property="name"/>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-outline-light" href="<c:url value='/cabinet'/>">Кабинет</a>
                    </li>
                    <li class="nav-item">
                        <form action="<c:url value='/logout'/>" method="post" class="d-inline">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-danger">Выйти</button>
                        </form>
                    </li>
                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                    <li class="nav-item">
                        <a class="btn btn-outline-light" href="<c:url value='/login'/>">Войти</a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-primary" href="<c:url value='/register'/>">Регистрация</a>
                    </li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>