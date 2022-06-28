--a. Find user by name (exact match)
select * from students where name = 'Ira';
--b. Find user by surname (partial match)
select * from students where surname like 'sur%';
--c. Find user by phone number (partial match)
select * from students where phone_numbers like '093%';
--d. Find user with marks by user surname (partial match)
select * from student join exam_result on student.student_id=exam_result.student_id
    where student.surname like '%name1';

-- 5. Add trigger that will update column updated_datetime to current date in case
--of updating any of student. (0.3 point)
UPDATE student SET phone_numbers = '063' WHERE student_id = 3;

-- 6. Add validation on DB level that will check username on special characters
--(reject student name with next characters '@', '#', '$'). (0.3 point)
CREATE OR REPLACE FUNCTION "patindex"( "pattern" VARCHAR, "expression" VARCHAR ) RETURNS INT AS $BODY$
SELECT
    COALESCE(
        STRPOS(
             $2
            ,(
                SELECT
                    ( REGEXP_MATCHES(
                        $2
                        ,'(' || REPLACE( REPLACE( TRIM( $1, '%' ), '%', '.*?' ), '_', '.' ) || ')'
                        ,'i'
                    ) )[ 1 ]
                LIMIT 1
            )
        )
        ,0
    )
;
$BODY$ LANGUAGE 'sql' IMMUTABLE;
ALTER table student
Add constraint validate_special_character
check(
	CASE WHEN PATINDEX('%@%',name) > 0 THEN false ELSE true END
	or
	CASE WHEN PATINDEX('%#%',name) > 0 THEN false ELSE true END
	or
	CASE WHEN PATINDEX('%$%',name) > 0 THEN false ELSE true END
	or
	CASE WHEN PATINDEX('%@%',surname) > 0 THEN false ELSE true END
	or
	CASE WHEN PATINDEX('%#%',surname) > 0 THEN false ELSE true END
	or
	CASE WHEN PATINDEX('%$%',surname) > 0 THEN false ELSE true END
)

-- 7. Create snapshot that will contain next data: student name, student surname, subject name, mark
--(snapshot means that in case of changing some data in source table – your snapshot should not change). (0.3 point)
CREATE SNAPSHOT second_semester_final
as select student.name, student.surname,subject.subject_name, exam_result.mark  from student
join exam_result on student.student_id=exam_result.student_id
join subject on exam_result.subject_id=subject.subject_id;

-- 8. Create function that will return average mark for input user. (0.3 point)
CREATE FUNCTION get_average_mark_by_student(surname varchar)
    RETURNS int
    LANGUAGE plpgsql

AS $BODY$
declare
   avg_mark int;
begin
   select AVG(mark) into avg_mark from student join exam_result on student.student_id=exam_result.student_id
        where student.surname like surname;
        return avg_mark;
end;
$BODY$;


--      9. Create function that will return avarage mark for input subject name. (0.3 point).
CREATE FUNCTION get_average_mark_by_subject(subject varchar)
    RETURNS int
    LANGUAGE plpgsql

AS $BODY$
declare
   avg_mark int;
begin
   select AVG(mark) from subject join exam_result on subject.subject_id=exam_result.subject_id
        where subject.subject_name like subject;
        return avg_mark;
end;
$BODY$;



-- 10. Create function that will return student at "red zone" (red zone means at least 2 marks <=3). (0.3 point)
CREATE FUNCTION red_zone()
    RETURNS int
    LANGUAGE plpgsql

AS $BODY$
declare
   student_id int;
begin
   select count(mark) from subject join exam_result on subject.subject_id=exam_result.subject_id
        where subject.subject_name like subject;
        return avg_mark;
end;
$BODY$;









