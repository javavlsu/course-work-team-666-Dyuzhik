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
    <title>Отзывы</title>
    <link rel="stylesheet" type="text/css" href="../../../resources/css/style.css">

</head>

<body>
<header id="black">
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/profile">Личный кабинет</a></li>
            <li><a href="${pageContext.request.contextPath}/service/index/0">Услуги</a></li>
            <li><a href="${pageContext.request.contextPath}/barbers/0">Барберы</a></li>
            <a href="index.jsp"><img src="../../../resources/img/logo.png" width="150" height="50"
                                     alt="barbershop"/></a>
        </ul>
    </nav>
</header>
<div class="index">
    <main id="index">
        <h1>Отзывы о барбере ${feedbacks[0].barber.username}</h1>
        <c:if test="${feedbacks.size() == 0}">
            <h3>Отзывов пока нет</h3>
        </c:if>
        <c:forEach items="${feedbacks}" var="feedback">
            <div class="feed">
                <h3>Оценка:</h3>
                <c:choose>

                    <c:when test="${feedback.mark>=8}">
                        <h3 class="code2">${feedback.mark}</h3>
                    </c:when>
                    <c:when test="${feedback.mark<=3}">
                        <h3 class="code5">${feedback.mark}</h3>
                    </c:when>
                    <c:otherwise>
                        <h3 class="feed_orange">${feedback.mark}</h3>
                    </c:otherwise>
                </c:choose>

                <h4 data-tooltip="${feedback.client.lastname} ${feedback.client.name} ${feedback.client.midname}">От ${feedback.client.username}</h4>

                <h4>${feedback.text}</h4>
                <c:if test="${feedback.client.id == client_id}">
                    <form method="get" action="/feedback/update/${feedback.id}">
                        <button class="detailed"
                                type="submit" name="${_csrf.parameterName}" value="${_csrf.token}">
                            Редактировать отзыв
                        </button>
                    </form>
                    <form method="post" action="/feedback/delete/${feedback.id}">
                        <button class="detailed"
                                onclick="return confirm('Вы действительно хотите удалить отзыв?')"
                                type="submit" name="${_csrf.parameterName}" value="${_csrf.token}">
                            Удалить отзыв
                        </button>
                    </form>
                </c:if>
            </div>
        </c:forEach>
        <form method="get" action="${pageContext.request.contextPath}/barbers/0">
            <button class="formcource" type="submit" >Назад</button>
        </form>
    </main>
</div>
<footer>
    <p>Телефон: +6(666)-666-66-66</p>
    <p>E-mail: thebestbarbershopintheworld@the.best</p>
</footer>
</body>

</html>
