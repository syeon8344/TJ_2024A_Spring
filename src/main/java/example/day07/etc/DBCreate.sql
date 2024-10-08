drop database if exists springday07todo;
create database springday07todo;
use springday07todo;

drop table if exists todo;
create table todo(
	listcode int auto_increment,
    listname varchar(40),
    listdate datetime default now(),
    isdone boolean default false,
    primary key (listcode)
);

insert into todo(listname) values("테스트할일1");
insert into todo(listname) values("테스트할일2");
insert into todo(listname) values("테스트할일3");
insert into todo(listcode, listname) values(5, "테스트할일5");

