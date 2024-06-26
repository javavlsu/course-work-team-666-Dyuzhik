<%@ page  contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <meta charset="utf-8">
    <title>Создание пользователя</title>
    <link rel="stylesheet" type="text/css" href="../../resources/css/style.css">
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
        <form:form method="POST" modelAttribute="user">
            <h3 class="form-signin-heading">Создание аккаунта</h3>
            <spring:bind path="username">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="username" class="form-control" value="${user.username}"
                                placeholder="Логин" field="${username}"></form:input>
                    <form:errors path="username"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="lastname">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="lastname" class="form-control" value="${user.lastname}" placeholder="Фамилия"></form:input>
                    <form:errors path="lastname"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="name">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="name" class="form-control" value="${user.name}" placeholder="Имя"></form:input>
                    <form:errors path="name"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="midname">
                <div class="form-group">
                    <form:input type="text" path="midname" class="form-control" value="${user.midname}" placeholder="Отчество"></form:input>
                    <form:errors path="midname"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="photolink">
                <div class="form-group">
                    <form:input type="text" path="photolink" class="form-control" value="${user.photolink}" placeholder="Фото"></form:input>
                    <form:errors path="photolink"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="password">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="password" class="form-control" value="${user.password}" placeholder="Пароль"></form:input>
                    <form:errors path="password"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="confirmPassword">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="confirmPassword" class="form-control" value="${user.password}"
                                placeholder="Введите пароль ещё раз"></form:input>
                    <form:errors path="confirmPassword"></form:errors>
                </div>
            </spring:bind>

            <form method="post" action="${pageContext.request.contextPath}/create">
                <button class="formcource" type="submit" name="${_csrf.parameterName}" value="${_csrf.token}">Создать</button>
            </form>
        </form:form>
        <form method="get" action="${pageContext.request.contextPath}/index">
            <button class="formcource" type="submit">Назад</button>
        </form>
    </main>
</div>
<footer>
    <p>Телефон: +6(666)-666-66-66</p>
    <p>E-mail: thebestbarbershopintheworld@the.best</p>
</footer>
</body>

</html>

