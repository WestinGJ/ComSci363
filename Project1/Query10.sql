-- The course number, name and the number of students registered for each course, order by course number (if a 
-- course has no student registered, the count should be 0)
SELECT courses.number, courses.name, (SELECT count(*)
									  FROM register
									  WHERE courses.number = register.course_number) as number_of_students
FROM courses
ORDER BY courses.number;