CREATE SEQUENCE student_seq;
CREATE TABLE student (
	student_id INT NOT NULL DEFAULT NEXTVAL ('student_seq'),
	name VARCHAR(100),
	surname VARCHAR(100),
	email VARCHAR(100),
	birth_date DATE,
	phone_numbers VARCHAR(100),
	primary_skill VARCHAR(100),
	created_datetime TIMESTAMP(0),
	updated_datetime  TIMESTAMP(0),
	PRIMARY KEY (student_id),
	CHECK name LIKE '%@%',
	CHECK name LIKE '%#%',
	CHECK name LIKE '%$%',
	CHECK surname LIKE '%@%',
	CHECK surname LIKE '%#%',
	CHECK surname LIKE '%$%';
);
CREATE INDEX email ON student USING HASH (email);


CREATE SEQUENCE subject_seq;
CREATE TABLE subject (
	subject_id INT NOT NULL DEFAULT NEXTVAL ('subject_seq'),
	subject_name VARCHAR(100),
	tutor VARCHAR(100),
	CONSTRAINT subject_id UNIQUE  (subject_id),
	PRIMARY KEY (subject_id)
);
CREATE INDEX subject_name ON subject (subject_name);




 CREATE SEQUENCE exam_results_seq;
 CREATE TABLE exam_result (
 	result_id INT NOT NULL DEFAULT NEXTVAL ('exam_result_seq'),
 	student_id INT NOT NULL,
 	subject_id INT NOT NULL,
 	mark INT NOT NULL,
 	PRIMARY KEY(result_id),
         CONSTRAINT fk_student
             FOREIGN KEY(student_id)
                 REFERENCES student(student_id),
 	    CONSTRAINT fk_subject
 	        FOREIGN KEY(subject_id)
 	            REFERENCES subject(subject_id),
    CHECK mark>0,
    CHECK mark<100;
 );


insert into student (student_id,name ,surname,email,birth_date,phone_numbers ,primary_skill,created_datetime ,updated_datetime) values
(1,'Ira','surname1','mail1','11-11-2022','098111111','Java','2016-06-22 19:10:25-07','2016-06-22 19:10:25-07');
insert into student (student_id,name ,surname,email,birth_date,phone_numbers ,primary_skill,created_datetime ,updated_datetime) values
(2,'Stas','surname2','mail2','12-12-2022','093111111','Java','2016-06-22 19:10:25-07','2016-06-22 19:10:25-07');
insert into student (student_id,name ,surname,email,birth_date,phone_numbers ,primary_skill,created_datetime ,updated_datetime) values
(3,'Pavlo','surname3','mail3','12-12-2022','095111111','Java','2016-06-22 19:10:25-07','2016-06-22 19:10:25-07');

insert into subject(subject_id,subject_name,tutor) values (1,'IO-NIO','Taras');
insert into subject(subject_id,subject_name,tutor) values (2,'Core','Taras');
insert into subject(subject_id,subject_name,tutor) values (3,'DB','Taras');
insert into exam_result(result_id,student_id, subject_id,mark) values (1,1,1,4);
insert into exam_result(result_id,student_id, subject_id,mark) values (2,2,1,4);
insert into exam_result(result_id,student_id, subject_id,mark) values (3,3,1,3);
insert into exam_result(result_id,student_id, subject_id,mark) values (4,1,2,4);
insert into exam_result(result_id,student_id, subject_id,mark) values (5,2,2,5);
insert into exam_result(result_id,student_id, subject_id,mark) values (6,3,2,5);
insert into exam_result(result_id,student_id, subject_id,mark) values (7,1,3,1);
insert into exam_result(result_id,student_id, subject_id,mark) values (8,2,3,2);
insert into exam_result(result_id,student_id, subject_id,mark) values (9,3,3,3);

CREATE FUNCTION update_student() RETURNS trigger AS $update_student$
    BEGIN
        NEW.updated_datetime := NOW();
        RETURN NEW;
    END;
$update_student$ LANGUAGE plpgsql;
CREATE TRIGGER update_student BEFORE INSERT OR UPDATE ON student
    FOR EACH ROW EXECUTE FUNCTION update_student();


