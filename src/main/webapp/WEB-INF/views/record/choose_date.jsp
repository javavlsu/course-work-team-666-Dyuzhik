<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
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
        <h1>Выберите дату</h1>
        <form method="get"
              action="${pageContext.request.contextPath}/record/add/${service_id}?${_csrf.parameterName}=${_csrf.token}">

            <%--        <form:form method="POST" modelAttribute="dates"--%>
            <%--                   action="/record/add/${service}?${_csrf.parameterName}=${_csrf.token}">--%>
            <select name="chDate" class="select">
                <%--                <form:select path="dates" items="${dates.dates.}">--%>
                <%--                </form:select>--%>
                <c:forEach items="${dates}" var="date" varStatus="loop">
                            <option value="${formatter.format(date)}">
                                    ${formatter.format(date)}
                            </option>
                </c:forEach>
            </select><br>
            <input class="formcource" type="submit">
        </form>
            <%--        </form:form>--%>
            <form method="get" action="${pageContext.request.contextPath}/service/index/0">
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