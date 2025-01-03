-- The snum and names of all students who have a minor, order by student snum
SELECT students.snum, students.name
FROM students, minor
WHERE students.snum = minor.snum
ORDER BY students.snum ASC;