create database springweb;
use springweb;
# 1. 게시물 카테고리
drop table if exists bcategory;
create table  bcategory(
   bcno int unsigned auto_increment ,
    bcname varchar( 30 ) not null unique,
   bcdate datetime default now() not null  ,
    constraint bcategory_bcno_pk primary key ( bcno )
);
insert into bcategory( bcname ) values ( '자유' ) , ( '노하우' ) , ( '판매' ) , ( '구매') ;
select * from bcategory;

# 2. 게시물
drop table if exists board;
create table board(
   bno bigint unsigned auto_increment ,
    btitle varchar( 255 ) not null ,
    bcontent longtext ,
    bfile longtext ,
    bview int unsigned default 0 not null ,
    bdate datetime  default now() not null  ,
    no  bigint ,
    bcno int unsigned,
    constraint board_bno_pk primary key( bno ) ,
    constraint board_no_fk foreign key( no) references member( no ) on update cascade on delete cascade ,
    constraint board_bcno_fk foreign key( bcno ) references bcategory( bcno ) on update cascade on delete cascade
);
select *from board;

# 3. 게시글 댓글
drop table if exists breply;
create table breply(
	brno bigint unsigned auto_increment,				# 댓글번호PK
    brindex int,				# 댓글 Z-인덱스 : 댓글 위치 분류, 0. 최상위댓글, 1이상. brno 참조하는 상위댓글번호
	brcontent varchar(255), # 댓글내용
    brdate datetime default now(), # 작성일
    no bigint, 				# 작성자FK
    bno bigint unsigned,				# 게시물번호FK
    primary key (brno),
	foreign key (no) references member(no) on delete cascade on update cascade,
    foreign key (bno) references board(bno) on delete cascade on update cascade
);
#샘플
insert into board(btitle,bcontent,no,bcno) values("테스트제목1","테스트내용1",1,1);
insert into board(btitle,bcontent,no,bcno) values("테스트제목2","테스트내용2",1,2);
insert into board(btitle,bcontent,no,bcno) values("테스트제목3","테스트내용3",1,3);
insert into board(btitle,bcontent,no,bcno) values("테스트제목4","테스트내용4",1,4);

# 레코드의 갯수 세기 
	# count (*) : 조회한 레코드의 총 개수
    # count(필드명) : 조회한 레코드의 총 개수 반환, 특정 필드가 Null이면 제회하고 센 갯수
	# 1. 레코드 조회
select * from board;
    # 2. 레코드 총 개수 조회
select count(*) from board;
select count(bfile) from board;

# 5. limit 연산자 이용한 레코드 제한 출력
	# limit 개수 : 개수만큼의 레코드 조회 
    # limit 시작레코드 , 개수 : 시작레코드(0~) 부터 개수만큼의 레코드 조회
select * from board limit 0;		
select * from board limit 1;
select * from board limit 1 , 2;
select * from board limit 1 , 3;
select * from board limit 0 , 3;
	# 페이징 처리 활용 , 가정 : 하나의 페이지 당 게시물 표시 수 는 5개씩 
	# 1페이지  	limit 0 , 5;		1페이지일때 시작인덱스 : 0		, 0 * 5 -> 0
select * from board limit 0 , 5;
    # 2페이지		limit 5 , 5;		2페이지일때 시작인덱스 : 5		, 1 * 5	-> 5
select * from board limit 5 , 5;
    # 3페이지 	limit 10 , 5;		3페이지일때 시작인덱스 : 10 		, 2 * 5	-> 10
select * from board limit 10 , 5;
	# 시작인덱스의 계산식 : (현재페이지번호-1) * 페이지당게시물수 

-- 활용
	# 1페이지 
select * from board inner join member on board.no = member.no limit 0 , 5 ;
	# 2페이지 
select * from board inner join member on board.no = member.no limit 5 , 5 ;
	# JDBC
# select * from board inner join member on board.no = member.no limit ? , ? ;

-- 정렬 : 작성일 순으로 
select * from board inner join member on board.no = member.no order by board.bno desc limit 0 , 5 ;