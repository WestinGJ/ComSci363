-- The name, snum and SSN of the students whose name is between “Amy” and “Christopher”, order by name
SELECT students.name, students.snum, students.SSN
FROM students
WHERE students.name > 'Amy' AND students.name < 'Christopher'
ORDER BY students.name;