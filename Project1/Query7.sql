-- The names and snums of all non-undergraduate students enrolled in course “database”, order by snum. (“non-undergraduate students” 
-- means the major degrees of these students are MS or PhD levels)
SELECT students.name, students.snum 
FROM students
JOIN major
ON students.snum = major.snum 
JOIN courses 
ON courses.number IN (SELECT register.course_number 
					  FROM register 
                      WHERE register.snum = students.snum)
WHERE courses.name = 'database' AND (major.level = 'MS' OR major.level = 'PhD')
ORDER BY students.snum;