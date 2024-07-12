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

https://code-with-me.global.jetbrains.com/shif_FXplKqQL8dQgCvtIg#p=IC&fp=16E956FCB2878BEED96F29EF7517A7315C8AE1CE0EABDB455B7276F6A890E01E&newUi=true