create table if not exists users(
id serial primary key,
username varchar(30) unique not null,
password varchar(30) not null,
role varchar(15) not null
);

insert into users (username, password, role) values ('admin', 'pass', 'ADMIN');
insert into users (username, password, role) values ('a', 'pass', 'MANAGER');
insert into users (username, password, role) values ('b', 'pass', 'BASIC_USER');