-- The name of the students enrolled in Fall2022 semester.
SELECT students.name 
FROM students 
JOIN register 
ON students.snum = register.snum
WHERE register.regtime = 'Fall2022';