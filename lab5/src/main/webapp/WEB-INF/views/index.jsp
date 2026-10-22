<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="_csrf" content="${_csrf.token}">
    <meta name="_csrf_header" content="${_csrf.headerName}">
    <title>Главная</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<jsp:include page="/WEB-INF/views/fragments/navbar.jsp"/>

<main class="container">
    <section class="mb-5" aria-labelledby="news-heading">
        <h1 id="news-heading" class="h3 mb-4">Новости</h1>
        <c:if test="${empty news}">
            <div class="alert alert-secondary">Новостей пока нет.</div>
        </c:if>
        <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
            <c:forEach var="item" items="${news}">
                <div class="col">
                    <article class="card h-100 shadow-sm">
                        <div class="card-body">
                            <h2 class="h5 card-title"><c:out value="${item.head}"/></h2>
                            <p class="card-text"><c:out value="${item.content}"/></p>
                        </div>
                        <footer class="card-footer d-flex justify-content-between">
                            <small class="text-muted">Автор: <c:out value="${item.author}"/></small>
                            <small class="text-muted"><c:out value="${item.createdAt}"/></small>
                        </footer>
                    </article>
                </div>
            </c:forEach>
        </div>
    </section>

    <div class="d-flex flex-wrap justify-content-between align-items-center mb-4 gap-2">
        <h1 class="h3 mb-0">Объявления</h1>
        <div class="text-muted">
            <span class="badge text-bg-secondary">Время сервера: <c:out value="${time}"/></span>
            <span class="badge text-bg-info">Посещений: <c:out value="${countOfView}"/></span>
        </div>
    </div>

    <c:if test="${empty texts}">
        <div class="alert alert-secondary">Объявлений пока нет.</div>
    </c:if>

    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
        <c:forEach var="n" items="${texts}">
            <div class="col">
                <div class="card h-100 shadow-sm">
                    <c:if test="${not empty n.picture}">
                        <img src="data:image/*;base64,${n.picture}" class="card-img-top" style="max-height: 220px; object-fit: cover;" alt="Изображение">
                    </c:if>
                    <div class="card-body">
                        <h5 class="card-title"><c:out value="${n.header}"/></h5>
                        <p class="card-text"><c:out value="${n.content}"/></p>
                    </div>
                    <div class="card-footer d-flex justify-content-between align-items-center">
                        <small class="text-muted">Автор: <c:out value="${n.author}"/></small>
                        <sec:authorize access="hasAnyAuthority('ROLE_ADMIN','ROLE_MODERATOR')">
                            <button type="button" class="btn btn-sm btn-outline-danger js-delete"
                                    data-url="<c:url value='/delete-notice/${n.id}'/>">Удалить</button>
                        </sec:authorize>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    const csrfToken = document.querySelector('meta[name="_csrf"]').content;
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;

    document.querySelectorAll('.js-delete').forEach(btn => {
        btn.addEventListener('click', async () => {
            if (!confirm('Удалить объявление?')) return;
            try {
                const res = await fetch(btn.dataset.url, {
                    method: 'DELETE',
                    headers: {[csrfHeader]: csrfToken}
                });
                if (res.ok) {
                    btn.closest('.col').remove();
                } else {
                    alert('Не удалось удалить (HTTP ' + res.status + ')');
                }
            } catch (e) {
                alert('Ошибка сети');
            }
        });
    });
</script>
</body>
</html>
