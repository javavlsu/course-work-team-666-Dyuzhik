create table User
(
    id        int          not null auto_increment,
    lastname  varchar(255) default 'Фамилия',
    midname   varchar(255) default 'Отчество',
    firstname varchar(255) default 'Имя',
    username  varchar(255) not null,
    password  varchar(255) not null,
    photo     varchar(255) default 'пока пусто',
    primary key (id)
);

create table Role
(
    id   int          not null auto_increment primary key,
    name varchar(255) not null
);

create table User_role
(
    users_id int not null,
    roles_id int not null,
    foreign key (users_id) references user (id),
    foreign key (roles_id) references role (id),
    UNIQUE (users_id, roles_id)
);

insert into User (lastname, midname, firstname, username, password, photo)
values ('Админов', 'Админович', 'Админ', 'admin', '$2a$12$bpZ/AtVO.D5ACo/sOEih8O4ZhKAP3oRwC.pCiOoVL90naF4s2F98O',
        'https://fikiwiki.com/uploads/posts/2022-02/1644852387_2-fikiwiki-com-p-kartinki-admina-2.jpg'),
       ('Дюжик', 'Викторович', 'Сергей', 'aspirin', '$2a$12$bpZ/AtVO.D5ACo/sOEih8O4ZhKAP3oRwC.pCiOoVL90naF4s2F98O',
        'https://sun9-61.userapi.com/c841635/v841635100/1651c/WVU7TqlC2qE.jpg'),
       ('Бутусов', 'Геннадьевич', 'Вячеслав', 'butusov', '$2a$12$bpZ/AtVO.D5ACo/sOEih8O4ZhKAP3oRwC.pCiOoVL90naF4s2F98O',
        'https://cdnn21.img.ria.ru/images/07e6/0b/03/1828979383_0:32:2886:1656_1920x0_80_0_0_aeb8eb35f83aa761068678a60d0db535.jpg'),
       ('Цой', 'Робертович', 'Виктор', 'choi_is_alive', '$2a$12$bpZ/AtVO.D5ACo/sOEih8O4ZhKAP3oRwC.pCiOoVL90naF4s2F98O',
        'https://cdn-st1.rtr-vesti.ru/vh/pictures/xw/225/986/5.jpg'),
       ('Самойлов', 'Рудольфович', 'Глеб', 'gleb', '$2a$12$bpZ/AtVO.D5ACo/sOEih8O4ZhKAP3oRwC.pCiOoVL90naF4s2F98O',
        'https://chel.kassy.ru/media/a4/a45a9a37b94b8c6e2fd3a0738834ce53-35815.png'),
       ('Летов', 'Фёдорович', 'Игорь (Егор)', 'letov', '$2a$12$bpZ/AtVO.D5ACo/sOEih8O4ZhKAP3oRwC.pCiOoVL90naF4s2F98O',
        'https://icdn.lenta.ru/images/2018/02/19/09/20180219090854281/detail_4cc9685f49dd3910db80eeca3acaa88e.jpg'),
       ('Дягилева', 'Станиславовна', 'Янка', 'yanka', '$2a$12$bpZ/AtVO.D5ACo/sOEih8O4ZhKAP3oRwC.pCiOoVL90naF4s2F98O',
        'https://www.media-nsglinka.ru/upload/mcspersona/316/1472663570.jpeg'),
       ('Горшенёв', 'Юрьевич', 'Алексей', 'yagoda', '$2a$12$bpZ/AtVO.D5ACo/sOEih8O4ZhKAP3oRwC.pCiOoVL90naF4s2F98O',
        'https://img.gazeta.ru/files3/799/11856799/RIAN_1250178.HR-pic905-895x505-29350.jpg'),
       ('Горшенёв', 'Юрьевич', 'Михаил', 'gorshok', '$2a$12$bpZ/AtVO.D5ACo/sOEih8O4ZhKAP3oRwC.pCiOoVL90naF4s2F98O',
        'https://static.mk.ru/upload/iblock_mk/475/b2/b0/92/DETAIL_PICTURE__47088623.jpg'),
       ('Шклярский', 'Мечиславович', 'Эдмунд', 'shklyarsky',
        '$2a$12$bpZ/AtVO.D5ACo/sOEih8O4ZhKAP3oRwC.pCiOoVL90naF4s2F98O',
        'https://www.nashe.ru/uploads/photos/1/2022/08/%D1%88%D0%BA%D0%BB%D1%8F%D1%80.jpg');
/*passwords: qwerty*/

insert into Role(name)
values ('client'),
       ('barber'),
       ('admin'),
       ('pro-barber');

