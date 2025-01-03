-- The major name and major level of the students whose name is "Gail"
SELECT major.name, major.level
FROM major
WHERE major.snum = (
	SELECT students.snum
    FROM students
    WHERE students.name = 'Gail'
);