DROP TABLE I_MEMBER;
DROP TABLE admin;


SELECT * FROM I_MEMBER;
SELECT * FROM admin;
SELECT * FROM dleldi;
COMMIT;

CREATE TABLE I_MEMBER (
    NAME        VARCHAR2(30),
    ID          VARCHAR2(30) PRIMARY KEY,
    PASSWORD    VARCHAR2(30),
    BIRTH       VARCHAR2(30),
    AGE         VARCHAR2(10),
    GENDER      VARCHAR2(10),
    REGION1     VARCHAR2(30),
    REGION2     VARCHAR2(30),
    MBTI        VARCHAR2(30)
);

CREATE TABLE ★ (
    NAME        VARCHAR2(30),
    CONTENT     VARCHAR2(30),
    DATETIME    VARCHAR2(50) DEFAULT TO_CHAR(SYSDATE, 'yyyy-mm-dd hh24:mi:ss')
);

INSERT INTO I_MEMBER VALUES('어드민', 'admin', 'admin1234', '1971-05-08', '52', '여자', '부산광역시', '해운대구', '');
INSERT INTO I_MEMBER VALUES('이디야', 'dleldi', 'dleldi88', '2000-06-06', '22', '남자', '대구광역시', '수성구', ''); 
INSERT INTO I_MEMBER VALUES('기요미', 'rldyal', 'rldyal59', '1997-06-25', '25', '여자', '서울특별시', '도봉구', ''); 

DELETE FROM I_MEMBER WHERE NAME = '어드민';


UPDATE I_MEMBER SET MBTI = 'ESTJ' WHERE ID = 'admin';

--(SYSDATE, 'yyyy-mm-dd hh24:mi:ss')

INSERT INTO admin (NAME, CONTENT) VALUES ('조혜경', '쪽지를 보내보자.');
