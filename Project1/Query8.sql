-- The name, snum and SSN of the students whose name contains letter “n” or “N”, order by snum
SELECT students.name, students.snum, students.SSN
FROM students
WHERE (students.name LIKE '%n%' OR students.name LIKE '%N%')
ORDER BY snum ASC;