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
    <title>Услуги</title>
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
    <main>

        <form action="${pageContext.request.contextPath}/service/find/" path="name">
            <input type="text" placeholder="Search" name="services" class="search">
        </form>

        <c:forEach items="${service}" var="servicelist">
            <div class="service">
                <img class="servimg" src="${servicelist.photo}" alt="Что-то пошло не так">
                <div class="serdesc">
                    <h3>${servicelist.name}</h3>
                    <h4>${servicelist.cost}₽</h4>
                    <div id="management">
                        <form method="get" action="/service/${servicelist.id}">
                            <button class="detailed" type="submit" value="Запись">Запись</button>
                        </form>
                    </div>
                    <c:if test="${status == 'admin'}">
                        <form method="post" action="/service/delete/${servicelist.id}">
                            <button class="detailed" type="submit" name="${_csrf.parameterName}" value="${_csrf.token}">
                                Удалить услугу
                            </button>
                        </form>
                    </c:if>
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