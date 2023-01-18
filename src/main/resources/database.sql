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

insert into User(username, password)
values ('admin', '$2a$11$4wXmhyZHOM8MJamwV6yVAeF5a2Q'),
       ('aspirin', '$2a$11$4wXmhyZHOM8MJamwV6yVAeF5a2Q');
/*passwords: qwertyqwerty*/

insert into Role(name)
values ('client'),
       ('barber'),
       ('admin');

insert into User_role
values (1, 1),
       (1, 2),
       (1, 3),
       (2, 1);

create table Service
(
    id   int          not null auto_increment,
    name varchar(255) not null,
    primary key (id)
);

create table Specialization
(
    id   int          not null auto_increment primary key,
    name varchar(255) not null
);

create table Status(
    id int not null auto_increment primary key ,
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
    id        int          not null auto_increment primary key,
    client_id int          not null,
    barber_id int          not null,
    service_id int not null,
    date      datetime     not null,
    status_id    int  not null,
    foreign key (client_id) references User (id),
    foreign key (barber_id) references Barber (id),
    foreign key (service_id) references service(id),
    foreign key (status_id) references Status(id)
);

insert into Specialization(name)
values ('бабер'),
       ('про-барбер');

insert into Status(name) values
('Активен'), ('В процессе'),('Завершён')