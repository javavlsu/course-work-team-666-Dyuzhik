<%@ page  contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <meta charset="utf-8">
    <title>Управление пользователями</title>
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
<div id="container">
    <main id="show">
        <section>
            <c:if test="${users.isEmpty()}">
                <h4>Пользователей пока нет</h4>
            </c:if>

            <c:if test="${!users.isEmpty()}">
                <div class="table">
                    <table>
                        <tr>
                            <th>id</th>
                            <th>Логин</th>
                            <th>Фамилия</th>
                            <th>Имя</th>
                            <th>Отчество</th>
                            <th>Удалить</th>
                        </tr>
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td>${user.id}</td>
                                <td>${user.username}</td>
                                <td>${user.lastname}</td>
                                <td>${user.name}</td>
                                <td>${user.midname}</td>
                                <td>
                                    <form method="post" action="/delete/${user.id}?${_csrf.parameterName}=${_csrf.token}">
                                        <input class="test" onclick="return confirm('Вы хотите удалить пользователя?')" type="submit"   value="Удалить">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:if>

            <c:if test="${!users.isEmpty()}">
                <form:form modelAttribute="checkRoles" method="POST" action="/modifyRoles">
                    <div class="table">
                        <table class="role">
                            <tr class="role">
                                <th>Логин</th>
                                <th>Назначить</th>
                                <th>Понизить</th>
                            </tr>
                            <form>
                                <c:forEach items="${users}" var="user">
                                    <tr class="role">
                                        <td>${user.username}</td>
                                        <td>
                                            <c:forEach items="${user.roles}" var="role">
                                                <c:if test="${role.id == 1}">
                                                    <c:set var="client" value="true"/>
                                                </c:if>
                                                <c:if test="${role.id == 2}">
                                                    <c:set var="barber" value="true"/>
                                                </c:if>
                                                <c:if test="${role.id == 3}">
                                                    <c:set var="admin" value="true"/>
                                                </c:if>
                                                <c:if test="${role.id == 4}">
                                                    <c:set var="probarber" value="true"/>
                                                </c:if>
                                            </c:forEach>
                                            <c:if test="${client != 'true'}">
                                                <div class="rowtable">
                                                    <label>Клиент</label>
                                                    <form:checkbox path="addRoles.clRoles" value="${user.id}" />
                                                </div>
                                            </c:if>
                                            <c:if test="${barber != 'true'}">
                                                <div class="rowtable">
                                                    <label>Барбер</label>
                                                    <form:checkbox path="addRoles.barRoles" value="${user.id}" />
                                                </div>
                                            </c:if>
                                            <c:if test="${probarber != 'true'}">
                                                <div class="rowtable">
                                                    <label>Про-барбер</label>
                                                    <form:checkbox path="addRoles.probarRoles" value="${user.id}" />
                                                </div>
                                            </c:if>
                                            <c:if test="${admin != 'true'}">
                                                <div class="rowtable">
                                                    <label>Админ</label>
                                                    <form:checkbox path="addRoles.admRoles" value="${user.id}" />
                                                </div>
                                            </c:if>
                                        </td>
                                        <td>
                                            <c:if test="${client == 'true'}">
                                                <div class="rowtable">
                                                    <label>Клиент</label>
                                                    <form:checkbox path="delRoles.clRoles" value="${user.id}" />
                                                </div>
                                            </c:if>
                                            <c:set var="client" value="false"/>

                                            <c:if test="${barber == true}">
                                                <div class="rowtable">
                                                    <label>Барбер</label>
                                                    <form:checkbox path="delRoles.barRoles" value="${user.id}" />
                                                </div>
                                            </c:if>
                                            <c:set var="barber" value="false"/>

                                            <c:if test="${probarber == true}">
                                                <div class="rowtable">
                                                    <label>Про-барбер</label>
                                                    <form:checkbox path="delRoles.probarRoles" value="${user.id}" />
                                                </div>
                                            </c:if>
                                            <c:set var="probarber" value="false"/>

                                            <c:if test="${admin == true}">
                                                <div class="rowtable">
                                                    <label>Админ</label>
                                                    <form:checkbox path="delRoles.admRoles" value="${user.id}" />
                                                </div>
                                            </c:if>
                                            <c:set var="admin" value="false"/>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </form>
                        </table>
                        <button class="teach" type="submit" name="${_csrf.parameterName}" value="${_csrf.token}">Обновить</button>
                    </div>
                </form:form>
            </c:if>
            <form method="get" action="/create">
                <button class="teach" type="submit" >Добавить пользователя</button>
            </form>
            <form method="get" action="/profile">
                <button class="teach" type="submit" >Назад</button>
            </form>
        </section>
    </main>
</div>
<footer>
    <p>Телефон: +6(666)-666-66-66</p>
    <p>E-mail: thebestbarbershopintheworld@the.best</p>
</footer>
</body>

</html>
