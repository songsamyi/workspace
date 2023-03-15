--1
SELECT STUDENT_NO "학번", STUDENT_NAME "이름", TO_CHAR(ENTRANCE_DATE,'YYYY-MM-DD') "입학년도" 
FROM TB_STUDENT 
WHERE DEPARTMENT_NO = 002
ORDER BY ENTRANCE_DATE ;


--2
SELECT PROFESSOR_NAME , PROFESSOR_SSN 
FROM TB_PROFESSOR
WHERE PROFESSOR_NAME NOT LIKE '___';



--3
SELECT PROFESSOR_NAME "교수이름", FLOOR(MONTHS_BETWEEN(SYSDATE, SUBSTR(PROFESSOR_SSN, 1, 6))/12) "나이"
FROM TB_PROFESSOR
WHERE SUBSTR(PROFESSOR_SSN, 8, 1) = 1;



--4
SELECT SUBSTR( PROFESSOR_NAME, 2) "이름"
FROM TB_PROFESSOR;


--5 
SELECT STUDENT_NO , STUDENT_NAME 
FROM TB_STUDENT 
WHERE MONTHS_BETWEEN(ENTRANCE_DATE, TO_DATE(SUBSTR(STUDENT_SSN, 1, 6)))/12 > 19
AND MONTHS_BETWEEN(ENTRANCE_DATE, TO_DATE(SUBSTR(STUDENT_SSN, 1, 6)))/12 <= 20;


--6 
SELECT TO_CHAR(TO_DATE(20201225), 'DAY')
FROM DUAL;



--7
SELECT TO_DATE('99/10/11', 'YY/MM/DD'),
	TO_DATE('49/10/11', 'YY/MM/DD'),
	TO_DATE('99/10/11', 'RR/MM/DD'),
	TO_DATE('49/10/11', 'RR/MM/DD')
FROM DUAL;



--8
SELECT STUDENT_NO , STUDENT_NAME 
FROM TB_STUDENT
WHERE STUDENT_NO NOT LIKE 'A%';


--9
SELECT ROUND(AVG(POINT),1) "평점"
FROM TB_GRADE
WHERE STUDENT_NO = 'A517178';


--10
SELECT DEPARTMENT_NO "학과번호", COUNT(STUDENT_NO) "학생수(명)" 
FROM TB_STUDENT
GROUP BY DEPARTMENT_NO 
ORDER BY DEPARTMENT_NO;


--11
SELECT COUNT(STUDENT_NO) "COUNT(*)"
FROM TB_STUDENT
WHERE COACH_PROFESSOR_NO IS NULL;


--12 
SELECT SUBSTR(TERM_NO, 1, 4) "년도", ROUND(AVG(POINT),1) "년도 별 평점"
FROM TB_GRADE
WHERE STUDENT_NO = 'A112113'
GROUP BY SUBSTR(TERM_NO, 1, 4) 
ORDER BY 년도;



--13 

SELECT DEPARTMENT_NO "학과코드명", COUNT(DECODE(ABSENCE_YN, 'Y', 'Y')) "휴학생 수"
FROM TB_STUDENT
GROUP BY DEPARTMENT_NO
ORDER BY DEPARTMENT_NO;

--14

SELECT NOT DISTINCT STUDENT_NAME 
FROM TB_STUDENT;


--15


SELECT NVL(SUBSTR(TERM_NO,1,4), ' ') "년도", NVL(SUBSTR(TERM_NO,5,6), ' ') "학기", ROUND(AVG(POINT),1) "평점"
FROM TB_GRADE
WHERE STUDENT_NO = 'A112113'
GROUP BY ROLLUP (SUBSTR(TERM_NO,1,4) , SUBSTR(TERM_NO,5,6));






