---
--- drop tables
---

DROP TABLE IF EXISTS tasks CASCADE;
DROP TABLE IF EXISTS todo_list CASCADE;
DROP TABLE IF EXISTS ta_to_seq CASCADE;

---
--- create tables
---

CREATE TABLE tasks
(
    id                  SERIAL NOT NULL,
    name                VARCHAR,
    time_limit          DATE,
    estimated_duration  TIME
);

CREATE TABLE todo_list
(
    id                  SERIAL NOT NULL,
    name                VARCHAR
);

CREATE TABLE ta_to_seq
(
    id                  SERIAL NOT NULL,
    task_id             SERIAL,
    todo_list_id        SERIAL
);

---
--- add constraints
---

ALTER TABLE ONLY tasks
    ADD CONSTRAINT pk_tasks_id PRIMARY KEY(id);

ALTER TABLE ONLY todo_list
    ADD CONSTRAINT pk_todo_list_id PRIMARY KEY(id);

ALTER TABLE ONLY ta_to_seq
    ADD CONSTRAINT fk_todo_list_id FOREIGN KEY (todo_list_id) REFERENCES todo_list(id) ON DELETE CASCADE,
    ADD CONSTRAINT fk_task_id FOREIGN KEY(task_id) REFERENCES task(id) ON DELETE CASCADE;
