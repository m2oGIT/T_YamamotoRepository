INSERT INTO
 moeiwast_eiwatest_DB01.test_table
VALUES
 (00005, '�R�{', 123.456, '2087/12/31')
;

UPDATE
 moeiwast_eiwatest_DB01.test_table
SET
 NAME_STR = '�X�V��ł��B'
WHERE
 INT_NUM = 00005
AND
 NAME_STR = '�R�{'
;

SELECT
 *
FROM
 moeiwast_eiwatest_DB01.test_table
ORDER BY
 CREATE_DATE DESC
;
