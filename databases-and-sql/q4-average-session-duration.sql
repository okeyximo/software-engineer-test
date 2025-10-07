-- Explanation

-- We use:

-- GROUP BY → to group sessions by userId

-- AVG() → to calculate the average duration per user

-- HAVING COUNT(*) > 1 → to only include users with more than one session


SELECT 
    userId,
    AVG(duration) AS AverageDuration
FROM sessions
GROUP BY userId
HAVING COUNT(*) > 1;