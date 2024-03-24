<%@ page  contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>

<head>
    <meta charset="utf-8">
    <title>Редактирование профиля</title>
    <link rel="stylesheet" type="text/css" href="../../resources/css/style.css">
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>

<body>
<header id="black">
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/profile">Личный кабинет</a></li>
            <li><a href="${pageContext.request.contextPath}/service/index/0">Услуги</a></li>
            <li><a href="${pageContext.request.contextPath}/barbers/0">Барберы</a></li>
            <a href="../../index.jsp"><img src="../../resources/img/logo.png" width="150" height="50"
                                              alt="barbershop"/></a>
        </ul>
    </nav>
</header>
<div id="container">
    <main class="edit">
        <section class="help">
            <form:form method="POST" modelAttribute="user" action="/updateUsername/${user.id}?${_csrf.parameterName}=${_csrf.token}">
                <h3 class="form-signin-heading">Сменить логин</h3>

                <spring:bind path="username">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="username" class="form-control" value="${user.username}"
                                    placeholder="Логин" field="${username}"></form:input>
                        <form:errors path="username"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="password">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:password path="password" class="form-control" placeholder="Пароль"></form:password>
                        <form:errors path="password"></form:errors>
                    </div>
                </spring:bind>

                <form method="post" action="/updateUsername/${user.id}">
                    <button class="formcource" type="submit" value="edit">Обновить</button>
                </form>
            </form:form>
        </section>

    <a class="formcource" href="${pageContext.request.contextPath}/profile">Вернуться к профилю</a>
    </main>
</div>

<footer>
    <p>Телефон: +6(666)-666-66-66</p>
    <p>E-mail: thebestbarbershopintheworld@the.best</p>
</footer>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>

</html>
