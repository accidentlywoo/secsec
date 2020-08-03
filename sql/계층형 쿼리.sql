CREATE TABLE board(
    board_no number, -- �۹�ȣ ( 1���� ����, 1�� ���� )
    parent_no number, -- �θ� �۹�ȣ (0: �Խ��Ǳ� �����̶�� flag, board_no������ ���)
    board_title VARCHAR2(30),
    board_writer VARCHAR2(10),
    board_dt TIMESTAMP,
    board_content VARCHAR2(60)    
);

ALTER TABLE board
ADD CONSTRAINT board_no_pk PRIMARY KEY(board_no);

ROLLBACK
;

INSERT INTO board VALUES(1, 0, '��1', '�쿩��', SYSTIMESTAMP, '��1 ����');
INSERT INTO board VALUES(2, 0, '��2', '�쿩��', SYSTIMESTAMP, '��2 ����');
INSERT INTO board VALUES(3, 2, '��2-��3', '�쿩��', SYSTIMESTAMP, '��2 ���3');
INSERT INTO board VALUES(4, 2, '��2-��4', '�쿩��', SYSTIMESTAMP, '��2 ���4');
INSERT INTO board VALUES(5, 3, '��3-��5', '�쿩��', SYSTIMESTAMP, '��3 ���5');
INSERT INTO board VALUES(6, 1, '��1-��6', '�쿩��', SYSTIMESTAMP, '��1 ���6');
INSERT INTO board VALUES(7, 0, '��7', '�쿩��', SYSTIMESTAMP, '�� 7����');
commit;

SELECT * FROM board
ORDER By board_no DESC
;
-- �ֽ� �� ���� > ���� ~ ���
-- �� 7
-- �� 2
-- -- ��2 ��4
-- -- ��2 ��3
-- -- -- ��3 ��5
-- �� 1
-- -- ��1 �� 6

SELECT level, a.*
FROM (SELECT * FROM board
ORDER BY board_no DESC) a
START WITH parent_no = 0
CONNECT BY PRIOR board_no = parent_no
; -- Sorting �ȵ�

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
; -- rownum �� Ȱ���� ������ �� ���� �ʴ´�. 

--SELECT level, * Error �߻�
SELECT *
FROM board
START WITH parent_no = 0
CONNECT BY PRIOR board_no = parent_no
ORDER BY board_no
; -- ������ ������ ȿ���� �� �� ����.

SELECT level,a.*
FROM (SELECT * FROM board ORDER BY board.board_no) a
START WITH parent_no = 0
CONNECT BY PRIOR board_no = parent_no
; -- �������� order by ����ȵ�

SELECT level, a.*
FROM (SELECT * 
        FROM board
        ORDER BY board_no DESC) a
START WITH parent_no = 0
CONNECT BY PRIOR board_no = parent_no
ORDER SIBLINGS BY a.board_no DESC
; -- �������� -> ���������� ����
-- ������ ����

-- ������ �׷���
-- -- 1 �������� 3�Ǿ� �˻�
-- 1 ������ : 1��~3��
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
-- 2 ������
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
;-- �⺻ ����¡ ����


-- 2 ������-- ������ ����¡ ���� Ȱ��
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

-- �۹�ȣ�� ������ ���� : ������ �̸��� board_seq, ���۰��� 8, ����ġ�� 1
CREATE SEQUENCE board_seq
INCREMENT BY 1
START WITH 8
NOCACHE
;
SELECT * FROM board
;
-- �Խ��� �۲���
INSERT INTO board VALUES(board_seq.NEXTVAL, 0,'~','�쿩��',SYSTIMESTAMP, '~');

-- ��� ����
INSERT INTO board VALUES(board_seq.NEXTVAL, ���۹�ȣ,'~','�쿩��',SYSTIMESTAMP, '~');

-- �� ���뺸��
SELECT *
FROM board
WHERE board_no=�۹�ȣ
;

-- �� ���� ����
UPDATE board
SET board_title='~', board_content='~',
    board_dt=SYSTIMESTAMP
WHERE board_no=�۹�ȣ
;

-- �� ���� ����
