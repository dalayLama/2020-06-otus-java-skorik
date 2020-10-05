create table user (
    id bigint(20) not null auto_increment,
    name varchar(255),
    age int(3)
);

insert into user (name, age) values ('Artur', 27);