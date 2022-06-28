CREATE SEQUENCE application_files_id_seq IF NOT EXISTS;

CREATE TABLE application_files IF NOT EXISTS (
	id INT NOT NULL DEFAULT NEXTVAL ('application_files_id_seq'),
	name VARCHAR(500),
	file VARCHAR(500),
	local_date DATE,
	PRIMARY KEY (id)
);