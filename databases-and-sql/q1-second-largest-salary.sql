Option 1
SELECT DISTINCT(salary) 
FROM emp 
ORDER BY salary DESC 
LIMIT 1 OFFSET 1;

✔ Works perfectly — it sorts unique salaries in descending order, skips the first (highest), and returns the next (second highest).
Correct

Option 2
SELECT MAX(salary) 
FROM emp 
WHERE salary < (SELECT MAX(salary) FROM emp);

✔ Finds the maximum salary that’s less than the overall maximum — exactly the second highest.
Correct

✅ Option 3
SELECT salary 
FROM (
  SELECT DISTINCT salary 
  FROM emp 
  ORDER BY salary DESC 
  LIMIT 2
) AS emp 
ORDER BY salary 
LIMIT 1;

✔ Selects top 2 distinct salaries, then orders ascending to get the lower one (second highest).
Correct

Option 4 and 5 Incorrect