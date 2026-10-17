<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="_csrf" content="${_csrf.token}">
    <meta name="_csrf_header" content="${_csrf.headerName}">
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

    <section class="mt-5" aria-labelledby="my-notices-heading">
        <div class="d-flex align-items-baseline justify-content-between mb-3">
            <h2 id="my-notices-heading" class="h4 mb-0">Мои объявления</h2>
            <span class="text-body-secondary small">Всего: ${fn:length(notices)}</span>
        </div>

        <c:choose>
            <c:when test="${empty notices}">
                <div class="alert alert-secondary mb-0">У вас пока нет объявлений.</div>
            </c:when>
            <c:otherwise>
                <div class="table-responsive bg-white border rounded shadow-sm">
                    <table class="table table-hover align-middle mb-0">
                        <thead class="table-light">
                        <tr>
                            <th scope="col">Заголовок</th>
                            <th scope="col">Текст</th>
                            <th scope="col">Изображение</th>
                            <th scope="col">Действия</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="notice" items="${notices}">
                            <tr>
                                <td class="fw-medium"><c:out value="${notice.header}"/></td>
                                <td class="text-break"><c:out value="${notice.content}"/></td>
                                <td>
                                    <c:if test="${not empty notice.picture}">
                                        <img src="data:image/*;base64,<c:out value='${notice.picture}'/>"
                                             alt="Изображение объявления" width="64" height="48"
                                             class="rounded object-fit-cover">
                                    </c:if>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-sm btn-outline-danger js-notice-delete"
                                            data-url="<c:url value='/delete-notice/${notice.id}'/>">
                                        Удалить
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>
    </section>

    <sec:authorize access="hasAuthority('ROLE_ADMIN')">
        <section class="mt-5" aria-labelledby="users-heading">
            <div class="d-flex align-items-baseline justify-content-between mb-3">
                <h2 id="users-heading" class="h4 mb-0">Пользователи</h2>
                <span class="text-body-secondary small">Всего: ${fn:length(users)}</span>
            </div>
            <div class="table-responsive bg-white border rounded shadow-sm">
                <table class="table table-hover align-middle mb-0">
                    <thead class="table-light">
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Никнейм</th>
                        <th scope="col">Роль</th>
                        <th scope="col">Действия</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td class="text-body-secondary"><c:out value="${user.id}"/></td>
                            <td class="fw-medium"><c:out value="${user.nickname}"/></td>
                            <td>
                                <c:choose>
                                    <c:when test="${user.role eq 'ROLE_ADMIN'}">
                                        <span class="badge text-bg-danger">Администратор</span>
                                    </c:when>
                                    <c:when test="${user.role eq 'ROLE_MODERATOR'}">
                                        <span class="badge text-bg-warning">Модератор</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge text-bg-secondary"><c:out value="${user.role}"/></span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <div class="d-flex flex-wrap gap-2">
                                    <div class="btn-group btn-group-sm" role="group" aria-label="Изменить роль">
                                        <button type="button" class="btn btn-outline-secondary js-user-action"
                                                data-method="PATCH" data-url="<c:url value='/users/make-user/${user.id}'/>"
                                                title="Назначить роль пользователя">Пользователь</button>
                                        <button type="button" class="btn btn-outline-secondary js-user-action"
                                                data-method="PATCH" data-url="<c:url value='/users/make-moder/${user.id}'/>"
                                                title="Назначить роль модератора">Модератор</button>
                                        <button type="button" class="btn btn-outline-secondary js-user-action"
                                                data-method="PATCH" data-url="<c:url value='/users/make-admin/${user.id}'/>"
                                                title="Назначить роль администратора">Админ</button>
                                    </div>
                                    <button type="button" class="btn btn-sm btn-outline-danger js-user-action"
                                            data-method="DELETE" data-url="<c:url value='/users/delete/${user.id}'/>">
                                        Удалить
                                    </button>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty users}">
                        <tr>
                            <td colspan="4" class="py-4 text-center text-body-secondary">Пользователей пока нет</td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </section>
    </sec:authorize>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    const csrfToken = document.querySelector('meta[name="_csrf"]').content;
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;

    document.querySelectorAll('.js-user-action').forEach(button => {
        button.addEventListener('click', async () => {
            if (button.dataset.method === 'DELETE' && !confirm('Удалить пользователя?')) return;

            button.disabled = true;
            try {
                const response = await fetch(button.dataset.url, {
                    method: button.dataset.method,
                    headers: {[csrfHeader]: csrfToken}
                });
                if (response.ok) {
                    window.location.reload();
                    return;
                }
                alert('Не удалось выполнить действие (HTTP ' + response.status + ')');
            } catch (error) {
                alert('Ошибка сети');
            }
            button.disabled = false;
        });
    });

    document.querySelectorAll('.js-notice-delete').forEach(button => {
        button.addEventListener('click', async () => {
            if (!confirm('Удалить объявление?')) return;

            button.disabled = true;
            try {
                const response = await fetch(button.dataset.url, {
                    method: 'DELETE',
                    headers: {[csrfHeader]: csrfToken}
                });
                if (response.ok) {
                    window.location.reload();
                    return;
                }
                alert('Не удалось удалить объявление (HTTP ' + response.status + ')');
            } catch (error) {
                alert('Ошибка сети');
            }
            button.disabled = false;
        });
    });
</script>
</body>
</html>
