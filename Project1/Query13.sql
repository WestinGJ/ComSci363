-- The degree name, degree level, and number of students of the most popular degrees (I.e., the degree program with the 
-- highest number of students taking it as major or minor), order by degree name if there is a tie
SELECT degrees.name, degrees.level, (SELECT COUNT(*)
								   FROM major
                                   WHERE degrees.name = major.name AND degrees.level = major.level) as student_count
FROM degrees
ORDER BY student_count DESC
LIMIT 1;