/*
	SQL 언어
    [1] DDL : 정의어, Database Definition Language
		- create 	: 데이터베이스, 테이블 생성
			create database [DB이름]
            create table [테이블이름]
        - drop 		: 데이터베이스, 테이블 삭제
			drop database [DB이름]
            drop table [테이블이름]
				- drop database/table if exists [DB/테이블이름]
		- use 		: 데이터베이스 활성화
			use [DB이름]
		- truncate 	: 테이블 전체 데이터 삭제
			truncate table [테이블이름]
		- show 		: 전체 DB 목록 조회
			show databases
    [2] DML : 조작어, Database Manipulation Language
		- select : 조회
			select [필드명] from [테이블명] where [조건]
        - insert : 삽입
			insert into [테이블명](필드명1, 필드명2) values (값1,값2)
		- update : 수정
			update [테이블명] set 필드명1 = 수정값1, 필드명2 = 수정값2 where [조건]
        - delete : 삭제
			delete from [테이블명] where [조건]
    [3] DCL : 제어어, Database Control Language
		- grant		: 권한 부여
        - revoke	: 권한 취소
		- TCL : 트랜젝션 제어어, Transaction Control Language
			commit 	: 작업한 데이터를 DB에 반영 완료
			rollback : 작업 반영 취소
			savepoint : 작업 위치 지정 (이후에 해당위치로 이동가능)
        
	[4] 연산자
		1. 산술연산자 : + - * / (사칙연산) | div = 몫 mod = 나머지
        2. 비교연산자 : > < >= <= | = 같다 != 같지 않다
        3. 논리연산자 : and 이거나 or 또는 not 부정
        4. 비트연산자 : & (비트 AND 연산, e.g. 36(100100) & 4(100) = 4(100), 110 & 1 = 0)
		5. 기타연산자 :
			- between 시작값 and 끝값 : 시작값부터 끝값까지의 포함된 값
            - in(값1, 값2) : 값이 하나라도 포함된 값 조회
            - [필드명] like [패턴]
				a. % : 모든 문자 대응
                b. _ : 개수만큼 문자수 대응
			- [필드명] is null, [필드명] is not null : 필드의 null여부 검사
            - [필드명] as 별칭 : 조회시 사용되는 별명/별칭
            - distinct [필드명] : 조회시 필드의 중복값을 제거
            + AVG(필드명) : 평균
            + ROUND(값, 소수점이하숫자개수) : 특정 자리에서 반올림
            + DATE_FORMAT(필드명, '%Y-%m-%d') : 날짜 양식 변환 %Y : 연도 4글자, %y : 연도 2글자 ...
            + YEAR(), MONTH(), DAY() : 특정 날짜에서 연/월/일 추출
            
	[5] 다양한 문법 절
		[1] where : 일반조건
			where [필드명] 연산자 
        [2] group by : 그룹 조건
			group by [필드명] : 집계 함수와 같이 사용해서 묶을 기준 필드명
        [3] having : (그룹 후) 그룹 내 조건
			having [필드명] 연산자
        [4] order by : 정렬조건
			order by [필드명] asc/desc
            order by [필드명1] asc/desc, [필드명2] asc/desc
        [5] limit
			limit 레코드수
            limit 시작레코드번호[0~], 레코드수
        => select *
				from [테이블명] 
                {join, on} 
                where [조건] 
                group by [그룹] 
                having [그룹조건] 
                order by [정렬조건] 
                limit [제약]
                
	[6] 내부 조인
		[1] [테이블명A], [테이블명B] where 테이블명A.PK = 테이블명B.FK
        [2] [테이블명A] natural join [테이블명B]
        [3] [테이블명A] join [테이블명B] on 테이블명A.PK = 테이블명B.FK
        [4] [테이블명A] inner join [테이블명B] on 테이블명A.PK = 테이블명B.FK
        [5] [테이블명A] join [테이블명B] using(필드명)
        
	[7] 제약조건
		[1] not null : 필드에 null값 포함 불가
        [2] default : 레코드 삽입시 기본값
        [3] unique : 필드에 중복 값 불가
        [4] (MySQL) auto_increment : 레코드 삽입시 자동 증가하는 수 부여
        [5] primary key (필드명) : 식별키 (해당 테이블의 고유값을 가진 값의 필드)
        [6] foreign key (필드명) references (다른 테이블 필드명) (다른 테이블의 식별키를 참조하는 경우)
        - 제약조건 옵션
            on [delete/update] restrict : pk 필드값이 삭제/수정할때 pk 필드값을 참조하는 FK필드값이 있으면 삭제/수정 취소
            on [delete/update] cascade  : pk 필드값이 삭제/수정할때 pk 필드값을 참조하는 FK필드값이 있으면 삭제/수정 같이 처리
            on [delete/update] set null : pk 필드값이 삭제/수정할때 pk 필드값을 참조하는 FK필드값이 있으면 삭제/수정 처리 후 FK 필드값을 NULL 설정

    [8] 집계 함수
        1. sum(필드명) : 해당 필드의 총 합계
        2. avg(필드명) : 해당 필드의 평균
        3. max(필드명) : 해당 필드의 최댓값
        4. min(필드명) : 해당 필드의 최솟값
        5. count(필드명) : 해당 필드의 레코드 수 (null 제외)
        5-1. count(*) : 전체 레코드 수 (null 포함)

	- 필드 타입
		[ 정수 ]
			TINYINT : 1바이트
            SMALLINT : 2바이트
            MEDIUMINT : 3바이트
            INT : 4바이트
            BIGINT : 8바이트
				- signed(기본값) : 음수 사용 (부호 O)
					TINYINT : -128 ~ 127
                - unsigned : 음수 사용 X, 양수 2배 사용 (부호 X)
					TINYINT : 0 ~ 255
        [ 실수 ]
			FLOAT : 4바이트
            DOUBLE : 8바이트
				- DECIMAL : 실수의 오차 없이 표현 사용
		[ 날짜 ]
			DATE : 날짜만
            DATETIME : 날짜 및 시간
		[ 문자 ] 
			CHAR : 고정 길이 문자열
            VARCHAR : 가변 길이 문자열
            TEXT : 6만글자 정도 고정된 길이
            LONGTEXT : 42억글자정도 고정된 길이 [4GB]
		[ 논리 ]
			BOOL
*/