INSERT INTO
 moeiwast_eiwatest_DB01.test_table
VALUES
 (00005, '山本', 123.456, '2087/12/31')
;

UPDATE
 moeiwast_eiwatest_DB01.test_table
SET
 NAME_STR = '更新後です。'
WHERE
 INT_NUM = 00005
AND
 NAME_STR = '山本'
;

SELECT
 *
FROM
 moeiwast_eiwatest_DB01.test_table
ORDER BY
 CREATE_DATE DESC
;
