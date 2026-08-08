# Write your MySQL query statement below
WITH first_logins AS (
  SELECT
    player_id,
    MIN(event_date) AS first_login
  FROM
    Activity
  GROUP BY
    player_id
),
consec_logins AS (
  SELECT
    COUNT(A.player_id) AS num_logins
  FROM
    first_logins F
    INNER JOIN Activity A ON F.player_id = A.player_id
    AND F.first_login = DATE_SUB(A.event_date, INTERVAL 1 DAY)
)
SELECT
  ROUND(
    (SELECT num_logins FROM consec_logins)
    / (SELECT COUNT(player_id) FROM first_logins)
  , 2) AS fraction;