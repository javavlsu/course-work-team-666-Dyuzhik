<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>

<head>
    <meta charset="utf-8">
    <title>Личный кабинет</title>
    <link rel="stylesheet" type="text/css" href="../../../resources/css/style.css">
</head>

<body>
<header id="black">
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/profile">Личный кабинет</a></li>
            <li><a href="${pageContext.request.contextPath}/service/index/0">Услуги</a></li>
            <li><a href="${pageContext.request.contextPath}/barbers/0">Барберы</a></li>
            <a href="../../../index.jsp"><img src="../../../resources/img/logo.png" width="150" height="50"
                                              alt="barbershop"/></a>
        </ul>
    </nav>
</header>
<div id="container">
    <main id="show">
        <section>
            <div class="show">
                <h2>${user.username}</h2>
                <h4>${user.lastname} ${user.name} ${user.midname}</h4>
                <div class="profile">
                    <form method="get" action="/update/${user.id}">
                        <button class="logout" type="submit" value="update">Обновить данные профиля</button>
                    </form>

                    <form method="get" action="/updateUsername/${user.id}">
                        <button class="logout" type="submit" value="update">Сменить логин</button>
                    </form>

                    <form method="get" action="/updatePassword/${user.id}">
                        <button class="logout" type="submit" value="update">Сменить пароль</button>
                    </form>

                    <c:if test="${user.id != 1}">
                        <form method="post" action="/delete/${user.id}?${_csrf.parameterName}=${_csrf.token}">
                            <button class="logout" type="submit"
                                    onclick="return confirm('Вы действительно хотите удалить профиль?')"
                                    value="Удалить">Удалить профиль
                            </button>
                        </form>
                    </c:if>

                    <form id="logoutForm" method="post" action="${contextPath}/logout">
                        <button class="logout" type="submit" name="${_csrf.parameterName}" value="${_csrf.token}">
                            Выйти
                        </button>
                    </form>

                </div>
            </div>

            <img src="${user.photolink}" alt="Фото пока нет">
            <div class="profile">

                <c:if test="${status == 'admin'}">
                    <form method="get" action="${pageContext.request.contextPath}/index">
                        <button class="logout" type="submit" value="index">Управление пользователями</button>
                    </form>
                    <form method="get" action="${pageContext.request.contextPath}/service/admin/0">
                        <button class="logout" type="submit" value="index">Управление услугами</button>
                    </form>
                </c:if>
            </div>

        </section>
    </main>
</div>
<footer>
    <p>Телефон: +6(666)-666-66-66</p>
    <p>E-mail: thebestbarbershopintheworld@the.best</p>
</footer>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>

</html>