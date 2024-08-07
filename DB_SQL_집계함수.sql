/* ------------------------------------------------------------------ */
drop database if exists sqltest2;
create database sqltest2;
use sqltest2;

-- 1. 집계 함수
select * from buy; # buy 테이블 모든 필드의 레코드 조회
select bamount+10 from buy; # 조회시 수량 필드에 연산해서 표시 (데이터에 적용은 X)

-- 1-1 sum(필드명) : 해당 필드의 총 합계
select sum(bamount) from buy; # bamount 총합계
    
-- 1-2 avg(필드명) : 해당 필드의 평균
select avg(bamount) from buy;

-- 1-3 max(필드명) : 해당 필드의 최댓값
select max(bamount) from buy;

-- 1-4 min(필드명) : 해당 필드의 최솟값;
select min(bamount) from buy;

-- 1-5 count(필드명) : 해당 필드의 레코드 수, null은 제외
select count(bgname) from buy;

-- 1-5 count(*) : 전체 레코드 수, null 포함
select count(*) from buy;

-- 2. 그룹 : 조회시 특정 필드 기준으로 그룹/묶음
select * from buy;
select * from buy group by mid; 
	#1 of SELECT list is not in GROUP BY clause and contains nonaggregated column 'sqltest2.buy.bnum' which is not functionally dependent on columns in GROUP BY clause; this is incompatible with sql_mode=only_full_group_by	0.000 sec
select distinct mid from buy;	# distinct : 조회 결과의 필드 값 중복 제거
select mid from buy group by mid;	# group by : 이용한 필드의 중복값 묶기
	# 조회 결과는 같다
select distinct mid, bamount from buy;	# 중복 제거?
select mid, bamount from buy group by mid;	# 그룹?
# RDBMS : 관계형 데이터베이스, 행과 열로 이루어진 테이블, 최소 데이터 단위 : 레코드 (java : 변수)

	# - 2개 이상의 필드를 집계해서 조회할 시 그룹 필요 (자료가 레코드 단위이므로)
	# - 총 집계는 그룹 필요 없지만 ~~별 집계시엔 그룹 필요
select sum(bamount) from buy; # 수량 필드 총 합계
select mid, sum(bamount) from buy; # group by 없음 오류
select mid, sum(bamount) from buy group by mid; # mid별 수량 총 합계

-- 2-1 전체 총 매출액
select bamount * bprice from buy; # 레코드 단위 매출액
select sum(bamount * bprice) from buy; # 총 매출액
select mid, sum(bamount * bprice) from buy group by mid; # mid별로 총 매출액

-- 2-2 전체 판매수량 평균
select avg(bamount) from buy; # 총 판매수량 평균
select mid, avg(bamount) from buy group by mid; # mid별 판매수량 평균

-- 2-3 전체 매출전표 개수
select count(*) from buy; # 총 레코드 수
select mid, count(*) from buy group by mid; # mid별 총 레코드 수

-- 3. 그룹의 조건 : 그룹 전은 where, 그룹 이후에는 having
select bamount * bprice from buy;	# 각 레코드마다의 매출액
select sum(bamount*bprice) from buy;	# 전체 매출액

-- 3-1. (조건) 각 판매의 매출이 1000 이상
select * from buy where bamount*bprice >= 1000;
select * from buy where sum(bamount*bprice) >= 1000;	
	# group function invalid use
select sum(bamount*bprice) as 합계 from buy where 합계>=1000;	
	# unknown column "합계" -> 조회시 계산한 결과를 where절에서 사용할 수 없음
	# select [필드명] from [테이블명] where [조건절] 에서 where 부분이 select 필드명보다 먼저 연산된다
select mid, sum(bamount*bprice) as 총매출액 from buy 
	group by mid having 총매출액 >= 1000;
	# 구매수량(bamount)이 3개 이상인 레코드(판매)들의 회원 아이디 별로 총매출액이 1000 이상
select mid, sum(bamount*bprice) as 총매출액 from buy where bamount >= 3 group by mid having 총매출액 >= 1000;
	# 1. 전체 판매 테이블 조회
	select * from buy where bamount >= 3;
    # 2.
    select sum(bamount*bprice) from buy where bamount >= 3;
    # 3.
    select mid, sum(bamount*bprice) from buy where bamount >= 3 group by mid;
    # 4.
    select mid, sum(bamount*bprice) total_price from buy where bamount>= 3 group by mid having total_price >= 100;
    # 5.
