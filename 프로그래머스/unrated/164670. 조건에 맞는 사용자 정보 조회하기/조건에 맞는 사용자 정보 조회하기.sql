SELECT 
    USER_BOARD.USER_ID,
    USER_BOARD.NICKNAME,
    USER_BOARD.전체주소,
    USER_BOARD.전화번호
FROM (
    SELECT
        usr.USER_ID as USER_ID,
        usr.NICKNAME as NICKNAME,
        CONCAT(
            usr.CITY, ' ', usr.STREET_ADDRESS1, ' ', usr.STREET_ADDRESS2
        ) as 전체주소,
        CONCAT(
            SUBSTR(usr.TLNO, 1, 3), '-'
            , SUBSTR(usr.TLNO, 4, 4), '-'
            , SUBSTR(usr.TLNO, 8)
        ) as 전화번호,
        count(*) as CNT
    FROM USED_GOODS_BOARD board
    INNER JOIN USED_GOODS_USER usr
    ON board.WRITER_ID = usr.USER_ID
    GROUP BY NICKNAME
) USER_BOARD
WHERE USER_BOARD.CNT >= 3
ORDER BY USER_BOARD.USER_ID DESC;