insert into User_role
values (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (2, 4),
       (3, 1),
       (4, 1),
       (5, 1),
       (5, 3),
       (6, 1),
       (6, 2),
       (7, 1),
       (7, 4),
       (8, 1),
       (9, 1),
       (9, 3),
       (10, 1),
       (10, 3),
       (10, 4);

create table Service
(
    id    int          not null auto_increment,
    name  varchar(255) not null,
    photo varchar(255) not null,
    cost  int          not null,
    primary key (id)
);

create table Status
(
    id   int          not null auto_increment primary key,
    name varchar(255) not null
);

create table Record
(
    id         int  not null auto_increment primary key,
    client_id  int  not null,
    barber_id  int  not null,
    service_id int  not null,
    status_id  int  not null,
    date       date not null,
    time       time not null,
    cost       int  not null,
    foreign key (client_id) references User (id),
    foreign key (barber_id) references User (id),
    foreign key (service_id) references service (id),
    foreign key (status_id) references Status (id)
);

create table Feedback
(
    id        int not null auto_increment primary key,
    mark      int not null,
    text      varchar(255),
    client_id int not null,
    barber_id int not null,
    foreign key (client_id) references User (id),
    foreign key (barber_id) references User (id)
);

insert into Status(name)
values ('Ожидание подтверждения'),
       ('Активен'),
       ('В процессе'),
       ('Завершён'),
       ('Отменён');

insert into Service(name, photo, cost)
values ('Ящерица', 'https://video-pricheski.ru/photo/img/pricheska-iashcheritsa-na-golove-foto-16.jpg', 1000),
       ('Назад в прошлое', 'https://barb.pro/uploads/images/strizhka-usov-3-min%5B1%5D.jpg', 2000),
       ('Стильно модно молодёжно',
        'https://n1s2.hsmedia.ru/56/2f/bb/562fbb79df2fe72f10cfc28adf2670a7/619x600_1_69503c41557a38cf70e9140139457dfa@634x615_0xac120003_19584321831560895588.jpg',
        300),
       ('Да кому нужны эти кепки', 'http://pricheska-strizhka.ru/wp-content/uploads/2017/12/hair-as-hat-ad.jpg', 1500),
       ('Московский метрополитен', 'https://hair-man.ru/wp-content/uploads/2017/08/1381438643_original-320x339.jpg',
        1380),
       ('Крыса обыкновенная', 'https://hair-man.ru/wp-content/uploads/2017/08/669312_600.jpg', 999),
       ('Кто проживает на дне океана?',
        'https://krot.info/uploads/posts/2022-03/1646301433_2-krot-info-p-smeshnie-muzhskie-strizhki-smeshnie-foto-2.jpg',
        3000),
       ('Волдеморт 2.0',
        'https://kartinkof.club/uploads/posts/2022-04/1649615492_24-kartinkof-club-p-ugarnie-kartinki-pro-strizhki-25.jpg',
        1799),
       ('Лучшая принцесса Дисней',
        'https://krot.info/uploads/posts/2022-03/1646301468_6-krot-info-p-smeshnie-muzhskie-strizhki-smeshnie-foto-6.png',
        1600),
       ('Бэтмен', 'https://ololo.tv/wp-content/uploads/2019/04/dwhfz.jpg', 500),
       ('Доктор Осьминог', 'https://content.onliner.by/news/820x5616/d4e03f4da3945acb47203b5b3a268eee.jpeg', 5990);

insert into Record(client_id, barber_id, service_id, status_id, date, time, cost)
VALUES (4, 2, 1, 5, '2023-02-02', '12:00', 1300),
       (5, 1, 2, 1, '2023-03-12', '13:00', 1312),
       (8, 6, 3, 1, '2023-04-16', '14:00', 900),
       (9, 7, 4, 2, '2023-04-02', '15:00', 500),
       (1, 10, 5, 2, '2023-02-26', '16:00', 150),
       (2, 1, 6, 2, '2023-02-27', '17:00', 1500),
       (3, 2, 7, 2, '2023-03-15', '18:00', 1999),
       (2, 10, 8, 1, '2023-08-01', '11:00', 560),
       (8, 2, 9, 1, '2023-08-02', '10:00', 700),
       (7, 2, 10, 4, '2023-01-02', '15:00', 800),
       (7, 1, 4, 4, '2023-01-22', '17:00', 300),
       (8, 2, 9, 4, '2023-01-15', '11:00', 400),
       (1, 10, 1, 4, '2023-01-03', '13:00', 500),
       (3, 2, 7, 4, '2023-01-12', '18:00', 1800),
       (8, 1, 11, 1, '2023-08-02', '17:00', 1700);

insert into Feedback(mark, text, client_id, barber_id)
VALUES (10,'Лучший барбер в мире!',7,2),
       (5,'Могло быть и лучше',7,1),
       (1,'Если бы можно было поставить ноль, я бы поставил именно его!',8,2),
       (8,'Стрижка понравилась, но могло быть и лучше',1,10),
       (4,'Ну такое',3,2);