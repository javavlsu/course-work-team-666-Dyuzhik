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
    <title>Мои записи</title>
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
    <main id="index">
        <c:choose>
            <c:when test="${status == 'barber'}">
                <form method="get" action="${pageContext.request.contextPath}/record/waiting">
                    <button class="detailed"
                            type="submit" >
                        Ожидают подтверждения
                    </button>
                </form>
                <form method="get" action="${pageContext.request.contextPath}/record/archive/ar_barber">
                    <button class="detailed"
                            type="submit" >
                        К архиву
                    </button>
                </form>
            </c:when>
            <c:otherwise>
                <c:if test="${((status != 'ar_client')&&(status != 'ar_barber'))}">
                    <form method="get" action="${pageContext.request.contextPath}/record/archive/ar_client">
                        <button class="detailed"
                                type="submit" >
                            К архиву
                        </button>
                    </form>
                </c:if>
            </c:otherwise>
        </c:choose>


        <c:if test="${records.size() == 0}">
            <h4>Записей пока нет</h4>
        </c:if>
            <c:forEach items="${records}" var="record">
                <div class="service">
                    <img class="servimg" src="${record.service.photo}" alt="Что-то пошло не так">
                    <div class="serdesc">
                        <h3 class="none">${record.service.name}</h3>
                        <h4 class="none">Стоимость: ${record.cost}₽</h4>
                        <c:choose>
                            <c:when test="${(status == 'client')||(status == 'ar_client')}">
                                <h4 class="none" data-tooltip="${record.barber.lastname} ${record.barber.name} ${record.barber.midname}">
                                    Барбер: ${record.barber.username}</h4>
                            </c:when>
                            <c:otherwise>
                                <h4 class="none" data-tooltip="${record.client.lastname} ${record.client.name} ${record.client.midname}">
                                    Клиент: ${record.client.username}</h4>
                            </c:otherwise>
                        </c:choose>

                        <h4 class="none">Дата: ${record.date}</h4>
                        <h4 class="none">Время: ${record.time}</h4>
                        <c:if test="${status != 'wait'}">
                            <c:choose>
                                <c:when test="${(record.status.id == 1)||(record.status.id == 4)}">
                                    <h4>${record.status.name}</h4>
                                </c:when>
                                <c:when test="${(record.status.id == 2)||(record.status.id == 3)}">
                                    <h4 class="code2">${record.status.name}</h4>
                                </c:when>
                                <c:otherwise>
                                    <h4 class="code5">${record.status.name}</h4>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                        <c:choose>
                            <c:when test="${status == 'wait'}">
                                <form method="post" action="/record/sub/${record.id}">
                                    <button class="detailed"
                                            type="submit" name="${_csrf.parameterName}" value="${_csrf.token}">
                                        Принять запись
                                    </button>
                                </form>
                                <form method="post" action="/record/delete/barber/${record.id}">
                                    <button class="detailed"
                                            onclick="return confirm('Вы действительно хотите отклонить запись?')"
                                            type="submit" name="${_csrf.parameterName}" value="${_csrf.token}">
                                        Отклонить запись
                                    </button>
                                </form>
                            </c:when>
                            <c:when test="${!((status == 'ar_client')||(status == 'ar_barber'))}">
                                <c:choose>
                                    <c:when test="${status != 'barber'}">
                                        <c:if test="${record.status.id!= 3}">
                                            <form method="post" action="/record/delete/client/${record.id}">
                                                <button class="detailed"
                                                        onclick="return confirm('Вы действительно хотите отменить запись?')"
                                                        type="submit" name="${_csrf.parameterName}" value="${_csrf.token}">
                                                    Отменить запись
                                                </button>
                                            </form>
                                        </c:if>
                                    </c:when>
                                    <c:otherwise>
                                        <c:if test="${(record.status.id !=3)}">
                                            <form method="post" action="/record/delete/barber/${record.id}">
                                                <button class="detailed"
                                                        onclick="return confirm('Вы действительно хотите отменить запись?')"
                                                        type="submit" name="${_csrf.parameterName}" value="${_csrf.token}">
                                                    Отменить запись
                                                </button>
                                            </form>
                                        </c:if>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
            </c:forEach>
    </main>
</div>
<footer>
    <p>Телефон: +6(666)-666-66-66</p>
    <p>E-mail: thebestbarbershopintheworld@the.best</p>
</footer>
</body>

</html>
