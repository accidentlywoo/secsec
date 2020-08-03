CREATE TABLE board(
    board_no number, -- 글번호 ( 1부터 시작, 1씩 증가 )
    parent_no number, -- 부모 글번호 (0: 게시판글 원본이라는 flag, board_no에따른 답글)
    board_title VARCHAR2(30),
    board_writer VARCHAR2(10),
    board_dt TIMESTAMP,
    board_content VARCHAR2(60)    
);

ALTER TABLE board
ADD CONSTRAINT board_no_pk PRIMARY KEY(board_no);

ROLLBACK
;

INSERT INTO board VALUES(1, 0, '글1', '우여니', SYSTIMESTAMP, '글1 내용');
INSERT INTO board VALUES(2, 0, '글2', '우여니', SYSTIMESTAMP, '글2 내용');
INSERT INTO board VALUES(3, 2, '글2-답3', '우여니', SYSTIMESTAMP, '글2 답글3');
INSERT INTO board VALUES(4, 2, '글2-답4', '우여니', SYSTIMESTAMP, '글2 답글4');
INSERT INTO board VALUES(5, 3, '답3-답5', '우여니', SYSTIMESTAMP, '답3 답글5');
INSERT INTO board VALUES(6, 1, '글1-답6', '우여니', SYSTIMESTAMP, '글1 답글6');
INSERT INTO board VALUES(7, 0, '글7', '우여니', SYSTIMESTAMP, '글 7내용');
commit;

SELECT * FROM board
ORDER By board_no DESC
;
-- 최신 글 순서 > 원글 ~ 답글
-- 글 7
-- 글 2
-- -- 글2 답4
-- -- 글2 답3
-- -- -- 답3 답5
-- 글 1
-- -- 글1 답 6

SELECT level, a.*
FROM (SELECT * FROM board
ORDER BY board_no DESC) a
START WITH parent_no = 0
CONNECT BY PRIOR board_no = parent_no
; -- Sorting 안됨

SELECT r, level, a.*
FROM (SELECT ROWNUM r, b.* FROM board b ORDER BY board_no DESC) a
START WITH parent_no = 0
CONNECT BY PRIOR board_no = parent_no
;

SELECT ROWNUM list, b.* 
        FROM (SELECT * FROM board ORDER BY board.board_no DESC) b 
        ;

SELECT list, level, a.*
FROM (SELECT ROWNUM list, b.* 
        FROM (SELECT * FROM board ORDER BY board_no DESC) b
    ) a
START WITH parent_no = 0
CONNECT BY PRIOR board_no = parent_no
; -- rownum 을 활용한 정렬이 잘 되지 않는다. 

--SELECT level, * Error 발생
SELECT *
FROM board
START WITH parent_no = 0
CONNECT BY PRIOR board_no = parent_no
ORDER BY board_no
; -- 계층형 쿼리의 효과를 볼 수 없다.

SELECT level,a.*
FROM (SELECT * FROM board ORDER BY board.board_no) a
START WITH parent_no = 0
CONNECT BY PRIOR board_no = parent_no
; -- 서비쿼리 order by 적용안됨

SELECT level, a.*
FROM (SELECT * 
        FROM board
        ORDER BY board_no DESC) a
START WITH parent_no = 0
CONNECT BY PRIOR board_no = parent_no
ORDER SIBLINGS BY a.board_no DESC
; -- 내림차순 -> 계층형쿼리 성공
-- 계층형 쿼리

-- 페이지 그루핑
-- -- 1 페이지당 3건씩 검색
-- 1 페이지 : 1행~3행
SELECT *
FROM(SELECT ROWNUM list, b.*
        FROM (SELECT level, a.*
                FROM (SELECT * 
                        FROM board
                        ORDER BY board_no DESC) a
                START WITH parent_no = 0
                CONNECT BY PRIOR board_no = parent_no
                ORDER SIBLINGS BY a.board_no DESC)b
)
WHERE list BETWEEN 1 AND 3
;
-- 2 페이지
SELECT *
FROM(SELECT ROWNUM list, b.*
        FROM (SELECT level, a.*
                FROM (SELECT * 
                        FROM board
                        ORDER BY board_no DESC) a
                START WITH parent_no = 0
                CONNECT BY PRIOR board_no = parent_no
                ORDER SIBLINGS BY a.board_no DESC)b
)
WHERE list BETWEEN 4 AND 6
;-- 기본 페이징 쿼리


-- 2 페이지-- 계층형 페이징 쿼리 활용
SELECT  *
        FROM (SELECT ROWNUM list,level, a.*
                FROM (SELECT * 
                        FROM board
                        ORDER BY board_no DESC) a
                START WITH parent_no = 0
                CONNECT BY PRIOR board_no = parent_no
                ORDER SIBLINGS BY a.board_no DESC) b
WHERE list BETWEEN 4 AND 6
;

-- 글번호용 시퀀스 생성 : 시퀀스 이름은 board_seq, 시작값은 8, 증가치는 1
CREATE SEQUENCE board_seq
INCREMENT BY 1
START WITH 8
NOCACHE
;
SELECT * FROM board
;
-- 게시판 글끄기
INSERT INTO board VALUES(board_seq.NEXTVAL, 0,'~','우여니',SYSTIMESTAMP, '~');

-- 답글 쓰기
INSERT INTO board VALUES(board_seq.NEXTVAL, 원글번호,'~','우여니',SYSTIMESTAMP, '~');

-- 글 내용보기
SELECT *
FROM board
WHERE board_no=글번호
;

-- 글 내용 수정
UPDATE board
SET board_title='~', board_content='~',
    board_dt=SYSTIMESTAMP
WHERE board_no=글번호
;

-- 글 내용 삭제
