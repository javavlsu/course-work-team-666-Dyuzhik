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
    <title>Наши барберы</title>
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

        <c:forEach items="${barbers}" var="barberlist">
            <div class="service">
                <img class="servimg" src="${barberlist.photolink}"  alt="Нет фото">
                <div class="serdesc">
                    <h3>${barberlist.username}</h3>
                    <h4>${barberlist.lastname} ${barberlist.name} ${barberlist.midname}</h4>
                    <c:forEach items="${barberlist.roles}" var="role">
                        <c:if test="${role.id == 2}">
                            <h4>Барбер</h4>
                        </c:if>
                        <c:if test="${role.id == 4}">
                            <h4>Про-барбер</h4>
                        </c:if>
                    </c:forEach>
                    <div id="management">
                        <form method="get" action="/feedback/add/${barberlist.id}">
                            <button class="detailed" type="submit" value="Оставить отзыв">Оставить отзыв</button>
                        </form>
                        <form method="get" action="/feedback/index/${barberlist.id}">
                            <button class="detailed" type="submit" value="Посмотреть отзывы">Посмотреть отзывы</button>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
        <div class="pages">
            <section class="page">
                <c:if test="${num>0}">
                    <form method="get" action="/service/index/${num-1}">
                        <button class="previous" type="submit" value="Подробнее">${num}</button>
                    </form>
                </c:if>
            </section>
            <section class="page">
                <span>Страница ${num+1}.</span>
            </section>
            <section class="page">
                <c:if test="${end != 'true'}">
                    <form method="get" action="/service/index/${num+1}">
                        <button class="next" type="submit" value="Подробнее">${num +2}</button>
                    </form>
                </c:if>
            </section>
        </div>
    </main>
</div>
<footer>
    <p>Телефон: +6(666)-666-66-66</p>
    <p>E-mail: thebestbarbershopintheworld@the.best</p>
</footer>
</body>

</html>