select b.mid, sum(b.bamount*b.bprice) as total_price
	from buy b inner join member m
    on b.mid = m.mid
	where b.bamount >= 3
    group by b.mid
    having total_price >= 100
    order by total_price desc
    limit 1;
# 집계/통계 계산하거나 그룸하거, DB(실무에서 권장) VS JAVA FS

# 1. 회원테이블
drop table if exists member;
create table member(            # 아이돌 그룹
   mid char(8) not null ,          # 식별키       최대 8자리
    mname varchar(10) not null ,   # 그룹명       최대 10자리
    mnumber int not null ,         # 인원수       정수 +-21억정도
    maddr char(2) not null ,       # 지역        최대 2자리
    mphone1 char(3) ,              # 지역번호     최대 2자리 
    mphone2 char(8) ,              # 전화번호     최대 8자리
    mheight smallint ,             # 평균키       정수 +-3만정도
   mdebut date ,                   # 데뷔일       yyyy-mm-dd 
    primary key ( mid )            # 제약조건 
);

# 2. 구매테이블
drop table if exists buy;
create table buy(
   bnum int auto_increment ,       # 구매번호      정수 자동번호 부여 
    mid char(8),                   # 구매자       FK 
    bpname char(6) not null ,      # 제품명       최대 6자리 
    bgname char(4) ,               # 분류명       최대 4자리
    bprice int not null ,          # 가격         정수 
    bamount smallint not null ,    # 구매수량      정수 
    primary key(bnum) ,            # 제약조건 
    foreign key ( mid ) references member(mid) # 제약조건 
);

# 샘플데이터 
INSERT INTO member VALUES('TWC', '트와이스', 9, '서울', '02', '11111111', 167, '2015.10.19');
INSERT INTO member VALUES('BLK', '블랙핑크', 4, '경남', '055', '22222222', 163, '2016.08.08');
INSERT INTO member VALUES('WMN', '여자친구', 6, '경기', '031', '33333333', 166, '2015.01.15');
INSERT INTO member VALUES('OMY', '오마이걸', 7, '서울', NULL, NULL, 160, '2015.04.21');
INSERT INTO member VALUES('GRL', '소녀시대', 8, '서울', '02', '44444444', 168, '2007.08.02');
INSERT INTO member VALUES('ITZ', '잇지', 5, '경남', NULL, NULL, 167, '2019.02.12');
INSERT INTO member VALUES('RED', '레드벨벳', 4, '경북', '054', '55555555', 161, '2014.08.01');
INSERT INTO member VALUES('APN', '에이핑크', 6, '경기', '031', '77777777', 164, '2011.02.10');
INSERT INTO member VALUES('SPC', '우주소녀', 13, '서울', '', '88888888', 162, '2016.02.25');
INSERT INTO member VALUES('MMU', '마마무', 4, '전남', '', '99999999', 165, '2014.06.19');

INSERT INTO buy VALUES(NULL, 'BLK', '지갑', NULL, 30, 2);
INSERT INTO buy VALUES(NULL, 'BLK', '맥북프로', '디지털', 1000, 1);
INSERT INTO buy VALUES(NULL, 'APN', '아이폰', '디지털', 200, 1);
INSERT INTO buy VALUES(NULL, 'MMU', '아이폰', '디지털', 200, 5);
INSERT INTO buy VALUES(NULL, 'BLK', '청바지', '패션', 50, 3);
INSERT INTO buy VALUES(NULL, 'MMU', '에어팟', '디지털', 80, 10);
INSERT INTO buy VALUES(NULL, 'GRL', '혼공SQL', '서적', 15, 5);
INSERT INTO buy VALUES(NULL, 'APN', '혼공SQL', '서적', 15, 2);
INSERT INTO buy VALUES(NULL, 'APN', '청바지', '패션', 50, 1);
INSERT INTO buy VALUES(NULL, 'MMU', '지갑', NULL, 30, 1);
INSERT INTO buy VALUES(NULL, 'APN', '혼공SQL', '서적', 15, 1);
INSERT INTO buy VALUES(NULL, 'MMU', '지갑', NULL, 30, 4);
