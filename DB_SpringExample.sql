# [1]
drop database if exists springexample;
create database springexample;
use springexample;

# [2]
drop table if exists phonebook;
create table phonebook(
	id int auto_increment,
    name varchar(20),
    phone varchar(15),
    primary key (id)
);
# [3] 입력
insert into phonebook(name, phone) values('유재석', '010-222-3333');
# [4] 조회	
select * from phonebook;