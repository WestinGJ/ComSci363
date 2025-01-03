-- The numbers and names of all courses offered by the department of Computer Science, order by course number
SELECT courses.number, courses.name
FROM courses
WHERE courses.department_code = (
    SELECT departments.code
    FROM departments
    WHERE departments.name = 'Computer Science'
)
ORDER BY courses.number ASC;