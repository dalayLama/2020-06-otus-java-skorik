create table user (
    id bigint(20) not null auto_increment,
    name varchar(255),
    age int(3)
);

create table account (
    no bigint(20) not null auto_increment,
    type varchar(255),
    rest number
);

insert into user (name, age) values ('Artur', 27);
insert into user (name, age) values ('Alexey', 28);