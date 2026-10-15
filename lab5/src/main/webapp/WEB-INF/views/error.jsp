<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Ошибка <c:out value="${status}"/></title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<jsp:include page="/WEB-INF/views/fragments/navbar.jsp"/>

<main class="container">
    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">
            <div class="card shadow-sm text-center">
                <div class="card-body p-5">

                    <c:choose>
                        <c:when test="${status == 403}">
                            <c:set var="title" value="Доступ запрещён"/>
                            <c:set var="hint" value="У вас нет прав для этого действия. Войдите под другой учётной записью или вернитесь на главную."/>
                            <c:set var="color" value="warning"/>
                        </c:when>
                        <c:when test="${status == 404}">
                            <c:set var="title" value="Страница не найдена"/>
                            <c:set var="hint" value="Такой страницы нет или она была удалена."/>
                            <c:set var="color" value="info"/>
                        </c:when>
                        <c:when test="${status == 400}">
                            <c:set var="title" value="Некорректный запрос"/>
                            <c:set var="hint" value="Сервер не смог разобрать отправленные данные. Проверьте форму и попробуйте снова."/>
                            <c:set var="color" value="warning"/>
                        </c:when>
                        <c:when test="${status == 405}">
                            <c:set var="title" value="Метод не поддерживается"/>
                            <c:set var="hint" value="Для этого адреса такой тип запроса не разрешён."/>
                            <c:set var="color" value="warning"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="title" value="Что-то пошло не так"/>
                            <c:set var="hint" value="На сервере произошла ошибка. Попробуйте повторить действие позже."/>
                            <c:set var="color" value="danger"/>
                        </c:otherwise>
                    </c:choose>

                    <div class="display-1 fw-bold text-${color}"><c:out value="${status}"/></div>
                    <h1 class="h3 mt-2"><c:out value="${title}"/></h1>
                    <p class="text-muted mt-3"><c:out value="${hint}"/></p>

                    <c:if test="${not empty message}">
                        <div class="alert alert-${color} text-start mt-4 mb-0" role="alert">
                            <div class="small text-uppercase fw-semibold mb-1"><c:out value="${error}"/></div>
                            <c:out value="${message}"/>
                        </div>
                    </c:if>

                    <div class="d-flex justify-content-center gap-2 mt-4">
                        <a href="<c:url value='/'/>" class="btn btn-primary">На главную</a>
                        <button type="button" class="btn btn-outline-secondary" onclick="history.back()">Назад</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>