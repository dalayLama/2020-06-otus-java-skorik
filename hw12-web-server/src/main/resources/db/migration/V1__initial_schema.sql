create table user (
    id bigint(20) not null auto_increment,
    login varchar(255),
    name varchar(255),
    age int(3),
    password varchar(255)
);

create table addresses (
    id_user bigint(20) not null,
    street varchar(255),
    foreign key (id_user) references user(id)
);

create table phones (
    id bigint(20) not null auto_increment,
    id_user bigint(20) not null,
    _number varchar(255) not null,
    foreign key (id_user) references user(id)
);

insert into user (login, name, age, password) values ('u1', 'User', 20, '111');