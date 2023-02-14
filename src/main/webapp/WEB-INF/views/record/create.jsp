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
    <title>Запись на услугу</title>
    <link rel="stylesheet" type="text/css" href="../../../resources/css/style.css">
    <script src="../../../resources/js/times.js"></script>
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
<div class="index">
    <main class="edit">

        <h1 class="form-signin-heading">Оформление записи</h1>
        <form:form method="POST" modelAttribute="record" action="${pageContext.request.contextPath}/record/add/${service_id}/${chDate}?${_csrf.parameterName}=${_csrf.token}">
            <div class="backBrown">
                <h4>Дата: ${chDate}</h4>
                <h4 class="cost">Стоимость: </h4><h4 class="cost" id="costRec">${cost}</h4>
                <br>
                <form:label path="time">Выберите время</form:label>
                <form:select path="time" id="time">
                    <c:forEach items="${times}" var="time" varStatus="loop">
                        <form:option value="${time.time}">
                            ${time.time}
                        </form:option>
                    </c:forEach>
                </form:select>

                <br>
                <div class="timeBarbers">
                    <c:forEach items="${times}" var="timev">
                        <div>
                            <c:forEach items="${timev.barbers}" var="barber">
                                <form:radiobutton onclick="changeCost(${barber.cost})" path="barberID" value="${barber.barber.id}" />
                                <label data-tooltip="${barber.barber.lastname} ${barber.barber.name} ${barber.barber.midname}">${barber.barber.username}</label>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <br>
            <br>
            <input class="formcource" type="submit"/>
        </form:form>
        <%--            </form>--%>
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

<script src="../../../resources/js/times.js"></script>

</html>
