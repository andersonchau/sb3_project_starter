drop database IF EXISTS todo_db;
create DATABASE todo_db;

# create user here...
CREATE USER 'app_user'@'%' IDENTIFIED BY 'P@ssw0rd';

GRANT ALL PRIVILEGES ON todo_db.* TO 'app_user2'@'%' identified by 'P@ssw0rd' WITH GRANT OPTION;

use todo_db;

CREATE TABLE t_todo_job_category (

  cat_id BIGINT NOT NULL AUTO_INCREMENT,
  parent_id BIGINT NULL,
  name VARCHAR(200) DEFAULT NULL,
  CONSTRAINT fk_todo_job_category_parent FOREIGN KEY (parent_id) REFERENCES t_todo_job_category(cat_id),
  primary key(cat_id)
);


CREATE TABLE t_todo_job (
  job_id BIGINT NOT NULL AUTO_INCREMENT,
  parent_job_id BIGINT NULL,
  cat_id BIGINT NOT NULL,
  job_summary VARCHAR(100) NOT NULL,
  job_details VARCHAR(1000) NOT NULL,
  deadline DATE NULL,
  status INT NOT NULL,
  importance INT NOT NULL DEFAULT 0,
  primary key(job_id),
  CONSTRAINT fk_job_cat FOREIGN KEY (cat_id) REFERENCES t_todo_job_category(cat_id)
);

CREATE TABLE t_todo_job_dependency_map (
  parent_job_id BIGINT NOT NULL,
  job_id BIGINT NOT NULL
);

CREATE TABLE t_todo_job_tmpl (
  template_id BIGINT NOT NULL AUTO_INCREMENT,
  cat_id BIGINT NOT NULL,
  job_summary VARCHAR(100) NOT NULL,
  job_details VARCHAR(1000) NOT NULL,
  primary key(template_id),
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
  primary key(sched_id)
);

INSERT INTO t_todo_job_category (name,parent_id) VALUES ('Work',NULL);
INSERT INTO t_todo_job_category (name,parent_id) VALUES ('Personal',null);

INSERT INTO t_todo_job_category (name,parent_id)
SELECT 'Project One' , cat_id FROM t_todo_job_category WHERE `name` = 'Work';

INSERT INTO t_todo_job_category (name,parent_id)
SELECT 'Health' , cat_id FROM t_todo_job_category WHERE `name` = 'Personal';

INSERT INTO t_todo_job_category (name,parent_id)
SELECT 'Finance' , cat_id FROM t_todo_job_category WHERE `name` = 'Personal';

