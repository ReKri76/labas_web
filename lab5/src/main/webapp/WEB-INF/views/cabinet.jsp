<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Личный кабинет</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<jsp:include page="/WEB-INF/views/fragments/navbar.jsp"/>

<main class="container">
    <h1 class="h3 mb-4">Личный кабинет</h1>

    <div class="row g-4">
        <div class="col-md-4">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h5 class="card-title">Профиль</h5>
                    <p class="mb-1"><strong>Никнейм:</strong> <sec:authentication property="name"/></p>
                    <p class="mb-0"><strong>Роль:</strong>
                        <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                            <span class="badge text-bg-danger">Администратор</span>
                        </sec:authorize>
                        <sec:authorize access="hasAuthority('ROLE_MODERATOR')">
                            <span class="badge text-bg-warning">Модератор</span>
                        </sec:authorize>
                        <sec:authorize access="hasAuthority('ROLE_USER')">
                            <span class="badge text-bg-secondary">Пользователь</span>
                        </sec:authorize>
                    </p>
                </div>
            </div>
        </div>

        <div class="col-md-8">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h5 class="card-title">Новое объявление</h5>
                    <form action="<c:url value='/create-notice'/>" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <div class="mb-3">
                            <label for="header" class="form-label">Заголовок</label>
                            <input type="text" class="form-control" id="header" name="header" required maxlength="255">
                        </div>
                        <div class="mb-3">
                            <label for="content" class="form-label">Текст</label>
                            <textarea class="form-control" id="content" name="content" rows="4" required></textarea>
                        </div>
                        <div class="mb-3">
                            <label for="image" class="form-label">Изображение (необязательно)</label>
                            <input type="file" class="form-control" id="image" name="image" accept="image/*">
                        </div>
                        <button type="submit" class="btn btn-primary">Опубликовать</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
