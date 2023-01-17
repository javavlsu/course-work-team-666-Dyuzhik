<%@ page  contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <meta charset="utf-8">
    <title>Добро пожаловать</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">

</head>

<body>
<header id="black">
    <nav>
        <ul>
            <li><a href="/profile">Личный кабинет</a></li>
            <li><a href="/services/index/0">Услуги</a></li>
            <li><a href="/barbers">Барберы</a></li>
            <a href="index.jsp"><img src="resources/img/logo.png" width="150" height="50" alt="barbershop"/></a>
        </ul>
    </nav>
</header>
<div id="container">
    <main id="main">
        <p>Барбершоп – необычное для российского уха слово, но оно
            уверенно входит в повседневный лексикон многих мужчин.
            Фактически это мужская парикмахерская, где создают исключительно
            стильные и эффектные стрижки, а также проводят уход за усами и бородами.
            Женщинам, как говорится, здесь «табу».</p>
        <p>В современной России барбершопы начали появляться сравнительно недавно,
            причём в основном в столице и крупных городах. Но на ваше счастье
            подобное заведение добралось и до не самых крупных городов, и наши самые лучшие специалисты с
            радостью окажут вам интересующие вас услуги.
        </p>
        <p>Никаких возрастных или социальных ограничений на посещение нашего барбершопа нет.</p>
        <p>Мы ждём именно тебя.</p>
    </main>
</div>
<footer>
    <p>Телефон: +6(666)-666-66-66</p>
    <p>E-mail: thebestbarbershopintheworld@the.best</p>
</footer>
</body>
</html>

