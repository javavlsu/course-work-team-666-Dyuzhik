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
values ('Админов', 'Админович', 'Админ', 'admin', '$2a$11$4wXmhyZHOM8MJamwV6yVAeF5a2Q',
        'https://fikiwiki.com/uploads/posts/2022-02/1644852387_2-fikiwiki-com-p-kartinki-admina-2.jpg'),
       ('Дюжик', 'Викторович', 'Сергей', 'aspirin', '$2a$11$4wXmhyZHOM8MJamwV6yVAeF5a2Q',
        'https://sun9-61.userapi.com/c841635/v841635100/1651c/WVU7TqlC2qE.jpg');
/*passwords: qwertyqwerty*/

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
       (2, 4);

# drop table service;
create table Service
(
    id    int          not null auto_increment,
    name  varchar(255) not null,
    photo varchar(255) not null,
    cost  int          not null,
    primary key (id)
);

create table Specialization
(
    id   int          not null auto_increment primary key,
    name varchar(255) not null
);

create table Status
(
    id   int          not null auto_increment primary key,
    name varchar(255) not null
);

create table Barber
(
    id                int not null auto_increment primary key,
    user_id           int not null,
    specialization_id int not null,
    foreign key (user_id) references user (id),
    foreign key (specialization_id) references Specialization (id)
);

# drop table Record;

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

insert into Specialization(name)
values ('бабер'),
       ('про-барбер');

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
VALUES (1,2,2,1,'2023-02-02','12:00',13),
       (1,2,2,1,'2023-02-02','13:00',13),
       (1,2,2,1,'2023-02-02','14:00',13),
       (1,2,2,1,'2023-02-02','15:00',13),
       (1,2,2,1,'2023-02-02','16:00',13),
       (1,2,2,1,'2023-02-02','17:00',13),
       (1,2,2,1,'2023-02-02','18:00',13),
       (1,2,2,1,'2023-02-02','11:00',13),
       (1,2,2,1,'2023-02-02','10:00',13);