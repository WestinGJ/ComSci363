-- The count of female students who major or minor in Software Engineering degrees at any level
SELECT COUNT(DISTINCT students.snum) as Female_Students
FROM students
LEFT JOIN major
ON students.snum = major.snum AND (major.name = 'Software Engineering')
LEFT JOIN minor
ON students.snum = minor.snum AND (minor.name = 'Software Engineering')
WHERE students.gender = 'F' AND (major.snum IS NOT NULL OR minor.snum);