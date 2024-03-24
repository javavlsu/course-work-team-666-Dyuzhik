<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <meta charset="utf-8">
    <title>Добавление услуги</title>
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
<div class="index">
    <main class="edit">
        <form:form method="POST" modelAttribute="service">
            <h3 class="form-signin-heading">Добавление услуги</h3>
            <spring:bind path="name">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="name" class="form-control" value="${service.name}"
                                placeholder="Название" field="${name}"></form:input>
                    <form:errors path="name"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="photo">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="photo" class="form-control" value="${service.photo}" placeholder="Фото"></form:input>
                    <form:errors path="photo"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="cost">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="cost" class="form-control" value="${service.cost}" placeholder="Стоимость"></form:input>
                    <form:errors path="cost"></form:errors>
                </div>
            </spring:bind>

            <form method="post" action="${pageContext.request.contextPath}/service/add">
                <button class="formcource" type="submit" name="${_csrf.parameterName}" value="${_csrf.token}">Создать</button>
            </form>
        </form:form>
        <form method="get" action="${pageContext.request.contextPath}/service/admin/0">
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