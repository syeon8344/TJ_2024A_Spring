# 제품 테이블 : 1, 제품 한개당 이미지 여러 개 (일대다 관계, PK-FK)
drop table if exists product;
create table product(
	pno int auto_increment, -- 제품번호 (권장사항)
    pname varchar(100), -- 제품명
    pcontent varchar(255), -- 제품설명
    pprice int, -- 제품가격
    pdate datetime default now(), -- 제품 등록일 (권장사항)
    pview int default 0, -- 제품조회수 기본값 0
	primary key(pno) -- 기본키
); 
select * from product;

# 제품 이미지 테이블 : M (이미지 개수가 정해져 있으면 제품 테이블에 합칠 수 있을 수 있다)
drop table if exists productimage;
create table productimage(
	pimgno int auto_increment, -- 제품 이미지 번호
    pimgname text, -- 제품 이미지 이름
    pimgdate datetime default now(), -- 제품 이미지 등록날짜 (권장)
    pno int, -- 제품번호 FK
    primary key(pimgno),
    foreign key(pno) references product(pno) on update cascade on delete cascade -- PK 수정/삭제시 FK 같이 수정/삭제된다
);
select * from productimage;