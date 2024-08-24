CREATE TABLE t_todo_job_category (
	cat_id BIGINT NOT NULL AUTO_INCREMENT ,
	name VARCHAR(200) DEFAULT NULL,
	primary_key(cat_id)
);

CREATE TABLE t_todo_job (
	job_id BIGINT NOT NULL AUTO_INCREMENT ,
	parent_job_id BIGINT NULL,
	cat_id BIGINT NOT NULL,
	job_desc VARCHAR(100) NOT NULL, 
	job_details VARCHAR(1000) NOT NULL,
	deadline DATE NULL,
	status INT NOT NULL,
    importance INT NOT NULL DEFAULT 0,
	primary_key(job_id),
	CONSTRAINT fk_job_cat FOREIGN KEY (cat_id) REFERENCES t_todo_job_category(cat_id),
);

CREATE TABLE t_todo_job_dependency_map (
    parent_job_id BIGINT NOT NULL,
    job_id BIGINT NOT NULL
);

CREATE TABLE t_todo_job_tmpl (
    template_id BIGINT NOT NULL AUTO_INCREMENT ,
    cat_id BIGINT NOT NULL,
    job_desc VARCHAR(100) NOT NULL,
    job_details VARCHAR(1000) NOT NULL,
    primary_key(template_id),
    CONSTRAINT fk_job_tmpl_cat FOREIGN KEY (cat_id) REFERENCES t_todo_job_category(cat_id)
);

CREATE TABLE t_todo_sched_rule (
    sched_id BIGINT NOT NULL AUTO_INCREMENT,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    which_hour VARCHAR(100) NULL,
    day_of_month VARCHAR(100) NULL,
    which_month VARCHAR(100) NULL,
    day_of_week VARCHAR(100) NULL,
    primary_key(sched_id)
);


