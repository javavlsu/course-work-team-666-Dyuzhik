<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <meta charset="utf-8">
    <title>Редактирование отзыва</title>
    <link rel="stylesheet" type="text/css" href="../../resources/css/style.css">
    <script src="resources/js/times.js"></script>
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
        <h1 class="form-signin-heading">Редактирование отзыва</h1>
        <form:form method="POST" modelAttribute="feedback">
            <spring:bind path="mark">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:label path="text">Оцените работу барбера по 10-бальной шкале</form:label><br>
                    <form:input type="text" path="mark" class="form-control" value="${feedback.mark}"
                                placeholder="Оценка" field="${mark}"></form:input>
                    <form:errors path="mark"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="text">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="text" class="form-control" value="${service.photo}" placeholder="Что вы можете сказать о барбере?"></form:input>
                    <form:errors path="text"></form:errors>
                </div>
            </spring:bind>

            <form method="post" action="${pageContext.request.contextPath}/feedback/update/${feedback.id}">
                <button class="formcource" type="submit" name="${_csrf.parameterName}" value="${_csrf.token}">Обновить отзыв</button>
            </form>
        </form:form>
        <form method="get" action="${pageContext.request.contextPath}/feedback/index/${feedback.barber.id}">
            <button class="formcource" type="submit">Назад</button>
        </form>

    </main>
</div>
<footer>
    <p>Телефон: +6(666)-666-66-66</p>
    <p>E-mail: thebestbarbershopintheworld@the.best</p>
</footer>
</body>

<script src="resources/js/times.js"></script>

</html>
