-- 1. 인기있는 아이스크림
SELECT FLAVOR FROM FIRST_HALF ORDER BY TOTAL_ORDER DESC, SHIPMENT_ID;

-- 2. 모든 레코드 조회하기
SELECT * FROM ANIMAL_INS ORDER BY ANIMAL_ID ASC;

-- 3. 역순 정렬하기
SELECT NAME, DATETIME FROM ANIMAL_INS ORDER BY ANIMAL_ID DESC;

-- 4. 동물의 아이디와 이름
SELECT ANIMAL_ID,NAME FROM ANIMAL_INS ORDER BY ANIMAL_ID ASC;

-- 5. 여러 기준으로 정렬하기   
SELECT ANIMAL_ID, NAME, DATETIME FROM ANIMAL_INS ORDER BY NAME, DATETIME DESC;

-- 6. 과일로 만든 아이스크림 고르기
SELECT FIRST_HALF.FLAVOR 
	FROM FIRST_HALF NATURAL JOIN ICECREAM_INFO 
    WHERE TOTAL_ORDER > 3000 AND INGREDIENT_TYPE='fruit_based' 
    ORDER BY TOTAL_ORDER DESC;
    
-- 7. 조건에 부합하는 중고거래 댓글 조회하기
SELECT TITLE, B.BOARD_ID, REPLY_ID, R.WRITER_ID, R.CONTENTS, DATE_FORMAT(R.CREATED_DATE, '%Y-%m-%d') AS CREATED_DATE
    FROM USED_GOODS_BOARD AS B INNER JOIN USED_GOODS_REPLY AS R ON B.BOARD_ID = R.BOARD_ID
    WHERE B.CREATED_DATE BETWEEN '2022-10-01' AND '2022-10-31'
    ORDER BY R.CREATED_DATE, TITLE;
    
-- 8. 강원도에 위치한 생산공장 목록 출력하기
SELECT FACTORY_ID, FACTORY_NAME, ADDRESS FROM FOOD_FACTORY WHERE ADDRESS LIKE '%강원도%' ORDER BY FACTORY_ID;

-- 9. 아픈 동물 찾기
SELECT ANIMAL_ID, NAME FROM ANIMAL_INS WHERE INTAKE_CONDITION = 'Sick';

-- 10. 어린 동물 찾기
SELECT ANIMAL_ID, NAME FROM ANIMAL_INS WHERE INTAKE_CONDITION != 'AGED';

-- 11. 상위 n개 레코드
SELECT NAME FROM ANIMAL_INS ORDER BY DATETIME LIMIT 1;

-- 12. Python 개발자 찾기
SELECT ID, EMAIL, FIRST_NAME, LAST_NAME FROM DEVELOPER_INFOS 
    WHERE 'Python' IN (SKILL_1, SKILL_2, SKILL_3)
    ORDER BY ID;