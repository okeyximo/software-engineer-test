-- A LEFT JOIN returns all rows from the left table (games), 
-- and matching rows from the right table (city).
-- If no match exists in the right table,
--  the result for that side is NULL.

 SELECT g.yr,
       g.city,
       c.country
FROM games g
LEFT JOIN city c
  ON g.city = c.name
ORDER BY g.yr;
