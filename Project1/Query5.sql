-- All degree names and levels offered by the department Computer Science, order by degree level
SELECT degrees.name, degrees.level
FROM degrees
WHERE degrees.department_code = (
	SELECT departments.code
    FROM departments
    WHERE departments.name = 'Computer Science'
)
ORDER BY degrees.level ASC;